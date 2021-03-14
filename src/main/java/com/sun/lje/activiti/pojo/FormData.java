package com.sun.lje.activiti.pojo;

import lombok.Data;

@Data
public class FormData {
    private String proc_def_id_;// 流程定义id
    private String parent_id_;// 流程实例id
    private String form_key_;// 表单taskkey
    private String control_id_;// 控件id
    private String control_value_; // 控件存放值
}
