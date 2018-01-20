package com.dq.huibao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.CheckInterface;
import com.dq.huibao.Interface.ModifyCountInterface;
import com.dq.huibao.R;
import com.dq.huibao.bean.order.Order;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 购物车数据适配器
 */
public class OrderTestAdapter extends BaseExpandableListAdapter {

    private List<Order.DataBean> groups;
    private Map<String, List<Order.DataBean.GoodslistBean>> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    public int flag = 0;

    int count = 0;

    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */
    public OrderTestAdapter(List<Order.DataBean> groups, Map<String, List<Order.DataBean.GoodslistBean>> children, Context context) {
        this.groups = groups;
        this.children = children;
        this.context = context;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = groups.get(groupPosition).getId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<Order.DataBean.GoodslistBean> childs = children.get(groups.get(groupPosition).getId());
        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final GroupViewHolder gholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_order, null);
            gholder = new GroupViewHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
        final Order.DataBean group = (Order.DataBean) getGroup(groupPosition);

        System.out.println("555 = " + group.getStatus());

        gholder.status.setText(group.getStatus());
        gholder.allcount.setText("" + group.getAllcount());
        gholder.pay_money.setText("" + group.getPay_money());

//        gholder.determineChekbox.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                group.setChoosed(((CheckBox) v).isChecked());
//                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());
//                // 暴露组选接口
//            }
//        });
//        gholder.determineChekbox.setChecked(group.isChoosed());

        return convertView;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {

        final ChildViewHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_order_goods, null);

            cholder = new ChildViewHolder(convertView);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }
        final Order.DataBean.GoodslistBean goodsInfo = (Order.DataBean.GoodslistBean) getChild(groupPosition, childPosition);

        if (goodsInfo != null) {

//            ImageUtils.loadIntoUseFitWidth(context,
//                    HttpUtils.IMG_HEADER + goodsInfo.getGoods().getThumb(),
//                    R.mipmap.icon_empty002,
//                    R.mipmap.icon_error002,
//                    cholder.ivAdapterListPic);

            cholder.goodsname.setText("" + goodsInfo.getGoodsname());
            cholder.optionname.setText("" + goodsInfo.getOptionname());
            cholder.price.setText("" + goodsInfo.getPrice());
            cholder.count.setText("" + goodsInfo.getCount());

//            cholder.checkBox.setChecked(goodsInfo.isChoosed());
//            cholder.checkBox.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
//                    cholder.checkBox.setChecked(((CheckBox) v).isChecked());
//                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
//                }
//            });
//            cholder.btAdd.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked(),
//                            goodsInfo.getGoodsid(), goodsInfo.getOptionid(), Integer.parseInt(goodsInfo.getCount()));
//                    // 暴露增加接口
//                }
//            });
//            cholder.btReduce.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked(),
//                            goodsInfo.getGoodsid(), goodsInfo.getOptionid(), Integer.parseInt(goodsInfo.getCount()));
//                    // 暴露删减接口
//                }
//            });
//            //删除 购物车
//            cholder.tvDelete.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog alert = new AlertDialog.Builder(context).create();
//                    alert.setTitle("操作提示");
//                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
//                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                    return;
//                                }
//                            });
//                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    modifyCountInterface.childDelete(groupPosition, childPosition);
//
//                                }
//                            });
//                    alert.show();
//
//                }
//            });

        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;

    }

    /**
     * 使某个组处于编辑状态
     * <p/>
     * groupPosition组的位置
     */
    class GroupViewClick implements OnClickListener {
        private int groupPosition;
        private Button edtor;
        private Order.DataBean group;

        public GroupViewClick(int groupPosition, Button edtor, Order.DataBean group) {
            this.groupPosition = groupPosition;
            this.edtor = edtor;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == edtor.getId()) {
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 组元素绑定器
     */
    static class GroupViewHolder {
        @Bind(R.id.tv_order_status)
        TextView status;
        @Bind(R.id.rv_order)
        RecyclerView recyclerView;
        @Bind(R.id.tv_order_allcount)
        TextView allcount;
        @Bind(R.id.tv_order_pay_money)
        TextView pay_money;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 子元素绑定器
     */
    static class ChildViewHolder {
        @Bind(R.id.iv_order_img)
        ImageView img;
        @Bind(R.id.tv_order_goodsname)
        TextView goodsname;
        @Bind(R.id.tv_order_optionname)
        TextView optionname;
        @Bind(R.id.tv_order_price)
        TextView price;
        @Bind(R.id.tv_order_count)
        TextView count;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    /**
     * 购物车的数量修改编辑框的内容监听
     */
    class GoodsNumWatcher implements TextWatcher {
        Order.DataBean.GoodslistBean goodsInfo;

        public GoodsNumWatcher(Order.DataBean.GoodslistBean goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s.toString())) {//当输入的数字不为空时，更新数字
                goodsInfo.setCount(s.toString().trim());
            }
        }

    }
}
