package org.difly.contactapp2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.difly.contactapp2.entity.Contact;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact_table")
    List<Contact> getAll();

    @Query("SELECT * FROM contact_table ORDER BY firstname ASC")
    LiveData<List<Contact>> getAlphabetizedContacts();

    @Query("SELECT * FROM contact_table WHERE id = :id")
    Contact getById(long id);

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

}
