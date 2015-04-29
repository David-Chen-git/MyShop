package com.parcool.myshop.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.parcool.myshop.impl.IWebViewStatus;
import com.parcool.myshop.impl.IWebViewProgress;

/***
 * MyChromeWebView
 * 调用的Chrome，因为系统默认的WebClient里没有暴露onProgressChanged，只有start与finish。。。
 * 
 * @author parcool
 *
 */
public class MyChromeWebView extends WebView {

	private IWebViewProgress theIWebViewProgress;
	private Activity theActivity;
	private String theUrl;

	@SuppressLint("SetJavaScriptEnabled")
	public MyChromeWebView(Activity activity, String url, IWebViewProgress iWebViewProgress) {
		// TODO Auto-generated constructor stub
		super(activity);
		this.theActivity = activity;
		this.theIWebViewProgress = iWebViewProgress;
		this.theUrl = url;

		setWebChromeClient(new MyWebChromeClient());
		this.addJavascriptInterface(new IWebViewProgress() {

			@JavascriptInterface
			public void onLogin() {
				// TODO Auto-generated method stub
				theIWebViewProgress.onLogin();
			}

			@JavascriptInterface
			public void callPhone(String num) {
				// TODO Auto-generated method stub
				theIWebViewProgress.callPhone(num);
			}

			@Override
			public void setProgress(int newProgress) {
				// TODO Auto-generated method stub
				theIWebViewProgress.setProgress(newProgress);
			}
		}, "login");
		this.getSettings().setJavaScriptEnabled(true);
		// 这个是监听下载，可以不设置
		this.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				Log.d("tag", "下载地址：" + url);
				if (url != null && url.startsWith("http://"))
					theActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});
		this.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(theActivity, "页面发生错误：" + description, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}
		});

		this.loadUrl(theUrl);
	}

	private IWebViewStatus theIWebViewStatus;
	@SuppressLint("SetJavaScriptEnabled")
	public MyChromeWebView(Activity activity, String url,IWebViewStatus iWebViewStatus) {
		// TODO Auto-generated constructor stub
		super(activity);
		this.theActivity = activity;
		this.theUrl = url;
		this.theIWebViewStatus = iWebViewStatus;
		setWebChromeClient(new WebChromeClient() {
			// 可以设置进度
		});
		this.getSettings().setJavaScriptEnabled(true);
		this.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(theActivity, "页面发生错误：" + description, Toast.LENGTH_SHORT).show();
				theIWebViewStatus.onPageError(errorCode,description);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				theIWebViewStatus.onPageFinished();
			}
		});

		this.loadUrl(theUrl);
	}

	/***
	 * 最好不要调这个方法(也就是不要在XML里调用这个WebView)。因为这里我没有设置它进度条的方法。
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyChromeWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/***
	 * 这里调用那个修改progressBar接口的setProgress()方法 通知MyWebViewActivity更新progress
	 * 而这个更新progress的方法又是在BaseActivity里，所以有点绕。。。
	 * 
	 * @author parcool
	 *
	 */
	public class MyWebChromeClient extends android.webkit.WebChromeClient {

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			theIWebViewProgress.setProgress(newProgress);
			super.onProgressChanged(view, newProgress);
		}

		@SuppressWarnings("deprecation")
		@Override
		public boolean onJsTimeout() {
			// TODO Auto-generated method stub
			return super.onJsTimeout();
		}

		@Override
		public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
			// TODO Auto-generated method stub
			Log.d("tag", "跳转地址：" + url);
			return super.onJsBeforeUnload(view, url, message, result);

		}

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
