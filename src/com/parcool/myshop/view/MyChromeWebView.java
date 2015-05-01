package com.parcool.myshop.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.parcool.myshop.R;
import com.parcool.myshop.activity.MyWebViewActivity;
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
	private Context theContext;
	private String theUrl;
	private IWebViewStatus theIWebViewStatus;

	@SuppressLint("SetJavaScriptEnabled")
	public MyChromeWebView(Context context, String url, IWebViewProgress iWebViewProgress) {
		// TODO Auto-generated constructor stub
		super(context);
		this.theContext = context;
		this.theIWebViewProgress = iWebViewProgress;
		this.theUrl = url;

		setWebChromeClient(new MyWebChromeClient(context));
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
					theContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});
		this.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				Toast.makeText(theContext, "页面发生错误：" + description, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}
		});

		this.loadUrl(theUrl);
	}

	/***
	 * 新建一个WebView。
	 * 
	 * @param context
	 *            context
	 * @param url
	 *            网址，必须带前缀http：//
	 * @param iWebViewStatus
	 *            当加载完成或加载失败后返回的状态接口
	 */
	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	public MyChromeWebView(Context context, String url, IWebViewStatus iWebViewStatus) {
		// TODO Auto-generated constructor stub
		super(context);
		this.theContext = context;
		this.theUrl = url;
		this.theIWebViewStatus = iWebViewStatus;
		setWebChromeClient(new WebChromeClient() {
			// 可以设置进度
		});
		this.getSettings().setJavaScriptEnabled(true);
		// 开启 DOM storage API 功能
		this.getSettings().setDomStorageEnabled(true);
		// 开启 database storage API 功能
//		this.getSettings().setDatabaseEnabled(true);
//		String cacheDirPath = context.getFilesDir().getAbsolutePath() +"/"+ context.getString(R.string.app_name)+"/webCache";
		// 阻塞图片加载，使其解析速度更快
		this.getSettings().setBlockNetworkImage(true);
		this.getSettings().setRenderPriority(RenderPriority.HIGH);
		this.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // 设置 缓存模式
		this.getSettings().setAppCacheEnabled(true);
		this.getSettings().setUseWideViewPort(true);//设置自适应屏幕
		
		this.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
				theIWebViewStatus.onPageStarted();
			}

			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				theIWebViewStatus.onPageError(errorCode, description);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				// 释放图片加载阻塞
				MyChromeWebView.this.getSettings().setBlockNetworkImage(false);
				super.onPageFinished(view, url);
				theIWebViewStatus.onPageFinished();
			}
		});
		this.loadUrl(theUrl);
	}

	/***
	 * 新建一个WebView。
	 * 
	 * @param context
	 *            context
	 * @param iWebViewStatus
	 *            当加载完成或加载失败后返回的状态接口
	 */
	@SuppressLint("SetJavaScriptEnabled")
	public MyChromeWebView(Context context, IWebViewStatus iWebViewStatus) {
		// TODO Auto-generated constructor stub
		super(context);
		this.theContext = context;
		this.theIWebViewStatus = iWebViewStatus;
		setWebChromeClient(new WebChromeClient() {
			// 可以设置进度
		});
		this.getSettings().setJavaScriptEnabled(true);
		this.setWebViewClient(new WebViewClient() {
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				theIWebViewStatus.onPageError(errorCode, description);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				theIWebViewStatus.onPageFinished();
			}
		});
		// 如果webView中需要用户手动输入用户名、密码或其他，则webview必须设置支持获取手势焦点。
		this.requestFocusFromTouch();
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
	 */
	public class MyWebChromeClient extends android.webkit.WebChromeClient {
		
		private Context context;
		public MyWebChromeClient(Context context) {
			// TODO Auto-generated constructor stub
			this.context = context;
		}
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

		// 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Toast.makeText(context, "hahahahah", 0).show();
//			Intent intent = new Intent(context,MyWebViewActivity.class);
//			intent.putExtra("url", url);
//			context.startActivity(intent);
			return false;
		}
		
		@Override 
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) { 
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show(); 
            result.confirm(); 
            return true; 
        } 

        @Override 
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) { 
            return super.onJsConfirm(view, url, message, result); 
        } 

        @Override 
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) { 
            return super.onJsPrompt(view, url, message, defaultValue, result); 
        } 

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
