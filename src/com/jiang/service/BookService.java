package com.jiang.service;

import com.jiang.pojo.Book;
import com.jiang.pojo.Page;

import java.util.List;

/**
 * @author jiangboss
 * @create 2021-05-19-21:58
 */
public interface BookService {
    public void addBook(Book book);
    public void deleteBook(Integer id);
    public void update(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize);
}
