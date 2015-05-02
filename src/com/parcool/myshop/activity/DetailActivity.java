package com.parcool.myshop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parcool.myshop.R;
import com.parcool.myshop.view.SmoothImageView;

public class DetailActivity extends BaseActivity {

	private TextView tvEn, tvZh;

	// private int mPosition;
	private int mLocationX;
	private int mLocationY;
	private int mWidth;
	private int mHeight;
	private SmoothImageView imageView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_detail);
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		String titleZh = getIntent().getStringExtra("titleZh");
		String titleEn = getIntent().getStringExtra("titleEn");
		imageView = (SmoothImageView) findViewById(R.id.iv);
		tvEn = (TextView) findViewById(R.id.tv_en);
		tvZh = (TextView) findViewById(R.id.tv_zh);
//		imageView.setImageResource(imgUrl);
		tvEn.setText(titleEn);
		tvZh.setText(titleZh);
		
		
		String imgUrl = getIntent().getStringExtra("imgUrl");
		mLocationX = getIntent().getIntExtra("locationX", 0);
		mLocationY = getIntent().getIntExtra("locationY", 0);
		mWidth = getIntent().getIntExtra("width", 0);
		mHeight = getIntent().getIntExtra("height", 0);

//		imageView = new SmoothImageView(this);
		imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
		imageView.transformIn();
		imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
		imageView.setScaleType(ScaleType.FIT_CENTER);
//		setContentView(imageView);
		Log.d("tag", "imgUrl="+imgUrl);
		ImageLoader.getInstance().displayImage("drawable://"+imgUrl, imageView);
		LinearLayout llContainer = (LinearLayout) findViewById(R.id.xll);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
		alphaAnimation.setStartOffset(200);
		alphaAnimation.setDuration(300);
		for (int i = 0; i < llContainer.getChildCount(); i++) {
			if (llContainer.getChildAt(i) != imageView) {
				llContainer.getChildAt(i).setVisibility(View.VISIBLE);
				llContainer.getChildAt(i).startAnimation(alphaAnimation);
			}
		}

		imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
			@Override
			public void onTransformComplete(int mode) {
				if (mode == 1) {
					
//					LinearLayout llContainer = (LinearLayout) findViewById(R.id.xll);
//					AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
//					alphaAnimation.setDuration(300);
//					for (int i = 0; i < llContainer.getChildCount(); i++) {
//						if (llContainer.getChildAt(i) != imageView) {
//							llContainer.getChildAt(i).setVisibility(View.VISIBLE);
//							llContainer.getChildAt(i).startAnimation(alphaAnimation);
//						}
//					}
//					AnimationSet animationSet = new AnimationSet(true);
//					animationSet.addAnimation(alphaAnimation);
//					rootView.startAnimation(animationSet);
				}
			}
		});
		
		


//		// mDatas = (ArrayList<String>)
//		// getIntent().getSerializableExtra("images");
//		// mPosition = getIntent().getIntExtra("position", 0);
//
//		xll = (XLinearLayout) findViewById(R.id.xll);
//
//		xll.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
//		xll.transformIn();
//		// imageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
//		// imageView.setScaleType(ScaleType.FIT_CENTER);
//		// imageView.setImageResource(imgUrl);
//		xll.start();
	}

//	@Override
//	public void onBackPressed() {
//		xll.setOnTransformListener(new XLinearLayout.TransformListener() {
//			@Override
//			public void onTransformComplete(int mode) {
//				if (mode == 2) {
//					finish();
//				}
//			}
//		});
//		xll.transformOut();
//	}

	@Override
	public void onBackPressed() {
		LinearLayout llContainer = (LinearLayout) findViewById(R.id.xll);
		AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
		alphaAnimation.setDuration(400);
		for (int i = 0; i < llContainer.getChildCount(); i++) {
			if (llContainer.getChildAt(i) != imageView) {
				llContainer.getChildAt(i).setVisibility(View.VISIBLE);
				llContainer.getChildAt(i).startAnimation(alphaAnimation);
			}
		}
		alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				LinearLayout llContainer = (LinearLayout) findViewById(R.id.xll);
				for (int i = 0; i < llContainer.getChildCount(); i++) {
					if (llContainer.getChildAt(i) != imageView) {
						llContainer.getChildAt(i).setVisibility(View.GONE);
					}
				}
			}
		});
		imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
			@Override
			public void onTransformComplete(int mode) {
				if (mode == 2) {
					finish();
				}
			}
		});
		imageView.transformOut();
	}
	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			overridePendingTransition(0, 0);
		}
	}
}