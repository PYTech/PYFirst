package com.pytech.hrm.activities;

import com.pytech.hrm.R;
import com.pytech.hrm.util.constants.Colors;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.Layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ColorActivity extends Activity {
	private LinearLayout color_gallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_color);

		this.processViews();
		this.createViews();		
	}

	protected void processViews() {
		this.color_gallery = (LinearLayout) this.findViewById(R.id.color_gallery);
	}
	
	protected void createViews() {
		ColorListener listener = new ColorListener();
		for(Colors c : Colors.values()) {
			Button button = new Button(this);
			button.setId(c.parseColor());
			LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(Layout.COLOR_DIALOG_WIDTH, Layout.COLOR_DIALOG_HEIGHT);
			layout.setMargins(Layout.COLOR_DIALOG_MARGIN, Layout.COLOR_DIALOG_MARGIN, Layout.COLOR_DIALOG_MARGIN, Layout.COLOR_DIALOG_MARGIN);
			button.setLayoutParams(layout);
			button.setBackgroundColor(c.parseColor());
			button.setOnClickListener(listener);
			this.color_gallery.addView(button);
		}
	}

	private class ColorListener implements OnClickListener {
		@Override
		public void onClick(View view) {
			Intent result = getIntent();
			result.putExtra(HRM.KEY_COLOR, view.getId());
			setResult(Activity.RESULT_OK, result);
			finish();
		}
	}
}
