package org.difly.contactapp2.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.difly.contactapp2.dao.ContactDao;
import org.difly.contactapp2.db.ContactRoomDatabase;
import org.difly.contactapp2.model.Contact;

import java.util.List;

public class ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mAllContacts = mContactDao.getAlphabetizedContacts();
    }

    LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            mContactDao.insert(contact);
        });
    }
}
