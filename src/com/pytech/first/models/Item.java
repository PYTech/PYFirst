package com.pytech.first.models;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import android.os.Parcel;
import android.os.Parcelable;

import com.pytech.first.util.constants.Colors;

public class Item implements Parcelable {
	
	private long id;
	private long datetime;
	private Colors color;
	private String title;
	private String content;
	private String fileName;
	private double latitude;
	private double longitude;
	private long lastModify;
	private boolean selected;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.id);
		dest.writeLong(this.datetime);
		dest.writeString((this.color == null) ? StringUtils.EMPTY : this.color.name());
		dest.writeString(this.title);
		dest.writeString(this.content);
		dest.writeString(this.fileName);
		dest.writeDouble(this.latitude);
		dest.writeDouble(this.longitude);
		dest.writeLong(this.lastModify);
		dest.writeInt(selected ? 1 : 0);
	}
	
	public void readFromParcel(Parcel source) {
		this.id = source.readLong();
		this.datetime = source.readLong();
		String colorStr = source.readString();
		this.color = StringUtils.isEmpty(colorStr) ? null : Colors.valueOf(colorStr);
		this.title = source.readString();
		this.content = source.readString();
		this.fileName = source.readString();
		this.latitude = source.readDouble();
		this.longitude = source.readDouble();
		this.lastModify = source.readLong();
		this.selected = source.readInt() > 0;
	}
	
	public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {

		@Override
		public Item createFromParcel(Parcel source) {
			return new Item(source);
		}

		@Override
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};

	public Item() {
		title = "";
		content = "";
		color = Colors.LIGHTGREY;
	}

	public Item(long id, long datetime, Colors color, String title, String content, String fileName, double latitude, double longitude, long lastModify) {
		this.id = id;
		this.datetime = datetime;
		this.color = color;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lastModify = lastModify;
	}
	
	private Item(Parcel parcel) {
		this.readFromParcel(parcel);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getLastModify() {
		return lastModify;
	}

	public void setLastModify(long lastModify) {
		this.lastModify = lastModify;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getLocaleDatetime() {
		return String.format(Locale.getDefault(), "%tF  %<tR", new Date(this.datetime));
	}

	public String getLocaleDate() {
		return String.format(Locale.getDefault(), "%tF", new Date(this.datetime));
	}

	public String getLocaleTime() {
		return String.format(Locale.getDefault(), "%tR", new Date(this.datetime));
	}
}
