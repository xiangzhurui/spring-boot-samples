package com.example.demo.drools.configuration;

import com.example.demo.drools.component.LoadRulesComponent;
import com.example.demo.drools.util.KieUtils;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.spring.annotations.KModuleAnnotationPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.demo.drools.util.KieUtils.KIE_SERVICES;

/**
 * @author xiangzhurui
 * @version 2018/8/15 09:46
 */
@Slf4j
@Configuration
public class DroolsConfiguration {

    @Autowired
    private LoadRulesComponent loadRulesComponent;


    @Bean
    public KieContainer kieContainer() {
        // 写入kjar 项目信息
        ReleaseId defaultReleaseId = KIE_SERVICES.newReleaseId(KieUtils.GROUP_ID, KieUtils.ARTIFACT_ID, KieUtils.VERSION_DEFAULT);
        return KieUtils.kieContainer(defaultReleaseId, loadRulesComponent);
    }

    @Bean
    public static KModuleAnnotationPostProcessor kModuleAnnotationPostProcessor() {
        KModuleAnnotationPostProcessor kModuleAnnotationPostProcessor = new KModuleAnnotationPostProcessor();
        return kModuleAnnotationPostProcessor;
    }

}
