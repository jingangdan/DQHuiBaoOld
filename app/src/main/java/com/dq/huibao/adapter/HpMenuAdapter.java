package com.dq.huibao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.bean.homepage.Menu;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/4.
 */

public class HpMenuAdapter extends RecyclerView.Adapter<HpMenuAdapter.MyViewHolder> {
    private Context mContext;
    private List<Menu.DataBeanX.DataBean> menuList;
    private OnItemClickListener onItemClickListener;

    public HpMenuAdapter(Context mContext, List<Menu.DataBeanX.DataBean> menuList) {
        this.mContext = mContext;
        this.menuList = menuList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_goods, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });

        }

        String img = menuList.get(position).getImgurl();

//        ImageUtils.loadIntoUseFitWidth(mContext,
//                img,
//                R.mipmap.icon_empty002,
//                R.mipmap.icon_error002,
//                holder.img);

        Glide.with(mContext)
                .load(img)
                .placeholder(R.mipmap.icon_empty)
                .error(R.mipmap.icon_error)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.img);

//        if (img.equals("")) {
//            holder.img.setImageResource(R.mipmap.ic_launcher);
//
//        } else {
//            x.image().bind(holder.img, img);
//        }

        holder.text.setText("" + menuList.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView img;
        private TextView text;

        public MyViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.iv_item_goods_img);
            text = (TextView) view.findViewById(R.id.tv_item_goods_name);
        }
    }
}
