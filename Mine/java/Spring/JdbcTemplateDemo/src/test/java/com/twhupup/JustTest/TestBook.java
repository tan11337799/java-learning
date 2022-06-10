package com.twhupup.JustTest;

import com.twhupup.entity.Book;
import com.twhupup.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Project: SpringDemo3
 * @Package: com.twhupup.JustTest
 * @Date: 2022/6/8 15:30
 * @Author: Wenhao Tan
 * @Version: 1.0
 * @License: (C)2022, MIPAV Lab(mipav.net), Soochow University. tanritian1@163.com All Rights Reserved.
 */

public class TestBook {
    @Test
    public void testBook(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        // //添加
        // Book addbook = new Book();
        // addbook.setBookId("1");
        // addbook.setBookName("java");
        // addbook.setBookStatus("borrowed");
        // bookService.addBook(addbook);
        // //删除
        // bookService.deleteBook(1);
        // //修改
        // Book updbook = new Book();
        // updbook.setBookId("2");
        // updbook.setBookName("php");
        // updbook.setBookStatus("sold");
        // bookService.updateBook(updbook);
        // //查询单个值
        // int count = bookService.findCount();
        // System.out.println(count);
        // //查询单个对象
        // Book book = bookService.findOne(1);
        // System.out.println(book);
        // 查询多个对象
        List<Book> bookList = bookService.findAll();
        System.out.println(bookList);
        // // 批量添加
        // List<Object[]> addBatchArgs = new ArrayList<>();
        // addBatchArgs.add(new Object[]{"3","Mysql","borrowed"});
        // addBatchArgs.add(new Object[]{"4","go","lost"});
        // bookService.batchAdd(addBatchArgs);
        // // 批量修改
        // List<Object[]> updBatchArgs = new ArrayList<>();
        // updBatchArgs.add(new Object[]{"c++","sold","3"});
        // updBatchArgs.add(new Object[]{"linux","sold","4"});
        // bookService.batchUpdate(updBatchArgs);
        // 批量删除
        // List<Object[]> delBatchArgs = new ArrayList<>();
        // delBatchArgs.add(new Object[]{"3"});
        // delBatchArgs.add(new Object[]{"4"});
        // bookService.batchDelete(delBatchArgs);
    }
}
