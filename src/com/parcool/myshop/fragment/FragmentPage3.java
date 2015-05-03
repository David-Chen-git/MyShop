package com.parcool.myshop.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.parcool.myshop.R;
import com.parcool.myshop.activity.ProductsActivity;
import com.parcool.myshop.adapter.MainMarketAdapter;
import com.parcool.myshop.model.MainMarketModel;

public class FragmentPage3 extends Fragment {

	private PullToRefreshGridView mPullRefreshGridView;
	private RelativeLayout rlRoot;
	private View view;
	private MainMarketAdapter mmAdapter;
	private List<MainMarketModel> list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_3, container, false);
		rlRoot = (RelativeLayout) view;
		init();
		return rlRoot;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	private void init() {
		mPullRefreshGridView = (PullToRefreshGridView) rlRoot.findViewById(R.id.pull_refresh_list);
		mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				// Do work to refresh the list here.
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				// Do work to refresh the list here.
				new GetDataTaskLoad().execute();
			}
		});

		
		initData();
		mmAdapter = new MainMarketAdapter(getActivity(), list);
		GridView actualListView = mPullRefreshGridView.getRefreshableView();
		actualListView.setAdapter(mmAdapter);
		actualListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(),ProductsActivity.class);
				intent.putExtra("title", ((TextView)view.findViewById(R.id.tv_zh)).getText().toString());
				startActivity(intent);
			}
		});
	}

	private void initData() {
		if (list == null) {
			list = new ArrayList<MainMarketModel>();
		}
		for (int i = 0; i < 50; i++) {
			MainMarketModel mmm = new MainMarketModel();
			if (i % 3 == 0) {
				mmm.setImgUrl("" + R.drawable.iv_one);
				mmm.setTitleEN("Celebs");
				mmm.setTitleZH("明星");
			} else if (i % 3 == 1) {
				mmm.setImgUrl("" + R.drawable.iv_two);
				mmm.setTitleEN("Runway");
				mmm.setTitleZH("秀场");
			} else {
				mmm.setImgUrl("" + R.drawable.iv_three);
				mmm.setTitleEN("Handbags");
				mmm.setTitleZH("包");
			}
			list.add(mmm);
		}
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
			MainMarketModel mmm = new MainMarketModel();
			mmm.setImgUrl("" + R.drawable.iv_three);
			mmm.setTitleEN("Handbags");
			mmm.setTitleZH("包_新增");
			list.add(mmm);
			mmm.setImgUrl("" + R.drawable.iv_three);
			mmm.setTitleEN("Handbags");
			mmm.setTitleZH("包_新增1");
			list.add(mmm);
			mmm.setImgUrl("" + R.drawable.iv_three);
			mmm.setTitleEN("Handbags");
			mmm.setTitleZH("包_新增2");
			list.add(mmm);
			mmm.setImgUrl("" + R.drawable.iv_three);
			mmm.setTitleEN("Handbags");
			mmm.setTitleZH("包_新增3");
			list.add(mmm);

			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			mmAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshGridView.onRefreshComplete();

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
			MainMarketModel mmm = new MainMarketModel();
			mmm.setImgUrl("" + R.drawable.iv_one);
			mmm.setTitleEN("Celebs");
			mmm.setTitleZH("明星_新增1");
			list.add(mmm);
			mmm.setImgUrl("" + R.drawable.iv_one);
			mmm.setTitleEN("Celebs");
			mmm.setTitleZH("明星_新增2");
			list.add(mmm);
			mmm.setImgUrl("" + R.drawable.iv_one);
			mmm.setTitleEN("Celebs");
			mmm.setTitleZH("明星_新增3");
			list.add(mmm);

			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			mmAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshGridView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}