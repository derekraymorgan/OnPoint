package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * The activity that manages the Category based testing menu
 */
public class CategoryTestMenuActivity extends ActionBarActivity
{
	/**
	 * When this activity is created combine it with the appropriate view
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		// set the view for this activity
		setContentView(R.layout.activity_category_test_menu);

		ListView listView = (ListView) findViewById(R.id.testCategoryListView);

		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		// open tghe database
		databaseAccess.open();

		// get all the test type categories
		List<String> testCategories = databaseAccess.getTestCategories();

		databaseAccess.close();

		// load the enu items from the list of test categories
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testCategories);

		listView.setAdapter(adapter);

	}

}
