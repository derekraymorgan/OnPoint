package com.mystatscloud.onpoint.expandListView.Classes;

import java.util.ArrayList;

/**
 * Implementation of header/title/parent in a expandable list
 */
public class ExpandListParent
{
    private String Name;
    private ArrayList<ExpandListChild> Items;


    /**
     * get method for name or value of the parent
     * @return Name
     */
    public String getName()
    {
        return Name;
    }

    /**
     * set method for a name or value of the parent
     * @param name
     */
    public void setName(String name)
    {
        this.Name = unescape(name);
    }

    /**
     * get items that are in the array list of the parent
     * @return Items
     */
    public ArrayList<ExpandListChild> getItems()
    {
        return Items;
    }

    /**
     * set items that are in the array list of the parent
     * @param Items
     */
    public void setItems(ArrayList<ExpandListChild> Items)
    {
        this.Items = Items;
    }

    /**
     * Unescape a string that escaped in database
     * @param s input string
     * @return string as unescape, string escapes when inserted in a database
     */
    private String unescape(String s)
    {
        return s.replaceAll("\\\\n", "\\\n");
    }

}
