package com.mystatscloud.onpoint;

import com.mystatscloud.onpoint.expandListView.Classes.ExpandListChild;
import com.mystatscloud.onpoint.expandListView.Classes.ExpandListParent;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * ExpandListAdapter that extends from BaseExpandableListAdapter to correspond to FaqActivity's ExpandableListView
 */

public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<ExpandListParent> groups;
    private ArrayList<ExpandListParent> groupsAll;

    /**
     * Constructor
     * @param context
     * @param groups
     */
    public ExpandListAdapter(Context context, ArrayList<ExpandListParent> groups) {
        this.context = context;
        this.groups = groups;
        this.groupsAll = groups;
    }

    /**
     * Add an item to the parent group
     * @param item item to be added
     * @param group group that item is added to
     */
    public void addItem(ExpandListChild item, ExpandListParent group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
        int index = groups.indexOf(group);
        ArrayList<ExpandListChild> ch = groups.get(index).getItems();
        ch.add(item);
        groups.get(index).setItems(ch);
    }

    /**
     * Gets the data associated with the given child within the given group.
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other children in the group
     * @return the data of the child
     */
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();
        return chList.get(childPosition);
    }

    /**
     * Gets the ID for the given child within the given group. This ID must be unique across all
     * children within the group. The combined ID (see getCombinedChildId(long, long)) must be
     * unique across ALL items (groups and all children).
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group for which the ID is wanted
     * @return the ID associated with the child
     */
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Gets a View that displays the data for the given child within the given group.
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child (for which the View is returned) within the group
     * @param isLastChild Whether the child is the last child within the group
     * @param view the old view to reuse, if possible. You should check that this view is non-null
     *                and of an appropriate type before using. If it is not possible to convert this
     *                view to display the correct data, this method can create a new view. It is not
     *                guaranteed that the convertView will have been previously created by
     *                getChildView(int, int, boolean, View, ViewGroup).
     * @param parent the parent that this view will eventually be attached to
     * @return the View corresponding to the child at the specified position
     */
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view,
                             ViewGroup parent) {
        ExpandListChild child = (ExpandListChild) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.faq_list_child, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.listItem_faq);
        tv.setText(child.getName());
        tv.setTag(child.getTag());
        return view;
    }

    /**
     * Gets the number of children in a specified group.
     * @param groupPosition the position of the group for which the children count should be returned
     * @return the children count in the specified group
     */
    public int getChildrenCount(int groupPosition) {
        ArrayList<ExpandListChild> chList = groups.get(groupPosition).getItems();

        return chList.size();

    }

    /**
     * Gets the data associated with the given group.
     * @param groupPosition the position of the group
     * @return the data child for the specified group
     */
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    /**
     * Gets the number of groups.
     * Default method in BaseExpandableListAdapter
     * @return the number of groups
     */
    public int getGroupCount() {
        return groups.size();
    }

    /**
     * Gets the ID for the group at the given position. This group ID must be unique across groups.
     * The combined ID (see getCombinedGroupId(long)) must be unique across ALL items (groups and all children).
     * Default method in BaseExpandableListAdapter
     * @param groupPosition the position of the group for which the ID is wanted
     * @return the ID associated with the group
     */
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * Gets a View that displays the given group. This View is only for the group--the Views for the
     * group's children will be fetched using getChildView(int, int, boolean, View, ViewGroup)
     * @param groupPosition the position of the group for which the View is returned
     * @param isLastChild is this the last child?
     * @param view the old view to reuse, if possible. You should check that this view is non-null
     *             and of an appropriate type before using. If it is not possible to convert this
     *             view to display the correct data, this method can create a new view. It is not
     *             guaranteed that the convertView will have been previously created by
     *             getGroupView(int, boolean, View, ViewGroup).
     * @param parent the parent that this view will eventually be attached to
     * @return the View corresponding to the group at the specified position
     */
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {
        ExpandListParent group = (ExpandListParent) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.faq_list_parent, null);
        }
        TextView tv = (TextView) view.findViewById(R.id.listHeader_faq);
        tv.setText(group.getName());
        return view;
    }

    /**
     * Indicates whether the child and group IDs are stable across changes to the underlying data.
     * Default method in BaseExpandableListAdapter
     * @return boolean whether or not the same ID always refers to the same object
     */
    public boolean hasStableIds() {
        return true;
    }

    /**
     * Whether the child at the specified position is selectable
     * Default method in BaseExpandableListAdapter
     * @param arg0 the position of the group that contains the child
     * @param arg1 the position of the child within the group
     * @return boolean whether the child is selectable.
     */
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        continentList.clear();

        if(query.isEmpty()){
            continentList.addAll(originalList);
        }
        else {

            for(Continent continent: originalList){

                ArrayList<Country> countryList = continent.getCountryList();
                ArrayList<Country> newList = new ArrayList<Country>();
                for(Country country: countryList){
                    if(country.getCode().toLowerCase().contains(query) ||
                            country.getName().toLowerCase().contains(query)){
                        newList.add(country);
                    }
                }
                if(newList.size() > 0){
                    Continent nContinent = new Continent(continent.getName(),newList);
                    continentList.add(nContinent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(continentList.size()));
        notifyDataSetChanged();

    }

}
