package com.example.phshrobot.constant.tapd;

public class BugCommonEnums {

    /**
     * 缺陷优先级(priority)字段说明
     */
    public enum BugPriorityEnums {
        URGENT("urgent", "紧急"),
        HIGH("high", "高"),
        MEDIUM("medium", "中"),
        LOW("low", "低"),
        INSIGNIFICANT("insignificant", "无关紧要"),
        ;


        private String code;
        private String msg;

        BugPriorityEnums(String code, String msg) {
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
            for (BugPriorityEnums s : BugPriorityEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }

    /**
     * 缺陷严重程度(severity)字段说明
     */
    public enum BugSeverityEnums {
        FATAL("fatal", "致命"),
        SERIOUS("serious", "严重"),
        NORMAL("normal", "一般"),
        PROMPT("prompt", "提示"),
        INSIGNIFICANT("advice", "建议"),
        ;


        private String code;
        private String msg;

        BugSeverityEnums(String code, String msg) {
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
            for (BugSeverityEnums s : BugSeverityEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }
    /**
     * 缺陷解决方法(resolution)字段说明
     */
    public enum BugResolutionEnums{
        IGNORE("ignore", "无需解决"),
        FIX_LATER("fix later", "延期处理"),
        FAILED("failed", "无法重现"),
        EXTERNAL("external", "外部原因"),
        DUPLICATED("duplicated", "重复"),
        INTENTIONAL("intentional", "设计如此"),
        UNCLEAR("unclear", "问题描述不准确"),
        HOLD("hold", "挂起"),
        FEATURE("hold", "需求变更"),
        FIXED("hold", "已解决"),
        ;


        private String code;
        private String msg;

        BugResolutionEnums(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }




        public static String getKeyByValue(Integer key) {
            for (BugResolutionEnums s : BugResolutionEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }

    /**
     * 缺陷优先级(flow)字段说明
     */
    public enum BugBugFlowEnums {
        NEW("new", "新增"),
        IN_PROGRESS("in_progress", "接受,处理"),
        RESOLVED("resolved", "已解决"),
        VERIFIED("verified", "已验证"),
        REOPENED("reopened", "重新打开"),
        REJECTED("rejected","已拒绝"),
        CLOSED("closed","已关闭")



        ;


        private String code;
        private String msg;

        BugBugFlowEnums(String code, String msg) {
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
            for (BugBugFlowEnums s : BugBugFlowEnums.values()) {
                if (s.getCode().equals(key)) {
                    return s.getMsg();
                }
            }
            return "未知";
        }
    }
}
