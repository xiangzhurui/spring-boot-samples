package com.xiangzhurui.demo.drools.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiangzhurui
 * @version 2018/9/5 16:59
 */
@Slf4j
public enum FinalDecisionEum {
    REJECT("Reject", "拒绝"),

    REVIEW("Review", "复核"),

    ACCEPT("Accept", "接受");

    @Getter
    private String code;
    @Getter
    private String desc;

    FinalDecisionEum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FinalDecisionEum parseCode(String code) {
        for (FinalDecisionEum decisionEum : FinalDecisionEum.values()) {
            if (decisionEum.code.equals(code)) {
                return decisionEum;
            }
        }
        log.warn("not supported code {} .", code);
        return null;
    }
}
