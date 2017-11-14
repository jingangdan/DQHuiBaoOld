package com.dq.huibao.adapter.gd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dq.huibao.R;
import com.dq.huibao.bean.goodsdetail.Comment;
import com.dq.huibao.utils.BaseRecyclerViewHolder;
import com.dq.huibao.utils.ImageUtils;

import java.util.List;

/**
 * Description：
 * Created by jingang on 2017/11/13.
 */

public class GdCommentAdapter extends RecyclerView.Adapter<GdCommentAdapter.MyViewHolder> {
    private Context mContext;
    private List<Comment> commentList;

    public GdCommentAdapter(Context mContext, List<Comment> commentList) {
        this.mContext = mContext;
        this.commentList = commentList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gd_comment, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_nickname.setText("" + commentList.get(position).getNickname());
        holder.tv_level.setText(commentList.get(position).getLevel() + "星");
        holder.tv_createtime.setText("" + commentList.get(position).getCreatetime());
        holder.tv_content.setText("" + commentList.get(position).getContent());

        ImageUtils.loadIntoUseFitWidth(mContext,
                commentList.get(position).getHeadimgurl(),
                R.mipmap.icon_empty002,
                R.mipmap.icon_error002,
                holder.iv_headimgurl);

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends BaseRecyclerViewHolder {
        private ImageView iv_headimgurl;
        private TextView tv_nickname, tv_content, tv_level, tv_createtime;

        public MyViewHolder(View view) {
            super(view);
            iv_headimgurl = (ImageView) view.findViewById(R.id.iv_gd_headimgurl);
            tv_nickname = (TextView) view.findViewById(R.id.tv_gd_nickname);
            tv_level = (TextView) view.findViewById(R.id.tv_gd_level);
            tv_content = (TextView) view.findViewById(R.id.tv_gd_comment_content);
            tv_createtime = (TextView) view.findViewById(R.id.tv_gd_createtime);
        }
    }
}
