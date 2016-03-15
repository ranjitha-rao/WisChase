package com.wischase;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjitha on 12/12/2015.
 */
public class Category implements Parcelable{
    public String categoryName;
    private static List<SubCategory> subCategory;

    public Category()   {

    }

    public Category(String category, List<SubCategory> subCategory)  {
        this.categoryName = category;
        Category.subCategory = subCategory;
    }

    public Category(String categoryName, String subCategoryName, int categoryId)    {
        this.categoryName = categoryName;
        add(subCategoryName, categoryId);
    }

    public String getCategory() {
        return categoryName;
    }

    public void setCategory(String category) {
        this.categoryName = category;
    }

    public static List<SubCategory> getSubCategory() {
        if(subCategory == null) {
            subCategory = new ArrayList<SubCategory>();
        }
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        Category.subCategory = subCategory;
    }

    public void add(String subCategory, int categoryId)    {
        if(Category.subCategory == null)    {
            Category.subCategory = new ArrayList<SubCategory>();
        }
        Category.subCategory.add(new SubCategory(subCategory, categoryId));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel categoryParcel, int flags) {
        categoryParcel.writeString(categoryName);
        categoryParcel.writeTypedList(subCategory);
    }
    public Category(Parcel categoryParcel) {
        categoryName = categoryParcel.readString();
        subCategory = new ArrayList<SubCategory>();
        categoryParcel.readTypedList(subCategory, SubCategory.CREATOR);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}
