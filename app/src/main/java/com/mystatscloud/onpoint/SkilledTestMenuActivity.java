package com.mystatscloud.onpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SkilledTestMenuActivity extends ActionBarActivity
{

	public ArrayList<String> selectedSkills = new ArrayList<String>();;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		selectedSkills.add("3");
		selectedSkills.add("4");
		selectedSkills.add("5");

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_skilled_test_menu);

		ListView listView = (ListView) findViewById(R.id.testSkillListView);

		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		databaseAccess.open();

		List<String> skillLevel = databaseAccess.getTestSkillLevels();

		databaseAccess.close();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, skillLevel);

		listView.setAdapter(adapter);

	}


	/** Start a new test upon click */
	public void showPracticeExamView(View view)
	{

		Intent myIntent = new Intent(SkilledTestMenuActivity.this, PracticeExamActivity.class);

		myIntent.putStringArrayListExtra("selectedSkills", selectedSkills);

		SkilledTestMenuActivity.this.startActivity(myIntent);
	}


}
