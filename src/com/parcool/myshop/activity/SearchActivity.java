package com.parcool.myshop.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parcool.myshop.R;
import com.parcool.myshop.adapter.SearchDetailAdapter;
import com.parcool.myshop.adapter.SimpleListViewLocalHistoryAdapter;
import com.parcool.myshop.model.SearchModel;
import com.parcool.myshop.utils.SPValue;
import com.parcool.myshop.view.MyFlowLayout;

public class SearchActivity extends Activity {

	private EditText etSearch;
	private String inputStr;
	private String[] hotMarkers = { "关键字", "关键字关键字", "关键字关键字关键字", "关键字关键字", "关键", "关键字关键字关键字关键字", "关键字", "关键字关键字关键字", "关键字关键字", "关键字" };
	private ListView lvLocalHistory;
	private String[] historyArr = { "搜", "高跟鞋", "包", "雨伞", "短裙", "皮鞋", "打底裤", "搜", "高跟鞋", "包", "雨伞", "短裙", "皮鞋", "打底裤", "搜", "高跟鞋", "包", "雨伞", "短裙", "皮鞋", "打底裤" };
	private ListView lvResult;
	private List<SearchModel> list = null;
	private SearchDetailAdapter searchDetailAdapter;
	private SPValue spValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		init();
		initKeyWords();
		initLocalHistory();
	}

	/***
	 * 初始化“热门标签”
	 */
	private void initKeyWords() {
		// TODO Auto-generated method stub
		MyFlowLayout mFlowLayout = (MyFlowLayout) findViewById(R.id.my_flowlayout);
		MarginLayoutParams lp = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.leftMargin = 15;
		lp.rightMargin = 15;
		lp.topMargin = 15;
		lp.bottomMargin = 15;
		for (int i = 0; i < hotMarkers.length; i++) {
			TextView view = new TextView(this);
			view.setText(hotMarkers[i]);
			view.setTextColor(Color.WHITE);
			// view.setPadding(CommonUtil.getInstance().dp2px(this, 5),
			// CommonUtil.getInstance().dp2px(this, 5),
			// CommonUtil.getInstance().dp2px(this, 5),
			// CommonUtil.getInstance().dp2px(this, 5));
			// view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
			// view.setBackgroundColor(getResources().getColor(R.color.app_color));
			view.setBackgroundResource(R.drawable.keywrod_bg);
			mFlowLayout.addView(view, lp);
		}
	}

	/***
	 * 初始化顶部搜索框
	 */
	private void init() {
		// TODO Auto-generated method stub
		findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SearchActivity.this.finish();
			}
		});
		spValue = new SPValue(this);
		etSearch = (EditText) findViewById(R.id.et_search);

		lvResult = (ListView) findViewById(R.id.lv_search_result);
		if (list == null) {
			list = new ArrayList<SearchModel>();
		}
		searchDetailAdapter = new SearchDetailAdapter(this, list);
		lvResult.setAdapter(searchDetailAdapter);
		lvResult.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				//添加到历史搜索记录中
				spValue.addLocalHistory(list.get(position).getTitle());
				Intent intent = new Intent(SearchActivity.this,ProductsActivity.class);
				intent.putExtra("title", list.get(position).getTitle());
				startActivity(intent);
			}
		});

		etSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Log.d("tag", "afterTextChanged");
				inputStr = etSearch.getText().toString();
				etSearch.setSelection(etSearch.getText().toString().length());
				if (inputStr != null && !inputStr.equals("")) {
					doPostSearch();
					setSearchingViewBehavior(true);
				} else {
					setSearchingViewBehavior(false);
				}
			}

		});

	}

	private void doPostSearch() {
		// TODO Auto-generated method stub
		list.clear();
		for (int i = 0; i < 20; i++) {
			SearchModel sm = new SearchModel();
			sm.setId("1");
			sm.setFollowCount(100);
			sm.setPrice("38.00");
			sm.setTitle("测试标题");
			list.add(sm);
		}
		searchDetailAdapter.setInputStr(inputStr);
		if (searchDetailAdapter != null) {
			searchDetailAdapter.notifyDataSetChanged();
		}
	}

	/***
	 * 设置搜索中隐藏哪些View，显示哪些View
	 * 
	 * @param isSearching
	 *            是否正在搜索中
	 */
	private void setSearchingViewBehavior(boolean isSearching) {
		if (isSearching) {
			//显示搜索结果listView，同时，隐藏其他所有
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl_content);
			for (int i = 0; i < rl.getChildCount(); i++) {
				if (rl.getChildAt(i).getId() != R.id.lv_search_result) {
					rl.getChildAt(i).setVisibility(View.GONE);
				} else {
					rl.getChildAt(i).setVisibility(View.VISIBLE);
				}
			}
		} else {
			//显示搜索结果listView，同时，隐藏其他所有
			RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl_content);
			for (int i = 0; i < rl.getChildCount(); i++) {
				if (rl.getChildAt(i).getId() == R.id.lv_search_result) {
					rl.getChildAt(i).setVisibility(View.GONE);
				} else {
					if (rl.getChildAt(i).getTag() == null) {
						rl.getChildAt(i).setVisibility(View.VISIBLE);
					} else if (!rl.getChildAt(i).getTag().toString().equals("gone")) {
						rl.getChildAt(i).setVisibility(View.VISIBLE);
					}
				}
			}
		}
	}
	
	private void clearLocalHistory(){
		spValue.clearLocalHistory();
		initLocalHistory();
	}

	/***
	 * 初始化本地 “搜索历史”
	 */
	private void initLocalHistory() {
		historyArr = spValue.getLocalHistory();
		lvLocalHistory = (ListView) findViewById(R.id.lv_local_history);
		//点击“回收”图标清空历史
		findViewById(R.id.iv_recycle).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearLocalHistory();
			}
		});
//		点击“清空历史记录”的文字清空历史
		findViewById(R.id.tv_clear_search_history).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clearLocalHistory();
			}
		});

		if (historyArr.length > 1) {
			findViewById(R.id.iv_recycle).setVisibility(View.VISIBLE);
			findViewById(R.id.iv_recycle).setTag("visible");
			findViewById(R.id.tv_clear_search_history).setVisibility(View.VISIBLE);
			findViewById(R.id.tv_clear_search_history).setTag("visible");
			findViewById(R.id.tv_search_history).setVisibility(View.VISIBLE);
			findViewById(R.id.tv_search_history).setTag("visible");
		} else {
			findViewById(R.id.iv_recycle).setVisibility(View.GONE);
			findViewById(R.id.iv_recycle).setTag("gone");
			findViewById(R.id.tv_clear_search_history).setVisibility(View.GONE);
			findViewById(R.id.tv_clear_search_history).setTag("gone");
			findViewById(R.id.tv_search_history).setVisibility(View.GONE);
			findViewById(R.id.tv_search_history).setTag("gone");
			return;
		}
		SimpleListViewLocalHistoryAdapter simpleListViewLocalHistory = new SimpleListViewLocalHistoryAdapter(this, historyArr);
		lvLocalHistory.setAdapter(simpleListViewLocalHistory);
		lvLocalHistory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				etSearch.setText(historyArr[position]);
			}

		});
		
	}

}
