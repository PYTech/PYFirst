package com.pytech.first.activities;

import com.pytech.first.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class AboutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
	}

	public void clickOk(View view) {
		finish();
	}
}