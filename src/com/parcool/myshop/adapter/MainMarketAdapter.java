package com.parcool.myshop.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.parcool.myshop.R;
import com.parcool.myshop.model.MainMarketModel;

public class MainMarketAdapter extends BaseAdapter {

	private List<MainMarketModel> list;
	private Context context;
	
	public MainMarketAdapter(Context context,List<MainMarketModel> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.layout_gridview_item, parent,false);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.tv_zh = (TextView) convertView.findViewById(R.id.tv_zh);
			holder.tv_en = (TextView) convertView.findViewById(R.id.tv_en);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_zh.setText(list.get(position).getTitleZH());
		holder.tv_en.setText(list.get(position).getTitleEN());
		ImageLoader.getInstance().displayImage("drawable://"+list.get(position).getImgUrl(), holder.iv);
		return convertView;
	}
	
	
	private class ViewHolder{
		private TextView tv_zh,tv_en;
		private ImageView iv;
	}

}
