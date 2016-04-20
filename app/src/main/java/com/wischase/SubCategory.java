package com.wischase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ranjitha on 3/8/2016.
 */
public class SubCategory implements Parcelable{
    private String subCategory;
    private int categoryId;

    public SubCategory(String subCategory, int categoryId)    {
        this.subCategory = subCategory;
        this.categoryId = categoryId;
    }

    public SubCategory()    {

    }
    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel subCategoryParcel, int flags) {
        subCategoryParcel.writeString((subCategory == null) ? "" : subCategory);
        subCategoryParcel.writeInt(categoryId);
    }

    public SubCategory(Parcel subCategoryParcel)  {
        subCategory = subCategoryParcel.readString();
        categoryId = subCategoryParcel.readInt();
    }
    public static final Parcelable.Creator<SubCategory> CREATOR = new Parcelable.Creator<SubCategory>() {
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }
        public SubCategory[] newArray(int size) {
            return new SubCategory[size];
        }
    };

    @Override
    public String toString() {
        return subCategory;
    }
}