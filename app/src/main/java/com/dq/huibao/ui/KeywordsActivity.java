package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.KeywordsAdapter;
import com.dq.huibao.bean.classifytest.Keywords;
import com.dq.huibao.sqlite.RecordSQLiteOpenHelper;
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
    @Bind(R.id.iv_search_back)
    ImageView ivSearchBack;
    @Bind(R.id.et_search_keywords)
    EditText etSearchKeywords;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.lv_search)
    ListView lvSearch;
    @Bind(R.id.but_clear_history)
    Button butClearHistory;

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

    private RecordSQLiteOpenHelper helper = new RecordSQLiteOpenHelper(this);
    ;
    private SQLiteDatabase db;
    private BaseAdapter adapter;

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
                intent.putExtra("gid", "&id=" + keywordsList.get(position).getGoodid());
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

        // 搜索框的文本变化实时监听
        etSearchKeywords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    //tv_tip.setText("搜索历史");
                } else {
                    //tv_tip.setText("搜索结果");
                }
                String tempName = etSearchKeywords.getText().toString();
                // 根据tempName去模糊查询数据库中有没有数据
                queryData(tempName);

            }
        });

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                String name = textView.getText().toString();
                etSearchKeywords.setText(name);
                //Toast.makeText(SearchActivity.this, name, Toast.LENGTH_SHORT).show();
                // TODO 获取到item上面的文字，根据该关键字跳转到另一个页面查询，由你自己去实现

                intent = new Intent(KeywordsActivity.this, GoodsListActivity.class);
                intent.putExtra("content", "cate=");
                intent.putExtra("catename", etSearchKeywords.getText().toString());
                intent.putExtra("keywords", etSearchKeywords.getText().toString());
                startActivity(intent);
            }
        });

        queryData("");

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.tv_pop_cancel,
            R.id.iv_search_back, R.id.iv_search, R.id.but_clear_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pop_cancel:
                this.finish();
                break;

            case R.id.iv_search_back:
                this.finish();
                break;

            case R.id.iv_search:
                //搜索
                if (!(etSearchKeywords.getText().toString().equals(""))) {

                    boolean hasData = hasData(etSearchKeywords.getText().toString().trim());
                    if (!hasData) {
                        insertData(etSearchKeywords.getText().toString().trim());
                        queryData("");
                    }

                    intent = new Intent(KeywordsActivity.this, GoodsListActivity.class);
                    intent.putExtra("content", "cate=");
                    intent.putExtra("catename", etSearchKeywords.getText().toString());
                    intent.putExtra("keywords", etSearchKeywords.getText().toString());
                    startActivity(intent);

//                    intent = new Intent(SearchActivity.this, SearchListActivity.class);
//                    intent.putExtra("searchvalue", searchvalue);
//                    intent.putExtra("lat", latitude);
//                    intent.putExtra("lng", longtitude);
//                    intent.putExtra("shopid", shopid);
//                    startActivity(intent);
//                    this.finish();

                } else {
                    Toast.makeText(this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
//                    toast("请输入搜索内容");
                }
                break;

            case R.id.but_clear_history:
                //清空历史记录
                deleteData();
                queryData("");
                break;

            default:
                break;
        }
    }

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"name"},
                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        // 设置适配器
        lvSearch.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * 检查数据库中是否已经有该条记录
     */
    private boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 清空数据
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
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
