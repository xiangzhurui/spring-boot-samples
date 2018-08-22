package com.example.demo.drools.component;

import com.example.demo.drools.DemoDroolsApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author xiangzhurui
 * @version 2018/8/15 17:18
 */
@Slf4j
public class KieContainerUpdateTest extends DemoDroolsApplicationTests {

    @Autowired
    private KieContainerUpdate kieContainerUpdate;
    KieRepository kieRepository = KieServices.get().getRepository();

    @Test
    public void update() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(5);
        kieContainerUpdate.update();
    }

    @Test
    public void updateTestManyUpdated() throws InterruptedException {
        log.info("看下不断更新规则版本是否会导致OOM");
        final LocalDateTime startTime = LocalDateTime.now();

        while (true) {
            TimeUnit.MICROSECONDS.sleep(5);
            kieContainerUpdate.update();

            if (Duration.between(startTime, LocalDateTime.now()).toMinutes() >= 30) {
                log.info("已运行30分钟，未OOM");
                break;
            }
        }
    }
}