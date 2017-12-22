package com.dq.huibao.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.homepage.Goods;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/5.
 */
public class HpGoodsAdapter extends RecyclerView.Adapter<HpGoodsAdapter.MyViewHolder> {
    private Context mContext;
    private List<Goods.DataBeanX.DataBean> goodsList;
    private OnItemClickListener onItemClickListener;

    public HpGoodsAdapter(Context mContext, List<Goods.DataBeanX.DataBean> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_hp_goods, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }

        String img = goodsList.get(position).getImg();

//        ImageUtils.loadIntoUseFitWidth(mContext,
//                img,
//                R.mipmap.icon_empty002,
//                R.mipmap.icon_error002,
//                holder.img);

        /*等比例显示图片*/
        ImageUtils.loadIntoUseFitWidths(mContext,
                img,
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.img);

        holder.tv_name.setText("" + goodsList.get(position).getName());

        holder.tv_pricenow.setText("¥" + goodsList.get(position).getPricenow());
        holder.tv_priceold.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tv_priceold.setText("¥" + goodsList.get(position).getPriceold());


    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private TextView tv_name, tv_pricenow, tv_priceold;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_item_hp_img);
            tv_name = (TextView) view.findViewById(R.id.tv_item_hp_name);
            tv_pricenow = (TextView) view.findViewById(R.id.tv_item_hp_tv_pricenow);
            tv_priceold = (TextView) view.findViewById(R.id.tv_item_hp_priceold);
        }
    }
}
