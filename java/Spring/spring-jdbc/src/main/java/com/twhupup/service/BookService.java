package com.twhupup.service;

import com.twhupup.dao.BookDao;
import com.twhupup.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void addBook(Book book){
        bookDao.add(book);
    }

    public void deleteBook(int id){
        bookDao.delete(id);
    }

    public void updateBook(Book book){
        bookDao.update(book);
    }

    public int findCount(){
        return bookDao.selectCount();
    }

    public Book findOne(int id){
        return bookDao.findBookInfo(id);
    }

    public List<Book> findAll(){
        return bookDao.findAllBook();
    }

    public void batchAdd(List<Object[]> batchArgs){
        bookDao.batchAddBook(batchArgs);
    }

    public void batchDelete(List<Object[]> batchArgs){
        bookDao.batchDelBook(batchArgs);
    }

    public void batchUpdate(List<Object[]> batchArgs){
        bookDao.batchUpdBook(batchArgs);
    }

}
