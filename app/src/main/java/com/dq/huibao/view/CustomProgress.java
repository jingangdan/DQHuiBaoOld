package com.dq.huibao.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;

/**
 * dialog样式
 * Created by jingang on 2017/7/19.
 */
public class CustomProgress extends Dialog {
    private Context context = null;
    private static CustomProgress customProgressDialog = null;

    public CustomProgress(Context context) {
        super(context);
        this.context = context;
    }

    public CustomProgress(Context context, int theme) {
        super(context, theme);
    }

    public static CustomProgress createDialog(Context context) {
        customProgressDialog = new CustomProgress(context, R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.progress_custom);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        customProgressDialog.setCanceledOnTouchOutside(false);

        return customProgressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        if (customProgressDialog == null) {
            return;
        }

        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }

    /**
     * [Summary]
     * setTitile 标题
     *
     * @param strTitle
     * @return
     */
    public CustomProgress setTitile(String strTitle) {
        return customProgressDialog;
    }

    /**
     * [Summary]
     * setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public CustomProgress setMessage(String strMessage) {
        TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.id_tv_loadingmsg);

        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return customProgressDialog;
    }
}