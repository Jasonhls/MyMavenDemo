package com.cn.mybatisStudyTwo.dynamicDataSource.fieldAutoFill;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 字段自动填充配置
 */
@Data
@ConfigurationProperties(prefix = FieldAutoFillProperties.PREFIX)
public class FieldAutoFillProperties {
    public static final String PREFIX="mybatis-plus.global-config.db-config.auto-fill-field";
    private boolean enabled = true;
    /** 创建人ID*/
    private String createUserId = "createUserId";
    /** 创建人名称*/
    private String createUserName = "createUserName";
    /** 创建时间*/
    private String createTime = "createTime";

    /** 更新人ID*/
    private String modifyUserId = "modifyUserId";
    /** 更新人名称*/
    private String modifyUserName = "modifyUserName";
    /** 更新时间*/
    private String modifyTime = "modifyTime";
    /** 删除标记*/
    private String isDeleted = "isDeleted";
}
