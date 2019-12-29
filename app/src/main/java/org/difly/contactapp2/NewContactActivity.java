package org.difly.contactapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.difly.contactapp2.R;
import org.difly.contactapp2.entity.Contact;
import org.difly.contactapp2.viewmodel.ContactViewModel;

import static org.difly.contactapp2.ContactDetailsActivity.CONTACT_KEY;

public class NewContactActivity extends AppCompatActivity {
    private EditText mEditContactFirstNameView;
    private EditText mEditContactLastNameView;
    private EditText mEditContactPhoneNumberView;
    private RadioGroup mRadioGroupSelectTypeView;

    private ContactViewModel mContactViewModel;
    private boolean editMode= false;

    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        mEditContactFirstNameView = findViewById(R.id.edit_firstname);
        mEditContactLastNameView = findViewById(R.id.edit_lastname);
        mEditContactPhoneNumberView = findViewById(R.id.edit_phonenumber);
        mRadioGroupSelectTypeView = findViewById(R.id.radioGroup_type);
        mRadioGroupSelectTypeView.check(R.id.radioButton_home);

        mContactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contact = (Contact) getIntent().getSerializableExtra(CONTACT_KEY);

        if (contact != null) {
            editMode = true;
            mEditContactFirstNameView.setText(contact.getFirstname());
            mEditContactLastNameView.setText(contact.getLastname());
            mEditContactPhoneNumberView.setText(contact.getPhonenumber());

            switch (contact.getPhonetype()) {
                case "Home":
                    mRadioGroupSelectTypeView.check(R.id.radioButton_home);
                    break;
                case "Work":
                    mRadioGroupSelectTypeView.check(R.id.radioButton_work);
                    break;
            }
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                handleItemClick();
            }
        });
    }

    private void handleItemClick() {
        Intent replyIntent = new Intent();
        if (
                TextUtils.isEmpty(mEditContactFirstNameView.getText()) ||
                TextUtils.isEmpty(mEditContactPhoneNumberView.getText())
        ) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String firstname = mEditContactFirstNameView.getText().toString();
            String lastname = mEditContactLastNameView.getText().toString();
            String phonenumber = mEditContactPhoneNumberView.getText().toString();
            String type = "Home";

            switch (mRadioGroupSelectTypeView.getCheckedRadioButtonId()) {
                case R.id.radioButton_home:
                    type = "Home";
                    break;
                case R.id.radioButton_work:
                    type = "Work";
                    break;
            }

            if (contact == null ) {
                contact = new Contact();
            }

            contact.setFirstname(firstname);
            contact.setLastname(lastname);
            contact.setPhonenumber(phonenumber);
            contact.setPhonetype(type);

            if (editMode) {
                mContactViewModel.update(contact);
            } else {
                mContactViewModel.insert(contact);
            }
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }
}
