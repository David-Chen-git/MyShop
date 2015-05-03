package com.parcool.myshop.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.parcool.myshop.R;
import com.parcool.myshop.impl.IWebViewStatus;
import com.parcool.myshop.utils.DialogUtil;
import com.parcool.myshop.view.MyChromeWebView;

public class FragmentPage1 extends Fragment{

	private RelativeLayout rlContainer;
	private MyChromeWebView myChromeWebViewOne;
	private View view;
	private boolean isPageFinished = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_1, container,false);
		rlContainer = (RelativeLayout) view.findViewById(R.id.ll_root);
		if (view == null || !isPageFinished) {
			init();
		}else{
			((ViewGroup)myChromeWebViewOne.getParent()).removeView(myChromeWebViewOne);
			rlContainer.addView(myChromeWebViewOne);
		}
		return view;
	}	
	
	
	//getter
	public MyChromeWebView getMyChromeWebViewOne() {
		return myChromeWebViewOne;
	}


	private void init() {
		// TODO Auto-generated method stub
		myChromeWebViewOne = new MyChromeWebView(getActivity(), "http://www.baidu.com", new IWebViewStatus() {

			@Override
			public void onPageStarted() {
				// TODO Auto-generated method stub
				DialogUtil.getInstance().showDotCircelProgressBar(getActivity(), (RelativeLayout) view.findViewById(R.id.ll_root));
			}

			@Override
			public void onPageFinished() {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "加载完成！", Toast.LENGTH_SHORT).show();
				DialogUtil.getInstance().dismissProgressBar();
				isPageFinished = true;
			}

			@Override
			public void onPageError(int errorCode, String description) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "加载错误！errorCode=" + errorCode + ",description=" + description, Toast.LENGTH_SHORT).show();
				DialogUtil.getInstance().dismissProgressBar();
				Toast.makeText(getActivity(), "errorCode=" + errorCode + ",description=" + description, Toast.LENGTH_LONG).show();
			}

		});
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		myChromeWebViewOne.setLayoutParams(lp);
		rlContainer.addView(myChromeWebViewOne);
	}
}
