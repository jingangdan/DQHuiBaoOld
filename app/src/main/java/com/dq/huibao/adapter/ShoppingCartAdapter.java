package com.dq.huibao.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.dq.huibao.R;
import com.dq.huibao.bean.ShoppingCartBean;

import java.util.List;

/**
 * Created by AYD on 2016/11/21.
 * <p/>
 * 购物车Adapter
 */
public class ShoppingCartAdapter extends BaseAdapter {

    //private boolean isShow = true;//是否显示编辑/完成
    private List<ShoppingCartBean> shoppingCartBeanList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private Context context;

    public ShoppingCartAdapter(Context context) {
        this.context = context;
    }

    public void setShoppingCartBeanList(List<ShoppingCartBean> shoppingCartBeanList) {
        this.shoppingCartBeanList = shoppingCartBeanList;
        notifyDataSetChanged();
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
    public int getCount() {
        return shoppingCartBeanList == null ? 0 : shoppingCartBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return shoppingCartBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /**
     * 是否显示可编辑
     *
     * @param
     */
//    public void isShow(boolean flag) {
//        isShow = flag;
//        notifyDataSetChanged();
//    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopcar, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        holder.tv_commodity_name.setText(shoppingCartBean.getShoppingName());
        holder.tv_price.setText("￥:" + shoppingCartBean.getPrice());
        holder.ck_chose.setChecked(shoppingCartBean.isChoosed());
        holder.tv_show_num.setText(shoppingCartBean.getCount() + "");

        //单选框按钮
        holder.ck_chose.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shoppingCartBean.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );

        //增加按钮
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露增加接口
            }
        });

        //删减按钮
        holder.iv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.tv_show_num, holder.ck_chose.isChecked());//暴露删减接口
            }
        });


        //删除弹窗
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = new AlertDialog.Builder(context).create();
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

        //判断是否在编辑状态下
//        if (isShow) {
//            holder.tv_commodity_name.setVisibility(View.VISIBLE);
//            holder.tv_fabric.setVisibility(View.VISIBLE);
//            holder.rl_edit.setVisibility(View.GONE);
//            holder.tv_delete.setVisibility(View.GONE);
//        } else {
//            holder.tv_commodity_name.setVisibility(View.GONE);
//            holder.tv_fabric.setVisibility(View.GONE);
//            holder.rl_edit.setVisibility(View.VISIBLE);
//            holder.tv_delete.setVisibility(View.VISIBLE);
//        }

        return convertView;
    }


    //初始化控件
    class ViewHolder {
        ImageView iv_chose;
        ImageView iv_show_pic, iv_sub, iv_add;
        TextView tv_commodity_name, tv_price, tv_delete, tv_show_num;
        CheckBox ck_chose;

        public ViewHolder(View itemView) {
            ck_chose = (CheckBox) itemView.findViewById(R.id.ck_chose);
            iv_show_pic = (ImageView) itemView.findViewById(R.id.iv_show_pic);
            iv_sub = (ImageView) itemView.findViewById(R.id.iv_sub);
            iv_add = (ImageView) itemView.findViewById(R.id.iv_add);

            tv_commodity_name = (TextView) itemView.findViewById(R.id.tv_commodity_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
            tv_show_num = (TextView) itemView.findViewById(R.id.tv_show_num);

        }

    }


    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      组元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param position
         */
        void childDelete(int position);
    }
}
