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

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.ClassifyAdapter;
import com.dq.huibao.adapter.ClassifyTwoAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.classify.Classify;
import com.dq.huibao.ui.GoodsListActivity;
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
 * Description：分类
 * Created by jingang on 2017/10/18.
 */

public class FMClassify extends BaseFragment {
    @Bind(R.id.rv_c_classify)
    RecyclerView rvCClassify;
    @Bind(R.id.rv_c_goods)
    RecyclerView rvCGoods;

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

                classifyAdapter.changeSelected(position);

                classifytwoList.addAll(classifyList.get(position).getChildren());

                classifyTwoAdapter.notifyDataSetChanged();

            }
        });

        classifyTwoAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ccate = classifytwoList.get(position).getId();
                name = classifytwoList.get(position).getName();
                intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("pcate", pcate);
                intent.putExtra("ccate", ccate);
                intent.putExtra("name", name);
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
}
