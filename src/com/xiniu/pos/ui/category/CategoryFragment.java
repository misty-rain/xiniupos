package com.xiniu.pos.ui.category;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiniu.pos.R;
import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.bean.PosItemBean;
import com.xiniu.pos.support.database.CategoryDBTask;
import com.xiniu.pos.ui.adapter.CategoryAdapter;
import com.xiniu.pos.ui.adapter.ChildCategoryAdapter;
import com.xiniu.pos.ui.commodity.CommodityList;
import com.xiniu.pos.ui.interfaces.AbstractAppFragment;

/**
 * 分类activity
 * 
 * @ClassName: ActiveFragment
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-5 下午3:29:24
 * 
 */
public class CategoryFragment extends AbstractAppFragment implements
		OnItemLongClickListener, OnItemClickListener, OnClickListener {

	private ListView mCateListView;
	private ImageView mCateIndicatorImg;
	private ImageButton mImageBtn;// 右侧的按钮
	private LayoutInflater layoutInflater;
	private CategoryAdapter mCateListAdapter;
	private List<CategoryBean> mCategories;
	private int mFromY = 0;
	private LinearLayout secondelistviewLayout;
	private ListView catergory_seconde_listview;// 菜单的二级分类
	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.category, container, false);
		initView();
		initParentCategoryData();
		initChildCategoryData();

		return view;
	}

	private void moveIconAnimation() {
		// 用于计算mCateIndicatorImg的高度
		int itemHeight = calculateListViewItemHeight();
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		mCateIndicatorImg.measure(w, h);

		// 第一次进来设置indicator的位置
		doAnimation(itemHeight / 2 - mCateIndicatorImg.getMeasuredHeight());

	}

	private void initParentCategoryData() {
		mCategories = CategoryDBTask.getInstance().getParentCategoryList();
		mCateListAdapter = new CategoryAdapter(this.getActivity(), mCategories);
		mCateListView.setAdapter(mCateListAdapter);
		mCateListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {

				CategoryBean cb = null;
				if (view instanceof CheckedTextView) {
					cb = (CategoryBean) view.getTag();
				} else {
					CheckedTextView ctv = (CheckedTextView) view
							.findViewById(R.id.cate_tv);
					cb = (CategoryBean) ctv.getTag();
				}
				if (cb == null)
					return;

				catergory_seconde_listview.setAdapter(new ChildCategoryAdapter(
						CategoryFragment.this.getActivity(), CategoryDBTask
								.getInstance().getCategoryListByParentId(
										cb.getId())));
				if (null != mCateListAdapter) {
					mCateListAdapter.setSelectedPos(arg2);
				}
				int toY = view.getTop() + view.getHeight() / 2;
				doAnimation(toY);

			}
		});

		moveIconAnimation();
	}

	private void initChildCategoryData() {

		catergory_seconde_listview.setAdapter(new ChildCategoryAdapter(
				CategoryFragment.this.getActivity(), CategoryDBTask
						.getInstance().getCategoryListByParentId("3")));

		catergory_seconde_listview
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> adapterview,
							View view, int parent, long id) {
						CategoryBean pb = null;
						if (view instanceof TextView) {
							pb = (CategoryBean) view.getTag();
						} else {
							TextView ctv = (TextView) view
									.findViewById(R.id.catergoryitem_title);
							pb = (CategoryBean) ctv.getTag();
						}
						if (pb == null)
							return;
						Intent intent=new Intent(CategoryFragment.this.getActivity(),CommodityList.class);
						intent.putExtra("categoryId", pb.getId());
						startActivity(intent);

					}
				});
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	protected void initView() {

		mCateListView = (ListView) view.findViewById(R.id.catergory_listview);
		mCateIndicatorImg = (ImageView) view
				.findViewById(R.id.cate_indicator_img);
		secondelistviewLayout = (LinearLayout) view
				.findViewById(R.id.seconde_listview);
		catergory_seconde_listview = (ListView) view
				.findViewById(R.id.catergory_seconde_listview);
		mCateListView.setOnItemClickListener(this);

	}

	private int calculateListViewItemHeight() {
		ListAdapter listAdapter = mCateListView.getAdapter();
		if (listAdapter == null) {
			return 0;
		}

		int totalHeight = 0;
		int count = listAdapter.getCount();
		for (int i = 0; i < count; i++) {
			View listItem = listAdapter.getView(i, null, mCateListView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		return totalHeight / count;
	}

	private void doAnimation(int toY) {
		int cateIndicatorY = mCateIndicatorImg.getTop()
				+ mCateIndicatorImg.getMeasuredHeight() / 2;
		TranslateAnimation animation = new TranslateAnimation(0, 0, mFromY
				- cateIndicatorY, toY - cateIndicatorY);
		animation.setInterpolator(new AccelerateDecelerateInterpolator());
		animation.setFillAfter(true);
		animation.setDuration(400);
		mCateIndicatorImg.startAnimation(animation);
		mFromY = toY;
	}

	/**
	 * 二级分类菜单
	 * 
	 * @author zhihong CategoryAdapter
	 */
	private class ChildCatergorAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageIds.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@SuppressWarnings("null")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = new ViewHolder();
			layoutInflater = LayoutInflater.from(CategoryFragment.this
					.getActivity());

			// 组装数据
			if (convertView == null) {
				convertView = layoutInflater.inflate(R.layout.category_item,
						null);
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
			holder.image.setImageResource(mImageIds[position]);
			holder.title.setText(mTitleValues[position]);
			holder.content.setText(mContentValues[position]);
			// holder.title.setText(array[position]);

			return convertView;

		}

	}

	// 适配显示的图片数组
	private Integer[] mImageIds = { R.drawable.catergory_appliance,
			R.drawable.catergory_book, R.drawable.catergory_cloth,
			R.drawable.catergory_deskbook, R.drawable.catergory_digtcamer,
			R.drawable.catergory_furnitrue, R.drawable.catergory_mobile,
			R.drawable.catergory_skincare };
	// 给照片添加文字显示(Title)
	private String[] mTitleValues = { "家电", "图书", "衣服", "笔记本", "数码", "家具",
			"手机", "护肤" };

	private String[] mContentValues = { "家电/生活电器/厨房电器", "电子书/图书/小说",
			"男装/女装/童装", "笔记本/笔记本配件/产品外设", "摄影摄像/数码配件", "家具/灯具/生活用品",
			"手机通讯/运营商/手机配件", "面部护理/口腔护理/..." };

	public static class ViewHolder {
		ImageView image;
		TextView title;
		TextView content;
	}

	// 点击左边的item时候调用本方法
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (null != mCateListAdapter) {
			mCateListAdapter.setSelectedPos(position);
		}
		int toY = view.getTop() + view.getHeight() / 2;
		doAnimation(toY);

	}

	@Override
	public void onClick(View v) {

	}

}
