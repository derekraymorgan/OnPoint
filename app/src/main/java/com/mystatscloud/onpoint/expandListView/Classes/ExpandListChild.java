package com.mystatscloud.onpoint.expandListView.Classes;

/**
 * Implementation of items in a expandable list
 */
public class ExpandListChild
{
    private String Name;
    private String Tag;


    /**
     * get method for name or value of the item
     * @return Name
     */
    public String getName()
    {
        return Name;
    }

    /**
     * set method for a name or value of the item
     * @param Name
     */
    public void setName(String Name) {
        this.Name = unescape(Name);
    }

    /**
     * get method for item tag
     * @return Tag
     */
    public String getTag()
    {
        return Tag;
    }

    /**
     * set method for item tag
     * @param Tag
     */
    public void setTag(String Tag)
    {
        this.Tag = Tag;
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

