package com.cn.mybatisStudy.interceptor.logicDelete;

import com.baomidou.mybatisplus.core.parser.AbstractJsqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.util.CollectionUtils;

import java.util.Set;

public class LogicDeleteSqlParser extends AbstractJsqlParser {

    LogicDeleteProperties logicDeleteProperties;

    public LogicDeleteSqlParser(LogicDeleteProperties logicDeleteProperties) {
        this.logicDeleteProperties = logicDeleteProperties;
    }

    @Override
    public SqlInfo processParser(Statement statement) {
        if(statement instanceof Delete) {
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

    @Override
    public void processInsert(Insert insert) {

    }

    @Override
    public void processDelete(Delete delete) {

    }

    @Override
    public void processUpdate(Update update) {

    }

    @Override
    public void processSelectBody(SelectBody selectBody) {

    }
}
