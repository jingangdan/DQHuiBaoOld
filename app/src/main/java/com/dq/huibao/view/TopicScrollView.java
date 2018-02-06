package com.dq.huibao.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by asus on 2018/1/29.
 */

public class TopicScrollView extends ScrollView {

    private int downX;

    private int downY;

    private int mTouchSlop;

    public TopicScrollView(Context context) {

        super(context);

        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }


    public TopicScrollView(Context context, AttributeSet attrs) {

        super(context, attrs);

        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }


    public TopicScrollView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }


    @Override

    public boolean onInterceptTouchEvent(MotionEvent e) {

        int action = e.getAction();

        switch (action) {

            case MotionEvent.ACTION_DOWN:

                downX = (int) e.getRawX();

                downY = (int) e.getRawY();

                break;

            case MotionEvent.ACTION_MOVE:

                int moveY = (int) e.getRawY();

                if (Math.abs(moveY - downY) > mTouchSlop) {

                    return true;

                }

        }

        return super.onInterceptTouchEvent(e);

    }

}
