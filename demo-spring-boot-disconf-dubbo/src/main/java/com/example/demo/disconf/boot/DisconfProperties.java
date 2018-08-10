package com.example.demo.disconf.boot;

import com.example.demo.disconf.boot.annotation.DisconfConfigAnnotation;
import com.baidu.disconf.client.support.utils.ClassUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;

/**
 * Disconf属性配置
 *
 * @author xinjingziranchan@gmail.com
 * @version 1.0.0
 * @since 1.0.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Configuration
@ConfigurationProperties(DisconfProperties.DISCONF_PROPERTIES_PREFIX)
@PropertySource(value = "classpath:bootstrap.properties")
public class DisconfProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisconfProperties.class);

    public static final String DISCONF_PROPERTIES_PREFIX = "spring.disconf";

    public DisconfProperties(Environment environment) {
        loadConfig(environment);
    }

    /**
     * disconf包扫描路径
     */
    @DisconfConfigAnnotation(springBootName = "scan-package", disconfName = "disconf.scanPackage")
    private String scanPackage;

    /**
     * 在disconf-web上修改配置文件时，不会reload到系统里的服务器配置文件（给spring托管）
     */
    @DisconfConfigAnnotation(springBootName = "un-reload-files", disconfName = "disconf.unReloadFiles")
    private String unReloadFiles;

    /**
     * 在disconf-web上修改配置文件时，会reload到系统里的服务器配置文件（给spring托管）
     */
    @DisconfConfigAnnotation(springBootName = "reload-files", disconfName = "disconf.reloadFiles")
    private String reloadFiles;

    /**
     * 是否使用远程配置文件
     * true(默认)会从远程获取配置 false则直接获取本地配置
     */
    @DisconfConfigAnnotation(springBootName = "enable-remote-conf", disconfName = "disconf.enable.remote.conf", defaultValue = "false")
    private boolean enableRemoteConf = false;

    /**
     * 配置服务器的 HOST(必填)
     * 用逗号分隔  127.0.0.1:8000,127.0.0.1:8000
     */
    @DisconfConfigAnnotation(springBootName = "conf-server-host", disconfName = "disconf.conf_server_host")
    private String confServerHost;

    /**
     * 版本, 请采用 X_X_X_X 格式
     */
    @DisconfConfigAnnotation(springBootName = "version", disconfName = "disconf.version")
    private String version;

    /**
     * 主或备
     */
    @DisconfConfigAnnotation(springBootName = "main_type", disconfName = "disconf.maintype")
    private String mainType;

    /**
     * APP 请采用 产品线_服务名 格式
     */
    @DisconfConfigAnnotation(springBootName = "app", disconfName = "disconf.app")
    private String app;

    /**
     * 环境
     */
    @DisconfConfigAnnotation(springBootName = "env", disconfName = "disconf.env")
    private String env;

    /**
     * 调试模式。调试模式下，ZK超时或断开连接后不会重新连接（常用于client单步debug）。
     * 非调试模式下，ZK超时或断开连接会自动重新连接。
     */
    @DisconfConfigAnnotation(springBootName = "debug", disconfName = "disconf.debug", defaultValue = "false")
    private boolean debug = false;

    /**
     * 忽略哪些分布式配置，用逗号分隔
     */
    @DisconfConfigAnnotation(springBootName = "ignore", disconfName = "disconf.ignore")
    private String ignore;

    /**
     * 获取远程配置 重试次数，默认是3次
     */
    @DisconfConfigAnnotation(springBootName = "conf-server-url-retry-times", disconfName = "disconf.conf_server_url_retry_times", defaultValue = "3")
    private int confServerUrlRetryTimes = 3;

    /**
     * 获取远程配置 重试时休眠时间，默认是5秒
     */
    @DisconfConfigAnnotation(springBootName = "conf-server-url-retry-sleep-seconds", disconfName = "disconf.conf_server_url_retry_sleep_seconds", defaultValue = "5")
    private int confServerUrlRetrySleepSeconds = 5;

    /**
     * 用户定义的下载文件夹, 远程文件下载后会放在这里。
     * 注意，此文件夹必须有有权限，否则无法下载到这里
     */
    @DisconfConfigAnnotation(springBootName = "user-define-download-dir", disconfName = "disconf.user_define_download_dir", defaultValue = "./disconf/download")
    private String userDefineDownloadDir = "./disconf/download";

    /**
     * 下载的文件会被迁移到classpath根路径下，强烈建议将此选项置为 true(默认是true)
     */
    @DisconfConfigAnnotation(springBootName = "enable-local-download-dir-in-class-path", disconfName = "disconf.enable_local_download_dir_in_class_path", defaultValue = "false")
    private boolean enableLocalDownloadDirInClassPath = false;


    /**
     * 加载配置
     *
     * @param environment 配置环境
     */
    private void loadConfig(Environment environment) {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DisconfConfigAnnotation.class)) {
                DisconfConfigAnnotation config = field.getAnnotation(DisconfConfigAnnotation.class);
                // 获取配置属性的值
                String value = environment.getProperty(DisconfProperties.DISCONF_PROPERTIES_PREFIX + "." + config.springBootName(), config.defaultValue());
                // 设置到系统环境变量中，给disClientConfig解析
                System.setProperty(config.disconfName(), value);
                try {
                    field.setAccessible(true);
                    ClassUtils.setFieldValeByType(field, this, value);
                } catch (Exception e) {
                    LOGGER.error(String.format("invalid config: %s", config.springBootName()), e);
                }
            }
        }
    }
}
