package com.pytech.first.activities;

import org.apache.commons.lang3.StringUtils;

import com.pytech.first.R;
import com.pytech.first.util.PrefUtils;
import com.pytech.first.util.constants.Colors;
import com.pytech.first.util.constants.FIRST;
import com.pytech.first.util.constants.Layout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
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
			String action = result.getAction();
			
			if(StringUtils.equals(action, getString(R.string.ACTION_COLOR_SET))) {
				Editor editor = PrefUtils.getEditor(ColorActivity.this);
				editor.putInt(getString(R.string.PREF_KEY_COLOR), view.getId());
				editor.commit();
			} else {
				result.putExtra(FIRST.KEY_COLOR, view.getId());
				setResult(Activity.RESULT_OK, result);
			}
			finish();
		}
	}
}
