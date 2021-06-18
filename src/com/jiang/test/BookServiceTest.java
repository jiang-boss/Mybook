package com.jiang.test;

import com.jiang.pojo.Book;
import com.jiang.pojo.Page;
import com.jiang.service.BookService;
import com.jiang.service.Impl.BookServiceImpl;
import org.junit.Test;

/**
 * @author jiangboss
 * @create 2021-05-20-9:33
 */
public class BookServiceTest {
    BookService bookService=new BookServiceImpl();
    @Test
    public void test(){
        Page<Book> page = bookService.pageByPrice(20, 100, 1, 4);
        System.out.println(page);
    }
}
