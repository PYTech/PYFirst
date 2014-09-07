package com.pytech.first.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;

import com.pytech.first.R;
import com.pytech.first.util.PrefUtils;
import com.pytech.first.util.constants.Colors;
import com.pytech.first.util.constants.FIRST;

public class PreferenceActivity extends android.preference.PreferenceActivity {
	private SharedPreferences sharedPreferences;
	private Preference defaultColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 指定使用的設定畫面配置資源
		addPreferencesFromResource(R.xml.mypreference);

		this.defaultColor = (Preference) this.findPreference(this.getString(R.string.PREF_KEY_COLOR));
		this.sharedPreferences = PrefUtils.getSharedPref(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 讀取設定的預設顏色
		int color = this.sharedPreferences.getInt(this.getString(R.string.PREF_KEY_COLOR), FIRST.UNKNOWN);

		if(color != FIRST.UNKNOWN) {
			// 設定顏色說明
			this.defaultColor.setSummary(getString(R.string.default_color_summary) + ": " + Colors.fromColorId(color));
		}
	}
}
