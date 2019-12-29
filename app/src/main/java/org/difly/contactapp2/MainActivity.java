package org.difly.contactapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.difly.contactapp2.entity.Contact;
import org.difly.contactapp2.recycler.ContactListAdapter;
import org.difly.contactapp2.recycler.OnContactClickListener;
import org.difly.contactapp2.viewmodel.ContactViewModel;

import java.util.List;

import static org.difly.contactapp2.ContactDetailsActivity.CONTACT_KEY;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    private ContactViewModel mContactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactListAdapter adapter = new ContactListAdapter(this, new OnContactClickListener() {
            @Override
            public void onContactClick(Contact contact) {
                handleRecyclerItemClick(contact);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        mContactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                adapter.setContacts(contacts);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    private void handleRecyclerItemClick(Contact contact) {
        Toast.makeText(getApplicationContext(), "Clicked on " + contact.getFirstname(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ContactDetailsActivity.class);
        intent.putExtra(CONTACT_KEY, contact);
        startActivity(intent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_saved,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
