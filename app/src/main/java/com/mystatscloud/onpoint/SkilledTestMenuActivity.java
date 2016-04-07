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
 * Show a menu of different skill levels that correspond to the difficulty of the test
 */
public class SkilledTestMenuActivity extends ActionBarActivity
{

	public ArrayList<String> selectedSkills = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		// set the view for this menu screen
		setContentView(R.layout.activity_skilled_test_menu);

		ListView listView = (ListView) findViewById(R.id.testSkillListView);

		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		databaseAccess.open();

		// get the unique test levels from the database
		List<String> skillLevel = databaseAccess.getTestSkillLevels();

		databaseAccess.close();

		// use a list with a text view and checkbox layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, skillLevel);

		// fill the list with list items(skill levels)
		listView.setAdapter(adapter);

	}


	/** Start a new test upon click */
	public void showPracticeExamView(View view)
	{

		// reset the selected skills(for the case when user hits back button)
		this.selectedSkills = new ArrayList<String>();

		// at least
		boolean atLeastOneSelected = false;

		// get the list of skill levels
		ListView skillList = (ListView) findViewById(R.id.testSkillListView);

		// get the indices of the selected items
		SparseBooleanArray checked = skillList.getCheckedItemPositions();

		int numItemsSelected = skillList.getAdapter().getCount();


		for(int i = 0; i < numItemsSelected; i++)
		{
			if(checked.get(i))
			{

				// add all skill levels that were selected by the user
				selectedSkills.add(skillList.getItemAtPosition(i).toString());


				atLeastOneSelected = true;

			}
		}

		// make sure user selects atleast one item
		if(!atLeastOneSelected)
		{
			Toast.makeText(this, "You must select at least one skill level.", Toast.LENGTH_SHORT).show();

			return;
		}


		Intent myIntent = new Intent(SkilledTestMenuActivity.this, PracticeExamActivity.class);

		// save the selected skill levels choosen by the user
		myIntent.putStringArrayListExtra("selectedSkills", selectedSkills);

		SkilledTestMenuActivity.this.startActivity(myIntent);
	}


}
