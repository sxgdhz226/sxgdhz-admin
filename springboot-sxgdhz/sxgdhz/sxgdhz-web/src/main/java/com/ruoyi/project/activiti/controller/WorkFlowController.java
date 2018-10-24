package com.ruoyi.project.activiti.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.activiti.entity.LeaveBill;
import com.ruoyi.project.activiti.entity.WorkflowBean;
import com.ruoyi.project.activiti.service.ILeaveBillService;
import com.ruoyi.project.activiti.service.IWorkflowService;
import com.ruoyi.project.activiti.vo.TaskVo;
import com.ruoyi.project.system.menu.domain.Menu;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * 流程管理控制器
 */
@Controller
@RequestMapping("/activiti")
public class WorkFlowController extends BaseController {

    @Autowired
    private IWorkflowService workflowService;

    @Autowired
    private ILeaveBillService leaveBillService;

    @GetMapping()
    public String deployHome(){
        return "workflow/workflow";
    }

    @RequiresPermissions("activiti:processDefine:view")
    @GetMapping("processDefine")
    public String processDefine(){
        return "workflow/workflow";
    }

    @GetMapping("historyProcess")
    public String historyProcess(){
        return "workflow/workflow";
    }


    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AjaxResult uploadProcessDefineFile(@RequestParam("processFile")  MultipartFile file){
        try {
            workflowService.saveNewDeploye(file,file.getOriginalFilename());
            return AjaxResult.success();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return AjaxResult.error();
    }

    @PostMapping("/list")
    @ResponseBody
    public Object findDeploymentList()
    {
        List<Map<String,Object>> result = new ArrayList<>();
        //1:查询部署对象信息，对应表（act_re_deployment）
        List<Deployment> depList = workflowService.findDeploymentList();
        //2:查询流程定义的信息，对应表（act_re_procdef）
        List<ProcessDefinition> pdList = workflowService.findProcessDefinitionList();

        pdList.forEach(e -> {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name",e.getName());
            map.put("key",e.getKey());
            map.put("version",e.getVersion());
            map.put("resourceName",e.getResourceName());
            map.put("deploymentId",e.getDeploymentId());
            map.put("diagramResourceName",e.getDiagramResourceName());
            result.add(map);
        });

        return getDataTable(result);
    }

    /**
     * 流程申请
     */
    @GetMapping(value = "processApply")
    public Object processApply(){
        return "workflow/input";
    }

    /**
     * 流程保存
     */

    @PostMapping(value = "processSave")
    @Log(title = "请假申请",businessType = BusinessType.INSERT)
    @ResponseBody
    public AjaxResult processSave(LeaveBill leaveBill){
        return toAjax(leaveBillService.saveLeaveBill(leaveBill));
    }

    @PostMapping("/processList")
    @ResponseBody
    public TableDataInfo processList(){

        List<LeaveBill> list = leaveBillService.findLeaveBillList();

        return getDataTable(list);
    }

    @GetMapping("/process")
    public Object process(){
        return "workflow/list";
    }


    /**
     * 请假删除
     */
    @Log(title = "请假删除",businessType = BusinessType.DELETE)
    @PostMapping(value = "leaveRomove")
    @ResponseBody
    public AjaxResult leaveRemove(String ids){
        return toAjax(leaveBillService.deleteLeaveBillById(ids));
    }

    /**
     * 申请请假
     */
    @Log(title = "申请请假",businessType = BusinessType.INSERT)
    @PostMapping(value = "startProcess")
    @ResponseBody
    public AjaxResult startProcess(WorkflowBean workflowBean){
        workflowService.saveProcess(workflowBean);
        return toAjax(1);
    }


    /**
     * 个人任务查询
     */
    @GetMapping(value = "apply")
    public Object apply(){
        return "workflow/task";
    }

    @Log(title = "个人任务查询",businessType = BusinessType.OTHER)
    @PostMapping(value = "listTask")
    @ResponseBody
    public TableDataInfo listTask(){
        //通过当前登录人查询其个人任务
        List<Task> taskList = workflowService.findTaskListByName(ShiroUtils.getLoginName());
        List<TaskVo> taskVoList = new ArrayList<>();
        taskList.forEach(e -> {
            TaskVo taskVo = new TaskVo();
            taskVo.setId(e.getId());
            taskVo.setAssignee(e.getAssignee());
            taskVo.setCreateTime(e.getCreateTime());
            taskVo.setName(e.getName());
            taskVoList.add(taskVo);
        });
        return getDataTable(taskVoList);
    }

    /**
     * 办理任务
     */
    @GetMapping(value = "viewTaskForm")
    public Object viewTaskForm(WorkflowBean workflowBean){
       String url =  workflowService.findTaskFormKeyByTaskId(workflowBean.getTaskId());
       return "redirect:/"+url+"?taskId="+workflowBean.getTaskId();
    }

    /**
     * 准备表单数据
     */
    @GetMapping(value = "add")
    public Object add(ModelMap modelMap,WorkflowBean workflowBean){
        LeaveBill leaveBill = workflowService.findLeaveBillByTaskId(workflowBean.getTaskId());
        //根据任务id查询流程定对象，从而获取连续的名称
        List<String> outcomeList =  workflowService.findOutComeListByTaskId(workflowBean.getTaskId());

        modelMap.put("outcomeList",outcomeList);
        modelMap.put("leaveBill",leaveBill);
        modelMap.put("taskId",workflowBean.getTaskId());
        return "workflow/taskForm";
    }

    /**
     * 完成个人任务
     */
    @PostMapping(value = "submitTask")
    public Object submitTask(WorkflowBean workflowBean){
        workflowService.saveSubmitTask(workflowBean);
        return "workflow/task";
    }

    /**
     * 签发任务
     */
    @PostMapping(value = "signWork")
    public Object signWork(WorkflowBean workflowBean){
        workflowService.saveSubmitTask(workflowBean);
        return "workflow/task";
    }

    /**
     * 发布任务
     * @param workflowBean
     * @return
     */
    @PostMapping(value = "publishWork")
    public Object publishWork(WorkflowBean workflowBean){
        workflowService.saveSubmitTask(workflowBean);
        return "workflow/task";
    }
}
