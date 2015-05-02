package com.parcool.myshop.adapter;

import java.util.LinkedList;
import com.parcool.myshop.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private LinkedList<String> mListItems;
	private Context context;
	
	public MyAdapter(Context context,LinkedList<String> mListItems) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mListItems = mListItems;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_listview_item, parent,false);
			holder.tv = (TextView) convertView.findViewById(R.id.tv);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv.setText(mListItems.get(position));
		return convertView;
	}
	
	
	private class ViewHolder{
		private TextView tv;
	}

}
