package com.example.phshrobot.constant.tapd;

public class TaskCommonEnums {


    /**
     * 任务状态(status)取值字段说明
     */
    public enum TaskStatusFlowEnums {
        OPEN("open", "未开始"),
        DEVELOPING("progressing", "进行中"),
        RESOLVED("done", "已完成");


        private String code;
        private String msg;

        TaskStatusFlowEnums(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }


        public static String getKeyByValue(String key) {
            for (TaskStatusFlowEnums s : TaskStatusFlowEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }

    /**
     * 需求优先级(priority)取值字段说明
     */
    public enum TaskPriorityEnums {
        NICE_TO_HAVE("1", "Nice To Have"),
        Low("2", "Low"),
        Middle("3", "Middle"),
        High("4", "High");


        private String code;
        private String msg;

        TaskPriorityEnums(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }


        public static String getKeyByValue(String key) {
            for (TaskPriorityEnums s : TaskPriorityEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }
}
