package com.ruoyi.activiti;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivitTest.class)
public class ActivitTest {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ProcessEngineConfigurationImpl processEngineConfiguration;
    /**	 * 布置流程 (操作数据表 act_re_deployment,act_re_procdef,act_ge_bytearray	 * 	 */
    @Test
    public void createDeployment() {
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("processes/myactiviti.xml");
        builder.addClasspathResource("processes/myactiviti.png");
        Deployment deploy = builder.deploy();
        System.out.println("布署id=" + deploy.getId());
    }

    @Test
    public void createDeploymentByZip() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("processes/MyProcess.zip");
    }


}
