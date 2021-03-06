package org.difly.contactapp2.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import org.difly.contactapp2.dao.ContactDao;
import org.difly.contactapp2.db.ContactRoomDatabase;
import org.difly.contactapp2.entity.Contact;

import java.util.List;

public class ContactRepository {
    private final ContactDao mContactDao;
    private final LiveData<List<Contact>> mAllContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mAllContacts = mContactDao.getAlphabetizedContacts();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    public void insert(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> mContactDao.insert(contact));
    }

    public void update(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> mContactDao.update(contact));
    }

    public void delete(Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(() -> mContactDao.delete(contact));
    }

    public LiveData<Contact> getById(long id){
        return mContactDao.getById(id);
    }
}
