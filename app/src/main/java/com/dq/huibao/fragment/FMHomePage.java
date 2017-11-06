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
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dq.huibao.Interface.OnItemClickListener;
import com.dq.huibao.R;
import com.dq.huibao.adapter.HpCubeAdapter;
import com.dq.huibao.adapter.HpGoodsAdapter;
import com.dq.huibao.adapter.HpMenuAdapter;
import com.dq.huibao.base.BaseFragment;
import com.dq.huibao.bean.homepage.Banner;
import com.dq.huibao.bean.homepage.Cube;
import com.dq.huibao.bean.homepage.Goods;
import com.dq.huibao.bean.homepage.Menu;
import com.dq.huibao.bean.homepage.Notice;
import com.dq.huibao.bean.homepage.Picture;
import com.dq.huibao.bean.homepage.Root;
import com.dq.huibao.lunbotu.ADInfo;
import com.dq.huibao.lunbotu.CycleViewPager;
import com.dq.huibao.lunbotu.ViewFactory;
import com.dq.huibao.utils.GsonUtil;
import com.dq.huibao.utils.HttpUtils;
import com.dq.huibao.utils.ImageUtils;
import com.dq.huibao.view.MarqueTextView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Description：首页
 * Created by jingang on 2017/10/16.
 */

public class FMHomePage extends BaseFragment {
    @Bind(R.id.lin_homepage)
    LinearLayout linHomepage;
    @Bind(R.id.sv_homepage)
    ScrollView svHomepage;
    private View view;

    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos;
    private ADInfo info;

    /*接收页面传值*/
    private Intent intent;

    /*接口地址*/
    private String PATH = "";

    RequestParams params = null;

    /*根*/
    private Root root;

    private LinearLayout lin_search = null, lin_banner = null, lin_menu = null,
            lin_notice = null, lin_picture = null, lin_cube = null, lin_goods = null;

    /*动态添加控件下标*/
    private int index_menu = 0, index_picture = 0, index_cube = 0,
            index_goods = 0;

    private CycleViewPager cycleViewPager;
    private RecyclerView recyclerView, recyclerView1;
    private MarqueTextView tv_notice;
    private ImageView iv_picture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_homepage_test, null);

        ButterKnife.bind(this, view);

        getHomePage("1604");
        return view;
    }

    /**
     * 获取首页数据
     *
     * @param i 店铺id
     */
    public void getHomePage(String i) {
        PATH = HttpUtils.PATH + HttpUtils.HP_ROOT + "i=" + i;

        System.out.println("首页数据" + PATH);

        params = new RequestParams(PATH);
        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("首页数据 = " + result);
                        root = GsonUtil.gsonIntance().gsonToBean(result, Root.class);

                        setFor(root);

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

    /**
     * @param root
     */
    public void setFor(Root root) {
        for (int i = 0; i < root.getData().size(); i++) {
            //System.out.println("" + root.getData().get(i).getTemp());
            setTemp(root.getData().get(i).getId(), root.getData().get(i).getTemp());
//            try {
//                Thread.sleep(2 * 100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     * 根据temp获取各类别的数据
     *
     * @param temp
     */
    public void setTemp(String id, final String temp) {
        switch (temp) {
            case "search":
                System.out.println("添加search");
                lin_search = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_search, null);
                linHomepage.addView(lin_search);
                //getData("1604", id, "search");
                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println(temp + " = " + result);
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

                break;

            case "banner":
                System.out.println("添加banner");

                lin_banner = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_lunbotu, null);
                linHomepage.addView(lin_banner);

                cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);
                //getData("1604", id, "banner");

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println(temp + " = " + result);

                                configImageLoader();
                                Banner banner = GsonUtil.gsonIntance().gsonToBean(result, Banner.class);
                                infos = new ArrayList<>();
                                for (int i = 0; i < banner.getData().getData().size(); i++) {
                                    info = new ADInfo();
                                    info.setUrl(banner.getData().getData().get(i).getImgurl());
                                    info.setContent(banner.getData().getData().get(i).getSysurl());
                                    info.setImg("");
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


                break;

            case "menu":
                System.out.println("添加menu");
                index_menu++;

                final RecyclerView rv = new RecyclerView(getActivity());
                rv.setId(index_menu);

                linHomepage.addView(rv);


                lin_menu = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_menu, null);
                linHomepage.addView(lin_menu);

                recyclerView = (RecyclerView) lin_menu.findViewById(R.id.rv_hp_menu);

                //getData("1604", id, "menu");

                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println(temp + " = " + result);

                                final Menu menu = GsonUtil.gsonIntance().gsonToBean(result, Menu.class);

                                HpMenuAdapter hpMenuAdapter = new HpMenuAdapter(getActivity(), menu.getData().getData());

                                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                                rv.setLayoutManager(new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false));

                                rv.setAdapter(hpMenuAdapter);

                                hpMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        toast("" + menu.getData().getData().get(position).getText());
                                    }
                                });

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

                break;

            case "notice":
                System.out.println("添加notice");
                lin_notice = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_notice, null);
                linHomepage.addView(lin_notice);

                tv_notice = (MarqueTextView) lin_notice.findViewById(R.id.tv_hp_notice);

                //System.out.println("添加notice");
                //getData("1604", id, "notice");
                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println(temp + " = " + result);

                                Notice notice = GsonUtil.gsonIntance().gsonToBean(result, Notice.class);

                                tv_notice.setText("" + notice.getData().getParams().getNotice());
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
                break;


            case "picture":
                System.out.println("添加picture");
                index_picture++;
