package com.jiang.test;

import com.jiang.dao.BookDao;
import com.jiang.dao.Impl.BookDaoImpl;
import com.jiang.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jiangboss
 * @create 2021-05-19-21:53
 */
public class BookDaoTest {
    private BookDao bookDao=new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"姜春雨超人","chunyu",new BigDecimal(233333),22222,4,null));

    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(22,"姜春雨超人2","chunyu",new BigDecimal(233333),22222,4,null));

    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }
}