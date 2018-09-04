package com.example.demo.disconf.config.dynamic;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author xiangzhurui
 * @version 2018/8/10 10:40
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "feature-switch.properties")
public class FeatureSwitchConfig {

}
