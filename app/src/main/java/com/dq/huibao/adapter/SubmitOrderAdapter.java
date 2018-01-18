package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.Interface.OnItemLongClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.cart.CheckOrder;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/10/30.
 */
public class SubmitOrderAdapter extends RecyclerView.Adapter<SubmitOrderAdapter.MyViewHolder> {
    private Context mContext;
    private List<CheckOrder.DataBean> shopList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private SOGoodsAdapter soGoodsAdapter;

    public SubmitOrderAdapter(Context mContext, List<CheckOrder.DataBean> shopList) {
        this.mContext = mContext;
        this.shopList = shopList;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_submitorder, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
        holder.shopname.setText("" + shopList.get(position).getShopname());
        holder.num.setText("" + shopList.get(position).getCount_all());
        holder.money.setText("" + shopList.get(position).getMoney_all());
        holder.dispatch.setText("" + shopList.get(position).getDispatch_all());
        holder.discount_all.setText(""+shopList.get(position).getDiscount_all());

        holder.pay_all.setText(""+(shopList.get(position).getMoney_all() - shopList.get(position).getDiscount_all() + shopList.get(position).getDispatch_all()));

        soGoodsAdapter = new SOGoodsAdapter(mContext, shopList.get(position).getGoodslist());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(soGoodsAdapter);

    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView shopname, num, money, dispatch, money_all, discount_all, pay_all;
        private EditText comment;
        RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            shopname = view.findViewById(R.id.tv_checkorder_shopname);
            num = view.findViewById(R.id.tv_checkorder_num);
            money = view.findViewById(R.id.tv_checkorder_money);
            dispatch = view.findViewById(R.id.tv_checkorder_dispatch);
            money_all = view.findViewById(R.id.tv_checkorder_money_all);
            discount_all = view.findViewById(R.id.tv_item_co_discount_all);
            pay_all = view.findViewById(R.id.tv_item_co_pay_all);
            recyclerView = (RecyclerView) view.findViewById(R.id.rv_so_goods);
        }
    }

}
