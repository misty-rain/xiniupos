package com.xiniu.pos.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiniu.pos.R;
import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.ui.adapter.CategoryAdapter.ViewHolder;

public class ChildCategoryAdapter extends BaseAdapter {

	private Context mContext;
	private List<CategoryBean> mCategories;
	private LayoutInflater mInflater;

	public ChildCategoryAdapter(Context context,
			List<CategoryBean> childCategory) {
		this.mContext = context;
		this.mCategories = childCategory;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return (null == mCategories) ? 0 : mCategories.size();
	}

	@Override
	public CategoryBean getItem(int position) {
		return (null == mCategories) ? null : mCategories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == mContext) {
			return null;
		}

		if (null == mCategories || mCategories.size() == 0
				|| mCategories.size() <= position) {
			return null;
		}

		 ViewHolder holder = null;

		if (null == convertView) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.category_item, parent,
					false);
			holder.image = (ImageView) convertView
					.findViewById(R.id.catergory_image);
			holder.title = (TextView) convertView
					.findViewById(R.id.catergoryitem_title);
			holder.content = (TextView) convertView
					.findViewById(R.id.catergoryitem_content);
			// 使用tag存储数据
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// holder.image.setImageResource(mCategories.get(position).);
		holder.title.setText(mCategories.get(position).getName());
		holder.title.setTag(mCategories.get(position));
		// holder.content.setText(mContentValues[position]);

		return convertView;
	}

	public static class ViewHolder {
		ImageView image;
		TextView title;
		TextView content;
	}
}
