package com.sun.lje.activiti.workflow;

import com.sun.lje.activiti.SecurityUtil;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Part9_TaskRuntime {
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private TaskRuntime taskRuntime;

    /**
     * 获取当前登录用户任务
     */
    @Test
    public void getTask(){
        securityUtil.logInAs("bob");
        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 100));
        List<Task> list = tasks.getContent();
        for (Task task : list) {
            if(task.getAssignee() == null){
                // 待拾取任务

            }else{
                task.getAssignee();
            }
        }

    }

    public void completeTask(){
        securityUtil.logInAs("bob");
        Task task = taskRuntime.task("");
        if(task.getAssignee() == null){
            Task claim = taskRuntime.claim(TaskPayloadBuilder.claim()
                    .withTaskId(task.getId())
                    .build());
        }

        taskRuntime.complete(TaskPayloadBuilder
        .complete()
        .withTaskId(task.getId())
        .build());
        //任务执行完成
    }
}
