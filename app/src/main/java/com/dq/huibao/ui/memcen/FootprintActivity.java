package com.dq.huibao.ui.memcen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dq.huibao.R;
import com.dq.huibao.adapter.CollectAdapter;
import com.dq.huibao.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：
 * Created by jingang on 2017/11/1.
 */

public class FootprintActivity extends BaseActivity {
    @Bind(R.id.rv_collect)
    RecyclerView rvCollect;

    private CollectAdapter collectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ButterKnife.bind(this);

        collectAdapter = new CollectAdapter(this);
        rvCollect.setLayoutManager(new LinearLayoutManager(this));
        rvCollect.setAdapter(collectAdapter);

    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("我的收藏");
    }
}
