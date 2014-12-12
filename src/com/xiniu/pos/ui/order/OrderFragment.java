package com.xiniu.pos.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;

import com.xiniu.pos.R;
import com.xiniu.pos.ui.category.Category;
import com.xiniu.pos.ui.category.CategoryFragment;
import com.xiniu.pos.ui.interfaces.AbstractAppFragment;
import com.xiniu.pos.ui.membership.Member;

/**
 * 收银台fragment
 * 
 * @ClassName: ActiveFragment
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author misty-rain
 * @date 2014-12-5 下午3:29:24
 * 
 */
public class OrderFragment extends AbstractAppFragment implements
		OnItemLongClickListener {

	// protected static final String TAG = ActiveFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "active_list";
	private boolean mIsWatingLogin;
	private Button btnVip, btnNormal;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.order_fragment, container, false);
		btnVip = (Button) view.findViewById(R.id.btnVIP);

		btnNormal = (Button) view.findViewById(R.id.btnNormal);
		initView();

		return view;
	}

	private void initView() {
		btnVip.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderFragment.this.getActivity(),
						Member.class);
				startActivity(intent);

			}
		});

		btnNormal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderFragment.this.getActivity(),
						Category.class);
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

}
