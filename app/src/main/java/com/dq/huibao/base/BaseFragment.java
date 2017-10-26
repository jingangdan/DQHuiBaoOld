package com.dq.huibao.base;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

/**
 * Created by jingang on 2017/10/18.
 * fragment 基类
 */

public abstract class BaseFragment extends Fragment{

    protected boolean isVisible;
    private Toast mToast;
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible(){}

    /**
     * As "Toast.makeText(context, text, duration).show()"
     *
     * @param text The message you wanna show.
     * @return Toast
     */
    @SuppressLint("WrongConstant")
    protected Toast toast(Object text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text.toString(), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text.toString());
        }
        mToast.show();
        return mToast;
    }


}
