package com.xiangzhurui.demo.drools;

import com.xiangzhurui.demo.drools.util.KieUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.cdi.KContainer;
import org.kie.api.cdi.KReleaseId;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author xiangzhurui
 * @version 2018/8/15 10:37
 */
@Slf4j
public class RulesTest extends DemoDroolsApplicationTests {
    public static void main(String[] args) throws IOException {
        System.out.println(Charset.forName("UTF-8").name());

        File file = new File("src/main/resources/rules/sample-rules.drl");
        System.out.println(file.getName());
        System.out.println(file.getCanonicalPath());

        System.getProperty("java.io.tmpdir");

        String s = LocalDateTime.now().format(KieUtils.MDDMM_H_HSS_SSS);
        System.out.println(s);
    }


    @KContainer
    @KReleaseId(groupId = "com.example", artifactId = "demo-drools", version = "1.0")
    KieContainer kieContainer;

    @KSession(value = "session-sample-stateful")
    @KReleaseId(groupId = "com.example", artifactId = "demo-drools", version = "1.0")
    KieSession kieSession;

    @Test
    public void testKSession0() {
        log.info("start");
//        int i = ksession.fireAllRules();
        KieSession kieSession = kieContainer.newKieSession("session-sample-stateful");
        kieSession.insert(new Object());
        int i = kieSession.fireAllRules();
        log.info("end=" + i);
        kieSession.dispose();
        StatelessKieSession statelessKieSession =  kieContainer.newStatelessKieSession("session-sample-stateless");
        statelessKieSession.execute(new Object());
        System.out.println("=======");
    }


    @Test
    public void testKSession1() {
        log.info("start");
//        int i = ksession.fireAllRules();
        kieSession.insert(new Object());
        int i = kieSession.fireAllRules();
        log.info("end=" + i);
        kieSession.dispose();
    }
}
