package com.dq.huibao.adapter.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.CheckInterface;
import com.dq.huibao.Interface.ModifyCountInterface;
import com.dq.huibao.R;
import com.dq.huibao.bean.cart.Cart;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 购物车数据适配器
 */
public class ShopCartAdapter extends BaseExpandableListAdapter {

    private List<Cart.DataBean> groups;
    private Map<String, List<Cart.DataBean.GoodslistBean>> children;
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
    public ShopCartAdapter(List<Cart.DataBean> groups, Map<String, List<Cart.DataBean.GoodslistBean>> children, Context context) {
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
        String groupId = groups.get(groupPosition).getShopid();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<Cart.DataBean.GoodslistBean> childs = children.get(groups.get(groupPosition).getShopid());
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
            convertView = View.inflate(context, R.layout.item_shop, null);
            gholder = new GroupViewHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
        final Cart.DataBean group = (Cart.DataBean) getGroup(groupPosition);

        gholder.tvSourceName.setText(group.getShopname());
        gholder.determineChekbox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());
                // 暴露组选接口
            }
        });
        gholder.determineChekbox.setChecked(group.isChoosed());

        return convertView;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {

        final ChildViewHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopcar, null);

            cholder = new ChildViewHolder(convertView);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }
//        if (groups.get(groupPosition).isEdtor() == true) {
//            cholder.llEdtor.setVisibility(View.VISIBLE);
//            cholder.rlNoEdtor.setVisibility(View.GONE);
//        } else {
//            cholder.llEdtor.setVisibility(View.GONE);
//            cholder.rlNoEdtor.setVisibility(View.VISIBLE);
//        }
        final Cart.DataBean.GoodslistBean goodsInfo = (Cart.DataBean.GoodslistBean) getChild(groupPosition, childPosition);

//        if(isLastChild&&getChild(groupPosition,childPosition)!=null){
//            cholder.stub.setVisibility(View.VISIBLE);
//              TextView tv= (TextView) cholder.stub.findViewById(R.id.txtFooter);
//                 这里用来动态显示店铺满99元包邮文字内容
//        }else{
//            cholder.stub.setVisibility(View.GONE);
//        }
        if (goodsInfo != null) {

            ImageUtils.loadIntoUseFitWidth(context,
                    HttpUtils.IMG_HEADER + goodsInfo.getGoods().getThumb(),
                    R.mipmap.icon_empty002,
                    R.mipmap.icon_error002,
                    cholder.ivAdapterListPic);

            cholder.tvIntro.setText("" + goodsInfo.getGoods().getGoodsname());
            cholder.tvColorSize.setText("" + goodsInfo.getGoods().getOption().getTitle());
            cholder.tvPrice.setText("" + goodsInfo.getGoods().getOption().getMarketprice());
            cholder.tvProductprice.setText("" + goodsInfo.getGoods().getOption().getProductprice());
            cholder.etNum.setText("" + goodsInfo.getCount());

//            SpannableString spanString = new SpannableString("￥" + String.valueOf(goodsInfo.getDiscountPrice()));
//            StrikethroughSpan span = new StrikethroughSpan();
//            spanString.setSpan(span, 0, String.valueOf(goodsInfo.getDiscountPrice()).length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            //避免无限次的appand
//            if (cholder.tvDiscountPrice.getText().toString().length() > 0) {
//                cholder.tvDiscountPrice.setText("");
//            }
//            cholder.tvDiscountPrice.append(spanString);
//            cholder.tvBuyNum.setText("x" + goodsInfo.getCount());
            cholder.checkBox.setChecked(goodsInfo.isChoosed());
            cholder.checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
                    cholder.checkBox.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            cholder.btAdd.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked(),
                            goodsInfo.getGoodsid(), goodsInfo.getOptionid(), Integer.parseInt(goodsInfo.getCount()));
                    // 暴露增加接口
                }
            });
            cholder.btReduce.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked(),
                            goodsInfo.getGoodsid(), goodsInfo.getOptionid(), Integer.parseInt(goodsInfo.getCount()));
                    // 暴露删减接口
                }
            });
            /********************方案一：弹出软键盘修改数量，应为又不知名的bug会使然键盘强行关闭***********************/
            /****在清单文件的activity下设置键盘：
             android:windowSoftInputMode="adjustUnspecified|stateHidden|adjustPan"
             android:configChanges="orientation|keyboardHidden"****/
//            cholder.etNum.addTextChangedListener(new GoodsNumWatcher(goodsInfo));//监听文本输入框的文字变化，并且刷新数据
//            notifyDataSetChanged();
            /********************方案一***************************************************************************/
            /********************方案二：让软键盘不能弹出，文本框不可编辑弹出dialog修改***********************/
//            cholder.etNum.setOnFocusChangeListener(new android.view.View.
//                    OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {//监听焦点的变化
//                    if (hasFocus) {//获取到焦点也就是文本框被点击修改了
//                        // 1，先强制键盘不弹出
//                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
//                        // 2.显示弹出dialog进行修改
//                        showDialog(goodsInfo,cholder.etNum);
            //3.清除焦点防止不断弹出dialog和软键盘
