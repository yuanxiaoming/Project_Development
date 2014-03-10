package com.cs.animationvideo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cs.animationvideo.R;

public class MyImageView extends RelativeLayout {
	private ImageView m_imageview;
	private TextView m_textview;
	private int color;
	private String text;
	private boolean singleLine;
	private int textSize;
	private int src;
	private int scaleType;

	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(context, attrs);
		init(context);
	}

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context, attrs);
		init(context);
	}

	public MyImageView(Context context) {
		super(context);
		init(context);
	}

	private static final ScaleType[] sScaleTypeArray = { ScaleType.MATRIX,
			ScaleType.FIT_XY, ScaleType.FIT_START, ScaleType.FIT_CENTER,
			ScaleType.FIT_END, ScaleType.CENTER, ScaleType.CENTER_CROP,
			ScaleType.CENTER_INSIDE };

	private void initAttrs(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.MyImageView);
		try {
			color = a.getColor(R.styleable.MyImageView_textColor,Color.parseColor("#FFFFFF"));
			text = a.getString(R.styleable.MyImageView_text);
			singleLine = a.getBoolean(R.styleable.MyImageView_singleLine, true);
			textSize = a.getDimensionPixelSize(R.styleable.MyImageView_textSize, 14);
			src = a.getResourceId(R.styleable.MyImageView_src,R.drawable.biz_news_specialmedia_default_bg);
			scaleType = a.getInt(R.styleable.MyImageView_scaleType, 6);
		} finally {
			a.recycle();
		}
	}

	private void init(Context context) {
		View.inflate(context, R.layout.custom_myimageview_merge, this);
		m_imageview = (ImageView) findViewById(R.id.iv_custom_myimageview);
		m_textview = (TextView) findViewById(R.id.tv_custom_myimageview);

//		if(m_textview == null  | m_imageview == null){
//			return ;
//		}
		// default attrs
		m_textview.setText(text);
		m_textview.setTextColor(color);
		m_textview.setSingleLine(singleLine);
		m_textview.setTextSize(textSize);
		m_textview.setGravity(Gravity.CENTER);
		m_imageview.setImageResource(src);
		this.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				m_imageview.setLayoutParams(new LayoutParams(MyImageView.this.getWidth(), MyImageView.this.getHeight()));
				m_imageview.setScaleType(sScaleTypeArray[scaleType]);
			}
		});
		
		
		removeView(m_textview);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_BOTTOM,R.id.iv_custom_myimageview);
		params.addRule(RelativeLayout.ALIGN_LEFT,R.id.iv_custom_myimageview);
		params.addRule(RelativeLayout.ALIGN_RIGHT,R.id.iv_custom_myimageview);
		addView(m_textview, params);
	}

	public void setText(String text) {
		m_textview.setText(text);
	}

	public void setTextColor(int color) {
		m_textview.setTextColor(color);
	}

	public void setSingleLine(boolean singleLine) {
		m_textview.setSingleLine(singleLine);
	}

	public void setTextSize(float textSize) {
		m_textview.setTextSize(textSize);
	}

	public void setImageResource(int resId) {
		m_imageview.setImageResource(resId);
		invalidate();
	}

	public void setScaleType(ScaleType scaleType) {
		m_imageview.setScaleType(scaleType);
	}

	public void setImageLayoutParams(LayoutParams params) {
		m_imageview.setLayoutParams(params);
	}
	
	public void setTextGravity(int gravity){
		m_textview.setGravity(gravity);
	}
	
	public ImageView getImageView(){
		return m_imageview ;
	}
}
