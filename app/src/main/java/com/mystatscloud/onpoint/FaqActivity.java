package com.mystatscloud.onpoint;

import com.mystatscloud.onpoint.expandListView.Classes.ExpandListParent;

import android.app.SearchManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import java.util.ArrayList;

import android.widget.ExpandableListView;
import android.widget.SearchView;

public class FaqActivity extends ActionBarActivity implements
		SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private SearchView search;
    private ExpandListAdapter ExpAdapter;
    private ArrayList<ExpandListParent> ExpListItems;
    private ExpandableListView ExpandList;

    /**
     * Display FAQ activity layout when created
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        ExpListItems = new ArrayList<ExpandListParent>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        ExpandList = (ExpandableListView) findViewById(R.id.expandableListView_faq);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ExpListItems.addAll(databaseAccess.getFAQs());
        databaseAccess.close();

        SearchManager searchManager = (SearchManager) getSystemService(FaqActivity.this.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        ExpAdapter = new ExpandListAdapter(FaqActivity.this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);
        }

    public void expandGroups() {
        int count = ExpAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            ExpandList.expandGroup(i);
        }
    }

    public void collapseGroups() {
        int count = ExpAdapter.getGroupCount();
        for (int i = 0; i < count; i++)
            ExpandList.collapseGroup(i);
    }

    public boolean onClose() {
        ExpAdapter.filterData("");
        expandGroups();
        return false;
    }

    public boolean onQueryTextChange(String query) {
        ExpAdapter.filterData(query);
        if (query.isEmpty()) {
            collapseGroups();
        }
        else{
            expandGroups();
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        ExpAdapter.filterData(query);
        if (query.isEmpty()) {
            collapseGroups();
        }
        else{
            expandGroups();
        }
        return false;
    }
}
