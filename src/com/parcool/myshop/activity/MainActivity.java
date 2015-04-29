package com.parcool.myshop.activity;

import com.parcool.myshop.R;
import com.parcool.myshop.impl.IWebViewStatus;
import com.parcool.myshop.utils.DialogUtil;
import com.parcool.myshop.view.MyChromeWebView;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private LinearLayout llContainer;
	private MyChromeWebView myChromeWebViewOne;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		DialogUtil.getInstance().showSingelProgressBar(this, R.id.ll_container);
		myChromeWebViewOne = new MyChromeWebView(this, "http://www.baidu.com", new IWebViewStatus() {
			
			@Override
			public void onPageFinished() {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "加载完毕！", Toast.LENGTH_SHORT).show();
				DialogUtil.getInstance().dismissProgressBar();
			}
			
			@Override
			public void onPageError(int errorCode, String description) {
				// TODO Auto-generated method stub
				DialogUtil.getInstance().dismissProgressBar();
				Toast.makeText(MainActivity.this, "errorCode="+errorCode+",description="+description, Toast.LENGTH_LONG).show();
			}
		});
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		myChromeWebViewOne.setLayoutParams(lp);
		llContainer.addView(myChromeWebViewOne);
	}

	// 设置字体不随系统设置而变动
	@Override
	public Resources getResources() {
		Resources res = super.getResources();
		Configuration config = new Configuration();
		config.setToDefaults();
		res.updateConfiguration(config, res.getDisplayMetrics());
		return res;
	}
	
	// 按下返回键，如果当前页面可以返回那么返回HTML，否则返回Activity
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && myChromeWebViewOne.canGoBack()) {
			myChromeWebViewOne.resumeTimers();
			myChromeWebViewOne.pauseTimers();
			myChromeWebViewOne.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
