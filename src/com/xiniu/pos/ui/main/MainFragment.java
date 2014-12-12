package com.xiniu.pos.ui.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiniu.pos.R;
import com.xiniu.pos.ui.commodity.CommodityDetail;
import com.xiniu.pos.ui.interfaces.AbstractAppFragment;
import com.xiniu.pos.ui.test.Test;

/**
 * 主fragment
 * 
 * @ClassName: ActiveFragment
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-5 下午3:29:24
 * 
 */
public class MainFragment extends AbstractAppFragment implements
		OnItemLongClickListener {

	// protected static final String TAG = ActiveFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "active_list";
	private boolean mIsWatingLogin;
	
	public List<String> imgtitleList; // 存放应用标题list
	public List<Integer> imgList; // 存放应用图片list
	public View[] itemViews;
	private GridView gvModem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, container, false);
		gvModem=(GridView) view.findViewById(R.id.gvmodem);
		initGridData();
		gvModem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent=new Intent(MainFragment.this.getActivity(),Test.class);
				startActivity(intent);
				
			}
		});

		return view;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	
	private void initGridData() {
		imgtitleList = new ArrayList<String>();
		imgList = new ArrayList<Integer>();

		imgtitleList.clear();
		imgList.clear();
		imgtitleList.add("会员查询");
		imgtitleList.add("商品查询");
		imgtitleList.add("库存查询");
		imgtitleList.add("会员充值");
		imgtitleList.add("收银对账");
		imgList.add(R.drawable.icon_vip_search);
		imgList.add(R.drawable.icon_shop_search);
	
		imgList.add(R.drawable.icon_order_search);
		imgList.add(R.drawable.icon_vip_insert);
		imgList.add(R.drawable.icon_shouyin);
		gvModem.setAdapter(new GridViewModemAdapter(imgtitleList, imgList));

	}

	/**
	 * 
	 * @ClassName: GridViewModemAdapter
	 * @Description: APPs 九宫格 数据适配源
	 * @author wutao
	 * 
	 * 
	 */
	public class GridViewModemAdapter extends BaseAdapter {

		public GridViewModemAdapter(List<String> imgTitles, List<Integer> images) {
			itemViews = new View[images.size()];
			for (int i = 0; i < itemViews.length; i++) {
				itemViews[i] = makeItemView(imgTitles.get(i), images.get(i));
			}
		}

		public View makeItemView(String imageTitilsId, int imageId) {
			// try {
			// LayoutInflater inflater = (LayoutInflater)
			// UtitlsModemFragment.this
			// .getSystemService(LAYOUT_INFLATER_SERVICE);
			// View
			// view=LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.grid_apps_item,
			// null);
			LayoutInflater inflater = LayoutInflater.from(MainFragment.this.getActivity());
			View itemView = inflater.inflate(R.layout.grid_apps_item, null);
			TextView title = (TextView) itemView.findViewById(R.id.TextItemId);
			title.setText(imageTitilsId);
			ImageView image = (ImageView) itemView
					.findViewById(R.id.ImageItemId);
			image.setImageResource(imageId);
			// image.setScaleType(ImageView.ScaleType.FIT_CENTER);
			return itemView;
			/*
			 * } catch (Exception e) {
			 * System.out.println("makeItemView Exception error" +
			 * e.getMessage()); return null; }
			 */

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return itemViews.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return itemViews[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {
				return itemViews[position];
			}
			return convertView;
		}
	}

}
