package com.parcool.myshop.activity;

import com.parcool.myshop.R;
import com.parcool.myshop.fragment.FragmentPage1;
import com.parcool.myshop.fragment.FragmentPage2;
import com.parcool.myshop.fragment.FragmentPage3;
import com.parcool.myshop.fragment.FragmentPage4;
import com.parcool.myshop.fragment.FragmentPage5;
import com.parcool.myshop.impl.IWebViewStatus;
import com.parcool.myshop.utils.DialogUtil;
import com.parcool.myshop.view.MyChromeWebView;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	private LinearLayout llContainer;
	private MyChromeWebView myChromeWebViewOne;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// init();
		initTab();
	}

	private void init() {
		// TODO Auto-generated method stub
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		myChromeWebViewOne = new MyChromeWebView(this, "http://www.google.com", new IWebViewStatus() {

			@Override
			public void onPageStarted() {
				// TODO Auto-generated method stub
				DialogUtil.getInstance().showDotCircelProgressBar(MainActivity.this, R.id.ll_container);
			}

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
				Toast.makeText(MainActivity.this, "errorCode=" + errorCode + ",description=" + description, Toast.LENGTH_LONG).show();
			}

		});
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
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

	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;

	// 定义一个布局
	private LayoutInflater layoutInflater;

	// 定义数组来存放Fragment界面
	private Class<?> fragmentArray[] = { FragmentPage1.class, FragmentPage2.class, FragmentPage3.class, FragmentPage4.class, FragmentPage5.class };

	// 定义数组来存放按钮图片
	private int mImageViewArray[] = { R.drawable.tab_home_btn, R.drawable.tab_message_btn, R.drawable.tab_selfinfo_btn, R.drawable.tab_square_btn, R.drawable.tab_more_btn };

	// Tab选项卡的文字
	private String mTextviewArray[] = { "首页", "消息息消息消", "好", "广", "更" };

	private void initTab() {
		// 实例化布局对象
		layoutInflater = LayoutInflater.from(this);

		// 实例化TabHost对象，得到TabHost
		mTabHost = (FragmentTabHost) findViewById(R.id.fragment_tab_host);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.ll_container);

		// 得到fragment的个数
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}

	/**
	 * 给Tab按钮设置图标和文字
	 */
	private View getTabItemView(int index) {
//		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
		View view = layoutInflater.inflate(R.layout.tab_item_view, mTabHost.getTabWidget(),false);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
		lp.weight = 1;
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);
		view.setLayoutParams(lp);
		return view;
	}
}
