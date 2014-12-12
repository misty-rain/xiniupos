package com.xiniu.pos.ui.notification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import com.xiniu.pos.R;
import com.xiniu.pos.ui.interfaces.AbstractAppActivity;

public class Notification extends AbstractAppActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
	TextView tv =(TextView) findViewById(R.id.txtmessage);
		Intent intent = getIntent();
		if (null != intent) {
			Bundle bundle = getIntent().getExtras();
			String title = bundle
					.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
			String content = bundle.getString(JPushInterface.EXTRA_ALERT);
			tv.setText(content);
		}
	}

}
