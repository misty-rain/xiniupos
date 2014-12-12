package com.xiniu.pos.ui.adapter;

import java.math.BigDecimal;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiniu.api.internal.util.StringUtils;
import com.xiniu.pos.R;
import com.xiniu.pos.bean.PosItemBean;
import com.xiniu.pos.bean.TempComCarBean;
import com.xiniu.pos.support.database.TempComCarDBTask;
import com.xiniu.pos.support.exception.AppException;
import com.xiniu.pos.support.utils.AppLogger;
import com.xiniu.pos.support.utils.GlobalContext;
import com.xiniu.pos.support.utils.SharePreferenceUtil;
import com.xiniu.pos.support.utils.StringUtility;
import com.xiniu.pos.support.utils.TimeTool;
import com.xiniu.pos.support.utils.TimeUtil;
import com.xiniu.pos.support.utils.Utility;
import com.xiniu.pos.support.widgets.AddAndSubView;

/**
 * 
 * @ClassName: CommodityAdapter
 * @Description: 商品列表适配器
 * @author misty-rain
 * @date 2014-12-10 下午3:59:54
 * 
 */
public class CommodityAdapter extends BaseAdapter {
	List<PosItemBean> list;
	protected Context context;
	protected LayoutInflater inflater;
	protected ListView listview;
	private SharePreferenceUtil sp;

	public CommodityAdapter(Context context, List<PosItemBean> list,
			ListView listView) {
		this.list = list;
		// this.inflater = LayoutInflater.from(context);
		this.inflater = LayoutInflater.from(context);
		this.listview = listView;
		// this.context = context;

		listView.setRecyclerListener(new AbsListView.RecyclerListener() {

			@Override
			public void onMovedToScrapHeap(View view) {
				ViewHolder holder = (ViewHolder) view.getTag();
				if (holder == null) {
					return;
				}
				// holder.imgThumbnails.setImageDrawable(null);

			}
		});
	}

	private List<PosItemBean> getList() {
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
					R.layout.listview_item_universal_layout, parent, false);
			holder.itemTitle = (TextView) convertView
					.findViewById(R.id.listitem_title);
			holder.imgThumbnails = (ImageView) convertView
					.findViewById(R.id.listitem_Thumbnails);
			holder.unitPrice = (TextView) convertView
					.findViewById(R.id.listitem_subtitle_or_time);
			holder.btnAdd = (Button) convertView
					.findViewById(R.id.btn_call_contacts);
			holder.aasComEdit = (AddAndSubView) convertView
					.findViewById(R.id.aasComEdite);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		final PosItemBean item = list.get(position);
		holder.btnAdd.setTag(item);
		holder.itemTitle.setText(item.getName());
		holder.itemTitle.setTag(item);
		holder.unitPrice.setText("￥" + item.getUnit_price());
		holder.btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				try {
					sp = GlobalContext.getInstance().getSpUtil();
					TempComCarBean cb = new TempComCarBean();
					cb.setCommodName(item.getName());
					cb.setId(item.getId());
					String count=String.valueOf(holder.aasComEdit.getNum());
					cb.setCommodTotalCount(Integer.parseInt(count));
					cb.setSingleComPicUrl("http://www.baidu.com");
					cb.setSingleCommodPrice(item.getUnit_price());
					cb.setSingleCommodTotalCount(Integer.parseInt(count));
					BigDecimal bdCount = new BigDecimal(count);
					BigDecimal bdTotal = new BigDecimal(item.getUnit_price());
					cb.setCommodTotalPrice(bdCount.multiply(bdTotal)
							.doubleValue());
					cb.setOperationTime(TimeUtil.getCurrentTime());
					TempComCarDBTask.getInstance().addOrUpdateItemToTempCar(cb);
				} catch (Exception e) {
					Toast.makeText(context, e.getMessage(), 300).show();
				

				}
				Utility.ToastMessage(GlobalContext.getInstance(),"添加商品成功");

			}
		});

		return convertView;
	}

	private class ViewHolder {

		TextView itemTitle;

		TextView unitPrice;

		ImageView imgThumbnails;

		Button btnAdd;
		AddAndSubView aasComEdit;
	}

}
