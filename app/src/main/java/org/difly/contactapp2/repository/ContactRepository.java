package org.difly.contactapp2.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.difly.contactapp2.dao.ContactDao;
import org.difly.contactapp2.db.ContactRoomDatabase;
import org.difly.contactapp2.entity.Contact;

import java.util.List;

public class ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mAllContacts = mContactDao.getAlphabetizedContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    public void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> {
            mContactDao.insert(contact);
        });
    }
}
