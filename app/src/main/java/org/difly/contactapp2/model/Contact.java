package org.difly.contactapp2.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "firstname")
    public String firstname;
    public String lastname;

    @NonNull
    public String phonenumber;
    public String phonetype;
}
