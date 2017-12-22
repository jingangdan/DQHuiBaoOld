package com.dq.huibao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.ClassifyAdapter;
import com.dq.huibao.adapter.ClassifyTwoAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.classify.Classify;
import com.dq.huibao.ui.GoodsListActivity;
import com.dq.huibao.ui.KeywordsActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：分类
 * Created by jingang on 2017/10/18.
 */

public class FMClassify extends BaseFragment {
    @Bind(R.id.rv_c_classify)
    RecyclerView rvCClassify;
    @Bind(R.id.rv_c_goods)
    RecyclerView rvCGoods;
    @Bind(R.id.tv_search)
    TextView tvSearch;

    @Bind(R.id.iv_classify_advimg)
    ImageView ivAdvImg;

    private View view;

    private LinearLayoutManager mLayoutManager, mLayoutManager1;
    private GridLayoutManager llmv;
    private ClassifyAdapter classifyAdapter;
    private ClassifyTwoAdapter classifyTwoAdapter;

    private List<Classify.DataBean> classifyList = new ArrayList<>();
    private List<Classify.DataBean.ChildrenBean> classifytwoList = new ArrayList<>();

    /*跳转页面*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";
    private String pcate = "", ccate = "", name = "";//一级分类id 二级分类id 分类名称
    private RequestParams params;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, null);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvCClassify.setLayoutManager(mLayoutManager);

        llmv = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        rvCGoods.setLayoutManager(llmv);

        /*一级分类*/
        classifyAdapter = new ClassifyAdapter(getActivity(), classifyList);
        rvCClassify.setAdapter(classifyAdapter);

        /*二级分类*/
        classifyTwoAdapter = new ClassifyTwoAdapter(getActivity(), classifytwoList);
        rvCGoods.setAdapter(classifyTwoAdapter);

        classifyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                classifytwoList.clear();

                if (position == 0) {
                    classifytwoList.addAll(classifyList.get(0).getChildren());
                } else {
                    classifytwoList.addAll(classifyList.get(position).getChildren());
                }

                pcate = classifyList.get(position).getId();

                if (!classifyList.get(position).getAdvimg().equals("")) {
                    ImageUtils.loadIntoUseFitWidths(getActivity(),
                            HttpUtils.HEADER + classifyList.get(position).getAdvimg(),
                            R.mipmap.icon_empty002,
                            R.mipmap.icon_error002,
                            ivAdvImg);
                } else {
                    ivAdvImg.setImageResource(R.mipmap.ic_launcher);
                }

                classifyAdapter.changeSelected(position);

                //classifytwoList.addAll(classifyList.get(position).getChildren());

                classifyTwoAdapter.notifyDataSetChanged();

            }
        });

        classifyTwoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ccate = classifytwoList.get(position).getId();
                name = classifytwoList.get(position).getName();
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("pcate", "&pcate=" + pcate);
                intent.putExtra("ccate", "&ccate=" + ccate);
                intent.putExtra("name", name);
                intent.putExtra("keywords", "");
                startActivity(intent);
            }
        });

        getClassify("1604");

        return view;
    }

    /**
     * 获取分类
     *
     * @param i
     */
    public void getClassify(String i) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_CATEGRAY + "i=" + i;
        params = new RequestParams(PATH);
        System.out.println("分类 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("分类 = " + result);

                        final Classify classify = GsonUtil.gsonIntance().gsonToBean(result, Classify.class);

                        classifyList.addAll(classify.getData());
                        classifytwoList.addAll(classify.getData().get(0).getChildren());

                        if (!classify.getData().get(0).getAdvimg().equals("")) {
                            ImageUtils.loadIntoUseFitWidths(getActivity(),
                                    HttpUtils.HEADER + classifyList.get(0).getAdvimg(),
                                    R.mipmap.icon_empty002,
                                    R.mipmap.icon_error002,
                                    ivAdvImg);
                        } else {
                            ivAdvImg.setImageResource(R.mipmap.ic_launcher) ;
                        }

                        pcate = classifyList.get(0).getId();

                        classifyAdapter.notifyDataSetChanged();
                        classifyTwoAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                intent = new Intent(getActivity(), KeywordsActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }


//    PopupWindow pop;
//    View view_pop;
//    LinearLayout ll_popup;
//
//    private AutoCompleteTextView autoCompleteTextView;
//    private TextView tv_cancel;
//    private ImageView iv_pop;
//    private RecyclerView rv_pop;
//
//    private String UTF_search = "";
//
//    private KeywordsAdapter keywordsAdapter;
//    private List<Keywords.DataBean.GoodsBean> keywordsList = new ArrayList<>();
//
//    /*
//    * 搜索弹出框
//    * */
//    public void setDialog() {
//        pop = new PopupWindow(getActivity());
//        view_pop = getActivity().getLayoutInflater().inflate(R.layout.pop_search, null);
//        view_pop.setAnimation(AnimationUtils.loadAnimation(
//                getActivity(), R.anim.slide_bottom_to_top));
//        ll_popup = (LinearLayout) view_pop.findViewById(R.id.lin_pop);
//
//        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        pop.setBackgroundDrawable(new BitmapDrawable());
//        pop.setFocusable(true);
//        pop.setOutsideTouchable(true);
//        pop.setContentView(view_pop);
//        pop.showAsDropDown(view_pop);
//
//        autoCompleteTextView = (AutoCompleteTextView) view_pop.findViewById(R.id.autoCompleteTextView);
//        iv_pop = (ImageView) view_pop.findViewById(R.id.iv_pop_search);
//        tv_cancel = (TextView) view_pop.findViewById(R.id.tv_pop_cancel);
//
//        rv_pop = (RecyclerView) view_pop.findViewById(R.id.rv_pop);
//        rv_pop.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        keywordsAdapter = new KeywordsAdapter(getActivity(), keywordsList);
//
//        rv_pop.setAdapter(keywordsAdapter);
//
//        //取消
//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop.dismiss();
//                ll_popup.clearAnimation();
//            }
//        });
//
//        /**
//         * 当输入关键字变化时，动态更新建议列表
//         */
//        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence arg0, int arg1,
//                                          int arg2, int arg3) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//
//                try {
//                    UTF_search = URLEncoder.encode(autoCompleteTextView.getText().toString(), "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//
//                getSearch(UTF_search);
//
//            }
//        });
//
//        /**/
//        keywordsAdapter.setmOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                toast("商品id = " + keywordsList.get(position).getGoodid());
//            }
//        });
//
//    }
//
//    /**
//     * 获取搜索数据
//     *
//     * @param keywords
//     */
//    public void getSearch(String keywords) {
//        PATH = HttpUtils.PATH + HttpUtils.SHOP_SEARCH + "keywords=" + keywords;
//
//        params = new RequestParams(PATH);
//        System.out.println("搜索 = " + PATH);
//        x.http().get(params,
//                new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
//                        System.out.println("搜索 = " + result);
//
//                        Keywords keywords = GsonUtil.gsonIntance().gsonToBean(result, Keywords.class);
//
//                        keywordsList.clear();
//                        keywordsList.addAll(keywords.getData().getGoods());
//
//                        keywordsAdapter.notifyDataSetChanged();
//
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                });
//
//    }


}
