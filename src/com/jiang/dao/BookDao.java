package com.jiang.dao;

import com.jiang.pojo.Book;
import com.jiang.pojo.Page;

import java.util.List;

/**
 * @author jiangboss
 * @create 2021-05-19-15:14
 */
public interface BookDao {

    public int addBook(Book book);

    public int deleteBook(Integer id);
    public int updateBook(Book book);
    public Book queryBookById(Integer id);
    public List<Book> queryBooks();

     Integer queryForPageTotal();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotal(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}

