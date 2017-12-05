package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    /*移至收藏夹 删除*/
    @Bind(R.id.but_shopcart_tofavorite)
    Button butShopcartTofavorite;
    @Bind(R.id.but_shopacrt_delete)
    Button butShopacrtDelete;

    private View view;

    private TextView tv_all_check;
    private ShoppingCartAdapter shoppingCartAdapter;


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

    /**
     * 购物车修改数量
     *
     * @param position
     * @param showCountView
     * @param isChecked
     * @param unionid
     * @param id
     * @param goodsid
     * @param total
     */
    public void setCartUpdateNum(final int position, final View showCountView, boolean isChecked,
                                 String unionid, String id, String goodsid, int total, final int tag) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_UPDATENUM + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_CART_UPDATENUM + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&id=" + id + "&goodsid=" + goodsid + "&total=" + total;

        params = new RequestParams(PATH);
        System.out.println("购物车修改数量 = " + PATH);

        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("购物车修改数量 = " + result);

                        Cart.DataBean.ListBean listBean = cartList.get(position);
                        int currentCount = Integer.parseInt(listBean.getTotal());

                        if (tag == 0) {
                            currentCount++;
                            listBean.setTotal("" + currentCount);
                            ((TextView) showCountView).setText(currentCount + "");
                            shopCartAdapter.notifyDataSetChanged();
                            statistics();
                        } else if (tag == 1) {
                            if (currentCount == 1) {
                                return;
                            }
                            currentCount--;
                            listBean.setTotal("" + currentCount);
                            ((TextView) showCountView).setText(currentCount + "");
                            shopCartAdapter.notifyDataSetChanged();
                            statistics();
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
     * 移至收藏夹
     *
     * @param unionid
     * @param ids     id以","隔开
     */
    public void setToFavorite(String unionid, String ids) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_TOFAVORITE +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_CART_TOFAVORITE + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&ids=" + ids;

        params = new RequestParams(PATH);
        System.out.println("移至收藏夹 = " + PATH);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("移至收藏夹 = " + result);
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
     * 删除
     *
     * @param unionid
     * @param ids
     */
    public void setRemova(final String unionid, String ids) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_CART_REMOVE +
                "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&doc=" +
                MD5Util.getMD5String(HttpUtils.SHOP_CART_REMOVE + "unionid=" + unionid + "&stamp=" + (System.currentTimeMillis() / 1000) + "&dequanhuibaocom") +
                "&ids=" + ids;

        params = new RequestParams(PATH);
        System.out.println("删除 = " + PATH);

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("删除 = " + result);

                getCart(unionid, "", "");
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

    String ids = "";

    @OnClick({R.id.ck_all, R.id.tv_settlement, R.id.but_shopcart_tofavorite, R.id.but_shopacrt_delete})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ck_all:
                //全选按钮
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

            case R.id.but_shopcart_tofavorite:
                //移至收藏夹

                if (cartList.size() != 0) {
                    for (int i = 0; i < cartList.size(); i++) {
                        cartList.get(i).isChoosed();
                        if (cartList.get(i).isChoosed()) {
                            if (ids.equals("")) {
                                ids = cartList.get(i).getId();
                            } else {
                                ids = ids + "," + cartList.get(i).getId();
                            }
                        }
                    }
                }

                if (!ids.equals("")) {
                    setToFavorite(unionid, ids);
                } else {

                }
                break;

            case R.id.but_shopacrt_delete:
                //删除
                ids = "";
                if (cartList.size() != 0) {
                    for (int i = 0; i < cartList.size(); i++) {
                        cartList.get(i).isChoosed();
                        if (cartList.get(i).isChoosed()) {
                            if (ids.equals("")) {
                                ids = cartList.get(i).getId();
                            } else {
                                ids = ids + "," + cartList.get(i).getId();
                            }
                        }
                    }
                }

                if (!ids.equals("")) {
//                    setRemova(unionid, ids);
                    setDialog(unionid, ids);
                } else {

                }
                break;

            case R.id.tv_settlement:
                //提交订单
                intent = new Intent(getActivity(), SubmitOrderActivity.class);
                startActivity(intent);

                break;

            default:
                break;
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

        cartList.get(position).setChoosed(isChecked);

        if (isAllCheck()) {
            ck_all.setChecked(true);
        } else {
            ck_all.setChecked(false);
        }
        shopCartAdapter.notifyDataSetChanged();
        statistics();

    }


    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {
        for (Cart.DataBean.ListBean group : cartList) {
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
        for (int i = 0; i < cartList.size(); i++) {
            Cart.DataBean.ListBean listBean = cartList.get(i);
            if (listBean.isChoosed()) {
                //totalCount++;
                totalCount += Integer.parseInt(listBean.getTotal());
                totalPrice += Double.parseDouble(listBean.getMarketprice()) * Integer.parseInt(listBean.getTotal());
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
     * @param id
     * @param goodsid
     * @param total
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked, String id, String goodsid, int total) {
        total++;

        setCartUpdateNum(position, showCountView, isChecked, unionid, id, goodsid, total, 0);

//        Cart.DataBean.ListBean listBean = cartList.get(position);
//        int currentCount = Integer.parseInt(listBean.getTotal());
//        currentCount++;
//        listBean.setTotal("" + currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shopCartAdapter.notifyDataSetChanged();
//        statistics();

    }

    /**
     * 减少
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     * @param id
     * @param goodsid
     * @param total
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked, String id, String goodsid, int total) {
        total--;
        setCartUpdateNum(position, showCountView, isChecked, unionid, id, goodsid, total, 1);
        //        Cart.DataBean.ListBean listBean = cartList.get(position);
//        int currentCount = Integer.parseInt(listBean.getTotal());
//        if (currentCount == 1) {
//            return;
//        }
//        currentCount--;
//        listBean.setTotal("" + currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shopCartAdapter.notifyDataSetChanged();
//        statistics();

    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {

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
                        setRemova(unionid, ids);
                    }
                });
        alert.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
