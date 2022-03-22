package com.example.phshrobot.constant.tapd;

public class StoryCommonEnums {
    /**
     * 需求优先级(priority)取值字段说明
     */
    public enum StoryPriorityEnums {
        NICE_TO_HAVE("1", "Nice To Have"),
        Low("2", "Low"),
        Middle("3", "Middle"),
        High("4", "High");


        private String code;
        private String msg;

        StoryPriorityEnums(String code, String msg) {
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
            for (StoryPriorityEnums s : StoryPriorityEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }

    /**
     * 需求工作流状态中英文名对应关系
     */
    public enum StoryFlowEnums {
        PLANNING("planning", "规划中"),
        DEVELOPING("developing", "实现中"),
        RESOLVED("resolved", "已实现"),
        REJECTED("rejected", "已拒绝");


        private String code;
        private String msg;

        StoryFlowEnums(String code, String msg) {
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
            for (StoryFlowEnums s : StoryFlowEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }
}
