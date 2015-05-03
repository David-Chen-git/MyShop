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
import com.parcool.myshop.model.ProductModel;

public class ProductsListAdapter extends BaseAdapter {

	private List<ProductModel> list;
	private Context context;
	
	public ProductsListAdapter(Context context,List<ProductModel> list) {
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_products, parent,false);
			holder.iv = (ImageView) convertView.findViewById(R.id.iv);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
			holder.tvMarkerCount = (TextView) convertView.findViewById(R.id.tv_marker_count);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvTitle.setText(list.get(position).getTitle());
		holder.tvPrice.setText("¥"+list.get(position).getPrice());
		holder.tvMarkerCount.setText(""+list.get(position).getMarkerCount());
		ImageLoader.getInstance().displayImage(list.get(position).getThumbImgUrl(), holder.iv);
		return convertView;
	}
	
	
	private class ViewHolder{
		private TextView tvTitle,tvPrice,tvMarkerCount;
		private ImageView iv;
	}

}
