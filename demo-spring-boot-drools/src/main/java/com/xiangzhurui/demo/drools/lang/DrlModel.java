package com.xiangzhurui.demo.drools.lang;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.kie.soup.project.datamodel.imports.Imports;

/**
 * @author xiangzhurui
 * @version 2018/3/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DrlModel implements Serializable {

    private static final long serialVersionUID = 5547386570595475774L;

    private String packageName;
    private Imports imports;
    private List<String> globals;
    private List<String> functions;
    private List<String> rules;

}
