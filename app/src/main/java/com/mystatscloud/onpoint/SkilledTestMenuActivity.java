package com.mystatscloud.onpoint;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SkilledTestMenuActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_skilled_test_menu);

		ListView listView = (ListView) findViewById(R.id.testSkillListView);

		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);

		databaseAccess.open();

		List<String> skillLevel = databaseAccess.getTestSkillLevels();

		databaseAccess.close();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, skillLevel);

		listView.setAdapter(adapter);

	}

}
