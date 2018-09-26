package com.xiangzhurui.demo.drools.engine.facts;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuditFact implements Serializable {

    private String sequenceId;
    private Date executeDate;
}
