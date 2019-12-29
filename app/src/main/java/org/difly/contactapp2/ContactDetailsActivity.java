package org.difly.contactapp2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.difly.contactapp2.entity.Contact;
import org.difly.contactapp2.viewmodel.ContactViewModel;

public class ContactDetailsActivity extends AppCompatActivity {
    public static final String CONTACT_KEY = "contact";
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        mContact = (Contact) getIntent().getSerializableExtra(CONTACT_KEY);
        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        TextView textViewValueOfFirstName = findViewById(R.id.text_firstname_value);
        TextView textViewValueOfLastName = findViewById(R.id.text_lastname_value);
        TextView textViewValueOfPhoneNumber = findViewById(R.id.text_phonenumber_value);
        TextView textViewValueOfPhoneType = findViewById(R.id.text_phonetype_value);

        contactViewModel.getContactById(mContact.getId()).observe(this, contact -> {
            textViewValueOfFirstName.setText(contact.getFirstname());
            textViewValueOfLastName.setText(contact.getLastname());
            textViewValueOfPhoneNumber.setText(contact.getPhonenumber());
            textViewValueOfPhoneType.setText(contact.getPhonetype());
        });

        FloatingActionButton fab = findViewById(R.id.fabEdit);
        fab.setOnClickListener(view -> handleEditItemClick());
    }

    private void handleEditItemClick() {
        Toast.makeText(getApplicationContext(), R.string.click_edit, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, NewContactActivity.class);
        intent.putExtra(CONTACT_KEY, mContact);
        startActivity(intent);
    }
}
