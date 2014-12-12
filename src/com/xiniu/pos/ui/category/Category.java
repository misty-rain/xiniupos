package com.xiniu.pos.ui.category;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.xiniu.pos.R;
import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.support.database.CategoryDBTask;
import com.xiniu.pos.ui.adapter.CategoryAdapter;
import com.xiniu.pos.ui.adapter.ChildCategoryAdapter;
import com.xiniu.pos.ui.commodity.CommodityList;
import com.xiniu.pos.ui.interfaces.BaseActivity;

/**
 * 分类activity
 * 
 * @ClassName: Category
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-11 下午2:38:22
 * 
 */
public class Category extends BaseActivity {

	private ListView mCateListView;
	private ImageView mCateIndicatorImg;
	private ImageButton mImageBtn;// 右侧的按钮
	private LayoutInflater layoutInflater;
	private CategoryAdapter mCateListAdapter;
	private List<CategoryBean> mCategories;
	private int mFromY = 0;
	private LinearLayout secondelistviewLayout;
	private ListView catergory_seconde_listview;// 菜单的二级分类

	@Override
	protected int getLayoutId() {
		return R.layout.category;
	}

	@Override
	protected boolean hasBackButton() {
		return true;
	}

	@Override
	protected int getActionBarTitle() {
		return R.string.action_bar_title_category;
	}

	@Override
	protected int getActionBarCustomView() {
		return R.layout.actionbar_custom_backtitle;
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		super.init(savedInstanceState);

		setActionBarTitle(getActionBarTitle());
		initView();
		initParentCategoryData();
		initChildCategoryData();

	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
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
		mCateListAdapter = new CategoryAdapter(this, mCategories);
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
						Category.this, CategoryDBTask.getInstance()
								.getCategoryListByParentId(cb.getId())));
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
				Category.this, CategoryDBTask.getInstance()
						.getCategoryListByParentId("3")));

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
						Intent intent = new Intent(Category.this,
								CommodityList.class);
						intent.putExtra("categoryId", pb.getId());
						startActivity(intent);

					}
				});
	}

	protected void initView() {

		mCateListView = (ListView) findViewById(R.id.catergory_listview);
		mCateIndicatorImg = (ImageView) findViewById(R.id.cate_indicator_img);
		secondelistviewLayout = (LinearLayout) findViewById(R.id.seconde_listview);
		catergory_seconde_listview = (ListView) findViewById(R.id.catergory_seconde_listview);

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

}