//                lin_picture = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_picture, null);
//                linHomepage.addView(lin_picture);
//
//                iv_picture = (ImageView) lin_picture.findViewById(R.id.iv_hp_picture);

                final ImageView iv = new ImageView(getActivity());
                iv.setId(index_picture);

                linHomepage.addView(iv);


                //System.out.println("添加picture");
                //getData("1604", id, "picture");
                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println(temp + " = " + result);

                                Picture picture = GsonUtil.gsonIntance().gsonToBean(result, Picture.class);

                                ImageUtils.loadIntoUseFitWidth(getActivity(),
                                        picture.getData().getData().get(0).getImgurl(),
                                        R.mipmap.icon_empty,
                                        R.mipmap.icon_error,
                                        iv);

//                                Glide.with(getActivity())
//                                        .load(picture.getData().getData().get(0).getImgurl())
//                                        .placeholder(R.mipmap.icon_empty)
//                                        .error(R.mipmap.icon_error)
//                                        .diskCacheStrategy(DiskCacheStrategy.NONE)
//                                        .into(iv);

                                //x.image().bind(iv, picture.getData().getData().get(0).getImgurl());

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
                break;

            case "cube":
                System.out.println("添加cube");
//                lin_cube = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_cube, null);
//                linHomepage.addView(lin_cube);
//
//                recyclerView1 = (RecyclerView) lin_cube.findViewById(R.id.rv_hp_cube);

                final RecyclerView rv_cube = new RecyclerView(getActivity());
                rv_cube.setId(index_cube);
                linHomepage.addView(rv_cube);

                //getData("1604", id, "cube");
                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println(temp + " = " + result);
                                final Cube cube = GsonUtil.gsonIntance().gsonToBean(result, Cube.class);

                                HpCubeAdapter hpCubeAdapter = new HpCubeAdapter(getActivity(), cube.getData().getParams().getLayout());

                                GridLayoutManager mManager;
                                mManager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
                                mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        String imgwidth = cube.getData().getParams().getLayout().get(position).getCols();
                                        if (imgwidth.equals("1")) {
                                            return 1;
                                        } else if (imgwidth.equals("2")) {
                                            return 2;
                                        }
                                        return 1;
                                    }
                                });

                                rv_cube.setLayoutManager(mManager);

                                rv_cube.setAdapter(hpCubeAdapter);

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
                break;

            case "goods":
                System.out.println("添加goods");
                index_goods++;

                final RecyclerView rv_goods = new RecyclerView(getActivity());
                rv_goods.setId(index_goods);
                linHomepage.addView(rv_goods);

                //getData("1604", id, "goods");
                params = new RequestParams(HttpUtils.PATH + HttpUtils.HP_ROOT + "i=1604" + "&id=" + id);
                x.http().get(params,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                System.out.println(temp + "  = " + result);

                                final Goods goods = GsonUtil.gsonIntance().gsonToBean(result, Goods.class);

                                HpGoodsAdapter hpGoodsAdapter = new HpGoodsAdapter(getActivity(), goods.getData().getData());

                                GridLayoutManager mManager;

                                mManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
                                mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                    @Override
                                    public int getSpanSize(int position) {
                                        String imgwidth = goods.getData().getParams().getStyle();
                                        if (imgwidth.equals("49.5%")) {
                                            return 1;
                                        } else if (imgwidth.equals("100%")) {
                                            return 2;
                                        }
                                        return 1;
                                    }
                                });

                                rv_goods.setLayoutManager(mManager);
                                rv_goods.setAdapter(hpGoodsAdapter);


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
                break;

            default:
                break;

        }
    }

    /**
     * 获取temp数据
     *
     * @param i
     * @param id temp id
     */
    public void getData(String i, String id, final String temp) {
        PATH = HttpUtils.PATH + HttpUtils.HP_ROOT +
                "i=" + i + "&id=" + id;

        params = new RequestParams(PATH);
        System.out.println("temp = " + PATH);

        x.http().get(params,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        setLayout(temp, result);

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

    /**
     * @param temp
     * @param result
     */
    @SuppressLint("WrongConstant")
    public void setLayout(String temp, String result) {
        switch (temp) {
            case "search":
                System.out.println("添加search");

                break;

            case "banner":
                System.out.println("添加banner");
//                LinearLayout lin_banner = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_lunbotu, null);
//                linHomepage.addView(lin_banner);
//
//                cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.fragment_cycle_viewpager_content);

                configImageLoader();


                Banner banner = GsonUtil.gsonIntance().gsonToBean(result, Banner.class);


                infos = new ArrayList<>();
                for (int i = 0; i < banner.getData().getData().size(); i++) {
                    info = new ADInfo();
                    info.setUrl(banner.getData().getData().get(i).getImgurl());
                    info.setContent(banner.getData().getData().get(i).getSysurl());
                    info.setImg("");
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


                break;

            case "menu":
                System.out.println("添加menu");
//                LinearLayout lin_menu = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_menu, null);
//                linHomepage.addView(lin_menu);
//
//                RecyclerView recyclerView = (RecyclerView) lin_menu.findViewById(R.id.rv_hp_menu);

                Menu menu = GsonUtil.gsonIntance().gsonToBean(result, Menu.class);

                HpMenuAdapter hpMenuAdapter = new HpMenuAdapter(getActivity(), menu.getData().getData());

                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5, GridLayoutManager.VERTICAL, false));

                recyclerView.setAdapter(hpMenuAdapter);


                break;

            case "notice":
                System.out.println("添加notice");

                Notice notice = GsonUtil.gsonIntance().gsonToBean(result, Notice.class);

                tv_notice.setText("" + notice.getData().getParams().getNotice());


                break;

            case "picture":
                System.out.println("添加picture");

//                LinearLayout lin_picture = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.include_hp_picture, null);
//                linHomepage.addView(lin_picture);
//
//
//                iv_picture = (ImageView) lin_picture.findViewById(R.id.iv_hp_picture);

                Picture picture = GsonUtil.gsonIntance().gsonToBean(result, Picture.class);
                //x.image().bind(iv_picture, "http://www.dequanhuibao.com/attachment/images/1604/2017/08/BiiJd063556743Nw07oz73dA6d74I7.jpg");
                x.image().bind(iv_picture, picture.getData().getData().get(0).getImgurl());

                break;

            case "cube":
                System.out.println("添加cube");

                final Cube cube = GsonUtil.gsonIntance().gsonToBean(result, Cube.class);

                HpCubeAdapter hpCubeAdapter = new HpCubeAdapter(getActivity(), cube.getData().getParams().getLayout());

                GridLayoutManager mManager;
                mManager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
                mManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        for (int i = 0; i < cube.getData().getParams().getLayout().size(); i++) {
                            String imgwidth = cube.getData().getParams().getLayout().get(i).getCols();
                            System.out.println("啥啥啥 = " + imgwidth);
                            if (imgwidth.equals("1")) {
                                return 2;
                            } else if (imgwidth.equals("2")) {
                                return 4;
                            }
                        }
//                        String imgwidth = tese.getMsg().get(position).getImgwidth();
//                        if (imgwidth.equals("50")) {
//                            return 1;
//                        } else if (imgwidth.equals("100")) {
//                            return 2;
//                        }

                        return 2;
                    }
                });

                recyclerView1.setLayoutManager(mManager);

                recyclerView1.setAdapter(hpCubeAdapter);

                break;

            case "goods":
                System.out.println("添加goods");
                break;

            default:
                break;
        }
    }


    /*轮播图点击事件*/
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener =
            new CycleViewPager.ImageCycleViewListener() {

                @SuppressLint("WrongConstant")
                @Override
                public void onImageClick(ADInfo info, int position, View imageView) {
                    if (cycleViewPager.isCycle()) {
                        //intent = new Intent(getActivity(), WebActivity.class);
                        //startActivity(intent);
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

}
