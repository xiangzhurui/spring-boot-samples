package com.xiangzhurui.samples.demo.spring.boot.kie.server.kie;

import java.io.IOException;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiangzhurui
 * @version 2019-01-14 11:06
 */
@Configuration
@Slf4j
public class DroolsConfigure {

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieServices KS = KieServices.Factory.get();

        KieResources ksResources = KS.getResources();

        String url = "http://localhost:8080/drools-wb/maven2/package/xxxx/version/xxxx.jar";

        UrlResource urlResource = (UrlResource) ksResources.newUrlResource(url);
        urlResource.setBasicAuthentication("false");
        InputStream inputStream = urlResource.getInputStream();

        KieRepository kieRepository = KS.getRepository();

        Resource resource = ksResources.newInputStreamResource(inputStream);

        KieModule kieModule = kieRepository.addKieModule(resource);

        KieContainer kieContainer = KS.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }
}
