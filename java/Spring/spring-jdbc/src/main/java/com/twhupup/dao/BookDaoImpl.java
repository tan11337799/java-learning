package com.twhupup.dao;

import com.twhupup.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{
    //根据xml文件中的属性注入JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Book book) {
        String sql = "insert into t_book values(?,?,?)";
        Object[] args = {book.getBookId(),book.getBookName(),book.getBookStatus()};
        jdbcTemplate.update(sql,args);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from t_book where book_id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void update(Book book) {
        String sql = "update t_book set book_name=?,book_status=? where book_id=?";
        Object[] args = {book.getBookName(),book.getBookStatus(),book.getBookId()};
        jdbcTemplate.update(sql,args);
    }

    @Override
    public Integer selectCount() {
        String sql = "select count(*) from t_book";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Override
    public Book findBookInfo(int id) {
        String sql = "select * from t_book where book_id=?";
        Book book = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Book>(Book.class),id);
        return book;
    }

    @Override
    public List<Book> findAllBook() {
        String sql = "select * from t_book limit 0,2";
        List<Book> bookList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<Book>(Book.class));
        return bookList;
    }

    @Override
    public void batchAddBook(List<Object[]> batchArgs) {
        String sql = "insert into t_book values(?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }


    @Override
    public void batchUpdBook(List<Object[]> batchArgs) {
        String sql = "update t_book set book_name=?,book_status=? where book_id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }

    @Override
    public void batchDelBook(List<Object[]> batchArgs) {
        String sql = "delete from t_book where book_id=?";
        int[] ints = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println(Arrays.toString(ints));
    }
}
