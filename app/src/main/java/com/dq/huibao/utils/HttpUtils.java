package com.dq.huibao.utils;

/**
 * Description：接口地址
 * Created by jingang on 2017/11/2.
 */

public class HttpUtils {

    /*请求头（主要用于图片请求）*/
    public static final String HEADER = "http://www.dequanhuibao.com/attachment/";

    /*总的请求头*/
    public static final String PATH = "http://www.dequanhuibao.com/addons/sz_yi/core/api/index.php?";

    /*
    * 首页
    * 参数：
    * i = 1604
    * */
    public static final String HP_ROOT = "api=shop/Goods/index&";

    /*
    * 分类
    * 参数：
    * i = 1604 shopid
    * */
    public static final String SHOP_CATEGRAY = "api=shop/Goods/categray&";

    /**
     * 商品列表
     * 参数：
     * i:  shopid
     * order------by-----
     * 销量：sales  从高到低：desc
     * 价格：marketprice  从高到低：desc  从低到高：asc
     * 评价：score 从高到低：asc
     */
    public static final String SHOP_GOODSLIST = "api=shop/Goods/goodslist&i=1604";

    /*
    * 搜索
    * 参数：
    * i: shopid
    * keywords:搜索关键字
    * */
    public static final String SHOP_SEARCH = "api=shop/Goods/search&i=1604&";

    /**
     * 商品详情
     * 参数：
     * i: shopid
     * id: 商品id
     */
    public static final String SHOP_GOODS_DETAIL = "api=shop/Goods/detail&i=1604";

}
