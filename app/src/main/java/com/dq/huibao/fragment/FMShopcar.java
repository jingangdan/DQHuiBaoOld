package com.dq.huibao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.ShoppingCartAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.ShoppingCartBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：
 * Created by jingang on 2017/10/18.
 */

public class FMShopcar extends BaseFragment implements
        ShoppingCartAdapter.CheckInterface,
        ShoppingCartAdapter.ModifyCountInterface {

//    @Bind(R.id.tv_edit)
//    TextView tv_edit;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopcar, null);
        ButterKnife.bind(this, view);

        //tv_edit.setOnClickListener(this);
        ck_all.setOnClickListener(this);
        shoppingCartAdapter = new ShoppingCartAdapter(getActivity());
        shoppingCartAdapter.setCheckInterface(this);
        shoppingCartAdapter.setModifyCountInterface(this);
        list_shopping_cart.setAdapter(shoppingCartAdapter);
        shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);

        initData();


        return view;
    }

    @Override
    protected void lazyLoad() {

    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (shoppingCartBeanList.size() != 0) {
                    if (ck_all.isChecked()) {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
                            shoppingCartBeanList.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
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

        shoppingCartBeanList.get(position).setChoosed(isChecked);

        if (isAllCheck())
            ck_all.setChecked(true);
        else
            ck_all.setChecked(false);

        shoppingCartAdapter.notifyDataSetChanged();
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
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
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
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();

    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}