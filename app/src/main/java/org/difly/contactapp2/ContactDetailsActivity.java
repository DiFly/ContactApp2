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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        Contact contact = (Contact) getIntent().getSerializableExtra(CONTACT_KEY);

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
