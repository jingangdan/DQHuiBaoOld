package com.dq.huibao.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.base.BaseActivity;
import com.dq.huibao.bean.order.KuaiDi;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.GsonUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 查看物流
 * Created by jingang on 2018/1/20.
 */

public class OrderKuaiDiActivity extends BaseActivity {
    @Bind(R.id.tv_kd_com)
    TextView tvKdCom;
    @Bind(R.id.tv_kd_nu)
    TextView tvKdNu;
    @Bind(R.id.rv_kd_goods)
    RecyclerView rvKdGoods;
    @Bind(R.id.rv_kd_data)
    RecyclerView rvKdData;
    /*接受页面传值*/
    private Intent intent;
    private String type = "", postid = "";

    /*接口地址*/
    private String PATH = "";
    private RequestParams params = null;

    private String com, nu;
    private List<KuaiDi.DataBean> dataList = new ArrayList<>();
    private KuaiDiAdapter kuaiDiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaidi);
        ButterKnife.bind(this);
        kuaiDiAdapter = new KuaiDiAdapter(this, dataList);
        rvKdData.setLayoutManager(new LinearLayoutManager(this));
        rvKdData.setAdapter(kuaiDiAdapter);

        intent = getIntent();
        type = intent.getStringExtra("type");
        postid = intent.getStringExtra("postid");

        getKuaidi(type, postid);
    }

    @Override
    protected void initWidght() {
        super.initWidght();
        setTitleName("查看物流");
    }

    /**
     * 获取物流
     *
     * @param type
     * @param postid
     */
    public void getKuaidi(String type, String postid) {
        PATH = "https://www.kuaidi100.com/query?type=" + type + "&postid=" + postid;
        params = new RequestParams(PATH);
        System.out.println("获取物流 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("获取物流 = " + result);
                        KuaiDi kuaiDi = GsonUtil.gsonIntance().gsonToBean(result, KuaiDi.class);
                        com = kuaiDi.getCom();
                        nu = kuaiDi.getNu();

                        dataList.clear();
                        dataList.addAll(kuaiDi.getData());
                        kuaiDiAdapter.notifyDataSetChanged();

                        setUI();
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

    private void setUI() {
        tvKdCom.setText("" + com);
        tvKdNu.setText("运单编号：" + nu);
    }

    public class KuaiDiAdapter extends RecyclerView.Adapter<KuaiDiAdapter.MyViewHolder> {
        private Context mContext;
        private List<KuaiDi.DataBean> dataList;

        public KuaiDiAdapter(Context mContext, List<KuaiDi.DataBean> dataList) {
            this.mContext = mContext;
            this.dataList = dataList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder vh = new MyViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item_kuaidi, viewGroup, false)
            );
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int i) {
            holder.context.setText("" + dataList.get(i).getContext());
            holder.time.setText("" + dataList.get(i).getTime());
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MyViewHolder extends BaseRecyclerViewHolder {
            private TextView context, time;

            public MyViewHolder(View view) {
                super(view);
                context = view.findViewById(R.id.tv_item_kd_context);
                time = view.findViewById(R.id.tv_item_kd_time);
            }
        }
    }

}
