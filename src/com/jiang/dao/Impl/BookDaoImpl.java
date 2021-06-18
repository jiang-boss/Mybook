package com.jiang.dao.Impl;

import com.jiang.dao.BookDao;
import com.jiang.pojo.Book;
import com.jiang.pojo.Page;

import java.util.List;

/**
 * @author jiangboss
 * @create 2021-05-19-21:38
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 增加图书
     * @param book
     * @return
     */
    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(`name`,`price`,`author`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?) ";
        return  update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath());
    }

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    @Override
    public int deleteBook(Integer id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    /**
     * 修改图书
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql="update t_book set `name`=?,`price`=?,`author`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book. getImgPath(),book.getId());
    }

    /**
     * 根据id查找图书
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book where id=?";
        return QueryForOne(Book.class, sql, id);
    }

    /**
     * 查询所有图书
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        String sql ="select id,name,price,author,sales,stock,img_path from t_book";
        return QueryForList(Book.class,sql);
    }

    /**
     * 返回总de记录数
     * @return
     */
    @Override
    public Integer queryForPageTotal() {
        String  sql="select count(*) from t_book";
        Number number =(Number) QueryForCount(sql);
        return number.intValue();
    }
    /**
     * 查询每页的数据
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        //分页查询
        String sql="select id,name,price,author,sales,stock,img_path from t_book limit ?,?";
        List<Book> books = QueryForList(Book.class, sql, begin, pageSize);
        return books;
    }
    /**
     * 前台的区间查询
     * @param min
     * @param max
     * @return
     */
    @Override
    public Integer queryForPageTotal(int min, int max) {
        String sql="select count(*) from t_book where price between ? and ?";
       Number o = (Number) QueryForCount(sql, min, max);
       return o.intValue();
    }
    /**
     * 前台区间查询的分页
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select id,name,price,author,sales,stock,img_path from t_book where price between ? and ? order by price limit ?,?";
        List<Book> books = QueryForList(Book.class, sql, min, max, begin, pageSize);
        return books;
    }
}
