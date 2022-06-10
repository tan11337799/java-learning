package com.twhupup.dao;

import com.twhupup.entity.Book;

import java.util.List;

public interface BookDao {
    void add(Book book);
    void delete(int id);
    void update(Book book);
    Integer selectCount();
    Book findBookInfo(int id);
    List<Book> findAllBook();
    void batchAddBook(List<Object[]> batchArgs);
    void batchDelBook(List<Object[]> batchArgs);
    void batchUpdBook(List<Object[]> batchArgs);
}
