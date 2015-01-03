package net.erickson.yzucss_app.DataObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erickson on 2015/1/4.
 */
public class UserTableListItem {
    private long id;
    private CharSequence name;
    private CharSequence comment;
    private CharSequence year;

    public UserTableListItem()
    {

    }

    public UserTableListItem(UserTableListItem userTableListItem)
    {

        setId(userTableListItem.getId());
        setName(userTableListItem.getName());
        setComment(userTableListItem.getComment());
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }


    public void setComment(CharSequence comment) {
        this.comment = comment;
    }

    public void setYear(CharSequence year)
    {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public CharSequence getName() {
        return name;
    }


    public CharSequence getComment() {
        return comment;
    }

    public CharSequence getYear()
    {
        return year;
    }

}
