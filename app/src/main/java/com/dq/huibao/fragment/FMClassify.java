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
import com.dq.huibao.adapter.classify.ClassifyAdapter;
import com.dq.huibao.adapter.classify.ClassifyTwoAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.goods.Cate;
import com.dq.huibao.bean.goods.CateChildren;
import com.dq.huibao.bean.classifytest.Classify;
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
    @Bind(R.id.tv_search)
    TextView tvSearch;

    @Bind(R.id.rv_c_classify_three)
    RecyclerView rvCClassifyThree;

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
    private RequestParams params;

    private List<Cate.DataBean> cateList = new ArrayList<>();
    private List<CateChildren.DataBean> cateChildrenList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, null);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        rvCClassify.setLayoutManager(mLayoutManager);

        llmv = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        rvCClassifyThree.setLayoutManager(llmv);

        /*一级分类*/
        classifyAdapter = new ClassifyAdapter(getActivity(), cateList);
        rvCClassify.setAdapter(classifyAdapter);

        /*二级分类*/
        classifyTwoAdapter = new ClassifyTwoAdapter(getActivity(), cateChildrenList);
        rvCClassifyThree.setAdapter(classifyTwoAdapter);

        classifyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                classifyAdapter.changeSelected(position);
                cateChildrenList.clear();
                getCateChildren(cateList.get(position).getId());

            }
        });
        getClassify();
        return view;
    }

    /**
     * 获取分类
     */
    public void getClassify() {
        PATH = HttpUtils.PATHS + HttpUtils.GOODS_CATE;
        params = new RequestParams(PATH);
        System.out.println("分类 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("分类 = " + result);
                        Cate cate = GsonUtil.gsonIntance().gsonToBean(result, Cate.class);
                        cateList.addAll(cate.getData());
                        classifyAdapter.notifyDataSetChanged();

                        getCateChildren(cateList.get(0).getId());

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

    /**
     * 获取子分类
     *
     * @param cateId 顶级分类id
     */
    public void getCateChildren(String cateId) {
        PATH = HttpUtils.PATHS + HttpUtils.GOODS_CATECHILDREN + "id=" + cateId;
        params = new RequestParams(PATH);
        System.out.println("子分类 = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("子分类 = " + result);
                        CateChildren cateChildren = GsonUtil.gsonIntance().gsonToBean(result, CateChildren.class);

                        cateChildrenList.addAll(cateChildren.getData());
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

}
