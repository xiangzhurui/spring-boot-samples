package com.example.demo.drools.component;

import com.example.demo.drools.util.KieUtils;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xiangzhurui
 * @version 2018/8/15 17:14
 */
@Slf4j
@Component
public class KieContainerUpdate {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private LoadRulesComponent loadRulesComponent;

    public void update() {

        ReleaseId releaseId = KieUtils.toVersion();

        KieUtils.buildRules(releaseId, loadRulesComponent);

        Results results = kieContainer.updateToVersion(releaseId);
        log.info("更新成功:[{}]", results);
    }
}
