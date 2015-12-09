package com.mystatscloud.onpoint.expandListView.Classes;

import java.util.ArrayList;

public class ExpandListParent
{
    private String Name;
    private ArrayList<ExpandListChild> Items;

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        this.Name = unescape(name);
    }

    public ArrayList<ExpandListChild> getItems()
    {
        return Items;
    }

    public void setItems(ArrayList<ExpandListChild> Items)
    {
        this.Items = Items;
    }

    private String unescape(String s)
    {
        return s.replaceAll("\\\\n", "\\\n");
    }

}
