package com.parcool.myshop.adapter;

import java.util.List;

import com.parcool.myshop.R;
import com.parcool.myshop.model.SearchModel;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SearchDetailAdapter extends BaseAdapter {

	private List<SearchModel> list;
	private Context context;
	private String inputStr = "";

	public SearchDetailAdapter(Context context, List<SearchModel> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
	}

	public String getInputStr() {
		return inputStr;
	}

	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = null;

		if (convertView == null) {
			holder = new Holder();
			convertView = LayoutInflater.from(context).inflate(R.layout.listview_main_serch, parent,false);
			holder.autoTitle1 = (TextView) convertView.findViewById(R.id.tv_child_title_1);
			holder.autoTitle2 = (TextView) convertView.findViewById(R.id.tv_child_title_2);
			holder.autoTitle3 = (TextView) convertView.findViewById(R.id.tv_child_title_3);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		String listTitleStr = list.get(position).getTitle();
		int a = listTitleStr.indexOf(inputStr);
		// 64, 64, 64是深灰色
		// 61, 204, 151是绿色
		if (a == 0) {
			// 开头
			holder.autoTitle1.setText("");
			holder.autoTitle2.setText(listTitleStr.substring(0, inputStr.length()));
			holder.autoTitle2.setTextColor(Color.rgb(61, 204, 151));
			holder.autoTitle3.setText(listTitleStr.substring(inputStr.length()));
			holder.autoTitle3.setTextColor(Color.rgb(64, 64, 64));
		} else if (a == (listTitleStr.length() - inputStr.length())) {
			// 末尾
			holder.autoTitle1.setText("");
			holder.autoTitle2.setText(listTitleStr.substring(0, a));
			holder.autoTitle2.setTextColor(Color.rgb(64, 64, 64));
			holder.autoTitle3.setText(listTitleStr.substring(a));
			holder.autoTitle3.setTextColor(Color.rgb(61, 204, 151));
		} else {
			// 中间
			holder.autoTitle1.setText(listTitleStr.substring(0, a));
			holder.autoTitle1.setTextColor(Color.rgb(64, 64, 64));
			holder.autoTitle2.setText(listTitleStr.substring(a, a + inputStr.length()));
			holder.autoTitle2.setTextColor(Color.rgb(61, 204, 151));
			holder.autoTitle3.setText(listTitleStr.substring(a + inputStr.length()));
			holder.autoTitle3.setTextColor(Color.rgb(64, 64, 64));
		}
		// holder.autoTitle.setText();
		holder.autoTitle1.setTag(list.get(position));
		return convertView;
	}

	class Holder {
		TextView autoTitle1;
		TextView autoTitle2;
		TextView autoTitle3;
		TextView tvDistance;
	}

}
