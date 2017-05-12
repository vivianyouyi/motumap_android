package com.motu.motumap.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

	private static final String CACHE_FILE_NAME = "EcoLink";
	private static SpUtils cacheUtils;
	private static SharedPreferences mSharedPreferences;

	public static SpUtils getInstance(Context context) {
		synchronized (SpUtils.class) {
			if (cacheUtils == null) {
				cacheUtils = new SpUtils();
				if(mSharedPreferences == null) {
					mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME, Context.MODE_PRIVATE);
				}
			}
		}
		return cacheUtils;
	}

	public void putBoolean(String key, boolean value) {
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}

	public boolean getBoolean(String key, boolean defValue) {
		return mSharedPreferences.getBoolean(key, defValue);
	}

	public void putInt(String key, int value) {
		mSharedPreferences.edit().putInt(key, value).commit();
	}

	public int getInt(String key, int defValue) {
		return mSharedPreferences.getInt(key, defValue);
	}

	public void putString(String key, String value) {
		mSharedPreferences.edit().putString(key, value).commit();
	}


	public String getString(String key, String defValue) {
		return mSharedPreferences.getString(key, defValue);
	}
}
