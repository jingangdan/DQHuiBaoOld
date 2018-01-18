//package com.dq.huibao.fragment;

//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.dq.huibao.Interface.CheckInterface;
//import com.dq.huibao.Interface.ModifyCountInterface;
//import com.dq.huibao.R;
//import com.dq.huibao.adapter.ShopCartAdapterOld;
//import com.dq.huibao.adapter.ShoppingCartAdapter;
//import com.dq.huibao.base.BaseFragment;
//import com.dq.huibao.bean.CartOld;
//import com.dq.huibao.bean.account.Login;
//import com.dq.huibao.bean.cart.Cart;
//import com.dq.huibao.ui.SubmitOrderActivity;
//import com.dq.huibao.utils.BaseRecyclerViewHolder;
//import com.dq.huibao.utils.GsonUtil;
//import com.dq.huibao.utils.HttpUtils;
//import com.dq.huibao.utils.MD5Util;
//import com.dq.huibao.utils.SPUserInfo;
//
//import org.xutils.common.Callback;
//import org.xutils.http.RequestParams;
//import org.xutils.x;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * Description：购物车（fragment）
// * Created by jingang on 2017/10/18.
// */
//public class FMShopcar extends BaseFragment
////        implements
////        CheckInterface,
////        ModifyCountInterface
//{
//
//    /*登录状态*/
//    @Bind(R.id.lin_shopcart_nologin)
//    LinearLayout linShopcartNologin;
//    @Bind(R.id.rel_shopcart_login)
//    RelativeLayout relShopCartLogin;
//
//    /**/
//    @Bind(R.id.tv_nologin_title)
//    TextView tvNologinTitle;
//
//    @Bind(R.id.tv_base_title)
//    TextView tvBaseTitle;
//
//    @Bind(R.id.ck_all)
//    CheckBox ck_all;
//    @Bind(R.id.tv_settlement)
//    TextView tv_settlement;
//    @Bind(R.id.tv_show_price)
//    TextView tv_show_price;
//    @Bind(R.id.rl_bottom)
//    RelativeLayout rl_bottom;
//    @Bind(R.id.rel_shopcar_header)
//    RelativeLayout relShopcarHeader;
//
//    /*移至收藏夹 删除*/
//    @Bind(R.id.but_shopcart_tofavorite)
//    Button butShopcartTofavorite;
//    @Bind(R.id.but_shopacrt_delete)
//    Button butShopacrtDelete;
//
//    private View view;
//
//    private TextView tv_all_check;
//    private ShoppingCartAdapter shoppingCartAdapter;
//
//
//    private boolean flag = false;
//    private boolean mSelect;
//    private double totalPrice = 0.00;// 购买的商品总价
//    private int totalCount = 0;// 购买的商品总数量
//    /**
//     * 批量模式下，用来记录当前选中状态
//     */
//    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();
//
//    /*接收页面传值*/
//    private Intent intent;
//
//    /*接口地址*/
//    private String PATH = "";
//    private String MD5_PATH = "";
//    private RequestParams params = null;
//
//    /*本地轻量型缓存*/
//    private SPUserInfo spUserInfo;
//    private String unionid = "";
//    private String phone = "", token = "";
//
//    /*UI显示*/
////    @Bind(R.id.rv_shopcart)
////    RecyclerView rvShopCart;
//    private ShopCartAdapterOld shopCartAdapter;
//    private List<CartOld.DataBean.ListBean> cartList = new ArrayList<>();
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_shopcar, null);
//        ButterKnife.bind(this, view);
//
//        tvNologinTitle.setText("购物车");
//
////        shopCartAdapter = new ShopCartAdapterOld(getActivity(), cartList);
////        rvShopCart.setLayoutManager(new LinearLayoutManager(getActivity()));
////        rvShopCart.setAdapter(shopCartAdapter);
////        shopCartAdapter.setCheckInterface(this);
////        shopCartAdapter.setModifyCountInterface(this);
//
//        isLogin();
//
//        return view;
//    }
//
//    /*
//   * 判断登录状态
//   *  */
//    @SuppressLint("WrongConstant")
//    public void isLogin() {
//        spUserInfo = new SPUserInfo(getActivity().getApplication());
//
//        if (spUserInfo.getLogin().equals("1")) {
//
//            if (!(spUserInfo.getLoginReturn().equals(""))) {
////                LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
////                unionid = loginBean.getData().getUnionid();
////
////                getCart(unionid, "", "");
//                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
//                phone = login.getData().getPhone();
//                token = login.getData().getToken();
//                getCart(phone, token);
//
//                tvBaseTitle.setText("购物车");
//
//            }
//
//            relShopCartLogin.setVisibility(View.VISIBLE);
//            linShopcartNologin.setVisibility(View.GONE);
//        } else {
//            relShopCartLogin.setVisibility(View.GONE);
//            linShopcartNologin.setVisibility(View.VISIBLE);
//        }
//    }
//
//    /**
//     * 获取购物车
//     *
//     * @param phone
//     * @param token
//     */
//    public void getCart(String phone, String token) {
//        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
////        PATH = HttpUtils.PATHS + HttpUtils.CART_GET +
////                "phone=" + phone + "&token=" + token;
//
//        PATH = HttpUtils.PATHS + HttpUtils.CART_GET + MD5_PATH + "&sign=" +
//                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");
//
//        params = new RequestParams(PATH);
//        System.out.println("获取购物车 = " + PATH);
//        x.http().get(params,
//                new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("获取购物车 = " + result);
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//    }
//
//    /**
//     * 获取购物车
//     *
//     * @param unionid
//     * @param ischannelpick 不知道
//     * @param ischannelpay  不知道
//     */
//    public void getCart(String unionid, String ischannelpick, String ischannelpay) {
//        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_CART + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
//                MD5Util.getMD5String(HttpUtils.SHOP_CART_CART + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
//                "&ischannelpick=" + ischannelpick + "&ischannelpay=" + ischannelpay;
//
//
//        params = new RequestParams(PATH);
//        System.out.println("购物车 = " + PATH);
//
//        x.http().get(params,
//                new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("购物车 = " + result);
//                        CartOld cart = GsonUtil.gsonIntance().gsonToBean(result, CartOld.class);
//                        cartList.clear();
//                        cartList.addAll(cart.getData().getList());
//                        shopCartAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//
//    }
//
//    /**
//     * 购物车修改数量
//     *
//     * @param position
//     * @param showCountView
//     * @param isChecked
//     * @param unionid
//     * @param id
//     * @param goodsid
//     * @param total
//     */
//    public void setCartUpdateNum(final int position, final View showCountView, boolean isChecked,
//                                 String unionid, String id, String goodsid, int total, final int tag) {
//        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_UPDATENUM + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
//                MD5Util.getMD5String(HttpUtils.SHOP_CART_UPDATENUM + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
//                "&id=" + id + "&goodsid=" + goodsid + "&total=" + total;
//
//        params = new RequestParams(PATH);
//        System.out.println("购物车修改数量 = " + PATH);
//
//        x.http().post(params,
//                new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("购物车修改数量 = " + result);
//
//                        CartOld.DataBean.ListBean listBean = cartList.get(position);
//                        int currentCount = Integer.parseInt(listBean.getTotal());
//
//                        if (tag == 0) {
//                            currentCount++;
//                            listBean.setTotal("" + currentCount);
//                            ((TextView) showCountView).setText(currentCount + "");
//                            shopCartAdapter.notifyDataSetChanged();
//                            statistics();
//                        } else if (tag == 1) {
//                            if (currentCount == 1) {
//                                return;
//                            }
//                            currentCount--;
//                            listBean.setTotal("" + currentCount);
//                            ((TextView) showCountView).setText(currentCount + "");
//                            shopCartAdapter.notifyDataSetChanged();
//                            statistics();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//
//    }
//
//    /**
//     * 移至收藏夹
//     *
//     * @param unionid
//     * @param ids     id以","隔开
//     */
//    public void setToFavorite(String unionid, String ids) {
//        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_TOFAVORITE +
//                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
//                MD5Util.getMD5String(HttpUtils.SHOP_CART_TOFAVORITE + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
//                "&ids=" + ids;
//
//        params = new RequestParams(PATH);
//        System.out.println("移至收藏夹 = " + PATH);
//
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                System.out.println("移至收藏夹 = " + result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
//
//    /**
//     * 删除
//     *
//     * @param unionid
//     * @param ids
//     */
//    public void setRemova(final String unionid, String ids) {
//        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_REMOVE +
//                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
//                MD5Util.getMD5String(HttpUtils.SHOP_CART_REMOVE + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
//                "&ids=" + ids;
//
//        params = new RequestParams(PATH);
//        System.out.println("删除 = " + PATH);
//
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                System.out.println("删除 = " + result);
//
//                getCart(unionid, "", "");
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
//
//    @Override
//    protected void lazyLoad() {
//
//    }
//
//    String ids = "";
//
//    @OnClick({R.id.ck_all, R.id.tv_settlement, R.id.but_shopcart_tofavorite, R.id.but_shopacrt_delete})
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            case R.id.ck_all:
//                //全选按钮
//                if (cartList.size() != 0) {
//                    if (ck_all.isChecked()) {
//                        for (int i = 0; i < cartList.size(); i++) {
//                            cartList.get(i).setChoosed(true);
//                        }
//                        shopCartAdapter.notifyDataSetChanged();
//                    } else {
//                        for (int i = 0; i < cartList.size(); i++) {
//                            cartList.get(i).setChoosed(false);
//                        }
//                        shopCartAdapter.notifyDataSetChanged();
//                    }
//                }
//                statistics();
//                break;
//
//            case R.id.but_shopcart_tofavorite:
//                //移至收藏夹
//
//                if (cartList.size() != 0) {
//                    for (int i = 0; i < cartList.size(); i++) {
//                        cartList.get(i).isChoosed();
//                        if (cartList.get(i).isChoosed()) {
//                            if (ids.equals("")) {
//                                ids = cartList.get(i).getId();
//                            } else {
//                                ids = ids + "," + cartList.get(i).getId();
//                            }
//                        }
//                    }
//                }
//
//                if (!ids.equals("")) {
//                    setToFavorite(unionid, ids);
//                } else {
//
//                }
//                break;
//
//            case R.id.but_shopacrt_delete:
//                //删除
//                ids = "";
//                if (cartList.size() != 0) {
//                    for (int i = 0; i < cartList.size(); i++) {
//                        cartList.get(i).isChoosed();
//                        if (cartList.get(i).isChoosed()) {
//                            if (ids.equals("")) {
//                                ids = cartList.get(i).getId();
//                            } else {
//                                ids = ids + "," + cartList.get(i).getId();
//                            }
//                        }
//                    }
//                }
//
//                if (!ids.equals("")) {
////                    setRemova(unionid, ids);
//                    setDialog(unionid, ids);
//                } else {
//
//                }
//                break;
//
//            case R.id.tv_settlement:
//                //提交订单
//                intent = new Intent(getActivity(), SubmitOrderActivity.class);
//                startActivity(intent);
//
//                break;
//
//            default:
//                break;
//        }
//    }
//
//
//    /**
//     * 全选 反选
//     *
//     * @param buttonView
//     * @param isChecked
//     */
////    @Override
////    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////        if (shoppingCartBeanList.size() != 0) {
////            if (isChecked) {
////                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
////                    shoppingCartBeanList.get(i).setChoosed(true);
////                }
////                shoppingCartAdapter.notifyDataSetChanged();
////            } else {
////                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
////                    shoppingCartBeanList.get(i).setChoosed(false);
////                }
////                shoppingCartAdapter.notifyDataSetChanged();
////            }
////        }
////        statistics();
////    }
//
//
//    /**
//     * 单选
//     *
//     * @param position  组元素位置
//     * @param isChecked 组元素选中与否
//     */
////    @Override
////    public void checkGroup(int position, boolean isChecked) {
////
////        cartList.get(position).setChoosed(isChecked);
////
////        if (isAllCheck()) {
////            ck_all.setChecked(true);
////        } else {
////            ck_all.setChecked(false);
////        }
////        shopCartAdapter.notifyDataSetChanged();
////        statistics();
////
////    }
//
//
//    /**
//     * 遍历list集合
//     *
//     * @return
//     */
//    private boolean isAllCheck() {
//        for (CartOld.DataBean.ListBean group : cartList) {
//            if (!group.isChoosed())
//                return false;
//        }
//        return true;
//    }
//
//    /**
//     * 统计操作
//     * 1.先清空全局计数器<br>
//     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
//     * 3.给底部的textView进行数据填充
//     */
//    public void statistics() {
//
//        totalCount = 0;
//        totalPrice = 0.00;
//        for (int i = 0; i < cartList.size(); i++) {
//            CartOld.DataBean.ListBean listBean = cartList.get(i);
//            if (listBean.isChoosed()) {
//                //totalCount++;
//                totalCount += Integer.parseInt(listBean.getTotal());
//                totalPrice += Double.parseDouble(listBean.getMarketprice()) * Integer.parseInt(listBean.getTotal());
//            }
//        }
//        tv_show_price.setText("合计:" + totalPrice);
//        tv_settlement.setText("结算(" + totalCount + ")");
//    }
//
//    /**
//     * 增加
//     *
//     * @param position      组元素位置
//     * @param showCountView 用于展示变化后数量的View
//     * @param isChecked     子元素选中与否
//     * @param id
//     * @param goodsid
//     * @param total
//     */
////    @Override
////    public void doIncrease(int position, View showCountView, boolean isChecked, String id, String goodsid, int total) {
////        total++;
////
////        setCartUpdateNum(position, showCountView, isChecked, unionid, id, goodsid, total, 0);
////
//////        CartOld.DataBean.ListBean listBean = cartList.get(position);
//////        int currentCount = Integer.parseInt(listBean.getTotal());
//////        currentCount++;
//////        listBean.setTotal("" + currentCount);
//////        ((TextView) showCountView).setText(currentCount + "");
//////        shopCartAdapter.notifyDataSetChanged();
//////        statistics();
////
////    }
//
//    /**
//     * 减少
//     *
//     * @param position      组元素位置
//     * @param showCountView 用于展示变化后数量的View
//     * @param isChecked     子元素选中与否
//     * @param id
//     * @param goodsid
//     * @param total
//     */
////    @Override
////    public void doDecrease(int position, View showCountView, boolean isChecked, String id, String goodsid, int total) {
////        total--;
////        setCartUpdateNum(position, showCountView, isChecked, unionid, id, goodsid, total, 1);
////        //        CartOld.DataBean.ListBean listBean = cartList.get(position);
//////        int currentCount = Integer.parseInt(listBean.getTotal());
//////        if (currentCount == 1) {
//////            return;
//////        }
//////        currentCount--;
//////        listBean.setTotal("" + currentCount);
//////        ((TextView) showCountView).setText(currentCount + "");
//////        shopCartAdapter.notifyDataSetChanged();
//////        statistics();
////
////    }
//
//    /**
//     * 删除
//     *
//     * @param position
//     */
////    @Override
////    public void childDelete(int position) {
////
////    }
//
//    /**
//     * 弹窗确定
//     *
//     * @param unionid
//     * @param ids
//     */
//    public void setDialog(final String unionid, final String ids) {
//        AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
//        alert.setTitle("操作提示");
//        alert.setMessage("您确定要将这些商品从购物车中移除吗？");
//        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        return;
//                    }
//                });
//        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // modifyCountInterface.childDelete(position);//删除 目前只是从item中移除
//                        setRemova(unionid, ids);
//                    }
//                });
//        alert.show();
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.unbind(this);
//    }
//
//    /**
//     * 预留多商户（购物车）
//     */
//    public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
//        private Context mContext;
//        private List<Cart.DataBean> shopList;
//
//        public ShopAdapter(Context mContext, List<Cart.DataBean> shopList) {
//            this.mContext = mContext;
//            this.shopList = shopList;
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//            MyViewHolder vh = new MyViewHolder(
//                    LayoutInflater.from(mContext).inflate(R.layout.item_classifytwo, viewGroup, false)
//            );
//            return vh;
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int i) {
//            holder.shopname.setText("" + shopList.get(i).getShopname());
//
//            shopCartAdapter = new ShopCartAdapterOld(mContext, shopList.get(i).getGoodslist());
//            holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//            holder.recyclerView.setAdapter(shopCartAdapter);
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return shopList.size();
//        }
//
//        public class MyViewHolder extends BaseRecyclerViewHolder {
//            private TextView shopname;
//            private RecyclerView recyclerView;
//
//            public MyViewHolder(View view) {
//                super(view);
//                shopname = (TextView) view.findViewById(R.id.tv_classifytwo_name);
//                recyclerView = (RecyclerView) view.findViewById(R.id.rv_classifytwo);
//            }
//        }
//    }
//
//}


package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.CheckInterface;
import com.dq.huibao.Interface.ModifyCountInterface;
import com.dq.huibao.R;
import com.dq.huibao.adapter.cart.ShopCartAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.account.Login;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.ui.SubmitOrderActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：购物车（activity）
 * Created by jingang on 2017/11/1.
 */
public class FMShopcar extends BaseFragment implements
        CheckInterface,
        ModifyCountInterface {

    /*登录状态*/
    @Bind(R.id.lin_shopcart_nologin)
    LinearLayout linShopcartNologin;
    @Bind(R.id.rel_shopcart_login)
    RelativeLayout relShopCartLogin;

    @Bind(R.id.tv_base_title)
    TextView tvBaseTitle;

    @Bind(R.id.ck_all)
    CheckBox ck_all;
    @Bind(R.id.tv_settlement)
    TextView tv_settlement;
    @Bind(R.id.tv_show_price)
    TextView tv_show_price;
    @Bind(R.id.rl_bottom)
    RelativeLayout rl_bottom;

    @Bind(R.id.rel_shopcar_header)
    RelativeLayout relShopcarHeader;

    /*移至收藏夹 删除*/
    @Bind(R.id.but_shopcart_tofavorite)
    Button butShopcartTofavorite;
    @Bind(R.id.but_shopacrt_delete)
    Button butShopacrtDelete;

    private View view;

    private TextView tv_all_check;

    private boolean flag = false;
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    /**
     * 批量模式下，用来记录当前选中状态
     */
    private SparseArray<Boolean> mSelectState = new SparseArray<Boolean>();

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";
    private String MD5_PATH = "";
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";
    private String phone = "", token = "";

    /*UI显示*/
    @Bind(R.id.exListView)
    ExpandableListView exListView;

    private List<Cart.DataBean> shopList = new ArrayList<>();
    private Map<String, List<Cart.DataBean.GoodslistBean>> children = new HashMap<String, List<Cart.DataBean.GoodslistBean>>();// 子元素数据列表
    private ShopCartAdapter shopCartAdapter;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopcar, null);

        ButterKnife.bind(this, view);

        relShopcarHeader.setVisibility(View.GONE);

        shopCartAdapter = new ShopCartAdapter(shopList, children, getActivity());
        shopCartAdapter.setCheckInterface(this);// 关键步骤1,设置复选框接口
        shopCartAdapter.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(shopCartAdapter);

        isLogin();

        return view;
    }

    /*
  * 判断登录状态
  *  */
    @SuppressLint("WrongConstant")
    public void isLogin() {
        spUserInfo = new SPUserInfo(getActivity().getApplication());

        if (spUserInfo.getLogin().equals("1")) {

            if (!(spUserInfo.getLoginReturn().equals(""))) {
                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
                phone = login.getData().getPhone();
                token = login.getData().getToken();
                getCart(phone, token);

                tvBaseTitle.setText("购物车");

            }

            relShopCartLogin.setVisibility(View.VISIBLE);
            linShopcartNologin.setVisibility(View.GONE);
        } else {
            relShopCartLogin.setVisibility(View.GONE);
            linShopcartNologin.setVisibility(View.VISIBLE);
        }
    }


    /*
  * 判断登录状态
  *  */
