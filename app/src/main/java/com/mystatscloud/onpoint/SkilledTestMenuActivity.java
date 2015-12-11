package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
		// add all skill level 3 test questions for debugging
		selectedSkills.add("3");

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

		// fill the list with list items
		listView.setAdapter(adapter);

	}


	/** Start a new test upon click */
	public void showPracticeExamView(View view)
	{

		Intent myIntent = new Intent(SkilledTestMenuActivity.this, PracticeExamActivity.class);

		// save the selected skill levels choosen by the user
		myIntent.putStringArrayListExtra("selectedSkills", selectedSkills);

		SkilledTestMenuActivity.this.startActivity(myIntent);
	}


}
