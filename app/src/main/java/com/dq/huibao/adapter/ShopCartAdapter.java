package com.dq.huibao.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.CheckInterface;
import com.dq.huibao.Interface.ModifyCountInterface;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
//import com.dq.huibao.bean.CartOld;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/27.
 */
public class ShopCartAdapter extends RecyclerView.Adapter<ShopCartAdapter.MyViewHolder> {
    private Context mContext;
    //private List<CartOld.DataBean.ListBean> cartList;
    private List<Cart.DataBean.GoodslistBean> goodsList;
    private OnItemClickListener onItemClickListener;

    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;

//    public ShopCartAdapter(Context mContext, List<CartOld.DataBean.ListBean> cartList) {
//        this.mContext = mContext;
//        this.cartList = cartList;
//    }

    public ShopCartAdapter(Context mContext, List<Cart.DataBean.GoodslistBean> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shopcar, parent, false));

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }

        ImageUtils.loadIntoUseFitWidth(mContext,
                HttpUtils.IMG_HEADER + goodsList.get(position).getGoods().getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.iv_show_pic);

        //final CartOld.DataBean.ListBean listBean = cartList.get(position);
        final  Cart.DataBean.GoodslistBean listBean = goodsList.get(position);

        holder.tv_commodity_name.setText(goodsList.get(position).getGoods().getGoodsname());
        holder.tv_price.setText("¥:" + goodsList.get(position).getMarketprice());
//        holder.tvProductPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        holder.tvProductPrice.setText("¥:" + goodsList.get(position));

//        holder.ck_chose.setChecked(shoppingCartBean.isChoosed());
        holder.ck_chose.setChecked(goodsList.get(position).isChoosed());
        holder.tv_show_num.setText("" + goodsList.get(position).getCount());


        //单选框按钮
        holder.ck_chose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listBean.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );

        //增加按钮
        holder.linAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tv_show_num, holder.ck_chose.isChecked(),
                        goodsList.get(position).getId(), goodsList.get(position).getGoodsid(), Integer.parseInt(goodsList.get(position).getCount()));//暴露增加接口
            }
        });

        //删减按钮
        holder.linSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tv_show_num, holder.ck_chose.isChecked(),
                        goodsList.get(position).getId(), goodsList.get(position).getGoodsid(), Integer.parseInt(goodsList.get(position).getCount()));//暴露删减接口
            }
        });


        //删除弹窗
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(mContext).create();
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
                                modifyCountInterface.childDelete(position);//删除 目前只是从item中移除

                            }
                        });
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        ImageView iv_show_pic;
        TextView tv_commodity_name, tv_price, tv_delete, tv_show_num, tvProductPrice;
        CheckBox ck_chose;

        LinearLayout linSub, linAdd;

        public MyViewHolder(View view) {
            super(view);
            ck_chose = (CheckBox) itemView.findViewById(R.id.ck_chose);
            iv_show_pic = (ImageView) itemView.findViewById(R.id.iv_show_pic);

            tv_commodity_name = (TextView) itemView.findViewById(R.id.tv_commodity_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            tv_show_num = (TextView) itemView.findViewById(R.id.tv_show_num);

            tvProductPrice = (TextView) itemView.findViewById(R.id.tv_shopcart_productprice);
            linSub = (LinearLayout) itemView.findViewById(R.id.lin_shopcart_sub);
            linAdd = (LinearLayout) itemView.findViewById(R.id.lin_shopcart_add);
        }
    }

}
