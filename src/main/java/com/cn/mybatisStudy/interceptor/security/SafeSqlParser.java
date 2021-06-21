package com.cn.mybatisStudy.interceptor.security;

import com.baomidou.mybatisplus.core.parser.AbstractJsqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.update.Update;

public class SafeSqlParser extends AbstractJsqlParser {
    SafeSqlProperties safeSqlProperties;

    public SafeSqlParser(SafeSqlProperties safeSqlProperties) {
        this.safeSqlProperties = safeSqlProperties;
    }

    /**
     * 重写父类方法，执行parser方法的时候，会调用processParser方法
     * @param statement
     * @return
     */
    @Override
    public SqlInfo processParser(Statement statement) {
        if(statement instanceof Update) {
            this.processUpdate((Update) statement);
        }else if(statement instanceof Delete) {
            this.processDelete((Delete) statement);
        }
        return SqlInfo.newInstance().setSql(statement.toString());
    }

    @Override
    public void processInsert(Insert insert) {

    }

    @Override
    public void processDelete(Delete delete) {
        if(safeSqlProperties.isEnableSafeDelete()) {
            String name = delete.getTable().getName();
            if(!safeSqlProperties.getSafeDeleteIgnoreTable().contains(name)) {
                Assert.notNull(delete.getWhere(), "数据删除安全检查, 当前删除的SQL没有指定查询条件, 不允许执行该操作!", "");
            }
        }
    }

    @Override
    public void processUpdate(Update update) {
        if(safeSqlProperties.isEnableSafeUpdate()) {
            String name = update.getTable().getName();
            if(!safeSqlProperties.getSafeUpdateIgnoreTable().contains(name)) {
                Assert.notNull(update.getWhere(), "数据更新安全检查, 当前更新的SQL没有指定查询条件, 不允许执行该操作!", "");
            }
        }
    }

    @Override
    public void processSelectBody(SelectBody selectBody) {

    }
}
