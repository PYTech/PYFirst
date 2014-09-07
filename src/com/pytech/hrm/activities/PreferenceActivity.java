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
		// ���w�ϥΪ��]�w�e���t�m�귽
		addPreferencesFromResource(R.xml.mypreference);

		this.defaultColor = (Preference) this.findPreference(this.getString(R.string.PREF_KEY_COLOR));
		this.sharedPreferences = PrefUtils.getSharedPref(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Ū���]�w���w�]�C��
		int color = this.sharedPreferences.getInt(this.getString(R.string.PREF_KEY_COLOR), HRM.UNKNOWN);

		if(color != HRM.UNKNOWN) {
			// �]�w�C�⻡��
			this.defaultColor.setSummary(getString(R.string.default_color_summary) + ": " + Colors.fromColorId(color));
		}
	}
}
