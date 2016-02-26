package com.wischase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ranjitha on 12/12/2015.
 */
public class Category {
    private String categoryName;
    private List<SubCategory> subCategory;

    public Category()   {

    }

    public Category(String category, List<SubCategory> subCategory)  {
        this.categoryName = category;
        this.subCategory = subCategory;
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

    public List<SubCategory> getSubCategory() {
        if(subCategory == null) {
            subCategory = new ArrayList<SubCategory>();
        }
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public void add(String subCategory, int categoryId)    {
        if(this.subCategory == null)    {
            this.subCategory = new ArrayList<SubCategory>();
        }
        this.subCategory.add(new SubCategory( subCategory,categoryId ));
    }
    public class SubCategory {
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

    }
}
