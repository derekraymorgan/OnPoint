package com.mystatscloud.onpoint.expandListView.Classes;

public class ExpandListChild
{
    private String Name;
    private String Tag;

    public String getName()
    {
        return Name;
    }

    public void setName(String Name) {
        this.Name = unescape(Name);
    }

    public String getTag()
    {
        return Tag;
    }

    public void setTag(String Tag)
    {
        this.Tag = Tag;
    }

    private String unescape(String s)
    {
        return s.replaceAll("\\\\n", "\\\n");
    }
}

