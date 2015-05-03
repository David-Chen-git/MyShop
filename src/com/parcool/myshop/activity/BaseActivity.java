package com.parcool.myshop.activity;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parcool.myshop.R;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		setBackListener();
	}

	// 子类调用这个方法设置其contentView，
	// 子类不要去调用setContentView。
	protected void setBaseContentView(int layoutResId) {
		getLayoutInflater().inflate(layoutResId, ((RelativeLayout) findViewById(R.id.rl_content)), true);
	}

	// 设置标题
	protected void setBaseTitle(String title) {
		((TextView) findViewById(R.id.tv_title)).setText(title);
	}

	// 设置分享按钮是否可见
	protected void setShareVisible(boolean isVisible) {
		if (isVisible) {
			findViewById(R.id.iv_share).setVisibility(View.VISIBLE);
		} else {
			findViewById(R.id.iv_share).setVisibility(View.GONE);
		}
	}

	// 设置分享按钮事件
	protected void setShareOnClickListener(View.OnClickListener onClickListener) {
		findViewById(R.id.iv_share).setOnClickListener(onClickListener);
	}

	// 设置返回键是否可见
	protected void setBackVisible(boolean isVisible) {
		if (isVisible) {
			findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
		} else {
			findViewById(R.id.iv_back).setVisibility(View.GONE);
		}
	}

	private void setBackListener(){
		findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BaseActivity.this.finish();
			}
		});
	}
	protected void setBackListener(View.OnClickListener onClickListener){
		findViewById(R.id.iv_back).setOnClickListener(onClickListener);
	}
	
	protected RelativeLayout getNavView(){
		return (RelativeLayout) findViewById(R.id.rl_nav);
	}

}