//                        cholder.etNum.clearFocus();
            // 4. 数据刷型
//                        ShopcartAdapter.this.notifyDataSetChanged();
//                    }
//                }
//            });
            /********************方案二***********************/
            //删除 购物车
            cholder.tvDelete.setOnClickListener(new OnClickListener() {
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
                                    modifyCountInterface.childDelete(groupPosition, childPosition);

                                }
                            });
                    alert.show();

                }
            });

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
        private Cart.DataBean group;

        public GroupViewClick(int groupPosition, Button edtor, Cart.DataBean group) {
            this.groupPosition = groupPosition;
            this.edtor = edtor;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == edtor.getId()) {
//                if (group.isEdtor()) {
//                    group.setIsEdtor(false);
//                } else {
//                    group.setIsEdtor(true);
//
//                }
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 组元素绑定器
     */
    static class GroupViewHolder {
        @Bind(R.id.item_ck_shop)
        CheckBox determineChekbox;
        @Bind(R.id.item_tv_shopname)
        TextView tvSourceName;
//        @BindView(R.id.tv_store_edtor)
//        Button tvStoreEdtor;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 子元素绑定器
     */
    static class ChildViewHolder {
        /*选择*/
        @Bind(R.id.ck_chose)
        CheckBox checkBox;

        /*图片*/
        @Bind(R.id.iv_show_pic)
        ImageView ivAdapterListPic;

        /*名称*/
        @Bind(R.id.tv_commodity_name)
        TextView tvIntro;

        /*规格*/
        @Bind(R.id.tv_cart_option)
        TextView tvColorSize;

        /*售价*/
        @Bind(R.id.tv_price)
        TextView tvPrice;

        /*原价*/
        @Bind(R.id.tv_shopcart_productprice)
        TextView tvProductprice;

        //@BindView(R.id.tv_discount_price)
//        TextView tvDiscountPrice;
//        @BindView(R.id.tv_buy_num)
//        TextView tvBuyNum;
//        @BindView(R.id.rl_no_edtor)
//        RelativeLayout rlNoEdtor;
        @Bind(R.id.lin_shopcart_sub)
        LinearLayout btReduce;
        @Bind(R.id.tv_show_num)
        TextView etNum;
        @Bind(R.id.lin_shopcart_add)
        LinearLayout btAdd;
        @Bind(R.id.tv_delete)
        TextView tvDelete;

        //        @BindView(R.id.ll_change_num)
//        RelativeLayout llChangeNum;
//        @BindView(R.id.tv_colorsize)
//        TextView tvColorsize;
//        @BindView(R.id.tv_goods_delete)
//        TextView tvGoodsDelete;
//        @BindView(R.id.ll_edtor)
//        LinearLayout llEdtor;
//        @BindView(R.id.stub)
//        ViewStub stub;
        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    /**
     * 购物车的数量修改编辑框的内容监听
     */
    class GoodsNumWatcher implements TextWatcher {
        Cart.DataBean.GoodslistBean goodsInfo;

        public GoodsNumWatcher(Cart.DataBean.GoodslistBean goodsInfo) {
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

    /**
     * 显示修改购物车商品数量的dialog
     * @param goodinfo  item的商品信息实体
     * @param edittext   购物车item的数量文本框
     */
//    private void showDialog(final Cart.DataBean.GoodslistBean   goodinfo,final EditText edittext){
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//        View alertDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_change_num, null,false);
//        final AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.setView(alertDialogView);
//        count = goodinfo.getCount();
//        final EditText editText = (EditText) alertDialogView.findViewById(R.id.et_num);
//        editText.setText(""+goodinfo.getCount());//设置dialog的数量初始值
//        //自动弹出软键盘
//        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            public void onShow(DialogInterface dialog) {
//                @SuppressLint("WrongConstant")
//                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
//            }
//        });
//        final   Button btadd= (Button) alertDialogView.findViewById(R.id.bt_add);
//        final   Button btreduce= (Button) alertDialogView.findViewById(R.id.bt_reduce);
//        final   TextView cancle= (TextView) alertDialogView.findViewById(R.id.tv_cancle);
//        final   TextView sure= (TextView) alertDialogView.findViewById(R.id.tv_sure);
//        cancle.setOnClickListener(new OnClickListener() { //取消按钮
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        sure.setOnClickListener(new OnClickListener() {//确定按钮
//            @Override
//            public void onClick(View v) {
//                goodinfo.setCount(count);//重新设置数量
//                edittext.setText(count+"");//购物车界面的文本框显示同步
//                alertDialog.dismiss();
//            }
//        });
//        btadd.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count ++;   //点一下量加1
//                editText.setText(""+count);//动态显示dialog的文本框的数据
//
//            }
//        });
//        btreduce.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(count>1) {//数量大雨1时操作
//                    count--; //点一下减1
//                    editText.setText("" + count);
//                }
//            }
//        });
//        alertDialog.show();
//    }
}