package com.example.demo.drools.util;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * @author xiangzhurui
 * @version 2018/8/15 11:52
 */
public class KieUtils {
    public static final DateTimeFormatter MDDMM_H_HSS_SSS = DateTimeFormatter.ofPattern("yyyyMMddmmHHssSSS").withResolverStyle(ResolverStyle.STRICT);
    /**
     * KieServices 单例
     */
    public static final KieServices KIE_SERVICES = KieServices.get();
    public static final String GROUP_ID = "com.example";
    public static final String ARTIFACT_ID = "demo-drools";
    public static final String VERSION_DEFAULT = "1.0";

    public ReleaseId toVersion() {
        ReleaseId releaseId = KIE_SERVICES.newReleaseId(GROUP_ID, ARTIFACT_ID, LocalDateTime.now().format(MDDMM_H_HSS_SSS));
        return releaseId;
    }
}