//    @SuppressLint("WrongConstant")
//    public void isLogin() {
//        spUserInfo = new SPUserInfo(getActivity().getApplication());
//
//        if (spUserInfo.getLogin().equals("1")) {
//
//            if (!(spUserInfo.getLoginReturn().equals(""))) {
//                Login login = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), Login.class);
//                phone = login.getData().getPhone();
//                token = login.getData().getToken();
//
//                getCart(phone, token);
//
//                tvBaseTitle.setText("购物车");
//
//            }
//        } else {
//
//        }
//    }

    /**
     * 获取购物车
     *
     * @param phone
     * @param token
     */
    public void getCart(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.CART_GET + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");

        params = new RequestParams(PATH);
        System.out.println("获取购物车 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取购物车 = " + result);
                        Cart cart = GsonUtil.gsonIntance().gsonToBean(result, Cart.class);
                        shopList.clear();
                        shopList.addAll(cart.getData());
                        //shopAdapter.notifyDataSetChanged();
                        for (int i = 0; i < shopList.size(); i++) {
                            children.put(shopList.get(i).getShopid(), shopList.get(i).getGoodslist());
                        }
                        shopCartAdapter.notifyDataSetChanged();

                        for (int i = 0; i < shopCartAdapter.getGroupCount(); i++) {
                            // 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
                            exListView.expandGroup(i);
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /**
     * 添加购物车（修改购物车数量）
     *
     * @param phone
     * @param token
     * @param gid
     * @param optionid
     * @param count
     */
    public void cartAdd(final int groupPosition, final int childPosition, final View showCountView, boolean isChecked,
                        String phone, String token, final String gid, String optionid, final int count, final int tag) {
        MD5_PATH = "count=" + count + "&goodsid=" + gid + "&optionid=" + optionid + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpUtils.PATHS + HttpUtils.CART_ADD + MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + "&key=ivKDDIZHF2b0Gjgvv2QpdzfCmhOpya5k");


        params = new RequestParams(PATH);
        System.out.println("添加购物车 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @SuppressLint("WrongConstant")
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("添加购物车 = " + result);
                        Cart cart = GsonUtil.gsonIntance().gsonToBean(result, Cart.class);
                        if (cart.getStatus() == 1) {
                            if (tag == 1) {
                                //增加
                                Cart.DataBean.GoodslistBean product = (Cart.DataBean.GoodslistBean) shopCartAdapter.getChild(groupPosition,
                                        childPosition);
                                int currentCount = Integer.parseInt(product.getCount());
                                currentCount++;
                                product.setCount("" + currentCount);
                                ((TextView) showCountView).setText("" + currentCount);

                            } else if (tag == 0) {
                                //减少
                                Cart.DataBean.GoodslistBean product = (Cart.DataBean.GoodslistBean) shopCartAdapter.getChild(groupPosition,
                                        childPosition);
                                int currentCount = Integer.parseInt(product.getCount());
                                if (currentCount == 1)
                                    return;
                                currentCount--;
                                product.setCount("" + currentCount);
                                ((TextView) showCountView).setText(currentCount + "");

                            }
                            shopCartAdapter.notifyDataSetChanged();
                            calculate();

                        } else {
                            toast("购物车操作失败");
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
    }

    /**
     * 删除购物车
     *
     * @param phone
     * @param token
     * @param ids
     */
    public void cartDel(String phone, String token, String ids) {
        MD5_PATH = "ids=" + ids + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpUtils.PATHS + HttpUtils.CART_DEL +
                MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpUtils.KEY);
        params = new RequestParams(PATH);
        System.out.println("删除购物车 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("删除购物车 = " + result);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

    }


    String ids = "";

    @OnClick({R.id.ck_all, R.id.tv_settlement, R.id.but_shopcart_tofavorite, R.id.but_shopacrt_delete})
    public void onClick(View v) {
        AlertDialog alert;
        switch (v.getId()) {

            case R.id.ck_all:
                //全选按钮
                doCheckAll();
                break;

            case R.id.but_shopcart_tofavorite:
                //移至收藏夹

//                if (cartList.size() != 0) {
//                    for (int i = 0; i < cartList.size(); i++) {
//                        cartList.get(i).isChoosed();
//                        if (cartList.get(i).isChoosed()) {
//                            if (ids.equals("")) {
//                                ids = cartList.get(i).getId();
//                            } else {
//                                ids = ids + "," + cartList.get(i).getId();
//                            }
//                        }
//                    }
//                }

                if (!ids.equals("")) {
                } else {

                }
                break;

            case R.id.but_shopacrt_delete:
                //删除

//                if (cartList.size() != 0) {
//                    for (int i = 0; i < cartList.size(); i++) {
//                        cartList.get(i).isChoosed();
//                        if (cartList.get(i).isChoosed()) {
//                            if (ids.equals("")) {
//                                ids = cartList.get(i).getId();
//                            } else {
//                                ids = ids + "," + cartList.get(i).getId();
//                            }
//                        }
//                    }
//                }

//                if (!ids.equals("")) {
//                    setDialog(unionid, ids);
//                } else {
//
//                }

                if (totalCount == 0) {
                    toast("请选择要移除的商品");
                    //Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？\n总计:\n" + totalCount + "种商品\n" + totalPrice + "元" + "\n商品id = " + ids);
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //doDelete();
                                System.out.println("ids = " + ids);
                                //cartDel(phone, token, ids);
                                //删除操作
                            }
                        });
                alert.show();
                break;


            case R.id.tv_settlement:
                //提交订单

                if (totalCount == 0) {
                    toast("请选择要支付的商品");
                    return;
                }
                alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle("操作提示");
                alert.setMessage("总计:\n" + totalCount + "种商品\n" + totalPrice + "元" + "\n商品id = " + ids);
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });

                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                intent = new Intent(getActivity(), SubmitOrderActivity.class);
                                intent.putExtra("cartids", ids);
                                startActivity(intent);
                                return;
                            }
                        });
                alert.show();


                break;

            default:
                break;
        }
    }

    /**
     * 组选框状态改变触发的事件
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        Cart.DataBean group = shopList.get(position);
        List<Cart.DataBean.GoodslistBean> childs = children.get(group.getShopid());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            ck_all.setChecked(true);
        else
            ck_all.setChecked(false);
        shopCartAdapter.notifyDataSetChanged();
        calculate();

    }

    /**
     * 子选框状态改变时触发的事件
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;
        // 判断改组下面的所有子元素是否是同一种状态
        Cart.DataBean group = shopList.get(groupPosition);
        List<Cart.DataBean.GoodslistBean> childs = children.get(group.getShopid());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            ck_all.setChecked(true);// 全选
        } else {
            ck_all.setChecked(false);// 反选
        }
        shopCartAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 增加操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked,
                           String gid, String optionid, int count) {
        cartAdd(groupPosition, childPosition, showCountView, isChecked,
                phone, token, gid, optionid, count, 1);

//        Cart.DataBean.GoodslistBean product = (Cart.DataBean.GoodslistBean) shopTestAdapter.getChild(groupPosition,
//                childPosition);
//        int currentCount = Integer.parseInt(product.getCount());
//        currentCount++;
//        product.setCount("" + currentCount);
//        ((TextView) showCountView).setText("" + currentCount);
//        shopTestAdapter.notifyDataSetChanged();
//
//        calculate();
    }

    /**
     * 删减操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked,
                           String gid, String optionid, int count) {

        cartAdd(groupPosition, childPosition, showCountView, isChecked,
                phone, token, gid, optionid, -1, 0);
//        Cart.DataBean.GoodslistBean product = (Cart.DataBean.GoodslistBean) shopCartAdapter.getChild(groupPosition,
//                childPosition);
//        int currentCount = Integer.parseInt(product.getCount());
//        if (currentCount == 1)
//            return;
//        currentCount--;
//        product.setCount("" + currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shopCartAdapter.notifyDataSetChanged();
//        calculate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
//        children.get(shopList.get(groupPosition).getShopid()).remove(childPosition);
//        Cart.DataBean group = shopList.get(groupPosition);
//        List<Cart.DataBean.GoodslistBean> childs = children.get(group.getShopid());
//        if (childs.size() == 0) {
//            shopList.remove(groupPosition);
//        }
//        shopTestAdapter.notifyDataSetChanged();
//        //     handler.sendEmptyMessage(0);
//        calculate();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {
        for (Cart.DataBean group : shopList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 弹窗确定
     *
     * @param unionid
     * @param ids
     */
    public void setDialog(final String unionid, final String ids) {
        AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
        alert.setTitle("操作提示");
        alert.setMessage("您确定要将这些商品从购物车中移除吗？");
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // modifyCountInterface.childDelete(position);//删除 目前只是从item中移除
                        //setRemova(unionid, ids);
                    }
                });
        alert.show();
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < shopList.size(); i++) {
            shopList.get(i).setChoosed(ck_all.isChecked());
            Cart.DataBean group = shopList.get(i);
            List<Cart.DataBean.GoodslistBean> childs = children.get(group.getShopid());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(ck_all.isChecked());
            }
        }
        shopCartAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        ids = "";
        for (int i = 0; i < shopList.size(); i++) {
            Cart.DataBean group = shopList.get(i);
            List<Cart.DataBean.GoodslistBean> childs = children.get(group.getShopid());
            for (int j = 0; j < childs.size(); j++) {
                Cart.DataBean.GoodslistBean product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += Double.parseDouble(product.getMarketprice()) * Integer.parseInt(product.getCount());
                    if (ids.equals("")) {
                        //ids = product.getGoodsid();
                        ids = product.getId();
                    } else {
                        //ids = ids + "," + product.getGoodsid();
                        ids = ids + "," + product.getId();
                    }
                }
            }
        }

        tv_show_price.setText("￥" + totalPrice);
        //tvGoToPay.setText("去支付(" + totalCount + ")");
        //计算购物车的金额为0时候清空购物车的视图
        if (totalCount == 0) {
            //setCartNum();
        } else {
            //title.setText("购物车" + "(" + totalCount + ")");
        }
    }


    @Override
    protected void lazyLoad() {

    }
}
