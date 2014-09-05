package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.pytech.hrm.R;
import com.pytech.hrm.models.Item;
import com.pytech.hrm.util.constants.HRM;

public class ItemActivity extends Activity {

	private EditText title_text, content_text;
	
	private Item item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		this.processViews();
		
		
	}

	public void onSubmit(View view) {
		if(view.getId() == R.id.ok_teim) {
			// Ū���ϥΪ̿�J�����D�P���e
			String titleText = this.title_text.getText().toString();
			String contentText = this.content_text.getText().toString();

			// ���o�^�Ǹ�ƥΪ�Intent����
			Intent result = this.getIntent();
			// �]�w���D�P���e
			result.putExtra(HRM.KEY_TITLE_TEXT, titleText);
			result.putExtra(HRM.KEY_CONTENT_TEXT, contentText);

			// �]�w�^�����G���T�w
			setResult(Activity.RESULT_OK, result);
		}

		// ����
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
				break;
		}
	}

	protected void processViews() {
		this.title_text = (EditText) this.findViewById(R.id.title_text);
		this.content_text = (EditText) this.findViewById(R.id.content_text);
	}
	
	protected void processAction() {
		Intent intent = this.getIntent();
		String action = intent.getAction();
		
		if(HRM.ACTION_EDIT.equals(action)) {
			// Use existed title.
			String titleText = intent.getStringExtra(HRM.KEY_TITLE_TEXT);
			this.title_text.setText(titleText);
		} else {
			this.item = new Item();
		}
	}
}
