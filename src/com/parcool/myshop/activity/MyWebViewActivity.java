package com.parcool.myshop.activity;

import com.parcool.myshop.impl.IWebViewProgress;
import com.parcool.myshop.view.MyChromeWebView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

public class MyWebViewActivity extends Activity {

	private String webViewUrl;
	private MyChromeWebView myChromeWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void init() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		webViewUrl = intent.getStringExtra("url");
		myChromeWebView = new MyChromeWebView(this, webViewUrl, new IWebViewProgress() {

			@Override
			public void setProgress(int newProgress) {
				// TODO Auto-generated method stub
				MyWebViewActivity.this.setWebViewProgressBarProgress(newProgress);
			}

			@Override
			public void onLogin() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MyWebViewActivity.this, ManiActivity.class);
				intent.putExtra("ext", "loginFromWeb");
				MyWebViewActivity.this.startActivityForResult(intent, 1);
			}

			@Override
			public void callPhone(String num) {
				// TODO Auto-generated method stub
				Log.d("tag", "打电话咯!电话号码：" + num);
				// Utils.getInstance().callPhone(MyWebViewActivity.this, num);
			}
		});

		setContentView(myChromeWebView);
	}

	// 按下返回键，如果当前页面可以返回那么返回HTML，否则返回Activity
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && myChromeWebView.canGoBack()) {
			myChromeWebView.resumeTimers();
			myChromeWebView.pauseTimers();
			myChromeWebView.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void setWebViewProgressBarProgress(int newProgress) {
		Log.d("tag", "网页加载进度：" + newProgress);
	}
}
