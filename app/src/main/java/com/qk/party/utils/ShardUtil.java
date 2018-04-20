package com.qk.party.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 存储工具类
 * @author zlf
 *Android SDK中并未提供Base64编码和解码库。因此，需要使用第三方的jar包。在本例中使用了Apache Commons组件集中的Codec组件进行Base64编码和解码
 *
 */
public class ShardUtil {
	public static String userinfo = "userinfo";
	/**
	 * 设置SharedPreferences名,不设置时默认为userinfo
	 * @param key
	 * @param value
	 */
	public static void savePreferenceBoolean(Context context,String key, boolean value)
	{

		SharedPreferences preferences = context.getSharedPreferences(userinfo,0);

		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 读取一个key Boolean值方法  默认为false
	 * @param key
	 * @return
	 */
	public static boolean getPreferenceBoolean(Context context,String key) {
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);
		return preferences.getBoolean(key, false);
	}




	/**
	 * 设置SharedPreferences名,不设置时默认为userinfo
	 * @param key
	 * @param value
	 */
	public  static void savePreferenceInt(Context context,String key, int value)
	{
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);

		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 读取一个key int值方法  默认为false
	 * @param key
	 * @return
	 */
	public  static int getPreferenceInt(Context context,String key) {
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);
		return preferences.getInt(key, 0);
	}

	/**
	 * 保存一个key方法
	 * @param key
	 * @param value
	 */
	public  static void savePreferenceString(Context context,String key, String value) {
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 删除一个key
	 * @param key
	 */
	public  static void deleteKey(Context context,String key) {
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(key);
		editor.commit();
	}

	/**
	 * 读取一个key Long值方法  默认Mode 为0
	 * @param key
	 * @return
	 */
	public  static void savePreferenceLong(Context context,String key, long value) {
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**读取一个key String值方法  默认Mode 为0
	 *
	 * @param key
	 * @return
	 */
	public static String getPreferenceString(Context context,String key) {
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);
		return preferences.getString(key, "");

	}

	public  static long getPreferenceLong(Context context,String key) {
		SharedPreferences preferences = context.getSharedPreferences(userinfo,
				0);
		return preferences.getLong(key, 0L);
	}

	public static boolean getBoolean(Context context, String key,
									 boolean defaultValue) {
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		return sp.getBoolean(key, defaultValue);
	}

	public static void setBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

}
