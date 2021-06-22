package com.cn.mybatisStudy.interceptor.logicDelete;

import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.util.CollectionUtils;

import java.util.Set;

public class LogicDeleteSqlParserTwo extends TenantSqlParser {

    LogicDeleteProperties logicDeleteProperties;

    public LogicDeleteSqlParserTwo(LogicDeleteProperties logicDeleteProperties) {
        this.logicDeleteProperties = logicDeleteProperties;
    }

    @Override
    public SqlInfo processParser(Statement statement) {
        if(statement instanceof Select) {
            //如果是查询，就自动在sql后面加上 and is_deleted = 0
            processSelectBody(((Select) statement).getSelectBody());
        }else if(statement instanceof Delete) {
            //如果是删除，就改变sql，走逻辑删除
            String deleteSql = statement.toString();
            String tableName = ((Delete) statement).getTable().getName();
            return SqlInfo.newInstance().setSql(handlerDeleteSql(deleteSql, tableName));
        }
        return SqlInfo.newInstance().setSql(statement.toString());
    }

    private String handlerDeleteSql(String deleteSql, String tableName) {
        Set<String> ignoreTableNames = logicDeleteProperties.getIgnoreTableNames();
        if(!CollectionUtils.isEmpty(ignoreTableNames) && ignoreTableNames.contains(tableName)) {
            return deleteSql;
        }
        //这里必须是大写的DELETE FROM
        String finalSql = deleteSql.replaceAll("DELETE FROM ", "update ")
                .replace(tableName, tableName + " set " + logicDeleteProperties.getColumnName() + " = " + logicDeleteProperties.getDeleteValue());
        return finalSql;
    }
}
