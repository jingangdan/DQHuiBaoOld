package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.classify.ClassifyAdapter;
import com.dq.huibao.adapter.classify.ClassifyTwoAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.goods.Cate;
import com.dq.huibao.bean.goods.CateChildren;
import com.dq.huibao.ui.KeywordsActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpPath;
import com.dq.huibao.utils.HttpxUtils;
import com.dq.huibao.view.CustomProgress;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

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
    @Bind(R.id.but_refresh)
    Button butRefresh;
    @Bind(R.id.lin_hp_nonetwork)
    LinearLayout linHpNonetwork;
    @Bind(R.id.lin_fc_nonetwork)
    LinearLayout linFcNonetwork;

    private View view;
    private CustomProgress progressDialog = null;

    private ClassifyAdapter classifyAdapter;
    private ClassifyTwoAdapter classifyTwoAdapter;

    /*跳转页面*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";
    //private RequestParams params;

    private List<Cate.DataBean> cateList = new ArrayList<>();
    private List<CateChildren.DataBean> cateChildrenList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_classify, null);
        ButterKnife.bind(this, view);

        rvCClassify.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCClassifyThree.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        startProgressDialog();
        getClassify();
        return view;
    }

    /**
     * 获取分类
     */
    public void getClassify() {
        PATH = HttpPath.PATHS + HttpPath.GOODS_CATE;
        System.out.println("分类 = " + PATH);
        HttpxUtils.Get(PATH, null, new Callback.CommonCallback<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(String result) {
                System.out.println("分类 = " + result);
                stopProgressDialog();
                linHpNonetwork.setVisibility(View.GONE);
                linFcNonetwork.setVisibility(View.VISIBLE);
                Cate cate = GsonUtil.gsonIntance().gsonToBean(result, Cate.class);
                cateList.addAll(cate.getData());
                classifyAdapter.notifyDataSetChanged();

                getCateChildren(cateList.get(0).getId());
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                stopProgressDialog();
                linFcNonetwork.setVisibility(View.GONE);
                linHpNonetwork.setVisibility(View.VISIBLE);
                //toast("网络不佳，请重试");
                //toast(ex.getMessage());
                if (ex instanceof HttpException) {
                    //网络错误
                    HttpException httpEx = (HttpException) ex;
                    int responseCode = httpEx.getCode();
                    String responseMsg = httpEx.getMessage();
                    String errorResult = httpEx.getResult();
                    toast("" + responseMsg);

                } else {
                    //其他错误
                    toast("网络不佳，请重试");
                }
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
        PATH = HttpPath.PATHS + HttpPath.GOODS_CATECHILDREN + "id=" + cateId;
        System.out.println("子分类 = " + PATH);
        HttpxUtils.Get(PATH, null, new Callback.CommonCallback<String>() {
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

    @OnClick({R.id.tv_search, R.id.but_refresh})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_refresh:
                getClassify();
                break;
            case R.id.tv_search:
                intent = new Intent(getActivity(), KeywordsActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    /*开始dialog*/
    private void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgress.createDialog(getActivity());
            progressDialog.setMessage("请稍候...");
        }
        progressDialog.show();
    }

    /*结束dialog*/
    private void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
