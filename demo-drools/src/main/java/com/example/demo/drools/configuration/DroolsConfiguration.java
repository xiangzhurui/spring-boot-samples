package com.example.demo.drools.configuration;

import com.example.demo.drools.component.LoadRulesComponent;
import com.example.demo.drools.domain.DrlDataDO;
import com.example.demo.drools.util.KieUtils;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel.KieSessionType;
import org.kie.api.logger.KieLoggers;
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
        // 局部对象
        KieModuleModel moduleModel = KIE_SERVICES.newKieModuleModel();
        KieFileSystem fileSystem = KIE_SERVICES.newKieFileSystem();

        for (DrlDataDO drlDataDO : loadRulesComponent.loadAll()) {
            //添加 kbase 配置
            KieBaseModel kieBase = moduleModel.newKieBaseModel(drlDataDO.getKBaseName())
                    .addPackage(drlDataDO.getPkgName());
            kieBase.newKieSessionModel(drlDataDO.getStatefulSessionName()).setType(KieSessionType.STATEFUL);
            kieBase.newKieSessionModel(drlDataDO.getStatelessSessionName()).setType(KieSessionType.STATELESS);

            //写入 drl
            fileSystem.write(drlDataDO.getPath(), drlDataDO.getContent());
        }

        // 写入kjar 项目信息
        ReleaseId defaultReleaseId = KIE_SERVICES.newReleaseId(KieUtils.GROUP_ID, KieUtils.ARTIFACT_ID, KieUtils.VERSION_DEFAULT);
        String kModuleXML = moduleModel.toXML();
        log.info("init kmoduleXML:\n{}", kModuleXML);

        fileSystem.generateAndWritePomXML(defaultReleaseId);
        fileSystem.writeKModuleXML(kModuleXML);

        // 编译 kjar 文件
        KieBuilder kieBuilder = KIE_SERVICES.newKieBuilder(fileSystem);
        kieBuilder.buildAll();

        KieLoggers kieLoggers = KIE_SERVICES.getLoggers();
        KieContainer kieContainer = KIE_SERVICES.newKieContainer(defaultReleaseId);
        return kieContainer;
    }

    @Bean
    public static KModuleAnnotationPostProcessor kModuleAnnotationPostProcessor() {
        KModuleAnnotationPostProcessor kModuleAnnotationPostProcessor = new KModuleAnnotationPostProcessor();
        return kModuleAnnotationPostProcessor;
    }

}
