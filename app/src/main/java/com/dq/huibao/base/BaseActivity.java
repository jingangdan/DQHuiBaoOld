package com.dq.huibao.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

/**
 * Description：
 * Created by jingang on 2017/10/26.
 */
public abstract class BaseActivity extends AppCompatActivity {
    /*Toast*/
    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定制流程
        setActivityState(this);
        initView(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 设置屏幕只能竖屏
     * @param activity
     *                  activity
     */
    @SuppressLint("WrongConstant")
    public void setActivityState(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 检测网络是否连接
     */
//    public boolean isNetConnect(){
//        return NetUtil.isNetConnect(this); // NetUtil 是我自己封装的类
//    }

    /**
     * As "Toast.makeText(context, text, duration).show()"
     *
     * @param text The message you wanna show.
     * @return Toast
     */
    @SuppressLint("WrongConstant")
    protected Toast toast(Object text) {
        if (mToast == null) {
            mToast = Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text.toString());
        }
        mToast.show();
        return mToast;
    }

    /**
     * <strong>AlertDialog with one button.</strong><br>
     * The difference between the method "alert" and "toast" is
     * that "alert" would get focus automatically, and won't
     * disappear until you click the button.
     *
     * @param message The message you wanna show.
     * @return AlertDialog
     */
    protected AlertDialog alert(Object message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message.toString());
        builder.setPositiveButton("确定", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return alertDialog;
    }

}