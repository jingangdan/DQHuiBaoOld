package com.dq.huibao.homepage;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dq.huibao.R;
import com.dq.huibao.adapter.HpMenuAdapter;
import com.dq.huibao.adapter.index.MenuAdapter;
import com.dq.huibao.bean.index.Index;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ChenJunMei on 2016/11/25.
 */

public class HomeRecycleViewAdapter extends RecyclerView.Adapter {
    /**
     * 4种类型
     */
    /**
     * 类型1：黑色星期五--使用banner实现
     */
    public static final int TYPE_MENU = 0;
    /**
     * 类型2：今日新品--使用GridView实现
     */
    public static final int TYPE_IMGLIST = 1;
    /**
     * 类型3：品牌福利--使用ImageView实现
     */
    public static final int TYPE_GOODSLIST = 2;

    /**
     * 当前类型
     */
    public int currentType = TYPE_MENU;

    private final Context mContext;
    private List<Index2.DataBean> dataList;
    /**
     * 以后用它来初始化布局
     */
    private final LayoutInflater mLayoutInflater;

    public HomeRecycleViewAdapter(Context mContext, List<Index2.DataBean> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;

        //以后用它来初始化布局
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView创建ViewHolder布局
     *
     * @param parent
     * @param viewType 当前的类型
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("555 = " + dataList.size());
        if (viewType == TYPE_MENU) {
            return new MenuViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_menu, null));
        } else if (viewType == TYPE_IMGLIST) {
            return new MenuViewHolder(mContext, mLayoutInflater.inflate(R.layout.layout_menu, null));
        } else if (viewType == TYPE_GOODSLIST) {

        }
//        }else if(viewType==TODAY_NEW_GV1){
//            return new TODAYViewHolder(mContext,mLayoutInflater.inflate(R.layout.gv_channel,null));
//        }else if(viewType==PIN_PAI_IV2) {
//            return new PINPAIViewHolder(mContext, mLayoutInflater.inflate(R.layout.iv_pinpai, null));
//        }else if(viewType== DAPEIQS_GV3) {
//            return new DaPeiViewHolder(mContext, mLayoutInflater.inflate(R.layout.dapeiqs_rv, null));
//        }
        return null;
    }

    /**
     * 相当于getView中的绑定数据模块
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_MENU) {
            MenuViewHolder bbnViewHolder = (MenuViewHolder) holder;
            bbnViewHolder.setData(dataList.get(0).getMenu());
        } else if (getItemViewType(position) == TYPE_IMGLIST) {
            ImgListViewHolder todayViewHolder = (ImgListViewHolder) holder;
            todayViewHolder.setData(dataList.get(1).getAppimglist());
        }
//        }else if(getItemViewType(position)==PIN_PAI_IV2) {
//            PINPAIViewHolder pinpaiViewHolder = (PINPAIViewHolder) holder;
//            List<WomenBean.WomenData.ModuleBean.DataBean> pinpai2data = moduleBeanList.get(2).getData();
//            pinpaiViewHolder.setData(pinpai2data);
//        }else if(getItemViewType(position)== DAPEIQS_GV3) {
//            DaPeiViewHolder dapeiViewHolder = (DaPeiViewHolder) holder;
//            List<WomenBean.WomenData.ModuleBean.DataBean> dapeiqs6data = moduleBeanList.get(6).getData();
//            dapeiViewHolder.setData(dapeiqs6data);
//        }
    }

    /**
     * 总共有多少个item
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * 得到类型
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TYPE_MENU:
                currentType = TYPE_MENU;
                break;
            case TYPE_IMGLIST:
                currentType = TYPE_IMGLIST;
                break;
            case TYPE_GOODSLIST:
                currentType = TYPE_GOODSLIST;
                break;
        }
        return currentType;
    }


    class MenuViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;

        public MenuViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_menu);

        }

        public void setData(List<Index2.DataBean.MenuBean> dapeiqs6data) {
            Menu2Adapter menuAdapter = new Menu2Adapter(mContext, dapeiqs6data);
            recyclerView.setAdapter(menuAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 5, GridLayoutManager.VERTICAL, false));

        }
    }

    class ImgListViewHolder extends RecyclerView.ViewHolder {

        private final Context mContext;
        private RecyclerView recyclerView;

        public ImgListViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_menu);

        }

        public void setData(List<Index2.DataBean.AppimglistBean> dapeiqs6data) {
            Appimglist2Adapter appimglist2Adapter = new Appimglist2Adapter(mContext, dapeiqs6data);
            recyclerView.setAdapter(appimglist2Adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));

        }
    }


//    class TODAYViewHolder extends RecyclerView.ViewHolder{
//
//        private final Context mContext;
//        private GridView gridView;
//
//        public TODAYViewHolder(Context mContext, View itemView) {
//            super(itemView);
//            this.mContext=mContext;
//            gridView= (GridView) itemView.findViewById(R.id.gv_channel);
//        }
//
//        public void setData(List<WomenBean.WomenData.ModuleBean.DataBean> module1data) {
//            //已得到数据了
//            //设置适配器
//            TodayGVAdapter adapter= new TodayGVAdapter(mContext,module1data);
//            gridView.setAdapter(adapter);
//        }
//    }

//    static class PINPAIViewHolder extends RecyclerView.ViewHolder {
//        private final Context mContext;
//        @Bind(R.id.iv_new_chok)
//        ImageView ivNewChok;
//
//        PINPAIViewHolder(Context mContext, View itemView) {
//            super(itemView);
//            this.mContext=mContext;
//            ButterKnife.bind(this, itemView);
//            ivNewChok= (ImageView) itemView.findViewById(R.id.iv_new_chok);
//        }
//
//        public void setData(List<WomenBean.WomenData.ModuleBean.DataBean> pinpai2data) {
//            Glide.with(mContext)
//                    .load(pinpai2data.get(0).getImg())
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .crossFade() //设置淡入淡出效果，默认300ms，可以传参
//                    .into(ivNewChok);
//
//        }
//    }


//    public  class BBNViewHolder extends RecyclerView.ViewHolder {
//
//        private final Context mContext;
//        private Banner banner;
//
//        public BBNViewHolder(Context mContext, View itemView) {
//            super(itemView);
//            this.mContext = mContext;
//            banner = (Banner) itemView.findViewById(R.id.banner);
//        }
//
//        public void setData(List<WomenBean.WomenData.ModuleBean.DataBean> module0data) {
//            //设置Banner的数据
//            //得到图片地址的集合
//            List<String> imageUrls=new ArrayList<>();
//            for (int i=0;i<module0data.size();i++){
//                String image=module0data.get(i).getImg();
//                imageUrls.add(image);
//            }
//
//            // 222222 //新版的banner的使用----偷下懒的使用方法
//            banner.setImages(imageUrls).setImageLoader(new GlideImageLoader()).start();
//
//            //设置item的点击事件
//            banner.setOnBannerClickListener(new OnBannerClickListener() {
//                @Override
//                public void OnBannerClick(int position) {
//                    //注意这里的position是从1开始的
//                    Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//    }

//    public class GlideImageLoader extends ImageLoader {
//        @Override
//        public void displayImage(Context context, Object path, ImageView imageView) {
//
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//
//            //Glide 加载图片简单用法
//            Glide.with(context).load(path).into(imageView);
//        }
//    }


}


