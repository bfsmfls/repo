package com.wx.test;

import com.wx.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    //编写测试类
    @Test
    public void testMybatisTest() throws IOException {
        // 加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql
        List<User> list = sqlSession.selectList("userMapper.findAll");
        //遍历集合，打印到控制台
        for (User user : list) {
            System.out.println(user);
        }
        //关闭资源
        sqlSession.close();
    }
    //新增用户测试
    @Test
    public void saveUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql
        User user = new User();
        user.setUsername("tom");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京海淀");
        sqlSession.insert("userMapper.saveUser",user);
        //数据库变动需要手动提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }

    //修改用户测试
    @Test
    public void updateUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql
        User user = new User();
        user.setId(4);
        user.setUsername("jack");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京朝阳");
        sqlSession.update("userMapper.updateUser",user);
        //数据库变动需要手动提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }

    //删除用户测试
    @Test
    public void deleteUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行sql
        sqlSession.delete("userMapper.deleteUser",5);
        //数据库变动需要手动提交事务
        sqlSession.commit();
        //关闭资源
        sqlSession.close();
    }
}
