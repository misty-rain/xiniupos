package com.xiniu.pos.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xiniu.pos.R;
import com.xiniu.pos.bean.TempComCarBean;
import com.xiniu.pos.support.utils.SharePreferenceUtil;

public class TempCarAdapter extends BaseAdapter {

	List<TempComCarBean> list;
	protected Context context;
	protected LayoutInflater inflater;
	protected ListView listview;
	private SharePreferenceUtil sp;

	public TempCarAdapter(Context context, List<TempComCarBean> list,
			ListView listView) {
		this.list = list;
		// this.inflater = LayoutInflater.from(context);
		this.inflater = LayoutInflater.from(context);
		
	}

	private List<TempComCarBean> getList() {
		return list;
	}

	@Override
	public int getCount() {
		if (getList() != null && getList().size() > 0) {
			return getList().size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getList().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null || convertView.getTag() == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.listview_item_tempcarlayout, parent, false);
			holder.itemTitle = (TextView) convertView
					.findViewById(R.id.listitem_title);
			holder.imgThumbnails = (ImageView) convertView
					.findViewById(R.id.listitem_Thumbnails);
			holder.count = (TextView) convertView
					.findViewById(R.id.listitem_unitprice);
			holder.unitPrice=(TextView) convertView.findViewById(R.id.listitem_price);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		final TempComCarBean item = list.get(position);
		holder.itemTitle.setText(item.getCommodName());
		holder.itemTitle.setTag(item);
		holder.unitPrice.setText("￥: " + item.getCommodTotalPrice());
		holder.count.setText("数量:"+item.getCommodTotalCount());
		

		return convertView;
	}

	private class ViewHolder {

		TextView itemTitle;

		TextView count;

		ImageView imgThumbnails;
		TextView unitPrice;

	}

}
