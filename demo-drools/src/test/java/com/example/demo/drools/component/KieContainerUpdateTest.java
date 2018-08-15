package com.example.demo.drools.component;

import com.example.demo.drools.DemoDroolsApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author xiangzhurui
 * @version 2018/8/15 17:18
 */
@Slf4j
public class KieContainerUpdateTest extends DemoDroolsApplicationTests {

    @Autowired
    private KieContainerUpdate kieContainerUpdate;


    @Test
    public void update() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        kieContainerUpdate.update();
    }
}