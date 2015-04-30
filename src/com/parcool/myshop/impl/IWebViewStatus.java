package com.parcool.myshop.impl;

public interface IWebViewStatus {
	public void onPageStarted();
	public void onPageFinished();
	public void onPageError(int errorCode, String description);
}
