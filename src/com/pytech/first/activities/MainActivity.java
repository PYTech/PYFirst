package com.pytech.first.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.pytech.first.R;
import com.pytech.first.adapters.ItemAdapter;
import com.pytech.first.models.Item;
import com.pytech.first.util.constants.Colors;
import com.pytech.first.util.constants.FIRST;

public class MainActivity extends Activity {

	private ItemAdapter itemAdapter;
	private List<Item> itemList;

	private ListView itemListView;
	private MenuItem add_item, search_item, revert_item, share_item, delete_item;
	private int selectedCount = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.setTitle(StringUtils.EMPTY);

		// Initialization.
		this.processViews();
		this.processControllers();
		this.processDatas();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		this.initializeMenu(menu);
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
				Intent intent = new Intent(this, ItemActivity.class);
				this.startActivityForResult(intent, FIRST.REQ_CODE_NEW);
				break;
			case R.id.item_revert:
				break;
			case R.id.item_delete:
				this.deleteSelected();
				break;
			case R.id.item_share_google:
				Toast.makeText(this, R.string.shared_google_ok, Toast.LENGTH_SHORT).show();
				break;
			case R.id.item_share_facebook:
				Toast.makeText(this, R.string.shared_facebook_ok, Toast.LENGTH_SHORT).show();
				break;
			case R.id.item_preference:
				this.startActivity(new Intent(this, PreferenceActivity.class));
				break;
		}
		this.reset();
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK) {
			Item item = (Item) data.getParcelableExtra(FIRST.KEY_ITEM);
			if(requestCode == FIRST.REQ_CODE_NEW) {
				item.setDatetime(System.currentTimeMillis());
				this.itemList.add(item);
			} else if(requestCode == FIRST.REQ_CODE_EDIT) {
				int position = data.getIntExtra(FIRST.KEY_POSITION, FIRST.UNKNOWN);
				if(position != FIRST.UNKNOWN) {
					item.setLastModify(System.currentTimeMillis());
					this.itemList.set(position, item);
				}
			}
			this.itemAdapter.notifyDataSetChanged();
		}
	}

	protected void processViews() {
		this.itemListView = (ListView) this.findViewById(R.id.record_list);
	}

	protected void processControllers() {
		// Click event handler for record list view.
		OnItemClickListener itemListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Item item = (Item) parent.getItemAtPosition(position);
				if(selectedCount > 0) {
					processMenu(item);
					itemAdapter.set(position, item);
				} else {
					Intent intent = new Intent(getString(R.string.ACTION_EDIT_ITEM));
					intent.putExtra(FIRST.KEY_POSITION, position);
					intent.putExtra(FIRST.KEY_ITEM, item);
					startActivityForResult(intent, FIRST.REQ_CODE_EDIT);
				}
			}
		};
		this.itemListView.setOnItemClickListener(itemListener);

		// Long click event handler for record list view.
		OnItemLongClickListener onItemLongClickListener = new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Item item = (Item) parent.getItemAtPosition(position);
				processMenu(item);
				itemAdapter.set(position, item);
				return true;
			}
		};
		this.itemListView.setOnItemLongClickListener(onItemLongClickListener);
	}

	protected void processDatas() {
		// Generate initial data and adapter.
		if(this.itemList == null) {
			this.itemList = new ArrayList<Item>();
			long now = System.currentTimeMillis();
			int i;
			for(i = 1; i <= FIRST.INIT_MOCK_DATA_NUM; ++i) {
				String mockTitle = FIRST.MODK_DATA_TITLE_HEADER + i;
				String mockContent = FIRST.MODK_DATA_CONTENT_HEADER + i;
				Colors[] colors = Colors.values();
				int randIndex = (int) (Math.random() * colors.length);
				Colors mockColor = colors[randIndex];
				this.itemList.add(new Item(i + 1, now, mockColor, mockTitle, mockContent, StringUtils.EMPTY, 0, 0, 0));
			}
	
			// Put item data into list view through self-defined adapter.
			this.itemAdapter = new ItemAdapter(this, R.layout.item, this.itemList);
			this.itemListView.setAdapter(this.itemAdapter);
		}
	}

	protected void initializeMenu(Menu menu) {
		this.add_item = menu.findItem(R.id.item_add);
		this.search_item = menu.findItem(R.id.item_search);
		this.delete_item = menu.findItem(R.id.item_delete);
		this.revert_item = menu.findItem(R.id.item_revert);
		this.share_item = menu.findItem(R.id.item_share);

		// Hide some menu items on create.
		this.processMenu(null);
	}
	
	protected void processMenu(Item item) {
		if(item != null) {
			if(item.isSelected()) {
				item.setSelected(false);
				this.selectedCount--;
			} else {
				item.setSelected(true);
				this.selectedCount++;
			}
		}
		
		// Handle menu visibility.
		this.add_item.setVisible(this.selectedCount == 0);
		this.search_item.setVisible(this.selectedCount == 0);
		this.delete_item.setVisible(this.selectedCount > 0);
		this.revert_item.setVisible(this.selectedCount > 0);
		this.share_item.setVisible(this.selectedCount >0);
	}
	
	private void deleteSelected() {
		if(this.selectedCount <= 0) {
			return;
		}
		
		List<Integer> delList = new ArrayList<Integer>();
		int i;
		for(i = this.itemList.size() - 1; i >= 0; --i) {
			Item item = this.itemList.get(i);
			if(item != null) {
				if(item.isSelected()) {
					delList.add(i);
				}
			}
		}
		for(int id : delList) {
			this.itemList.remove(id);
		}
	}
	
	private void reset() {
		this.selectedCount = 0;
		this.processMenu(null);
		for(Item item : this.itemList) {
			item.setSelected(false);
		}
		this.itemAdapter.notifyDataSetChanged();
	}
}
