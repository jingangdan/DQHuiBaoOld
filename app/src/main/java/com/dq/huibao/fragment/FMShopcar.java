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
import com.dq.huibao.bean.addr.AddrReturn;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.ui.LoginActivity;
import com.dq.huibao.ui.SubmitOrderActivity;
import com.dq.huibao.utils.CodeUtils;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
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
    @Bind(R.id.tv_nologin_title)
    TextView tvNologinTitle;

    @Bind(R.id.but_percen_login)
    Button butPercenLogin;

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

//    @Bind(R.id.ptrv_fm_cart)
//    PullToRefreshView pullToRefreshView;

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

    private List<Cart.DataBean.CartBean> shopList = new ArrayList<>();
    private Map<String, List<Cart.DataBean.CartBean.GoodslistBean>> children = new HashMap<String, List<Cart.DataBean.CartBean.GoodslistBean>>();// 子元素数据列表
    private ShopCartAdapter shopCartAdapter;

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopcar, null);
        ButterKnife.bind(this, view);
        tvNologinTitle.setText("购物车");

        shopCartAdapter = new ShopCartAdapter(shopList, children, getActivity());
        shopCartAdapter.setCheckInterface(this);// 关键步骤1,设置复选框接口
        shopCartAdapter.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        exListView.setAdapter(shopCartAdapter);

        isLogin();

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            System.out.println("离开FMShopCart");

        } else {
            System.out.println("刷新FMShopCart");
            shopList.clear();
            isLogin();

        }

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


    /**
     * 获取购物车
     *
     * @param phone
     * @param token
     */
    public void getCart(String phone, String token) {
        MD5_PATH = "phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;

        PATH = HttpPath.PATHS + HttpPath.CART_GET + MD5_PATH + "&sign=" +
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
                        shopList.addAll(cart.getData().getCart());
                        for (int i = 0; i < shopList.size(); i++) {
                            children.put(shopList.get(i).getShopid(), shopList.get(i).getGoodslist());
                        }
                        shopCartAdapter.notifyDataSetChanged();

                        for (int i = 0; i < shopCartAdapter.getGroupCount(); i++) {
                            // 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
                            exListView.expandGroup(i);
                        }

                        calculate();

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

        PATH = HttpPath.PATHS + HttpPath.CART_ADD + MD5_PATH + "&sign=" +
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
                                Cart.DataBean.CartBean.GoodslistBean product = (Cart.DataBean.CartBean.GoodslistBean) shopCartAdapter.getChild(groupPosition,
                                        childPosition);
                                int currentCount = Integer.parseInt(product.getCount());
                                currentCount++;
                                product.setCount("" + currentCount);
                                ((TextView) showCountView).setText("" + currentCount);

                            } else if (tag == 0) {
                                //减少
                                Cart.DataBean.CartBean.GoodslistBean product = (Cart.DataBean.CartBean.GoodslistBean) shopCartAdapter.getChild(groupPosition,
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
    public void cartDel(final String phone, final String token, String ids) {
        MD5_PATH = "ids=" + ids + "&phone=" + phone + "&timestamp=" + (System.currentTimeMillis() / 1000) + "&token=" + token;
        PATH = HttpPath.PATHS + HttpPath.CART_DEL +
                MD5_PATH + "&sign=" +
                MD5Util.getMD5String(MD5_PATH + HttpPath.KEY);
        params = new RequestParams(PATH);
        System.out.println("删除购物车 = " + PATH);
        x.http().post(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("删除购物车 = " + result);

                        AddrReturn addrReturn = GsonUtil.gsonIntance().gsonToBean(result, AddrReturn.class);
                        if (addrReturn.getStatus() == 1) {
                            toast("" + addrReturn.getData());
                            getCart(phone, token);
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


    String ids = "";

    @OnClick({R.id.but_percen_login, R.id.ck_all, R.id.tv_settlement,
            R.id.but_shopcart_tofavorite, R.id.but_shopacrt_delete})
    public void onClick(View v) {
        AlertDialog alert;
        switch (v.getId()) {
            case R.id.but_percen_login:
                //登录
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, CodeUtils.CART_FM);
                break;
            case R.id.ck_all:
                //全选按钮
                doCheckAll();
                break;

            case R.id.but_shopcart_tofavorite:
                //移至收藏夹
                break;

            case R.id.but_shopacrt_delete:
                //删除
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
                                cartDel(phone, token, ids);
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

                intent = new Intent(getActivity(), SubmitOrderActivity.class);
                intent.putExtra("cartids", ids);
                intent.putExtra("tag", "0");
                startActivityForResult(intent, CodeUtils.CART_FM);

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
        Cart.DataBean.CartBean group = shopList.get(position);
        List<Cart.DataBean.CartBean.GoodslistBean> childs = children.get(group.getShopid());
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
        Cart.DataBean.CartBean group = shopList.get(groupPosition);
        List<Cart.DataBean.CartBean.GoodslistBean> childs = children.get(group.getShopid());
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
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {
        for (Cart.DataBean.CartBean group : shopList) {
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
            Cart.DataBean.CartBean group = shopList.get(i);
            List<Cart.DataBean.CartBean.GoodslistBean> childs = children.get(group.getShopid());
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
            Cart.DataBean.CartBean group = shopList.get(i);
            List<Cart.DataBean.CartBean.GoodslistBean> childs = children.get(group.getShopid());
            for (int j = 0; j < childs.size(); j++) {
                Cart.DataBean.CartBean.GoodslistBean product = childs.get(j);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CodeUtils.CART_FM) {
            if (resultCode == CodeUtils.CONFIRM_ORDER || resultCode == CodeUtils.LOGIN) {
                isLogin();
            }
        }
    }

//    @Override
//    public void onFooterRefresh(PullToRefreshView view) {
//        pullToRefreshView.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                //加载更多数据
//                pullToRefreshView.onFooterRefreshComplete();
//
//            }
//
//        }, 1000);
//    }
//
//    @Override
//    public void onHeaderRefresh(PullToRefreshView view) {
//        pullToRefreshView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //刷新数据
//                pullToRefreshView.onHeaderRefreshComplete("更新于:"
//                        + Calendar.getInstance().getTime().toLocaleString());
//                pullToRefreshView.onHeaderRefreshComplete();
//
//                isLogin();
//
//            }
//
//        }, 1000);
//    }
}
