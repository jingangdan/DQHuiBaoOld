package com.dq.huibao.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dq.huibao.R;
import com.dq.huibao.adapter.HotClassifyAdapter;
import com.dq.huibao.adapter.HotClassifyAdapter1;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.HomePage;
import com.dq.huibao.bean.HomePageTest;
import com.dq.huibao.bean.Root;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.ui.GoodsDetailsActivity;
import com.dq.huibao.ui.homepage.WebActivity;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：首页
 * Created by jingang on 2017/10/16.
 */

public class FMHomePage extends BaseFragment {
    /*搜索*/
    @Bind(R.id.iv_hp_sreach)
    ImageView ivHpSreach;

    /**/
    @Bind(R.id.rv_hot_classify)
    RecyclerView rvHotClassify;

    @Bind(R.id.rv_hot_classify1)
    RecyclerView rvHotClassify1;

    @Bind(R.id.rv_hot_classify2)
    RecyclerView rvHotClassify2;

    @Bind(R.id.rv_hot_classify3)
    RecyclerView rvHotClassify3;

    @Bind(R.id.rv_hot_classify4)
    RecyclerView rvHotClassify4;

    /*广告位1*/
    @Bind(R.id.iv_hp_adware)
    ImageView ivHpAdware;

    private View view;

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos;
    private ADInfo info;
    private CycleViewPager cycleViewPager;

    private List<String> imgs = new ArrayList<>();

    /*热门类别*/
    private RecyclerView rv_hot_classify;
    private HotClassifyAdapter hotClassifyAdapter;
    private HotClassifyAdapter1 hotClassifyAdapter1;

    private LinearLayoutManager mLayoutManager, mLayoutManager1, mLayoutManager2, mLayoutManager3, mLayoutManager4;
    private GridLayoutManager llmv, llmv1, llmv2, llmv3, llmv4;

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homepage, null);

        imgs.add("http://www.dequanhuibao.com/attachment/images/1604/2017/10/Z5qSsusTTeXQSSt4EE5X558PElS57q.jpg");
        imgs.add("http://www.dequanhuibao.com/attachment/images/1604/2017/10/Y9jUQZpZ3UEj9ZLc49DU9zp9eJceAM.jpg");
        imgs.add("http://www.dequanhuibao.com/attachment/images/1604/2017/10/aXkzkxCRsRFcB5FBRr2RRKXRcbxD1R.jpg");
        imgs.add("http://www.dequanhuibao.com/attachment/images/1604/2017/09/kUiQNP0RnWWiI0a064I6IPin4I0n1p.jpg");
        imgs.add("http://www.dequanhuibao.com/attachment/images/1604/2017/09/sB0USHrEZmsIeb0p0PeMIShB3YbheH.jpg ");
        imgs.add("http://www.dequanhuibao.com/attachment/images/1604/2017/10/H83azgc3VdkV48614eWQ6deVn4eW6C.jpg");

        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager2 = new LinearLayoutManager(getActivity());
        mLayoutManager3 = new LinearLayoutManager(getActivity());
        mLayoutManager4 = new LinearLayoutManager(getActivity());

        rvHotClassify.setLayoutManager(mLayoutManager);
        rvHotClassify1.setLayoutManager(mLayoutManager1);
        rvHotClassify2.setLayoutManager(mLayoutManager2);

        rvHotClassify3.setLayoutManager(mLayoutManager3);
        rvHotClassify4.setLayoutManager(mLayoutManager4);

        llmv = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

        rvHotClassify.setLayoutManager(llmv);

        hotClassifyAdapter = new HotClassifyAdapter(getActivity());
        rvHotClassify.setAdapter(hotClassifyAdapter);

        /*测试一行四列*/
        llmv2 = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        llmv1 = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        llmv3 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        llmv4 = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);

        rvHotClassify1.setLayoutManager(llmv1);
        rvHotClassify2.setLayoutManager(llmv2);
        rvHotClassify3.setLayoutManager(llmv3);
        rvHotClassify4.setLayoutManager(llmv4);

        hotClassifyAdapter1 = new HotClassifyAdapter1(getActivity());

        rvHotClassify1.setAdapter(hotClassifyAdapter1);
        rvHotClassify2.setAdapter(hotClassifyAdapter1);
        rvHotClassify3.setAdapter(hotClassifyAdapter);
        rvHotClassify4.setAdapter(hotClassifyAdapter1);

        initData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 获取首页数据
     *
     * @param i 店铺id
     */
    public void getHomePage(String i) {
        PATH = HttpUtils.PATH + HttpUtils.API_HOMEPGE + "i=" + i;

        System.out.println("首页数据" + PATH);

        RequestParams params = new RequestParams(PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //Root root = GsonUtil.gsonIntance().gsonToBean(result, Root.class);
                        System.out.println("111");

                        Gson gson = new Gson();
                        Root homeNewsBean = gson.fromJson(result, Root.class);

                        System.out.println("111111111111 = "+homeNewsBean.getMsg());

//                        Gson gson = new Gson();
//                        System.out.println("222");
//
//                        Type type = new TypeToken<Root>() {}.getType();
//                        System.out.println("333");
//                        Root jsonBean = gson.fromJson(result, type);

//                        System.out.println("1111111111111111111 = "+jsonBean.getMsg());
//
//                        toast("11111111  = "+jsonBean.getResult());



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

    /*组件初始化*/
    public void initData() {
        cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);


        configImageLoader();
        getLunbo();

        getHomePage("1604");

    }

    /*获取轮播图*/
    public void getLunbo() {
        infos = new ArrayList<>();

        for (int i = 0; i < imgs.size(); i++) {
            info = new ADInfo();
            info.setUrl(imgs.get(i).toString());
//            info.setContent("" + wxADV.getMsg().get(i).getTitle());
//            info.setImg("http://www.ybt9.com/" + wxADV.getMsg().get(i).getLinkurl());
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(3000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    /*轮播图点击事件*/
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener =
            new CycleViewPager.ImageCycleViewListener() {

                @SuppressLint("WrongConstant")
                @Override
                public void onImageClick(ADInfo info, int position, View imageView) {
                    if (cycleViewPager.isCycle()) {
                        intent = new Intent(getActivity(), WebActivity.class);
                        startActivity(intent);
                    }

                }

            };

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }


    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_hp_sreach, R.id.rv_hot_classify, R.id.iv_hp_adware})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_hp_sreach:

                break;
            case R.id.rv_hot_classify:
                break;

            case R.id.iv_hp_adware:
                Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                startActivity(intent);

                break;
        }
    }

}
