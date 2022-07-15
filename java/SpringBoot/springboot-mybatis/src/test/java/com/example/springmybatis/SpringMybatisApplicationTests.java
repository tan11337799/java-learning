// package com.example.springmybatis;
//
//
// import com.example.springmybatis.domain.User;
// import com.example.springmybatis.mapper.UserMapper;
// import com.example.springmybatis.mapper.UserXmlMapper;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import java.util.List;
//
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = SpringMybatisApplication.class)
// public class SpringMybatisApplicationTests {
//     @Autowired
//     private UserMapper userMapper;
//     @Autowired
//     private UserXmlMapper userXmlMapper;
//
//     @Test
//     public void testFindAll() {
//         List<User> userList = userMapper.findAll();
//         System.out.println(userList);
//     }
//
//     @Test
//     public void testFindAll2() {
//         List<User> list = userXmlMapper.findAll();
//         System.out.println(list);
//     }
// }
