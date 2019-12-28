package org.difly.contactapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.difly.contactapp2.entity.Contact;

public class ContactDetailsActivity extends AppCompatActivity {
    public static final String CONTACT_KEY = "contact";

    TextView textViewValueOfFirstName;
    TextView textViewValueOfLastName;
    TextView textViewValueOfPhoneNumber;
    TextView textViewValueOfPhoneType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        textViewValueOfFirstName = findViewById(R.id.text_firstname_value);
        textViewValueOfLastName = findViewById(R.id.text_lastname_value);
        textViewValueOfPhoneNumber = findViewById(R.id.text_phonenumber_value);
        textViewValueOfPhoneType = findViewById(R.id.text_phonetype_value);

        Contact contact = (Contact) getIntent().getSerializableExtra(CONTACT_KEY);

        textViewValueOfFirstName.setText(contact.getFirstname());
        textViewValueOfLastName.setText(contact.getLastname());
        textViewValueOfPhoneNumber.setText(contact.getPhonenumber());
        textViewValueOfPhoneType.setText(contact.getPhonetype());

        FloatingActionButton fab = findViewById(R.id.fabEdit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEditItemClick(contact);
            }
        });
    }

    private void handleEditItemClick(Contact contact) {
        Toast.makeText(this, R.string.click_edit + contact.getFirstname(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, NewContactActivity.class);
        intent.putExtra(CONTACT_KEY, contact);
        startActivity(intent);
    }
}
