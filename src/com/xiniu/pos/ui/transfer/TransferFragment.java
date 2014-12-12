package com.xiniu.pos.ui.transfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

import com.xiniu.pos.R;
import com.xiniu.pos.ui.interfaces.AbstractAppFragment;


/**
 * 传输列表fragment
* @ClassName: ActiveFragment 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author misty-rain 
* @date 2014-12-5 下午3:29:24 
*
 */
public class TransferFragment extends AbstractAppFragment implements
		OnItemLongClickListener {

	//protected static final String TAG = ActiveFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "active_list";
	private boolean mIsWatingLogin;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	  
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.transfer_fragment, container,
				false);

		return view;
	}




	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
