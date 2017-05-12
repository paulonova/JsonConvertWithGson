package com.example.android.restful.model;

import android.os.Parcel;
import android.os.Parcelable;

/** * Created by Paulo Vila Nova on 2017-05-08.
 * POJO: Plain Old Java Object är en beskrivning av en typ av objekt i programspråket Java
 *
 *
 * My goal is to create instances of this class, and be able to pass that data from the
 * service back to the activity. I'll be wrapping the data inside an intent, which will be
 * put into a broadcast message. You can't take a class like this and put it into an intent,
 * though, unless it implements the Parcelable interface.
 *
 Parcelable process is much faster than serializable. One of the reasons for this is that we are
 being explicit about the serialization process instead of using reflection to infer it.
 It also stands to reason that the code has been heavily optimized for this purpose.
 */

public class DataItem implements Parcelable {

    private String itemName;
    private String category;
    private String description;
    private int sort;
    private double price;
    private String image;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemName);
        dest.writeString(this.category);
        dest.writeString(this.description);
        dest.writeInt(this.sort);
        dest.writeDouble(this.price);
        dest.writeString(this.image);
    }

    public DataItem() {
    }

    protected DataItem(Parcel in) {
        this.itemName = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.sort = in.readInt();
        this.price = in.readDouble();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}