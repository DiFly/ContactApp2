package org.difly.contactapp2.entity;

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

    public Contact() {
    }

    public Contact(@NonNull String firstname, String lastname, @NonNull String phonenumber, String phonetype) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.phonetype = phonetype;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NonNull String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @NonNull
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(@NonNull String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonetype() {
        return phonetype;
    }

    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }
}
