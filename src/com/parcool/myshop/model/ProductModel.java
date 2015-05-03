package com.parcool.myshop.model;

public class ProductModel {
	private String id;
	private String title;
	private String price;
	private String thumbImgUrl;
	private int markerCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getThumbImgUrl() {
		return thumbImgUrl;
	}

	public void setThumbImgUrl(String thumbImgUrl) {
		this.thumbImgUrl = thumbImgUrl;
	}

	public int getMarkerCount() {
		return markerCount;
	}

	public void setMarkerCount(int markerCount) {
		this.markerCount = markerCount;
	}

}
