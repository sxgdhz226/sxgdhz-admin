package com.ruoyi.project.activiti.service.impl;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.activiti.config.mapper.LeaveMapper;
import com.ruoyi.project.activiti.entity.LeaveBill;
import com.ruoyi.project.activiti.entity.WorkflowBean;
import com.ruoyi.project.activiti.service.IWorkflowService;
import com.ruoyi.project.activiti.vo.CustomizeProcessVo;
import org.activiti.bpmn.model.*;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

@Service("WorkflowServiceImpl")
public class WorkflowServiceImpl implements IWorkflowService {

    @Autowired
    private LeaveMapper leaveMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Override
    public void saveNewDeploye(MultipartFile file, String filename) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
            repositoryService.createDeployment().name(filename)
                    .addZipInputStream(zipInputStream).deploy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Deployment> findDeploymentList() {
        return repositoryService.createDeploymentQuery().orderByDeploymenTime().asc().list();
    }

    @Override
    public List<ProcessDefinition> findProcessDefinitionList() {

        return repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
    }

    @Override
    public InputStream findImageInputStream(String deploymentId, String imageName) {
        return null;
    }

    @Override
    public void deleteProcessDefinitionByDeploymentId(String deploymentId) {

    }

    @Override
    public void saveStartProcess(WorkflowBean workflowBean) {

    }

    @Override
    public void saveProcess(WorkflowBean workflowBean) {
        LeaveBill leaveBill = leaveMapper.findLeaveBillById(workflowBean.getId());
        Map<String,Object> variables = new HashMap<>();
        variables.put("username", ShiroUtils.getLoginName());

        // 第一个参数是processDefinitionKey, 第二个参数是businssKey
        runtimeService.startProcessInstanceByKey("myProcess_1","myProcess_1."+workflowBean.getId(),variables);
    }

    @Override
    public List<Task> findTaskListByName(String name) {
        return taskService.createTaskQuery().taskAssignee(name).orderByTaskCreateTime().asc().list();
    }

    @Override
    public String findTaskFormKeyByTaskId(String taskId) {
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        return taskFormData.getFormKey();
    }

    @Override
    public LeaveBill findLeaveBillByTaskId(String taskId) {
        //根据任务id拿到业务id
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();

        String businessKey = processInstance.getBusinessKey();
        String id = "";
        if (StringUtils.isNoneBlank(businessKey)){
            id = businessKey.split("\\.")[1];
        }

        //拿到业务id查询业务表
        LeaveBill leaveBill = leaveMapper.findLeaveBillById(Long.parseLong(id));

        return leaveBill;
    }

    @Override
    public List<String> findOutComeListByTaskId(String taskId) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(task.getProcessDefinitionId());

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        //根据参数名称查询流程实例id
        //CustomizeProcessVo customizeProcessVo = (CustomizeProcessVo)runtimeService.getVariable(task.getExecutionId(),"username");

        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        String activityId = execution.getActivityId();

        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());

        FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(activityId);

        List<String> result = new ArrayList<>();
        //获取全部的Flow
        List<SequenceFlow> incomingFlows = flowNode.getIncomingFlows();
                //获取入线信息
        incomingFlows.forEach(a -> {
            if (StringUtils.isNotBlank(a.getName())){
                result.add(a.getName());
            } else {
                result.add("默认提交");
            }
        });

        return result;
    }

    @Override
    public void saveSubmitTask(WorkflowBean workflowBean) {
        String taskId = workflowBean.getTaskId();
        //获取批注信息
        String comment = workflowBean.getComment();
        //获得连线名称
        String outcome = workflowBean.getOutcome();

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        //String taskId, String processInstance, String message
        taskService.addComment(taskId,task.getProcessInstanceId(),comment);

        Map<String, Object> variables = new HashMap<String,Object>();
        if(outcome!=null && !outcome.equals("默认提交")){
            variables.put("outcome", outcome);
        }
        Authentication.setAuthenticatedUserId(ShiroUtils.getLoginName());
        //完成个人任务，同时设置流程变量
        taskService.complete(taskId,variables);

        //在完成任务之后，判断流程是否结束
        //如果流程结束了，更新请假单表的状态从1变成2（审核中-->审核完成）
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        //流程结束了
        if(processInstance == null){
            //更新请假单表的状态从1变成2（审核中-->审核完成）
            LeaveBill bill = leaveMapper.findLeaveBillById(workflowBean.getId());
            bill.setState(2);
        }

    }

    @Override
    public List<Comment> findCommentByTaskId(String taskId) {
        return null;
    }

    @Override
    public List<Comment> findCommentByLeaveBillId(Long id) {
        return null;
    }

    @Override
    public ProcessDefinition findProcessDefinitionByTaskId(String taskId) {
        return null;
    }

    @Override
    public Map<String, Object> findCoordingByTask(String taskId) {
        return null;
    }
}
