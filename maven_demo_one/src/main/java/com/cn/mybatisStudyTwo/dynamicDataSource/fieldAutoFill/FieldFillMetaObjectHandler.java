package com.cn.mybatisStudyTwo.dynamicDataSource.fieldAutoFill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.cn.mybatisStudy.interceptor.logicDelete.LogicDeleteProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class FieldFillMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private FieldAutoFillProperties fillProperties;

    @Override
    public void insertFill(MetaObject metaObject) {
        fillCreateTime(metaObject);
        fillCreateUserId(metaObject);
        fillCreateUserName(metaObject);
        fillIsDeleted(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillModifyUserId(metaObject);
        fillModifyUserName(metaObject);
        fillModifyTime(metaObject);
    }


    private void fillCreateTime(MetaObject metaObject) {
        if(metaObject.hasGetter(fillProperties.getCreateTime())) {
            fillDateField(fillProperties.getCreateTime(), metaObject);
        }
    }

    private void fillCreateUserId(MetaObject metaObject) {
        setUserId(metaObject, fillProperties.getCreateUserId());
    }

    private void fillCreateUserName(MetaObject metaObject) {
        setUserName(metaObject, fillProperties.getCreateUserName());
    }

    private void fillIsDeleted(MetaObject metaObject) {
        String fieldName = fillProperties.getIsDeleted();
        if(metaObject.hasGetter(fieldName)) {
            Object fieldValue = metaObject.getValue(fieldName);
            if(fieldValue == null) {
                Class<?> setterType = metaObject.getSetterType(fieldName);
                if(setterType.isAssignableFrom(Integer.class)) {
                    this.strictInsertFill(metaObject, fieldName, Integer.class, Integer.valueOf(LogicDeleteProperties.noDeleteValue));
                }else if(setterType.isAssignableFrom(Boolean.class)) {
                    this.strictInsertFill(metaObject, fieldName, Boolean.class, Boolean.valueOf(LogicDeleteProperties.noDeleteValue));
                }else {
                    this.strictInsertFill(metaObject, fieldName, Byte.class, Byte.valueOf(LogicDeleteProperties.noDeleteValue));
                }
            }
        }
    }

    private void fillModifyUserId(MetaObject metaObject) {
        setUserId(metaObject, fillProperties.getModifyUserId());
    }

    private void fillModifyUserName(MetaObject metaObject) {
        setUserName(metaObject, fillProperties.getModifyUserName());
    }

    private void fillModifyTime(MetaObject metaObject) {
        if(metaObject.hasGetter(fillProperties.getModifyTime())) {
            fillDateField(fillProperties.getModifyTime(), metaObject);
        }
    }

    private void setUserName(MetaObject metaObject, String fieldName) {
        if(metaObject.hasGetter(fieldName)) {
            Object fieldValue = metaObject.getValue(fieldName);
            if(fieldValue == null) {
                //获取当前系统登录的userName
                LoginContext loginContext = getUserInfoLoginContext();
                String userName = Optional.ofNullable(loginContext).map(LoginContext::getUserName).orElse(null);
                if(StringUtils.isBlank(userName)) {
                    userName = "system";
                }
                this.strictInsertFill(metaObject, fieldName, String.class, userName);
            }
        }
    }

    private void setUserId(MetaObject metaObject, String fieldName) {
        if(metaObject.hasGetter(fieldName)) {
            Object fieldValue = metaObject.getValue(fieldName);
            if(fieldValue == null) {
                Class<?> setterType = metaObject.getSetterType(fieldName);
                //获取当前系统登录的userId
                LoginContext loginContext = getUserInfoLoginContext();
                Long userId = Optional.ofNullable(loginContext).map(LoginContext::getUserId).orElse(null);
                if(userId == null) {
                    userId = 0L;
                }
                if(setterType.isAssignableFrom(Integer.class)) {
                    this.strictInsertFill(metaObject, fieldName, Integer.class, userId.intValue());
                }else if(setterType.isAssignableFrom(Long.class)) {
                    this.strictInsertFill(metaObject, fieldName, Long.class, userId);
                }
            }
        }
    }

    //获取当前登录用户信息
    private LoginContext getUserInfoLoginContext() {
        //toDo
        return null;
    }


    private void fillDateField(String fieldName, MetaObject metaObject) {
        Class<?> setterType = metaObject.getSetterType(fieldName);
        if(Date.class.equals(setterType)) {
            this.strictInsertFill(metaObject, fieldName, Date.class, new Date());
        }else if(LocalDate.class.equals(setterType)) {
            this.strictInsertFill(metaObject, fieldName, LocalDate.class, LocalDate.now());
        } else if(LocalDateTime.class.equals(setterType)) {
            this.strictInsertFill(metaObject, fieldName, LocalDateTime.class, LocalDateTime.now());
        }else if(Long.class.equals(setterType)) {
            this.strictInsertFill(metaObject, fieldName, Long.class, System.currentTimeMillis());
        }
    }
}
