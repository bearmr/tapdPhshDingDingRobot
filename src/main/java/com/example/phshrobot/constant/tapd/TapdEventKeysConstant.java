package com.example.phshrobot.constant.tapd;

public class TapdEventKeysConstant {

    public static final String EVENTS_TYPE_TASK = "task";
    public static final String EVENTS_TYPE_BUG = "bug";
    public static final String EVENTS_TYPE_STORY = "story";

    public static final String EVENTS_TYPE_SCHEDULE = "schedule";

    /**
     * bug创建
     */
    public static final String EVENTS_TAG_BUG_CREATE = "bug::create";
    /**
     * bug状态流转
     */
    public static final String EVENTS_TAG_BUG_STATUS_CHANGE = "bug::status_change";
    public static final String EVENTS_TAG_BUG_UPDATE = "bug::update";
    /**
     * 需求创建
     */
    public static final String EVENTS_TAG_STORY_CREATE = "story::create";

    /**
     * 需求变更
     */
    public static final String EVENTS_TAG_STORY_CHANGE = "story::status_change";
    public static final String EVENTS_TAG_STORY_UPDATE = "story::update";

    /**
     * 任务创建
     */
    public static final String EVENTS_TAG_TASK_CREATE = "task::create";

    /**
     * 任务创建
     */
    public static final String EVENTS_TAG_TASK_STATUS_CHANGE = "task::status_change";


    public static final String EVENTS_TAG_TASK_UPDATE = "task::update";


    /**
     * 时间计划
     */
    public static final String EVENTS_TAG_SCHEDULE_TIMER = "schedule::timer";
}
