package org.difly.contactapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.difly.contactapp2.entity.Contact;

public class ContactDetailsActivity extends AppCompatActivity {
    public static final String CONTACT_KEY = "contact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Contact contact = (Contact) getIntent().getSerializableExtra(CONTACT_KEY);

        Intent intent = new Intent(this, NewContactActivity.class);
        intent.putExtra(CONTACT_KEY, contact);
        startActivity(intent);
    }
}
