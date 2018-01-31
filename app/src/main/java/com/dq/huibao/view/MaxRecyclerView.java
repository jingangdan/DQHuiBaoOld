package com.dq.huibao.view;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

/**
 * Created by asus on 2018/1/28.
 */

public class MaxRecyclerView extends RecyclerView {

    public MaxRecyclerView(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxRecyclerView(android.content.Context context) {
        super(context);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        @SuppressLint("WrongConstant") int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
