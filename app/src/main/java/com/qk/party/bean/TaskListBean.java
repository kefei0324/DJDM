package com.qk.party.bean;

/**
 * 作者：Think
 * 创建于 2017/10/27 11:41
 */

public class TaskListBean {

    /**
     * leader : 安悦测试1020
     * arder_created_at : 1508465135000
     * num : 安悦测试1020
     * statusString : 审核通过
     * name : 安悦测试1020
     * progress : 100.00
     * task_id : 474
     * dept : 黄龙县委（县委常委班子）
     * source : 安悦测试1020
     * create_at : 2017-10-20 10:05:35
     * status : 3
     */

    private String name;
    private String progress;
    private int task_id;
    private String source;
    private String create_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
}
