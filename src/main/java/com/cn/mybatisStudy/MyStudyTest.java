/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.cn.mybatisStudy;

import com.cn.mybatisStudy.config.MybatisConfig;
import com.cn.mybatisStudy.mapper.MyStudyMapper;
import com.cn.mybatisStudy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @description:
 * @author: helisen
 * @create: 2020-07-06 09:56
 **/
public class MyStudyTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void before() {
        System.out.println("测试类：执行之前的动作");
    }

    /**
     * xml方式，使用mybatis + 数据库
     */
    @Test
    public void xmlTest()  {
        SqlSession sqlSession = null;
        try {
            Reader resourceAsReader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsReader);
            sqlSession = sqlSessionFactory.openSession();
            /*方式一*/
//            User user = sqlSession.selectOne("study.MyStudyMapper.getUserById", 1L);
//            System.out.println(user.toString());
//            /*方式二*/
            MyStudyMapper mapper = sqlSession.getMapper(MyStudyMapper.class);
//            User user2 = mapper.getUserById(2L);
            List<User> user = mapper.getUser();
            sqlSession.commit();
//            System.out.println(user2.toString());
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * config注解配置方式，使用mybatis + 数据库
     */
    @Test
    public void configTest() {
        SqlSession sqlSession = null;
        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);
            SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
            SqlSession session = sqlSessionFactory.openSession();

            MyStudyMapper mapper = session.getMapper(MyStudyMapper.class);
            User userById = mapper.getUserById(2L);

//            mapper.deleteUserById(2L);
            //没有带条件的删除语句，在执行的时候通过SafeSqlInterceptor，会报错：数据删除安全检查, 当前删除的SQL没有指定查询条件, 不允许执行该操作！
//            mapper.testDeleteAll();
            session.commit();
            System.out.println(userById.toString());
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }

    }

    @Test
    public void test() {
        String sql = "hello da\njia ha    o";
        System.out.println(sql);
        //替换空格、换行、tab缩进等
        sql = sql.replaceAll("[\\s]+", " ");
        System.out.println(sql);
    }
}
