package com.mystatscloud.onpoint;

import com.mystatscloud.onpoint.expandListView.Classes.ExpandListChild;
import com.mystatscloud.onpoint.expandListView.Classes.ExpandListParent;

import com.mystatscloud.onpoint.DatabaseAccess;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;

import android.widget.ExpandableListView;

public class FaqActivity extends ActionBarActivity {
	private ExpandListAdapter ExpAdapter;
	private ArrayList<ExpandListParent> ExpListItems;
	private ExpandableListView ExpandList;

	/**
	 * Display FAQ activity layout when created
	 * @param savedInstanceState
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faq);
		ExpandList = (ExpandableListView) findViewById(R.id.expandableListView_faq);

		DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
		databaseAccess.open();
		ExpListItems = databaseAccess.getFAQs();
		databaseAccess.close();

		ExpAdapter = new ExpandListAdapter(FaqActivity.this, ExpListItems);
		ExpandList.setAdapter(ExpAdapter);
	}

}
