package com.cn.mybatisStudy.interceptor.logicDelete;

import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.util.CollectionUtils;

public class LogicDeleteSelectHandler implements TenantHandler {

    private LogicDeleteProperties logicDeleteProperties;

    public LogicDeleteSelectHandler(LogicDeleteProperties logicDeleteProperties) {
        this.logicDeleteProperties = logicDeleteProperties;
    }

    @Override
    public Expression getTenantId(boolean select) {
        //返回 0
        return new LongValue(logicDeleteProperties.getNoDeleteValue());
    }

    @Override
    public String getTenantIdColumn() {
        //返回 is_deleted
        return logicDeleteProperties.getColumnName();
    }

    /**
     * 是否需要过滤掉当前表
     * @param tableName
     * @return
     */
    @Override
    public boolean doTableFilter(String tableName) {
        if(!CollectionUtils.isEmpty(logicDeleteProperties.getIgnoreTableNames())) {
            return logicDeleteProperties.getIgnoreTableNames().contains(tableName);
        }
        return false;
    }
}
