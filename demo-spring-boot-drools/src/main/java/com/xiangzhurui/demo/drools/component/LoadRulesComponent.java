package com.xiangzhurui.demo.drools.component;

import java.util.Set;

import com.google.common.collect.Sets;
import com.xiangzhurui.demo.drools.domain.DrlDataDO;
import org.springframework.stereotype.Component;

/**
 * @author xiangzhurui
 * @version 2018/8/15 09:53
 */
@Component
public class LoadRulesComponent {

    private static DrlDataDO drlDataDO() {
        String pkgName = "com.example.demo.drools.rules.sample";
        String content = "package com.example.demo.rules.sample;\n" +
                "\n" +
                "import java.util.*\n" +
                "\n" +
                "rule \"hello-world\"\n" +
                "when\n" +
                "    eval(true);\n" +
                "then\n" +
                "    System.out.println(\"hello-world\");\n" +
                "    System.out.println(((org.kie.api.runtime.rule.RuleContext)kcontext).getRule().getName());\n" +
                "end";
        DrlDataDO drlDataDO = new DrlDataDO(pkgName, content);

        return drlDataDO;
    }

    public static void main(String[] args) {
        System.out.println(drlDataDO());
    }

    public Set<DrlDataDO> loadAll() {
        // TODO implements get data from database
        Set<DrlDataDO> set = Sets.newHashSet();

        set.add(drlDataDO());

        return set;
    }
}
