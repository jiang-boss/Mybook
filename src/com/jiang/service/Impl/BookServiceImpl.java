package com.jiang.service.Impl;

import com.jiang.dao.BookDao;
import com.jiang.dao.Impl.BookDaoImpl;
import com.jiang.pojo.Book;
import com.jiang.pojo.Page;
import com.jiang.service.BookService;

import java.util.List;

/**
 * @author jiangboss
 * @create 2021-05-19-22:00
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    /**
     * 增加图书
     * @param book
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /**
     * 删除图书
     * @param id
     */
    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    /**
     * 修改图书
     * @param book
     */
    @Override
    public void update(Book book) {
        bookDao.updateBook(book);
    }

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(Integer id) {
         return  bookDao.queryBookById(id);
    }

    /**
     * 查询全部图书
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    /**
     * 处理分页的操作
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page=new Page<>();
        //设置每页最大显示数
        page.setPageSize(pageSize);
        //设置总的记录数
        Integer pageTotalCount = bookDao.queryForPageTotal();
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal=pageTotalCount/Page.PAGE_SIZE;
        if(pageTotalCount%Page.PAGE_SIZE>0){
            pageTotal+=1;
        }
        //设置总页码
        page.setPageTotal(pageTotal);
        //给当前的页码赋值
        page.setPageNo(pageNo);
        //求出当前页面的索引
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> books=bookDao.queryForPageItems(begin,pageSize);
        //当前页面数据
        page.setItems(books);
        //返回数据
        return page;
    }

    /**
     * 前台分页
     * @param min
     * @param max
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> pageByPrice(int min, int max, int pageNo, int pageSize) {
        Page<Book> page=new Page<>();
        page.setPageSize(pageSize);//设置最大显示数
        Integer pageTotalcount=bookDao.queryForPageTotal(min,max);//求得满足条件的书籍
        Integer pageTotal=pageTotalcount/Page.PAGE_SIZE;
        if(pageTotal%pageSize>0){//余数不为一的时候  就新加一页
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);//设置总页码
        page.setPageNo(pageNo);
        int begin=(page.getPageNo()-1)*pageSize;
        List<Book> books=bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        page.setItems(books);
        return page;
    }
}
