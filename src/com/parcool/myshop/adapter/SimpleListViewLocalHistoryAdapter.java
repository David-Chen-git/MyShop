package com.parcool.myshop.adapter;

import com.parcool.myshop.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SimpleListViewLocalHistoryAdapter extends BaseAdapter{
	
	private String[] historyArr =  null;
	private Context context;
	
	public SimpleListViewLocalHistoryAdapter(Context context,String[] historyArr) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.historyArr = historyArr;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return historyArr.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return historyArr[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.simple_listview, parent,false);
			viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tv.setText(historyArr[position]);
		return convertView;
	}
	
	private class ViewHolder{
		public TextView tv;
	}

}
