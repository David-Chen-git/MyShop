package com.parcool.myshop.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class CommonUtil {

	private static CommonUtil commonUtil = null;

	private CommonUtil() {
	}

	public static CommonUtil getInstance() {
		if (commonUtil == null) {
			commonUtil = new CommonUtil();
		}
		return commonUtil;
	}

	private DisplayMetrics displayMeterics = new DisplayMetrics();

	// 获取屏幕宽度
	public int getPhoneWidth(Activity activity) {
		if (displayMeterics == null) {
			WindowManager wm = activity.getWindowManager();
			wm.getDefaultDisplay().getMetrics(displayMeterics);
		}
		return displayMeterics.widthPixels;
	}

	// 获取屏幕高度
	public int getPhoneHeight(Activity activity) {
		if (displayMeterics == null) {
			WindowManager wm = activity.getWindowManager();
			wm.getDefaultDisplay().getMetrics(displayMeterics);
		}
		return displayMeterics.heightPixels;
	}

	public int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	
	public int dp2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}


}
