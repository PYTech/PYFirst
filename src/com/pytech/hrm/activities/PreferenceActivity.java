package com.pytech.hrm.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;

import com.pytech.hrm.R;
import com.pytech.hrm.util.PrefUtils;
import com.pytech.hrm.util.constants.Colors;
import com.pytech.hrm.util.constants.HRM;

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
		int color = this.sharedPreferences.getInt(this.getString(R.string.PREF_KEY_COLOR), HRM.UNKNOWN);

		if(color != HRM.UNKNOWN) {
			// 設定顏色說明
			this.defaultColor.setSummary(getString(R.string.default_color_summary) + ": " + Colors.fromColorId(color));
		}
	}
}
