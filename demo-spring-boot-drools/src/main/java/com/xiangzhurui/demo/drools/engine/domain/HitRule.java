package com.xiangzhurui.demo.drools.engine.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.xiangzhurui.demo.drools.engine.enums.DecisionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 命中规则
 *
 * @author xiangzhurui
 * @version 2018/4/26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HitRule implements Serializable {
    private static final long serialVersionUID = -1931134929830439027L;

    /**
     * 规则ID
     */
    private String id;
    /**
     * 规则名
     */
    private String name;
    /**
     * 决策结果：最坏匹配是具有该值
     */
    private DecisionEnum decision;
    /**
     * 分数：权重模式时具有该值
     */
    private int score;
}
