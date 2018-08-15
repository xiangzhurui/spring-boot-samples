package com.example.demo.drools.util;

import com.example.demo.drools.component.LoadRulesComponent;
import com.example.demo.drools.domain.DrlDataDO;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.logger.KieLoggers;
import org.kie.api.runtime.KieContainer;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * @author xiangzhurui
 * @version 2018/8/15 11:52
 */
@Slf4j
public class KieUtils {
    public static final DateTimeFormatter MDDMM_H_HSS_SSS = DateTimeFormatter.ofPattern("yyyyMMddmmHHssSSS").withResolverStyle(ResolverStyle.STRICT);
    /**
     * KieServices 单例
     */
    public static final KieServices KIE_SERVICES = KieServices.get();
    public static final String GROUP_ID = "com.example";
    public static final String ARTIFACT_ID = "demo-drools";
    public static final String VERSION_DEFAULT = "1.0";

    public static ReleaseId toVersion() {
        String nowTimeStr = LocalDateTime.now().format(MDDMM_H_HSS_SSS);

        ReleaseId releaseId = KIE_SERVICES.newReleaseId(GROUP_ID, ARTIFACT_ID, nowTimeStr);

        return releaseId;
    }



    public static KieContainer kieContainer(ReleaseId defaultReleaseId, LoadRulesComponent loadRulesComponent) {
        buildRules(defaultReleaseId, loadRulesComponent);
        KieContainer kieContainer = KIE_SERVICES.newKieContainer(defaultReleaseId);
        return kieContainer;
    }

    public static void buildRules(ReleaseId defaultReleaseId, LoadRulesComponent loadRulesComponent) {
        // 局部对象
        KieModuleModel moduleModel = KIE_SERVICES.newKieModuleModel();
        KieFileSystem fileSystem = KIE_SERVICES.newKieFileSystem();

        for (DrlDataDO drlDataDO : loadRulesComponent.loadAll()) {
            //添加 kbase 配置
            KieBaseModel kieBase = moduleModel.newKieBaseModel(drlDataDO.getKBaseName())
                    .addPackage(drlDataDO.getPkgName());
            kieBase.newKieSessionModel(drlDataDO.getStatefulSessionName()).setType(KieSessionModel.KieSessionType.STATEFUL);
            kieBase.newKieSessionModel(drlDataDO.getStatelessSessionName()).setType(KieSessionModel.KieSessionType.STATELESS);

            //写入 drl
            fileSystem.write(drlDataDO.getPath(), drlDataDO.getContent());
        }

        String kModuleXML = moduleModel.toXML();
        log.info("init kmoduleXML:\n{}", kModuleXML);

        fileSystem.generateAndWritePomXML(defaultReleaseId);
        fileSystem.writeKModuleXML(kModuleXML);

        // 编译 kjar 文件
        KieBuilder kieBuilder = KIE_SERVICES.newKieBuilder(fileSystem);
        kieBuilder.buildAll();
    }
}
