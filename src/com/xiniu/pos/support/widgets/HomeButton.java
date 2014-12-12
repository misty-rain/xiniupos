package com.xiniu.pos.support.widgets;



import com.xiniu.pos.R;
import com.xiniu.pos.support.utils.ImageUtility;
import com.xiniu.pos.ui.home.HomeClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class HomeButton extends ImageView{

	
	private Bitmap bitmap;
	private Bitmap home_flight;
	private Bitmap label_new;
	private int state = 0; // 按下

	private int color;
	private float textsize;
	private boolean big;
	private int home;
	private String text;

	private int screenW;
	private int screenH;
	
	//点击事件
	private HomeClickListener listener=null;

	private int[] colors = { getResources().getColor(R.color.index_frament_icon_news),
			getResources().getColor(R.color.index_fragment_icon_mycard),
			getResources().getColor(R.color.index_fragment_icon_payment),
			getResources().getColor(R.color.index_fragment_icon_transfer),
			getResources().getColor(R.color.index_fragment_icon_record)
			 };

	private Bitmap[] bitmaps = {
			BitmapFactory.decodeResource(getResources(), R.drawable.home_news_icon),
			BitmapFactory.decodeResource(getResources(),
					R.drawable.home_groupbuy),
			BitmapFactory.decodeResource(getResources(), R.drawable.home_mycard_record),
			BitmapFactory.decodeResource(getResources(),
					R.drawable.home_lastmin_1),
			//BitmapFactory
				//	.decodeResource(getResources(), R.drawable.home_jiaofei_icon),
			//BitmapFactory.decodeResource(getResources(), R.drawable.home_car),
			//BitmapFactory.decodeResource(getResources(),
				//	R.drawable.home_scenery)

	};

	public HomeButton(Context context) {
		super(context);
	}

	public HomeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		bitmap = ImageUtility.zoomImage(BitmapFactory.decodeResource(
				getResources(), R.drawable.fingerprint), 127, 122);

		//label_new 				=BitmapFactory.decodeResource(getResources(), R.drawable.label_new);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.HomeButton);
		color = typedArray.getInt(R.styleable.HomeButton_backcolor, 0);
		textsize = typedArray.getDimension(R.styleable.HomeButton_textSize, 32);
		big = typedArray.getBoolean(R.styleable.HomeButton_big, true);
		home = typedArray.getInt(R.styleable.HomeButton_home, 0);
		text = typedArray.getString(R.styleable.HomeButton_text);
		System.out.println("color:" + color + " textsize:" + textsize + " big:"
				+ big + " home:" + home);
		home_flight = bitmaps[home];
		// 获取屏幕的大小，
		screenW = ((Activity) context).getWindow().getWindowManager()
				.getDefaultDisplay().getWidth() / 2 - 16;
		int screewDouble=(screenW / 2-4);
		if (big) {
			screenH = screenW+screewDouble;
		} else {
			//int screewSmall=
			screenH = screenW-4;
		}

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 重新设置屏幕大小
		setMeasuredDimension(screenW, screenH);
	}

	/*
	 * orange 2182F7 light red 7359EF 紫 B551A5 Blue CE8A39 air CEBE00 texi
	 * 9CAA00 jingdian 00AA73
	 */

	// 这里计算大小不要用cnavas的大小来计算，会出错，因为真机以模拟器中得到的大小不一样，具体为什么是这样还没有找到原因
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(colors[color]);
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(32);
		if (big) {
			Matrix matrix = new Matrix();
			matrix.postTranslate(this.getWidth() / 2 - home_flight.getWidth()
					/ 2, this.getHeight() / 2 - home_flight.getHeight() / 2);
			canvas.drawText(text, 10, 40, paint);
			canvas.drawBitmap(home_flight, matrix, paint);
		} else {
			Matrix matrix_small			=new Matrix();
			matrix_small.postTranslate(10, this.getHeight() / 2 - home_flight.getHeight() / 2);
			canvas.drawBitmap(home_flight, matrix_small, new Paint());
			if(home==3){
				paint.setTextSize(32);
				canvas.drawText("我的校园卡", home_flight.getWidth()+20, this.getHeight() / 2-home_flight.getHeight()/2+10 , paint);
				//canvas.drawText("", home_flight.getWidth()+20, this.getHeight() / 2+home_flight.getHeight()/2, paint);
			}else if(home==5){
				paint.setTextSize(16);
				canvas.drawText("", home_flight.getWidth()+20, this.getHeight() / 2-home_flight.getHeight()/2+10 , paint);
				canvas.drawText("", home_flight.getWidth()+20, this.getHeight() / 2+home_flight.getHeight()/2, paint);
			}else{
				canvas.drawText(text, home_flight.getWidth()+20, this.getHeight() / 2+home_flight.getHeight()/2 , paint);
			}
			if(home==6){
				Matrix matrix_new		=new Matrix();
				matrix_new.postTranslate(screenW-label_new.getWidth(), 0);
				canvas.drawBitmap(label_new, matrix_new, new Paint());
			}
			
		}
		if (state == 1) {
			Matrix matrix2 = new Matrix();
			matrix2.postTranslate(this.getWidth() / 2 - bitmap.getWidth() / 2,
					this.getHeight() / 2 - bitmap.getHeight() / 2);
			canvas.drawBitmap(bitmap, matrix2, new Paint());
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float start = 1.0f;
		float end = 0.95f;
		Animation scaleAnimation = new ScaleAnimation(start, end, start, end,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		Animation endAnimation = new ScaleAnimation(end, start, end, start,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(200);
		scaleAnimation.setFillAfter(true);
		endAnimation.setDuration(200);
		endAnimation.setFillAfter(true);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			this.startAnimation(scaleAnimation);
			state = 1;
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			this.startAnimation(endAnimation);
			state = 0;
			invalidate();
			if(listener!=null){
				listener.onclick();
			}
			break;
		// 滑动出去不会调用action_up,调用action_cancel
		case MotionEvent.ACTION_CANCEL:
			this.startAnimation(endAnimation);
			state = 0;
			invalidate();
			break;
		}
		// 不返回true，Action_up就响应不了
		return true;
	}

	/**
	 * 加入响应事件
	 * @param clickListener
	 */
	public void setOnHomeClick(HomeClickListener clickListener){
		this.listener			=clickListener;
	}



}
