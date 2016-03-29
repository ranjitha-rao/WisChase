package com.wischase.activity;

import android.view.View;

import com.wischase.Category;
import com.wischase.R;
import com.wischase.view.CustomTextView;
import com.wischase.view.menu.ScrollingActivity;

/**
 * Created by disha on 3/27/2016.
 */
public class UpdateTable extends ScrollingActivity {

    public void updateCategoryTable(Category userCat,int usergrade)
    {
        CustomTextView customGrade=(CustomTextView)findViewById(R.id.grade);
        customGrade.setText(String.valueOf(usergrade));
        CustomTextView customCategory=(CustomTextView)findViewById(R.id.category);
        customCategory.setText(userCat.getCategory());
        CustomTextView customSubCat=(CustomTextView)findViewById(R.id.subcategory);
        customSubCat.setText(userCat.getSubCategory().get(0).getSubCategory());
    }

}
