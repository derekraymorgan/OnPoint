package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * The activity that manages the Category based testing menu
 */
public class CategoryTestMenuActivity extends ActionBarActivity
{
	public ArrayList<String> selectedCategories = new ArrayList<String>();

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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, testCategories);

		listView.setAdapter(adapter);

	}

	/** Start a new test upon click */
	public void showPracticeExamView(View view)
	{

		// reset the selected skills(for the case when user hits back button)
		this.selectedCategories = new ArrayList<String>();

		// at least
		boolean atLeastOneSelected = false;

		// get the list of skill levels
		ListView skillList = (ListView) findViewById(R.id.testCategoryListView);

		// get the indices of the selected items
		SparseBooleanArray checked = skillList.getCheckedItemPositions();

		int numItemsSelected = skillList.getAdapter().getCount();


		for(int i = 0; i < numItemsSelected; i++)
		{
			if(checked.get(i))
			{

				// add all skill levels that were selected by the user
				selectedCategories.add(skillList.getItemAtPosition(i).toString());

				atLeastOneSelected = true;

			}
		}

		// make sure user selects at least one item is selected
		if(!atLeastOneSelected)
		{
			Toast.makeText(this, "You must select at least one test category.", Toast.LENGTH_SHORT).show();

			return;
		}


		Intent myIntent = new Intent(CategoryTestMenuActivity.this, PracticeExamActivity.class);

		// save the selected categories levels choosen by the user
		myIntent.putStringArrayListExtra("selectedCategories", selectedCategories);

		CategoryTestMenuActivity.this.startActivity(myIntent);
	}

}
