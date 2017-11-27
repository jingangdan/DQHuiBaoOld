package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.ShopCartAdapter;
import com.dq.huibao.adapter.ShoppingCartAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.Cart;
import com.dq.huibao.bean.LoginBean;
import com.dq.huibao.bean.ShoppingCartBean;
import com.dq.huibao.ui.SubmitOrderActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.MD5Util;
import com.dq.huibao.utils.SPUserInfo;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindInt;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：购物车（fragment）
 * Created by jingang on 2017/10/18.
 */

public class FMShopcar extends BaseFragment implements
        ShopCartAdapter.CheckInterface,
        ShopCartAdapter.ModifyCountInterface {

    /*登录状态*/
    @Bind(R.id.lin_shopcart_nologin)
    LinearLayout linShopcartNologin;
    @Bind(R.id.rel_shopcart_login)
    RelativeLayout relShopCartLogin;

    @Bind(R.id.tv_base_title)
    TextView tvBaseTitle;

    @Bind(R.id.list_shopping_cart)
    ListView list_shopping_cart;
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

    private View view;

    private TextView tv_all_check;
    private ShoppingCartAdapter shoppingCartAdapter;


    private boolean flag = false;
    private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
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
    private RequestParams params = null;

    /*本地轻量型缓存*/
    private SPUserInfo spUserInfo;
    private String unionid = "";

    /*UI显示*/
    @Bind(R.id.rv_shopcart)
    RecyclerView rvShopCart;
    private ShopCartAdapter shopCartAdapter;
    private List<Cart.DataBean.ListBean> cartList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopcar, null);
        ButterKnife.bind(this, view);

        shopCartAdapter = new ShopCartAdapter(getActivity(), cartList);
        rvShopCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvShopCart.setAdapter(shopCartAdapter);
        shopCartAdapter.setCheckInterface(this);
        shopCartAdapter.setModifyCountInterface(this);

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
                LoginBean loginBean = GsonUtil.gsonIntance().gsonToBean(spUserInfo.getLoginReturn(), LoginBean.class);
                unionid = loginBean.getData().getUnionid();

                getCart(unionid, "", "");

                tvBaseTitle.setText("购物车");

