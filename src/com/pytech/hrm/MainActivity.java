package com.pytech.hrm;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialization.
		this.setUp();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showAbout(View view) {
		Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show();
	}

	public boolean clickMenuItem(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.item_search:
				break;
			case R.id.item_add:
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

		AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
		dialog.setTitle("MenuItem Test").setMessage(item.getTitle()).setIcon(item.getIcon()).show();
		
		return true;
	}

	protected void setUp() {
		// Generate initial data and adapter.
		String[] data = { "To Do List", "A Simple Recipe", "My Travel Note" };
		int layoutId = android.R.layout.simple_list_item_1;
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layoutId, data);

		// Put data into list view through adapter.
		ListView listView = (ListView) findViewById(R.id.record_list);
		listView.setAdapter(adapter);
	}
}
