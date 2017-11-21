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

    /**
     * 登录
     * &openid=*******&stamp=1234567890&doc=*********
     */
    public static final String SHOP_GOODS_LOGIN = "api=shop/Member/login&i=1604&";

    /*
    * 个人信息
    * 参数：
    * uid
    * */
    public static final String SHOP_MEMBER_CENTER = "api=shop/Member/center&i=1604&";

    /*
    * 优惠券列表
    * 参数：
    * 登录验证
    * used  默认空  1已使用，
    * past  默认空 1已过期
    * page 页码
    * */
    public static final String ShOP_MEMBER_COUPON = "api=shop/Member/coupon&i=1604&";


}