//                shoppingCartAdapter = new ShoppingCartAdapter(getActivity());
//                shoppingCartAdapter.setCheckInterface(this);
//                shoppingCartAdapter.setModifyCountInterface(this);
//                list_shopping_cart.setAdapter(shoppingCartAdapter);
//                shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);
//
//                initData();

            }

            relShopCartLogin.setVisibility(View.VISIBLE);
            linShopcartNologin.setVisibility(View.GONE);
        } else {
            relShopCartLogin.setVisibility(View.GONE);
            linShopcartNologin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取购物车
     *
     * @param unionid
     * @param ischannelpick 不知道
     * @param ischannelpay  不知道
     */
    public void getCart(String unionid, String ischannelpick, String ischannelpay) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_CART + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_CART_CART + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&ischannelpick=" + ischannelpick + "&ischannelpay=" + ischannelpay;


        params = new RequestParams(PATH);
        System.out.println("购物车 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("购物车 = " + result);
                        Cart cart = GsonUtil.gsonIntance().gsonToBean(result, Cart.class);
                        cartList.clear();
                        cartList.addAll(cart.getData().getList());
                        shopCartAdapter.notifyDataSetChanged();
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

    @Override
    protected void lazyLoad() {

    }

    /**/
    protected void initData() {

        for (int i = 0; i < 6; i++) {
            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
            shoppingCartBean.setShoppingName("高端大气上档次的酒");
            shoppingCartBean.setFabric("纯棉");
            shoppingCartBean.setDressSize(48);
            shoppingCartBean.setPantsSize(65);
            shoppingCartBean.setPrice(60);
            shoppingCartBean.setCount(2);
            shoppingCartBeanList.add(shoppingCartBean);
        }

    }

    @OnClick({R.id.ck_all, R.id.tv_settlement})
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
//                if (shoppingCartBeanList.size() != 0) {
//                    if (ck_all.isChecked()) {
//                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
//                            shoppingCartBeanList.get(i).setChoosed(true);
//                        }
//                        shoppingCartAdapter.notifyDataSetChanged();
//                    } else {
//                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
//                            shoppingCartBeanList.get(i).setChoosed(false);
//                        }
//                        shoppingCartAdapter.notifyDataSetChanged();
//                    }
//                }
//                statistics();

                if (cartList.size() != 0) {
                    if (ck_all.isChecked()) {
                        for (int i = 0; i < cartList.size(); i++) {
                            cartList.get(i).setChoosed(true);
                        }
                        shopCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < cartList.size(); i++) {
                            cartList.get(i).setChoosed(false);
                        }
                        shopCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;

            case R.id.tv_settlement:
                intent = new Intent(getActivity(), SubmitOrderActivity.class);
                startActivity(intent);

                break;
//            case R.id.tv_edit:
//                flag = !flag;
//                if (flag) {
//                    tv_edit.setText("完成");
//                    shoppingCartAdapter.isShow(false);
//                } else {
//                    tv_edit.setText("编辑");
//                    shoppingCartAdapter.isShow(true);
//                }
//                break;
        }
    }


    /**
     * 全选 反选
     *
     * @param buttonView
     * @param isChecked
     */
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if (shoppingCartBeanList.size() != 0) {
//            if (isChecked) {
//                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
//                    shoppingCartBeanList.get(i).setChoosed(true);
//                }
//                shoppingCartAdapter.notifyDataSetChanged();
//            } else {
//                for (int i = 0; i < shoppingCartBeanList.size(); i++) {
//                    shoppingCartBeanList.get(i).setChoosed(false);
//                }
//                shoppingCartAdapter.notifyDataSetChanged();
//            }
//        }
//        statistics();
//    }


    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {

//        shoppingCartBeanList.get(position).setChoosed(isChecked);
//
//        if (isAllCheck())
//            ck_all.setChecked(true);
//        else
//            ck_all.setChecked(false);
//
//        shoppingCartAdapter.notifyDataSetChanged();
//        statistics();

        cartList.get(position).setChoosed(isChecked);

        if (isAllCheck())
            ck_all.setChecked(true);
        else
            ck_all.setChecked(false);

        shopCartAdapter.notifyDataSetChanged();
        statistics();

    }


    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

        for (ShoppingCartBean group : shoppingCartBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tv_show_price.setText("合计:" + totalPrice);
        tv_settlement.setText("结算(" + totalCount + ")");
    }

    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
//        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
//        int currentCount = shoppingCartBean.getCount();
//        currentCount++;
//        shoppingCartBean.setCount(currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shoppingCartAdapter.notifyDataSetChanged();
//        statistics();

        Cart.DataBean.ListBean listBean = cartList.get(position);
        int currentCount = Integer.parseInt(listBean.getTotal());
        currentCount++;
        listBean.setTotal("" + currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shopCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
//        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
//        int currentCount = shoppingCartBean.getCount();
//        if (currentCount == 1) {
//            return;
//        }
//        currentCount--;
//        shoppingCartBean.setCount(currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shoppingCartAdapter.notifyDataSetChanged();
//        statistics();

        Cart.DataBean.ListBean listBean = cartList.get(position);
        int currentCount = Integer.parseInt(listBean.getTotal());
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        listBean.setTotal("" + currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shopCartAdapter.notifyDataSetChanged();
        statistics();

    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
//        shoppingCartBeanList.remove(position);
//        shoppingCartAdapter.notifyDataSetChanged();
//        statistics();

//        shopCartAdapter.remove(position);
//        shopCartAdapter.notifyDataSetChanged();
//        statistics();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
