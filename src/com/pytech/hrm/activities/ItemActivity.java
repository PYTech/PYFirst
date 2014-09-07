package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.pytech.hrm.R;
import com.pytech.hrm.models.Item;
import com.pytech.hrm.util.PrefUtils;
import com.pytech.hrm.util.constants.Colors;
import com.pytech.hrm.util.constants.HRM;

public class ItemActivity extends Activity {

	private EditText title_text, content_text;

	private Item item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		this.processViews();
		this.processAction();
	}

	public void onSubmit(View view) {
		if(view.getId() == R.id.ok_teim) {
			// Get user input title and content.
			String titleText = this.title_text.getText().toString();
			String contentText = this.content_text.getText().toString();

			// Return item with new title and content.
			Intent result = this.getIntent();
			this.item.setTitle(titleText);
			this.item.setContent(contentText);
			result.putExtra(HRM.KEY_ITEM, this.item);

			// Result OK.
			setResult(Activity.RESULT_OK, result);
		}
		finish();
	}

	public void clickFunction(View view) {
		switch(view.getId()) {
			case R.id.take_picture:
				break;
			case R.id.record_sound:
				break;
			case R.id.set_location:
				break;
			case R.id.set_alarm:
				break;
			case R.id.select_color:
				Intent intent = new Intent(this, ColorActivity.class);
				this.startActivityForResult(intent, HRM.ACTION_CODE_START_COLOR);
				break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK) {
			switch(requestCode) {
				case HRM.ACTION_CODE_START_CAMERA:
					break;
				case HRM.ACTION_CODE_START_RECORD:
					break;
				case HRM.ACTION_CODE_START_LOCATION:
					break;
				case HRM.ACTION_CODE_START_ALARM:
					break;
				case HRM.ACTION_CODE_START_COLOR:
					int colorId = data.getIntExtra(HRM.KEY_COLOR, Colors.LIGHTGREY.parseColor());
					item.setColor(Colors.fromColorId(colorId));
					break;
			}
		}
	}

	protected void processViews() {
		this.title_text = (EditText) this.findViewById(R.id.title_text);
		this.content_text = (EditText) this.findViewById(R.id.content_text);
	}

	protected void processAction() {
		Intent intent = this.getIntent();
		String action = intent.getAction();

		if(this.getString(R.string.ACTION_EDIT_ITEM).equals(action)) {
			// Use existed title & content.
			this.item = intent.getParcelableExtra(HRM.KEY_ITEM);
			this.title_text.setText(item.getTitle());
			this.content_text.setText(item.getContent());
		} else {
			this.item = new Item();
			// Handle default color setting.
			SharedPreferences pref = PrefUtils.getSharedPref(this);
			int color = pref.getInt(this.getString(R.string.PREF_KEY_COLOR), HRM.UNKNOWN);
			if(color != HRM.UNKNOWN) {
				this.item.setColor(Colors.fromColorId(color));
			}
		}
	}
}
