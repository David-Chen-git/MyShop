package com.parcool.myshop.utils;

import com.parcool.myshop.R;
import com.parcool.myshop.view.DotCircleProgressView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DialogUtil {
	// 防止被实例化
	private DialogUtil() {
	}

	// 定义私有实例
	private static DialogUtil util = null;

	// 对外公开的获取实例方法
	public static DialogUtil getInstance() {
		if (util == null) {
			util = new DialogUtil();
		}
		return util;
	}

	private ProgressBar pb;
	private RelativeLayout viewGroupRl;
	private LinearLayout viewGroupLl;
	private FrameLayout viewGroupFl;
	private ProgressDialog pd;
	private int backCount = 0;
	private Activity dismissActivity = null;
	private int LOADING_TOP_MARGIN = 100;// 单位，dp。实际应用中会自动转成dp
	private final int DOT_CIRCEL_WIDTH = 40;// 单位，dp。实际应用中会自动转成dp

	/***
	 * 显示单独的progressBar.如果要设置某个例外，那么也设置这个view的tag为“gone”或“invisible”
	 * 
	 * @param activity
	 * @param viewGroupResId
	 *            父容器资源ID。只能是LinearLayout或者RelativeLayout或者FrameLayout
	 */
	public void showSingelProgressBar(Activity activity, int viewGroupResId) {
		dismissProgressBar();
		View tempView = null;
		if (activity.findViewById(viewGroupResId) instanceof RelativeLayout) {
			viewGroupRl = (RelativeLayout) activity.findViewById(viewGroupResId);
			for (int i = 0; i < viewGroupRl.getChildCount(); i++) {
				tempView = viewGroupRl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupRl:共" + viewGroupRl.getChildCount() + "个childCount!");
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			pb = new ProgressBar(activity);
			pb.setLayoutParams(layoutParams);
			pb.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.circle_progressbar));
			viewGroupRl.addView(pb);
		} else if (activity.findViewById(viewGroupResId) instanceof LinearLayout) {
			viewGroupLl = (LinearLayout) activity.findViewById(viewGroupResId);
			for (int i = 0; i < viewGroupLl.getChildCount(); i++) {
				tempView = viewGroupLl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupLl:共" + viewGroupLl.getChildCount() + "个childCount!");
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			pb = new ProgressBar(activity);
			pb.setLayoutParams(layoutParams);
			pb.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.circle_progressbar));
			viewGroupLl.addView(pb);
		} else if (activity.findViewById(viewGroupResId) instanceof FrameLayout) {
			viewGroupFl = (FrameLayout) activity.findViewById(viewGroupResId);
			for (int i = 0; i < viewGroupFl.getChildCount(); i++) {
				tempView = viewGroupFl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupFl:共" + viewGroupFl.getChildCount() + "个childCount!");
			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			pb = new ProgressBar(activity);
			pb.setLayoutParams(layoutParams);
			pb.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.circle_progressbar));
			viewGroupFl.addView(pb);
		}
		tempView = null;

	}

	/***
	 * 显示单独的progressBar.如果要设置某个例外，那么也设置这个view的tag为“gone”或“invisible”
	 * 
	 * @param activity
	 * @param viewGroupResId
	 *            父容器资源ID。只能是LinearLayout或者RelativeLayout或者FrameLayout
	 */
	public void showDotCircelProgressBar(Activity activity, int viewGroupResId) {
		dismissProgressBar();
		View tempView = null;
		if (activity.findViewById(viewGroupResId) instanceof RelativeLayout) {
			viewGroupRl = (RelativeLayout) activity.findViewById(viewGroupResId);
			for (int i = 0; i < viewGroupRl.getChildCount(); i++) {
				tempView = viewGroupRl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupRl:共" + viewGroupRl.getChildCount() + "个childCount!");
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);

			DotCircleProgressView dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			viewGroupRl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		} else if (activity.findViewById(viewGroupResId) instanceof LinearLayout) {
			viewGroupLl = (LinearLayout) activity.findViewById(viewGroupResId);
			for (int i = 0; i < viewGroupLl.getChildCount(); i++) {
				tempView = viewGroupLl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupLl:共" + viewGroupLl.getChildCount() + "个childCount!");
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);

			DotCircleProgressView dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			dotCircleProgressView.initView(activity);
			viewGroupLl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		} else if (activity.findViewById(viewGroupResId) instanceof FrameLayout) {
			viewGroupFl = (FrameLayout) activity.findViewById(viewGroupResId);
			for (int i = 0; i < viewGroupFl.getChildCount(); i++) {
				tempView = viewGroupFl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupFl:共" + viewGroupFl.getChildCount() + "个childCount!");
			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			DotCircleProgressView dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			dotCircleProgressView.initView(activity);
			viewGroupFl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		}
		tempView = null;

	}

	private DotCircleProgressView dotCircleProgressView;
	private Activity dotCircelActivity;

	/***
	 * 显示单独的progressBar.如果要设置某个例外，那么也设置这个view的tag为“gone”或“invisible”
	 * 
	 * @param activity
	 * @param viewGroupResId
	 *            父容器资源ID。只能是LinearLayout或者RelativeLayout或者FrameLayout
	 */
	public void showDotCircelProgressBar(Activity activity, ViewGroup viewGroup) {
		dotCircelActivity = activity;
		dismissProgressBar();
		View tempView = null;
		if (viewGroup instanceof RelativeLayout) {
			viewGroupRl = (RelativeLayout) viewGroup;
			for (int i = 0; i < viewGroupRl.getChildCount(); i++) {
				tempView = viewGroupRl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupRl:共" + viewGroupRl.getChildCount() + "个childCount!");
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);

			dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			dotCircleProgressView.initView(activity);
			viewGroupRl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		} else if (viewGroup instanceof LinearLayout) {
			viewGroupLl = (LinearLayout) viewGroup;
			for (int i = 0; i < viewGroupLl.getChildCount(); i++) {
				tempView = viewGroupLl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupLl:共" + viewGroupLl.getChildCount() + "个childCount!");
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);

			dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			dotCircleProgressView.initView(activity);
			viewGroupLl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		} else if (viewGroup instanceof FrameLayout) {
			viewGroupFl = (FrameLayout) viewGroup;
			for (int i = 0; i < viewGroupFl.getChildCount(); i++) {
				tempView = viewGroupFl.getChildAt(i);
				if (tempView.getTag() == null || (tempView.getVisibility() == View.GONE && tempView.getTag().toString().equals("gone")) || (tempView.getVisibility() == View.INVISIBLE && tempView.getTag().toString().equals("invisible"))) {
					tempView.setVisibility(View.GONE);
				}
			}
			Log.i("tag", "viewGroupFl:共" + viewGroupFl.getChildCount() + "个childCount!");
			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			dotCircleProgressView.initView(activity);
			viewGroupFl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		} else {
			Toast.makeText(activity, "不识别的ViewGroup", Toast.LENGTH_SHORT).show();
		}
		tempView = null;
	}

	/***
	 * 隐藏progressBar
	 */
	public void dismissProgressBar() {
		backCount = 0;
		// 设计dismissActivity是因为有可能报错：
		// java.lang.IllegalArgumentException: View not attached to window
		// manager
		if (pd != null && pd.isShowing() && dismissActivity != null && !dismissActivity.isFinishing()) {
			pd.dismiss();
			pd = null;
			return;
		}

		if (viewGroupRl != null) {
			if (pb != null) {
				viewGroupRl.removeView(pb);
			}
			for (int i = 0; i < viewGroupRl.getChildCount(); i++) {
				if (viewGroupRl.getChildAt(i).getTag() == null || (!viewGroupRl.getChildAt(i).getTag().toString().equals("gone") && !viewGroupRl.getChildAt(i).getTag().toString().equals("invisible"))) {
					viewGroupRl.getChildAt(i).setVisibility(View.VISIBLE);
				}
			}
			if (dotCircleProgressView != null) {
				dotCircleProgressView.clearAnimation();
				viewGroupRl.removeView(dotCircleProgressView);
				Log.d("tag", "viewGroupRl.removeView");
			}
			viewGroupRl = null;
		} else if (viewGroupLl != null) {
			if (pb != null) {
				viewGroupLl.removeView(pb);
			}
			for (int i = 0; i < viewGroupLl.getChildCount(); i++) {
				if (viewGroupLl.getChildAt(i).getTag() == null || (!viewGroupLl.getChildAt(i).getTag().toString().equals("gone") && !viewGroupLl.getChildAt(i).getTag().toString().equals("invisible"))) {
					viewGroupLl.getChildAt(i).setVisibility(View.VISIBLE);
				}
			}
//			Animation anim = AnimationUtils.loadAnimation(dotCircelActivity, R.anim.item_alpha);
//			LayoutAnimationController lac = new LayoutAnimationController(anim);
//			// 设置控件显示的顺序；
//			lac.setOrder(LayoutAnimationController.ORDER_REVERSE);
//			viewGroupLl.setLayoutAnimation(lac);
//			viewGroupLl.startAnimation(anim);
			
			// viewGroupLl.setLayoutAnimation(LayoutAnimationController.)
			if (dotCircleProgressView != null) {
				dotCircleProgressView.clearAnimation();
				viewGroupLl.removeView(dotCircleProgressView);
				Log.d("tag", "viewGroupLl.removeView");
			}
			viewGroupLl = null;
		} else if (viewGroupFl != null) {
			if (pb != null) {
				viewGroupFl.removeView(pb);
			}
			for (int i = 0; i < viewGroupFl.getChildCount(); i++) {
				if (viewGroupFl.getChildAt(i).getTag() == null || (!viewGroupFl.getChildAt(i).getTag().toString().equals("gone") && !viewGroupFl.getChildAt(i).getTag().toString().equals("invisible"))) {
					viewGroupFl.getChildAt(i).setVisibility(View.VISIBLE);
				}
			}
			if (dotCircleProgressView != null) {
				dotCircleProgressView.clearAnimation();
				viewGroupFl.removeView(dotCircleProgressView);
				Log.d("tag", "viewGroupFl.removeView");
			}
			viewGroupFl = null;
		}
		dismissActivity = null;
	}

	/***
	 * 创建progressDialog,不带任何提示文字
	 * 
	 * @param context
	 */
	public void showProgressDialog(Context context) {
		pd = new ProgressDialog(context);
		pd.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.circle_progressbar));
		pd.show();
	}

	/***
	 * 创建progressDialog,带提示文字。如：正在加载中……
	 * 
	 * @param context
	 */
	public void showProgressDialog(Context context, String msg) {
		pd = new ProgressDialog(context);
		pd.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.circle_progressbar));
		pd.setMessage(msg);
		pd.show();
	}

	/***
	 * 创建progressDialog,不带任何提示文字。是否可以按返回键返回？true：可以按返回键返回，但需要按3次。false：不可按返回键返回
	 * 
	 * @param context
	 */
	public void showProgressDialog(final Activity activity, boolean cancelable) {
		dismissActivity = activity;
		backCount = 0;
		pd = new ProgressDialog(activity);
		pd.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.circle_progressbar));
		pd.setCancelable(cancelable);
		if (!cancelable) {
			pd.setOnKeyListener(new DialogInterface.OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == KeyEvent.ACTION_DOWN) {
						if (keyCode == KeyEvent.KEYCODE_BACK) {
							if (backCount > 1) {
								activity.finish();
								return false;
							} else if (backCount == 0) {
								Toast.makeText(activity, "正在操作中，强行返回请再按2次返回键!", Toast.LENGTH_SHORT).show();
							} else if (backCount == 1) {
								Toast.makeText(activity, "正在操作中，强行返回请再按1次返回键!", Toast.LENGTH_SHORT).show();
							}
							backCount++;
						}
					}
					return false;
				}
			});
		}
		pd.show();
	}

	/***
	 * 创建progressDialog,带提示文字。是否可以按返回键返回？true：可以按返回键返回，但需要按3次。false：不可按返回键返回
	 * 
	 * @param context
	 */
	public void showProgressDialog(final Activity activity, String msg, boolean cancelable) {
		dismissActivity = activity;
		backCount = 0;
		pd = new ProgressDialog(activity);
		pd.setIndeterminateDrawable(activity.getResources().getDrawable(R.drawable.circle_progressbar));
		pd.setMessage(msg);
		pd.setCancelable(cancelable);
		if (!cancelable) {
			pd.setOnKeyListener(new DialogInterface.OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == KeyEvent.ACTION_DOWN) {
						if (backCount > 1) {
							dismissProgressBar();
							activity.finish();
							return false;
						} else if (backCount == 0) {
							Toast.makeText(activity, "正在操作中，强行返回请再按2次返回键!", Toast.LENGTH_SHORT).show();
						} else if (backCount == 1) {
							Toast.makeText(activity, "正在操作中，强行返回请再按1次返回键!", Toast.LENGTH_SHORT).show();
						}
						backCount++;
					}
					return false;
				}
			});
		}
		if (!activity.isFinishing()) {
			pd.show();
		}
	}
	
	
	public void addRootLoading(Activity activity){
		//获取Activity的根视图
		View view = activity.findViewById(android.R.id.content).getRootView();
		LinearLayout ll  = null;
		RelativeLayout rl = null;
		FrameLayout fl = null;
		if (view instanceof LinearLayout) {
			ll = (LinearLayout) view;
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			dotCircleProgressView.initView(activity);
			ll.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		}else if(view instanceof RelativeLayout){
			rl = (RelativeLayout) view;
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			rl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		}else if(view instanceof FrameLayout){
			fl = (FrameLayout) view;
			FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH), CommonUtil.getInstance().dp2px(activity, DOT_CIRCEL_WIDTH));
			layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
			layoutParams.topMargin = CommonUtil.getInstance().dp2px(activity, LOADING_TOP_MARGIN);
			dotCircleProgressView = new DotCircleProgressView(activity);
			dotCircleProgressView.setDotColor(Color.RED);
			dotCircleProgressView.setDotCount(8);
			dotCircleProgressView.setDotSizeMin(3);
			dotCircleProgressView.setDotSizeMax(5);
			dotCircleProgressView.setLayoutParams(layoutParams);
			dotCircleProgressView.initView(activity);
			fl.addView(dotCircleProgressView);
			dotCircleProgressView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_loading));
		}
	}
}