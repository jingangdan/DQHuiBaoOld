package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.KeywordsAdapter;
import com.dq.huibao.bean.classifytest.Keywords;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：建议搜索
 * Created by jingang on 2017/11/8.
 */

public class KeywordsActivity extends Activity {

    @Bind(R.id.lin_keywords)
    LinearLayout linKeywords;

    private KeywordsActivity TAG = KeywordsActivity.this;

    @Bind(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @Bind(R.id.iv_pop_search)
    ImageView ivPopSearch;
    @Bind(R.id.tv_pop_cancel)
    TextView tvPopCancel;
    @Bind(R.id.rv_keywords)
    RecyclerView rvKeywords;


    private String UTF_search = "";

    private KeywordsAdapter keywordsAdapter;
    private List<Keywords.DataBean.GoodsBean> keywordsList = new ArrayList<>();

    /*接口地址*/
    private String PATH = "";
    private RequestParams params;

    /*页面传值*/
    private Intent intent;
    private String goodid = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywords);
        ButterKnife.bind(this);

        rvKeywords.setLayoutManager(new LinearLayoutManager(this));

        keywordsAdapter = new KeywordsAdapter(this, keywordsList);

        rvKeywords.setAdapter(keywordsAdapter);

        /**
         * 当输入关键字变化时，动态更新建议列表
         */
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                try {
                    UTF_search = URLEncoder.encode(autoCompleteTextView.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                getSearch(UTF_search);

            }
        });

        /**/
        keywordsAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //toast("商品id = " + keywordsList.get(position).getGoodid());
//                intent = new Intent(TAG, GoodsDetailsActivity.class);
//                startActivity(intent);
                intent = new Intent(TAG, GoodsDetailsActivity.class);
                intent.putExtra("gid", "&id="+keywordsList.get(position).getGoodid());
                startActivity(intent);
            }
        });

        /*列表被滑动时隐藏软键盘*/
        rvKeywords.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mScrollThreshold;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
                if (isSignificantDelta) {
                    @SuppressLint("WrongConstant")
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);
                }
            }
        });

    }

    @OnClick(R.id.tv_pop_cancel)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pop_cancel:
                this.finish();
                break;

            default:
                break;
        }
    }


    /**
     * 获取搜索数据
     *
     * @param keywords
     */
    public void getSearch(String keywords) {
        PATH = HttpUtils.PATH + HttpUtils.SHOP_SEARCH + "keywords=" + keywords;

        params = new RequestParams(PATH);
        System.out.println("搜索 = " + PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("搜索 = " + result);

                        Keywords keywords = GsonUtil.gsonIntance().gsonToBean(result, Keywords.class);

                        keywordsList.clear();
                        keywordsList.addAll(keywords.getData().getGoods());

                        keywordsAdapter.notifyDataSetChanged();

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
}
