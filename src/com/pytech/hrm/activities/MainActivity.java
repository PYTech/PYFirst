package com.pytech.hrm.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.pytech.hrm.R;
import com.pytech.hrm.adapters.ItemAdapter;
import com.pytech.hrm.models.Item;
import com.pytech.hrm.util.constants.Colors;

public class MainActivity extends Activity {

	private ItemAdapter itemAdapter;
	private List<Item> itemList;
	
	private ListView itemListView;
	private MenuItem add_item, search_item, revert_item, share_item, delete_item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialization.
		this.processViews();
		this.processControllers();
		this.processDatas();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showAbout(View view) {
		// Switch to about activity.
		Intent intent = new Intent(this, AboutActivity.class);
		this.startActivity(intent);
	}

	public boolean clickMenuItem(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.item_search:
				break;
			case R.id.item_add:
				// Switch to item activity.
				Intent intent = new Intent(this, ItemActivity.class);
				this.startActivityForResult(intent, 0);
				break;
			case R.id.item_revert:
				break;
			case R.id.item_delete:
				break;
			case R.id.item_share_google:
				break;
			case R.id.item_share_facebook:
				break;
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if(resultCode == Activity.RESULT_OK) {
//			String titleText = data.getStringExtra(HRM.KEY_TITLE_TEXT);
//			if(titleText != null && !titleText.trim().equals("")) {
//				this.dataList.add(titleText);
//			}
//			this.adapter.notifyDataSetChanged();
//		}
	}

	protected void processViews() {
		this.itemListView = (ListView) this.findViewById(R.id.record_list);
	}

	protected void processControllers() {
//		// Long click event handler for title text view.
//		OnLongClickListener titleOnLongClickListener = new OnLongClickListener() {
//			@Override
//			public boolean onLongClick(View v) {
//				AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//				dialog.setTitle(R.string.app_name).setMessage(R.string.about).show();
//				return false;
//			}
//		};
//		this.titleTextView.setOnLongClickListener(titleOnLongClickListener);
//
//		// Click event handler for record list view.
//		OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//				String content = (String) parent.getItemAtPosition(position);
//				Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
//				return false;
//			}
//		};
//		this.itemListView.setOnItemLongClickListener(onItemLongClickListener);
	}

	protected void processDatas() {
		// Generate initial data and adapter.
		this.itemList = new ArrayList<Item>();
		long now = System.currentTimeMillis();
		this.itemList.add(new Item(1, now, Colors.RED, "Mission 1", "Scene: xxx.ma; Frames: 1-10", "", 0, 0, 0));
		this.itemList.add(new Item(2, now, Colors.BLUE, "Mission 2", "Scene: abc.blend; Frames: 1-20", "", 0, 0, 0));
		this.itemList.add(new Item(3, now, Colors.GREEN, "Mission 3", "Scene: test.mb; Frames: 30-50", "", 0, 0, 0));
	 
		// Put item data into list view through self-defined adapter.
		this.itemAdapter = new ItemAdapter(this, R.layout.item, this.itemList);
		this.itemListView.setAdapter(this.itemAdapter);
	}
}
