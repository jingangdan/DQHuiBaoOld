package com.dq.huibao.homepage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jingang on 2018/1/29.
 */

public class FMHomePageOld extends BaseFragment {
    @Bind(R.id.lv_home_listview)
    RecyclerView recyclerView;
    private View view;

    private String PATH = "";
    private RequestParams params = null;

    private HomeRecycleViewAdapter homeRecycleAdapter;

    List<Index2.DataBean> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_homepage_test, null);
        ButterKnife.bind(this, view);

        getIndex();

        return view;
    }


    /**
     * 获取首页信息
     * <p>
     * -url链接
     * article文章
     * cate分类
     * goods商品
     * custom自定义分类
     * articlecate文章分类
     * search  搜索
     * url # 不做操作
     */
    public void getIndex() {
        PATH = HttpUtils.PATHS + HttpUtils.INDEXT_INDEX + "2";
        params = new RequestParams(PATH);
        System.out.println("首页 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("首页 = " + result);
                        Index2 index2 = GsonUtil.gsonIntance().gsonToBean(result, Index2.class);
                        dataList.clear();
                        dataList.addAll(index2.getData());

                        System.out.println("111 = " + dataList.size());

                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        homeRecycleAdapter = new HomeRecycleViewAdapter(getActivity(), dataList);
                        recyclerView.setAdapter(homeRecycleAdapter);

                       // homeRecycleAdapter.notifyDataSetChanged();
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
}
