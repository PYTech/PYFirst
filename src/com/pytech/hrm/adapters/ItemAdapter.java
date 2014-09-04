package com.pytech.hrm.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pytech.hrm.R;
import com.pytech.hrm.models.Item;

public class ItemAdapter extends ArrayAdapter<Item> {
	private int resource;
	private List<Item> items;

	public ItemAdapter(Context context, int resource, List<Item> items) {
		super(context, resource, items);
		this.resource = resource;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout itemView;
		// Ū���ثe��m���O�ƪ���
		final Item item = this.getItem(position);

		if(convertView == null) {
			// �إ߶��صe������
			itemView = new LinearLayout(this.getContext());
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(this.resource, itemView, true);
		} else {
			itemView = (LinearLayout) convertView;
		}

		// Ū���O���C��B�w��ܡB���D�P����ɶ�����
		RelativeLayout typeColor = (RelativeLayout) itemView.findViewById(R.id.type_color);
		ImageView selectedItem = (ImageView) itemView.findViewById(R.id.selected_item);
		TextView titleView = (TextView) itemView.findViewById(R.id.title_text);
		TextView dateView = (TextView) itemView.findViewById(R.id.date_text);

		// �]�w�O���C��
		GradientDrawable background = (GradientDrawable) typeColor.getBackground();
		background.setColor(item.getColor().parseColor());

		// �]�w���D�P����ɶ�
		titleView.setText(item.getTitle());
		dateView.setText(item.getLocaleDatetime());

		// �]�w�O�_�w���
		selectedItem.setVisibility(item.isSelected() ? View.VISIBLE : View.INVISIBLE);

		return itemView;
	}

	// �]�w���w�s�����O�Ƹ��
	public void set(int index, Item item) {
		if(index >= 0 && index < this.items.size()) {
			this.items.set(index, item);
			this.notifyDataSetChanged();
		}
	}

	// Ū�����w�s�����O�Ƹ��
	public Item get(int index) {
		return this.items.get(index);
	}
}
