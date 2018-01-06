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

//    public static final String PATH = "http://oneshop.mynatapp.cc/addons/sz_yi/core/api/index.php?";

    /*
    * 首页
    * 参数：
    * i = 1604
    * id
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
    public static final String SHOP_MEMBER_COUPON = "api=shop/Member/coupon&i=1604&";

    /**
     * 优惠券领取列表
     * 参数 ：
     * 登录验证
     * page：页码
     */
    public static final String SHOP_COUPON_INDEX = "api=shop/Coupon/index&i=1604&";

    /*
    * 优惠券详情
    * 参数：
    * id=
    * 登录验证
    * */
    public static final String SHOP_COUPON_DETAIL = "api=shop/Coupon/detail&i=1604&";

    /*获取推荐商品*/
    public static final String SHOP_GOODS_RECOMMENT = "api=shop/Goods/recommand&i=1604";

    /*获取个人资料
    * 参数：
    *  登录验证
    * */
    public static final String SHOP_MEMBER_GETMEMBER = "api=shop/Member/getmember&i=1604&";

    /**
     * 修改个人资料
     * 参数：
     * 登录验证
     * realname：真实姓名
     * weixin：微信号
     * membermobile：手机号
     * alipay：支付宝账号
     * alipayname：支付宝名称
     * gender：性别
     * province：省
     * city：市
     * birthyear：生日年
     * birthmonth：生日月
     * birthday：生日天
     */
    public static final String SHOP_MEMBER_SETMEMBER = "api=shop/Member/setmember&i=1604&";

    /**
     * 添加购物车(商品详情)
     * 参数：
     * 登录验证
     * id:93767
     * optionid:120834
     * total:1
     */
    public static final String SHOP_CART_ADD = "api=shop/Cart/add&i=1604&";

    /**
     * 购物车列表
     * 参数：
     * 登录验证
     */
    public static final String SHOP_CART_CART = "api=shop/Cart/cart&i=1604&";

    /*
    * 购物车修改数量（添加 减少）
    * 参数：
    * id=
    * goodsid=
    * total=
    * 登录验证
    * */
    public static final String SHOP_CART_UPDATENUM = "api=shop/Cart/updatenum&i=1604&";

    /*
    * 移至收藏夹
    * 参数:
    * ids=
    * 登录验证
    * */
    public static final String SHOP_CART_TOFAVORITE = "api=shop/Cart/tofavorite&i=1604&";

    /*
    * 购车删除
    * 参数：
    * ids
    * 登录验证
    * */
    public static final String SHOP_CART_REMOVE = "api=shop/Cart/remove&i=1604&";


    /**
     * 请求头
     */
    public static final String PATHS = "http://new.dequanhuibao.com/Api/";

    /**
     * 图片头部
     */
    public static final String IMG_HEADER = "http://www.dequanhuibao.com";

    /**
     * 1.
     * 验证手机号是否已经注册
     * <p>
     * 方式：get/post
     * 参数：
     * phone: 手机号
     */
    public static final String ACCOUNT_CHECKPHONE = "Account/checkphone?";


    /**
     * 2.
     * 发送验证码
     * 方式：post/get
     * 参数：
     * phone：手机号
     * type：类型
     * <p>
     * type类型：
     * 1 => '注册reg', wu
     * 2 => '快速登录fastlogin', /
     * 3 => '找回密码repwd', you
     * 4 => '微信绑定手机号', /
     * 5 => '更换手机号-旧', you
     * 6 => '更换手机号-新', wu
     * 7 => '修改密码' you
     */
    public static final String ACCOUNT_VERIFY = "Account/verify?";


    /**
     * 3.
     * 注册
     * 方式：post/get
     * 参数：
     * phone手机号
     * verify验证码
     * pwd密码
     */
    public static final String ACCOUNT_REG = "Account/reg?";

    /**
     * 4.
     * 登录
     * 方式：post/get
     * 参数：
     * phone手机号
     * pwd密码
     */
    public static final String ACCOUNT_LOGIN = "Account/login?";

    /**
     * 5.
     * 退出登录
     * 方式：post/get
     * 参数：
     * phone手机号
     * token 登录状态码
     * timestamp 时间戳
     * sign 签名
     */
    public static final String ACCOUNT_LOGINOUT = "Account/loginout?";

    /**
     * 6.
     * 忘记密码找回
     * 方式：post/get
     * 参数：
     * phone手机号
     * verify验证码
     * pwd密码
     */
    public static final String ACCOUNT_BACKPWD = "Account/backpwd?";

    /**
     * 7.
     * 获取用户信息
     * 方式：post/get
     * 参数：
     * phone手机号
     * token 登录状态码
     * timestamp 时间戳
     * sign 签名
     */
    public static final String MEM_MEMBER = "Member/member?";

    /**
     * 8.
     * 修改用户信息
     * 方式：post/get
     * 参数：
     * phone手机号
     * token 登录状态码
     * sex性别
     * region 地区列表id
     * $_FILES['file']用户头像（这个不能同时上传的话跟我说，我给改）
     * timestamp 时间戳
     * sign签名
     */
    public static final String MEM_EDITINFO = "Member/editinfo?";

    /**
     * 9.
     * 获取省市列表
     */
    public static final String COMMON_REGION = "Common/region";

    /**
     * 10.
     * 获取顶级分类
     * 方式：post/get
     */
    public static final String GOODS_CATE = "Goods/cate";

    /**
     * 11.
     * 获取子分类(二、三级)
     * 方式：post/get
     * 参数：id 上级分类
     */
    public static final String GOODS_CATECHILDREN = "Goods/catechildren?";

    /**
     * 12.
     * 获取分类下商品
     * 方式：post/get
     * 参数：
     * cate 分类
     */
    public static final String GOODS_CATEGOODS = "Goods/categoods?";

    /**
     * 13.
     * 搜索商品
     * 方式：post/get
     * 参数：
     * cate 分类
     * custom 自定义分类
     * key 关键字
     * ishot 热销 0/1
     * isrecommand 推荐 0/1
     * isnew 新品 0/1
     * isdiscount 促销 0/1
     * issendfree 包邮 0/1
     * istime 限时 0/1
     * page 页数
     * price 价格排序 asc/desc
     * sales销量排序 asc/desc
     * comment 评价排序 asc/desc
     */
    public static final String GOODS_SEARCH = "Goods/search?";

    /**
     * 14.
     * 商品详情
     * 方式：post/get
     * 参数：
     * id 商品id
     * token （不用加密，没登陆就不传）
     * phone（不用加密，没登陆就不传）
     */
    public static final String GOODS_DETAIL = "Goods/detail?";

}
