package com.dq.huibao.adapter.gd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dq.huibao.R;
//import com.dq.huibao.bean.goodsdetail.Params;
import com.dq.huibao.bean.goods.GoodsDetail;
import com.dq.huibao.utils.BaseRecyclerViewHolder;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class GdParmasAdapter extends RecyclerView.Adapter<GdParmasAdapter.MyViewHolder> {
    private Context mContext;
    //private List<Params> paramsList;
    private List<GoodsDetail.DataBean.ParamBean> paramsList;

    //    public GdParmasAdapter(Context mContext, List<Params> paramsList) {
//        this.mContext = mContext;
//        this.paramsList = paramsList;
//    }
    public GdParmasAdapter(Context mContext, List<GoodsDetail.DataBean.ParamBean> paramsList) {
        this.mContext = mContext;
        this.paramsList = paramsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gd_parmar, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_title.setText("" + paramsList.get(position).getTitle() + "：");
        holder.tv_value.setText("" + paramsList.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return paramsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private TextView tv_title, tv_value;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_item_gd_title);
            tv_value = (TextView) view.findViewById(R.id.tv_item_gd_value);
        }
    }
}
