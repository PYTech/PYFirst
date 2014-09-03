package com.pytech.hrm;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

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
