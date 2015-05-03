package com.parcool.myshop.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.parcool.myshop.R;
import com.parcool.myshop.adapter.ProductsListAdapter;
import com.parcool.myshop.model.ProductModel;

public class ProductsActivity extends BaseActivity {

	private PullToRefreshGridView mPullRefreshGridView;
	private List<ProductModel> list = new ArrayList<ProductModel>();
	private ProductsListAdapter pla = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_products);
		setBaseTitle(getIntent().getStringExtra("title"));
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mPullRefreshGridView = (PullToRefreshGridView) findViewById(R.id.pull_refresh_list);
		mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(ProductsActivity.this.getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				// Do work to refresh the list here.
				new GetDataTask().execute();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(ProductsActivity.this.getApplicationContext(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});
		initData();
		pla = new ProductsListAdapter(this, list);
		mPullRefreshGridView.setAdapter(pla);
		mPullRefreshGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProductsActivity.this,DetailActivity.class);
				intent.putExtra("imgUrl", list.get(position).getThumbImgUrl());
				intent.putExtra("title", list.get(position).getTitle());
				intent.putExtra("intro", "这是介绍这是介绍这是介绍这是介绍这是介绍这是介绍这是介绍这是介绍");
				
				intent.putExtra("position", position);
				int[] location = new int[2];
				view.findViewById(R.id.iv).getLocationOnScreen(location);
				intent.putExtra("locationX", location[0]);
				intent.putExtra("locationY", location[1]);

				intent.putExtra("width", view.findViewById(R.id.iv).getWidth());
				intent.putExtra("height", view.findViewById(R.id.iv).getHeight());
				Log.d("tag", "location[0]="+location[0]+",location[1]="+location[1]+",width="+view.findViewById(R.id.iv).getWidth()+",height="+view.findViewById(R.id.iv).getHeight());
				startActivity(intent);
				overridePendingTransition(0, 0);
			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		if (list == null) {
			list = new ArrayList<ProductModel>();
		}
		for (int i = 0; i < 50; i++) {
			if (i % 2 == 0) {
				ProductModel pm = new ProductModel();
				pm.setId("id1");
				pm.setMarkerCount(368);
				pm.setPrice("398.00");
				pm.setThumbImgUrl("http://gd3.alicdn.com/imgextra/i3/21659330/TB2JTiVcpXXXXaiXpXXXXXXXXXX-21659330.jpg");
				pm.setTitle("欧根纱条纹印花连衣裙");
				list.add(pm);
			} else {
				ProductModel pm = new ProductModel();
				pm.setId("id1");
				pm.setMarkerCount(226);
				pm.setPrice("298.00");
				pm.setThumbImgUrl("http://gd1.alicdn.com/imgextra/i1/21659330/TB2XqqFcpXXXXcfXXXXXXXXXXXX-21659330.jpg");
				pm.setTitle("欧根纱条纹印花连衣裙2");
				list.add(pm);
			}
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
			ProductModel pm = new ProductModel();
			pm.setId("id1");
			pm.setMarkerCount(100);
			pm.setPrice("398.00");
			pm.setThumbImgUrl("http://gd2.alicdn.com/imgextra/i2/1112661805/TB2j2Q.XpXXXXaRXpXXXXXXXXXX_!!1112661805.jpg");
			pm.setTitle("欧根纱条纹印花连衣裙");

			// 通知程序数据集已经改变，如果不做通知，那么将不会刷新mListItems的集合
			pla.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshGridView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}
