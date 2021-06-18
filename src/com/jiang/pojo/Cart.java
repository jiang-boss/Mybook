package com.jiang.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jiangboss
 * @create 2021-05-21-16:53
 * 购物车的模型  加入购物车  删除商品项  清空购物车 修改商品数量
 */
public class Cart {
//    private Integer totalCount;//总数量
//    private Integer totalPrice;//总金额
    /**
     * key   是商品编号
     * value  是商品信息
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * 得到总数量
     * @return
     */
    public Integer getTotalCount() {
         Integer totalCount=0;
        //遍历购物车的商品获取总商品
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }
    /**
     * 得到总价格
     * @return
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    /**
     * 添加商品
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) {
            //说明购物车里面没有该商品 储存商品
            items.put(cartItem.getId(), cartItem);
        } else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }
    }
    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 删除购物车中的商品
     *
     * @param id
     */
    public void delete(Integer id) {
        items.remove(id);
    }

    /**
     * 修改商品的数量
     */
    public void updateCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {//判断购物车中中是否有这个商品
            cartItem.setCount(count);//修改数量
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));//修改当前单个商品的总价格
        }
    }
    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
