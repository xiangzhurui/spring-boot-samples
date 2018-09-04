package com.xiangzhurui.demo.drools.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.io.ResourceType;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * @author xiangzhurui
 * @version 2018/8/15 10:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DrlDataDO implements Serializable {
    private static final long serialVersionUID = -255345757032720985L;
    private static final String KIE_FILESYSTEM_SEPARATOR = "/";
    private static final String KIE_PACKAGE_REGEX = "\\.";
    private static final String KIE_PACKAGE_SEPARATOR = ".";

    public DrlDataDO(String pkgName, String content) {
        this.pkgName = pkgName;
        this.content = content;
        this.path = newKieFileSystemPath();
        this.kBaseName = this.newBaseName();
        this.statefulSessionName = this.newStatefulSessionName();
        this.statelessSessionName = this.newStatelessSessionName();
    }

    private String pkgName;
    private String path;
    private String content;
    private String kBaseName;
    private String statefulSessionName;
    private String statelessSessionName;

    /**
     * 获取约定路径
     *
     * @return
     */
    private String newKieFileSystemPath() {
        String[] tmpStrArr = this.pkgName.split(KIE_PACKAGE_REGEX);
        String pkgPath = StringUtils.join(tmpStrArr, KIE_FILESYSTEM_SEPARATOR);
        String drlFilePath = MessageFormat.format("{0}{1}{2}/script.drl", ResourceType.DRL.getDefaultPath(), KIE_FILESYSTEM_SEPARATOR, pkgPath);
        return drlFilePath;
    }

    private String newStatefulSessionName() {
        String prefix = this.pkgName.substring(getBeginIndex(pkgName), getLength(pkgName));
        String format = MessageFormat.format("session-{0}-stateful", prefix);
        return format;
    }

    private String newStatelessSessionName() {
        String prefix = this.pkgName.substring(getBeginIndex(pkgName), getLength(this.pkgName));
        String format = MessageFormat.format("session-{0}-stateless", prefix);
        return format;
    }

    private String newBaseName() {
        String prefix = this.pkgName.substring(getBeginIndex(pkgName), getLength(this.pkgName));
        String format = MessageFormat.format("base-{0}", prefix);
        return format;
    }

    private static int getLength(String pkgName) {
        return pkgName.length();
    }

    private static int getBeginIndex(String pkgName) {
        return pkgName.lastIndexOf(KIE_PACKAGE_SEPARATOR)+1;
    }
}
