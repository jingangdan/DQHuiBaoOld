package com.dq.huibao.lunbotu;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.dq.huibao.R;
import com.dq.huibao.utils.ImageUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * ImageView创建工厂
 */
public class ViewFactory {

    /**
     * 获取ImageView视图的同时加载显示url
     *
     * @return
     */
    public static ImageView getImageView(Context context, String url) {
        ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(
                R.layout.view_banner, null);
       // ImageLoader.getInstance().displayImage(url, imageView);

        ImageUtils.loadIntoUseFitWidth(context,
                url,
                R.mipmap.icon_empty,
                R.mipmap.icon_error,
                imageView);


        return imageView;
    }
}
