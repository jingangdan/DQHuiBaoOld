package com.dq.huibao.rollpagerview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dq.huibao.R;
import com.dq.huibao.bean.index.Index;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Created by asus on 2018/2/2.
 */

public class ImageLoopAdapter extends LoopPagerAdapter {
    private Context mContext;
    private List<Index.DataBean.BannerBean> bannerBeans;

    public ImageLoopAdapter(RollPagerView viewPager, Context mContext, List<Index.DataBean.BannerBean> bannerBeans) {
        super(viewPager);
        this.mContext = mContext;
        this.bannerBeans = bannerBeans;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ImageUtils.loadIntoUseFitWidths(mContext,
                HttpUtils.NEW_HEADER + bannerBeans.get(position).getThumb(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                view);

        return view;
    }

    @Override
    public int getRealCount() {
        return bannerBeans.size();
    }
}
