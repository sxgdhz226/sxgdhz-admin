package com.ruoyi.project.activiti.service;

import com.ruoyi.project.activiti.entity.LeaveBill;
import com.ruoyi.project.activiti.entity.WorkflowBean;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IWorkflowService {
    void saveNewDeploye(MultipartFile file, String filename);

    List<Deployment> findDeploymentList();

    List<ProcessDefinition> findProcessDefinitionList();

    InputStream findImageInputStream(String deploymentId, String imageName);

    void deleteProcessDefinitionByDeploymentId(String deploymentId);

    void saveStartProcess(WorkflowBean workflowBean);

    void saveProcess(WorkflowBean workflowBean);

    List<Task> findTaskListByName(String name);

    String findTaskFormKeyByTaskId(String taskId);

    LeaveBill findLeaveBillByTaskId(String taskId);

    List<String> findOutComeListByTaskId(String taskId);

    void saveSubmitTask(WorkflowBean workflowBean);

    List<Comment> findCommentByTaskId(String taskId);

    List<Comment> findCommentByLeaveBillId(Long id);

    ProcessDefinition findProcessDefinitionByTaskId(String taskId);

    Map<String, Object> findCoordingByTask(String taskId);

}
