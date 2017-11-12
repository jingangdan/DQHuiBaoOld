package com.dq.huibao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dq.huibao.R;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.view.goodsdetails.PictrueFragment;
import com.dq.huibao.view.HackyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示大图界面
 *
 * @author http://yecaoly.taobao.com
 *         <p>
 *         1
 */
public class ShowBigPictrueActivity extends FragmentActivity {

    private HackyViewPager viewPager;
//    private int[] resId = {R.mipmap.detail_show_1, R.mipmap.detail_show_2, R.mipmap.detail_show_3,
//            R.mipmap.detail_show_4, R.mipmap.detail_show_5, R.mipmap.detail_show_6};
    /**
     * 得到上一个界面点击图片的位置
     */
    private Intent intent;
    private int position = 0;
    private String pics = "";
    private List<String> picsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_big_pictrue_a);
        intent = getIntent();

        position = intent.getIntExtra("position", 0);
        pics = intent.getStringExtra("picslist");

        picsList = GoodsDetailsActivity.picsList;

        initViewPager();
    }

    private void initViewPager() {

        viewPager = (HackyViewPager) findViewById(R.id.viewPager_show_bigPic);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //跳转到第几个界面
        viewPager.setCurrentItem(position);

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //int show_resId = resId[position];
            String show_resId = HttpUtils.HEADER + picsList.get(position).toString();
            System.out.println("88888 = " + show_resId);
            return new PictrueFragment(show_resId);
        }

        @Override
        public int getCount() {
            return picsList.size();
            //return resId.length;
        }


    }

}
