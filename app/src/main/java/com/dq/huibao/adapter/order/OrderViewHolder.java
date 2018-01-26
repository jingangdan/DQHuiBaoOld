package com.dq.huibao.adapter.order;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class OrderViewHolder {
	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	private OrderViewHolder(Context context, ViewGroup parent, int layoutId,
							int position) {
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		// setTag
		mConvertView.setTag(this);
	}

	/**
	 * 拿到ViewHolder对象
	 *
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static OrderViewHolder get(Context context, View convertView,
									  ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new OrderViewHolder(context, parent, layoutId, position);
		}
		return (OrderViewHolder) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 *
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 为TextView设置字符�?
	 *
	 * @param viewId
	 * @param text
	 * @return
	 */
	public OrderViewHolder setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}
	/**
	 * 为TextView设置字符�?
	 *
	 * @param viewId
	 * @param text
	 * @return
	 */
	public OrderViewHolder setTextColor(int viewId, int text) {
		TextView view = getView(viewId);
		view.setTextColor(text);
		return this;
	}
	/**
	 * 为ImageView设置图片
	 *
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public OrderViewHolder setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);

		return this;
	}

	/**
	 * 为ImageView设置图片
	 *
	 * @param viewId
	 * @return
	 */
	public OrderViewHolder setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}
	/**
	 * 为View更换背景色
	 */
	public OrderViewHolder setBackColor(int viewId, int color, int index) {
		TextView view = getView(viewId);
		view.setBackgroundColor(color);
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

}
