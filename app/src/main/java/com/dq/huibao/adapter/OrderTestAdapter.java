package com.dq.huibao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.order.Order;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.ImageUtils;

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

        gholder.status.setText(group.getStatus());
        gholder.allcount.setText("" + group.getAllcount());
        gholder.pay_money.setText("" + group.getPay_money());

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

            ImageUtils.loadIntoUseFitWidth(context,
                    HttpPath.IMG_HEADER + goodsInfo.getThumb(),
                    R.mipmap.icon_empty002,
                    R.mipmap.icon_error002,
                    cholder.img);

            cholder.goodsname.setText("" + goodsInfo.getGoodsname());
            cholder.optionname.setText("" + goodsInfo.getOptionname());
            cholder.price.setText("" + goodsInfo.getPrice());
            cholder.count.setText("" + goodsInfo.getCount());

        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;

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
}
