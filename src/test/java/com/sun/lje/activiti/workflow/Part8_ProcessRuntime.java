package com.sun.lje.activiti.workflow;

import com.sun.lje.activiti.SecurityUtil;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Part8_ProcessRuntime {

    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private SecurityUtil securityUtil;

    // 获取流程实例
    @Test
    public void getProcessInstance() {
        securityUtil.logInAs("john");
        Page<ProcessInstance> processInstancePage = processRuntime
                .processInstances(Pageable.of(0,100));
        System.out.println("流程实例数"+ processInstancePage.getTotalItems());
        List<ProcessInstance> content =
                processInstancePage.getContent();
        for (ProcessInstance pi : content) {
            System.out.println("" + pi.getId());
            System.out.println("" + pi.getName());
            System.out.println("" + pi.getStartDate());
            System.out.println("" + pi.getStatus());
            System.out.println("" + pi.getProcessDefinitionId());
            System.out.println("" + pi.getProcessDefinitionKey());
        }

    }

    // 启动流程实例
    @Test
    public void startProcessInstance() {
        securityUtil.logInAs("bob");
        ProcessInstance start = processRuntime.start(ProcessPayloadBuilder.start()
                .withProcessDefinitionKey("Process_1")
                .withName("第一个流程实例名称")
                .withBusinessKey("自定义key").build());

    }

    // 删除流程实例
    @Test
    public void delProcessInstance() {
        securityUtil.logInAs("john");
        ProcessInstance start = processRuntime.delete(ProcessPayloadBuilder
                .delete()
                .withProcessInstanceId("0bbe0e3e-83eb-11eb-a52d-c809a8e4389d")
                .build());

    }

    //挂起
    @Test
    public void suspendProcessInstance() {
        securityUtil.logInAs("john");
        ProcessInstance processInstance = processRuntime.suspend(ProcessPayloadBuilder
                .suspend()
                .withProcessInstanceId("0bbe0e3e-83eb-11eb-a52d-c809a8e4389d")
                .build());
    }

    // 启动
    @Test
    public void resumeProcessInstance() {
        securityUtil.logInAs("john");
        ProcessInstance processInstance = processRuntime.resume(ProcessPayloadBuilder
                .resume()
                .withProcessInstanceId("0bbe0e3e-83eb-11eb-a52d-c809a8e4389d")
                .build());

    }

    // 流程实例参数
    @Test
    public void getVariables() {


    }

}
