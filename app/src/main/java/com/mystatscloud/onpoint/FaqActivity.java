package com.mystatscloud.onpoint;

import com.mystatscloud.onpoint.com.expandListView.Classes.*;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;

import android.widget.ExpandableListView;

public class FaqActivity extends ActionBarActivity {
	private ExpandListAdapter ExpAdapter;
	private ArrayList<ExpandListParent> ExpListItems;
	private ExpandableListView ExpandList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faq);
		ExpandList = (ExpandableListView) findViewById(R.id.expandableListView);
		ExpListItems = SetStandardGroups();
		ExpAdapter = new ExpandListAdapter(FaqActivity.this, ExpListItems);
		ExpandList.setAdapter(ExpAdapter);
	}

	public ArrayList<ExpandListParent> SetStandardGroups() {
		ArrayList<ExpandListParent> groupList = new ArrayList<ExpandListParent>();
		ArrayList<ExpandListChild> itemList = new ArrayList<ExpandListChild>();

		// Make a new question
			ExpandListParent question = new ExpandListParent();
			question.setName("What is OnPoint?");
		// Make a new answer
			ExpandListChild answer = new ExpandListChild();
			answer.setName("Blah Blah");
			answer.setTag(null);
		// Add answer to an item list (multiple items possible) and connect to answer
			itemList.add(answer);
			question.setItems(itemList);
		// Add question to list
			groupList.add(question);

		// New item list
			itemList = new ArrayList<ExpandListChild>();
		// Make a new question
			question = new ExpandListParent();
			question.setName("What is NCRC?");
		// Make a new answer
			answer = new ExpandListChild();
			answer.setName("Moo Moo Mooooooooooooooooooooooooooooooooooooooooooooooooooooooooo ");
			answer.setTag(null);
		// Add answer to an item list (multiple items possible) and connect to answer
			itemList.add(answer);
			question.setItems(itemList);
		// Add question to list
			groupList.add(question);

		// New item list
			itemList = new ArrayList<ExpandListChild>();
		// Make a new question
			question = new ExpandListParent();
			question.setName("Question 3");
		// Make a new answer
			answer = new ExpandListChild();
			answer.setName("Blah Blah");
			answer.setTag(null);
		// Add answer to an item list (multiple items possible) and connect to answer
			itemList.add(answer);
			question.setItems(itemList);
		// Add question to list
			groupList.add(question);

		// New item list
			itemList = new ArrayList<ExpandListChild>();
		// Make a new question
			question = new ExpandListParent();
			question.setName("Question 4");
		// Make a new answer
			answer = new ExpandListChild();
			answer.setName("Blah Blah ");
			answer.setTag(null);
		// Add answer to an item list (multiple items possible) and connect to answer
			itemList.add(answer);
			question.setItems(itemList);
		// Add question to list
			groupList.add(question);

		// New item list
			itemList = new ArrayList<ExpandListChild>();
		// Make a new question
			question = new ExpandListParent();
			question.setName("Question 5");
		// Make a new answer
			answer = new ExpandListChild();
			answer.setName("Blah Blah");
			answer.setTag(null);
		// Add answer to an item list (multiple items possible) and connect to answer
			itemList.add(answer);
			question.setItems(itemList);
		// Add question to list
			groupList.add(question);

		// New item list
			itemList = new ArrayList<ExpandListChild>();
		// Make a new question
			question = new ExpandListParent();
			question.setName("Question 6");
		// Make a new answer
			answer = new ExpandListChild();
			answer.setName("Blah Blah");
			answer.setTag(null);
		// Add answer to an item list (multiple items possible) and connect to answer
			itemList.add(answer);
			question.setItems(itemList);
		// Add question to list
			groupList.add(question);

		// New item list
			itemList = new ArrayList<ExpandListChild>();
		// Make a new question
			question = new ExpandListParent();
			question.setName("Question 7");
		// Make a new answer
			answer = new ExpandListChild();
			answer.setName("Blah Blah");
			answer .setTag(null);
		// Add answer to an item list (multiple items possible) and connect to answer
			itemList.add(answer);
			question.setItems(itemList);
		// Add question to list
			groupList.add(question);

		return groupList;
	}
}
