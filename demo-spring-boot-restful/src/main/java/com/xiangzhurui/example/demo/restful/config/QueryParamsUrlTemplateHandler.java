package com.xiangzhurui.example.demo.restful.config;

import java.net.URI;
import java.util.Map;

import org.springframework.web.util.DefaultUriTemplateHandler;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author xiangzhurui
 * @version 2018/10/29 20:15
 */
public class QueryParamsUrlTemplateHandler extends DefaultUriTemplateHandler {
    @Override
    public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(uriTemplate);
        for (Map.Entry<String, ?> varEntry : uriVariables.entrySet()) {
            uriComponentsBuilder.queryParam(varEntry.getKey(), varEntry.getValue());
        }
        uriTemplate = uriComponentsBuilder.build().toUriString();
        return super.expand(uriTemplate, uriVariables);
    }
}
