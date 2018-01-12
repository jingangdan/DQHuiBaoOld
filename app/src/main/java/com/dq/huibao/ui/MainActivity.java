package com.dq.huibao.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.fragment.FMClassify;
import com.dq.huibao.fragment.FMDistribution;
//import com.dq.huibao.fragment.FMHomePageOld;
import com.dq.huibao.fragment.FMHomePage;
import com.dq.huibao.fragment.FMMemCen;
import com.dq.huibao.fragment.FMShopcar;
import com.dq.huibao.fragment.FMStore;

import org.xutils.BuildConfig;
import org.xutils.x;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 惠宝商城主界面
 */
@SuppressLint("Registered")
public class MainActivity extends FragmentActivity {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.iv_home_page)
    ImageView ivHomePage;
    @Bind(R.id.tv_home_page)
    TextView tvHomePage;
    @Bind(R.id.lin_home_page)
    LinearLayout linHomePage;
    @Bind(R.id.lin_footer)
    LinearLayout linFooter;

    //使用Fragment组件  简单开启事务
    FragmentManager fm;
    FragmentTransaction ft;

    @Bind(R.id.iv_classify)
    ImageView ivClassify;
    @Bind(R.id.tv_classify)
    TextView tvClassify;
    @Bind(R.id.lin_classify)
    LinearLayout linClassify;
    @Bind(R.id.iv_shopcar)
    ImageView ivShopcar;
    @Bind(R.id.tv_shopcar)
    TextView tvShopcar;
    @Bind(R.id.lin_shopcar)
    LinearLayout linShopcar;
    @Bind(R.id.iv_members_center)
    ImageView ivMembersCenter;
    @Bind(R.id.tv_members_center)
    TextView tvMembersCenter;
    @Bind(R.id.lin_members_center)
    LinearLayout linMembersCenter;
    @Bind(R.id.iv_hp_store)
    ImageView ivHpStore;
    @Bind(R.id.tv_hp_store)
    TextView tvHpStore;
    @Bind(R.id.lin_hp_store)
    LinearLayout linHpStore;
    @Bind(R.id.iv_store)
    ImageView ivStore;
    @Bind(R.id.tv_store)
    TextView tvStore;
    @Bind(R.id.lin_store)
    LinearLayout linStore;
    @Bind(R.id.iv_distribution)
    ImageView ivDistribution;
    @Bind(R.id.tv_distribution)
    TextView tvDistribution;
    @Bind(R.id.lin_distribution)
    LinearLayout linDistribution;

    /*Fragment*/
    //private FMHomePageOld fmHomePage;
    private FMHomePage fmHomePage;
    private FMClassify fmClassify;
    private FMShopcar fmShopcar;
    private FMMemCen fmMemCen;

    private FMStore fmStore;
    private FMDistribution fmDistribution;

    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainActivity = this;

        x.Ext.init(this.getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);


        fm = getSupportFragmentManager();
        setTabSelection(0);

    }

    @SuppressLint("WrongConstant")
    @OnClick({R.id.lin_home_page, R.id.lin_classify, R.id.lin_shopcar, R.id.lin_members_center,
            R.id.lin_hp_store, R.id.lin_store, R.id.lin_distribution})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_home_page:
                setTabSelection(0);
                break;
            case R.id.lin_classify:
                setTabSelection(1);
                linHomePage.setVisibility(View.VISIBLE);
                linHpStore.setVisibility(View.GONE);
                linDistribution.setVisibility(View.GONE);
                linStore.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_shopcar:
                setTabSelection(2);
                linHomePage.setVisibility(View.VISIBLE);
                linHpStore.setVisibility(View.GONE);
                linDistribution.setVisibility(View.GONE);
                linStore.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_members_center:
                setTabSelection(3);
                linHomePage.setVisibility(View.VISIBLE);
                linHpStore.setVisibility(View.GONE);
                linDistribution.setVisibility(View.GONE);
                linStore.setVisibility(View.VISIBLE);
                break;
            case R.id.lin_hp_store:
                setTabSelection(6);
                break;

            case R.id.lin_store:
                setTabSelection(4);
                linHomePage.setVisibility(View.GONE);
                linHpStore.setVisibility(View.VISIBLE);
                linDistribution.setVisibility(View.VISIBLE);
                linStore.setVisibility(View.GONE);
                break;

            case R.id.lin_distribution:
                setTabSelection(5);
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     */
    private void setTabSelection(int index) {
        int mycolor = getResources().getColor(R.color.mycolor);
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction ft = fm.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(ft);

        switch (index) {
            case 0:
                ivHomePage.setImageResource(R.mipmap.icon_homepage002);
                tvHomePage.setTextColor(mycolor);

                if (fmHomePage == null) {
                    // 如果HomePageFragment为空，则创建一个并添加到界面上
                    fmHomePage = new FMHomePage();
                    //fmHomePage.setArguments(bundle);
                    ft.add(R.id.content, fmHomePage);
                } else {
                    // 如果HomePageFragment不为空，则直接将它显示出来
                    ft.show(fmHomePage);
                }

                break;
            case 1:

                ivClassify.setImageResource(R.mipmap.icon_classify002);
                tvClassify.setTextColor(mycolor);

                if (fmClassify == null) {
                    fmClassify = new FMClassify();
                    //fmOrderTest.setArguments(bundle);
                    ft.add(R.id.content, fmClassify);
                } else {
                    ft.show(fmClassify);
                }
                break;
            case 2:

                ivShopcar.setImageResource(R.mipmap.icon_shopcar002);
                tvShopcar.setTextColor(mycolor);

                if (fmShopcar == null) {
                    fmShopcar = new FMShopcar();
                    //fmShopping.setArguments(bundle);
                    ft.add(R.id.content, fmShopcar);
                } else {
                    ft.show(fmShopcar);
                }
                break;
            case 3:

                ivMembersCenter.setImageResource(R.mipmap.icon_memcen002);
                tvMembersCenter.setTextColor(mycolor);

                if (fmMemCen == null) {
                    fmMemCen = new FMMemCen();
                    //fmPerCen.setArguments(bundle);
                    ft.add(R.id.content, fmMemCen);
                } else {
                    ft.show(fmMemCen);
                }
                break;
            case 4:

                ivHpStore.setImageResource(R.mipmap.icon_store002);
                tvHpStore.setTextColor(mycolor);
                if (fmStore == null) {
                    fmStore = new FMStore();
                    ft.add(R.id.content, fmStore);
                } else {
                    ft.show(fmStore);
                }
                break;

            case 6:

                ivHpStore.setImageResource(R.mipmap.icon_store002);
                tvHpStore.setTextColor(mycolor);

                if (fmStore == null) {
                    fmStore = new FMStore();
                    ft.add(R.id.content, fmStore);
                } else {
                    ft.show(fmStore);
                }
                break;

            case 5:
                ivDistribution.setImageResource(R.mipmap.icon_distribution002);
                tvDistribution.setTextColor(mycolor);

                if (fmDistribution == null) {
                    fmDistribution = new FMDistribution();
                    ft.add(R.id.content, fmDistribution);
                } else {
                    ft.show(fmDistribution);
                }
                break;

            default:
                break;
        }
        ft.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        //还原初始状态

        ivHomePage.setImageResource(R.mipmap.icon_homepage001);
        ivClassify.setImageResource(R.mipmap.icon_classify001);
        ivShopcar.setImageResource(R.mipmap.icon_shopcar001);
        ivMembersCenter.setImageResource(R.mipmap.icon_memcen001);
        ivStore.setImageResource(R.mipmap.icon_store001);
        ivDistribution.setImageResource(R.mipmap.icon_distribution001);
        ivHpStore.setImageResource(R.mipmap.icon_store001);

        tvHomePage.setTextColor(Color.GRAY);
        tvClassify.setTextColor(Color.GRAY);
        tvShopcar.setTextColor(Color.GRAY);
        tvMembersCenter.setTextColor(Color.GRAY);
        tvStore.setTextColor(Color.GRAY);
        tvDistribution.setTextColor(Color.GRAY);
        tvHpStore.setTextColor(Color.GRAY);

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    public void hideFragments(FragmentTransaction transaction) {
        if (fmHomePage != null) {
            transaction.hide(fmHomePage);
        }

        if (fmClassify != null) {
            transaction.hide(fmClassify);
        }
        if (fmShopcar != null) {
            transaction.hide(fmShopcar);
        }
        if (fmMemCen != null) {
            transaction.hide(fmMemCen);
        }
        if (fmStore != null) {
            transaction.hide(fmStore);
        }
        if (fmDistribution != null) {
            transaction.hide(fmDistribution);
        }

    }

    /*版本更新 依赖update_version*/

//    public void getVersion(){
//        //you can add your request params and request method
//        //eg.
//        //只有requsetUrl service 是必须值 其他参数都有默认值，可选
//
////        com.allenliu.versionchecklib.core.http.HttpHeaders headers=new com.allenliu.versionchecklib.core.http.HttpHeaders();
////        headers.put("a","b");
//        VersionParams.Builder builder = new VersionParams.Builder()
////                .setHttpHeaders(headers)
////                .setRequestMethod(requestMethod)
////                .setRequestParams(httpParams)
//                .setRequestUrl("http://www.baidu.com")
////                .setDownloadAPKPath(getApplicationContext().getFilesDir()+"/")
//                .setService(DemoService.class);
//
//        stopService(new Intent(this, DemoService.class));
//        switch (view.getId()) {
//            case R.id.sendbtn:
//                String pauseTime = etPauseTime.getText().toString();
//                String address = etAddress.getText().toString();
//                try {
//                    if (!pauseTime.isEmpty() && Long.valueOf(pauseTime) > 0) {
//                        builder.setPauseRequestTime(Long.valueOf(pauseTime));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                if (!address.isEmpty())
//                    builder.setDownloadAPKPath(address);
//                //更新界面选择
//                switch (radioGroup.getCheckedRadioButtonId()) {
//                    case R.id.btn1:
//                        CustomVersionDialogActivity.customVersionDialogIndex = 3;
//                        builder.setCustomDownloadActivityClass(VersionDialogActivity.class);
//                        break;
//                    case R.id.btn2:
//                        CustomVersionDialogActivity.customVersionDialogIndex = 1;
//                        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                        break;
//                    case R.id.btn3:
//                        CustomVersionDialogActivity.customVersionDialogIndex = 2;
//                        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                        break;
//                }
//                //下载进度界面选择
//                switch (radioGroup2.getCheckedRadioButtonId()) {
//                    case R.id.btn21:
//                        //同理
//                        CustomVersionDialogActivity.isCustomDownloading = false;
//                        builder.setCustomDownloadActivityClass(VersionDialogActivity.class);
//                        break;
//                    case R.id.btn22:
//                        //可以看到 更改更新界面或者是更改下载界面都是重写VersionDialogActivity
//                        CustomVersionDialogActivity.isCustomDownloading = true;
//                        builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                        break;
//                }
//                //强制更新
//                if (forceUpdateCheckBox.isChecked()) {
//                    CustomVersionDialogActivity.isForceUpdate = true;
//                    builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                } else {
//                    //同理
//                    CustomVersionDialogActivity.isForceUpdate = false;
//                    builder.setCustomDownloadActivityClass(CustomVersionDialogActivity.class);
//                }
//                //静默下载
//                if (silentDownloadCheckBox.isChecked()) {
//                    builder.setSilentDownload(true);
//                } else {
//                    builder.setSilentDownload(false);
//                }
//                //强制重新下载
//                if (forceDownloadCheckBox.isChecked()) {
//                    builder.setForceRedownload(true);
//                } else {
//                    builder.setForceRedownload(false);
//                }
//                //是否仅使用下载功能
//                if (onlyDownloadCheckBox.isChecked()) {
//                    //如果仅使用下载功能，downloadUrl是必须的
//                    builder.setOnlyDownload(true)
//                            .setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk")
//                            .setTitle("检测到新版本")
//                            .setUpdateMsg(getString(R.string.updatecontent));
//                } else
//                    builder.setOnlyDownload(false);
//                //是否显示通知栏
//                if (showNotificationCheckBox.isChecked()) {
//                    builder.setShowNotification(true);
//                } else
//                    builder.setShowNotification(false);
//                if (showDownloadingCheckBox.isChecked()) {
//                    builder.setShowDownloadingDialog(true);
//                } else
//                    builder.setShowDownloadingDialog(false);
//
//                AllenChecker.startVersionCheck(this, builder.build());
//                break;
//
//            case R.id.cancelBtn:
//                AllenChecker.cancelMission();
////                VersionParams.Builder builder2 = new VersionParams.Builder();
////                builder2.setOnlyDownload(true)
////                        .setDownloadUrl("http://test-1251233192.coscd.myqcloud.com/1_1.apk")
////                        .setTitle("检测到新版本")
////                        .setForceRedownload(true)
////                        .setUpdateMsg(getString(R.string.updatecontent));
////                AllenChecker.startVersionCheck(this, builder2.build());
//                break;
//    }


    //双击的时间间隔
    private long millis = 0;

    /**
     * 监听返回键 双击退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @SuppressLint("WrongConstant")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //
            boolean is;
            //双击退出
            if (System.currentTimeMillis() - millis < 1000) {

                //FMHomePageOld.mLocationClient.stop();
                return super.onKeyDown(keyCode, event);
            } else {
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                millis = System.currentTimeMillis();
                return false;
            }

        }
        return false;
    }


}