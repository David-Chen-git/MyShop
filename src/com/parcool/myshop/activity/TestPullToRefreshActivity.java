package com.parcool.myshop.activity;
import java.util.Arrays;
import java.util.LinkedList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.AbsListView.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.parcool.myshop.R;


public class TestPullToRefreshActivity extends Activity{
	
	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler",
			"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler" };
	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	private ArrayAdapter<String> mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_testpulltorefresh);
		init();
	}
	

	private void init() {
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(this);
		tv.setText("测试HeaderView");
		tv.setTextSize(36);
		tv.setLayoutParams(lp);
		mPullRefreshListView.getRefreshableView().addHeaderView(tv);
		// Set a listener to be invoked when the list should be refreshed.
		// mPullRefreshListView.setOnRefreshListener(new
		// OnRefreshListener<ListView>() {
		// @Override
		// public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// String label = DateUtils.formatDateTime(getApplicationContext(),
		// System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME |
		// DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
		//
		// // Update the LastUpdatedLabel
		// refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		//
		// // Do work to refresh the list here.
		// new GetDataTask().execute();
		// }
		//
		//
		// });
		//
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTaskLoad().execute();
			}
		});

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

		// 这两个绑定方法用其一
		// 方法一
		// mPullRefreshListView.setAdapter(mAdapter);
		// 方法二
		ListView actualListView = mPullRefreshListView.getRefreshableView();
		actualListView.setAdapter(mAdapter);

	}

	private class GetDataTask extends AsyncTask<Void, Void, String> {

		// 后台处理部分
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			String str = "Added after refresh...I add";
			return str;
		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(String result) {
			// 在头部增加新添内容
			mListItems.addFirst(result);

			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
	
	
	private class GetDataTaskLoad extends AsyncTask<Void, Void, String> {

		// 后台处理部分
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			String str = "Added after refresh...I add";
			return str;
		}

		// 这里是对刷新的响应，可以利用addFirst（）和addLast()函数将新加的内容加到LISTView中
		// 根据AsyncTask的原理，onPostExecute里的result的值就是doInBackground()的返回值
		@Override
		protected void onPostExecute(String result) {
			// 在头部增加新添内容
			mListItems.addLast(result);

			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

}
