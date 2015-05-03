package com.parcool.myshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPValue {

	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SPValue(Context context) {
		sp = context.getSharedPreferences("myShop", Context.MODE_PRIVATE);
		editor = sp.edit();
		editor.commit();
	}

	public void addLocalHistory(String keyWord) {
		String[] strArr = getLocalHistory();
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].equals(keyWord)) {
				return;
			}
		}
		String str = sp.getString("localHistory", "");
		str += "|" + keyWord;
		editor.putString("localHistory", str);
		editor.commit();
	}

	public String[] getLocalHistory() {
		String str = sp.getString("localHistory", "");
		String[] strArr = str.split("\\|");
		return strArr;
	}
	
	public void clearLocalHistory(){
		editor.putString("localHistory", "");
		editor.commit();
	}

}
