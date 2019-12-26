package org.difly.contactapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.difly.contactapp2.R;

public class NewContactActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditContactFirstNameView;
    private EditText mEditContactLastNameView;
    private EditText mEditContactPhoneNumberView;
    private RadioGroup mRadioGroupSelectTypeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        mEditContactFirstNameView = findViewById(R.id.edit_firstname);
        mEditContactLastNameView = findViewById(R.id.edit_lastname);
        mEditContactPhoneNumberView = findViewById(R.id.edit_phonenumber);
        mRadioGroupSelectTypeView = findViewById(R.id.radioGroup_type);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (
                        TextUtils.isEmpty(mEditContactFirstNameView.getText()) ||
                        TextUtils.isEmpty(mEditContactPhoneNumberView.getText())
                ) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //ToDo
                    String firstname = mEditContactFirstNameView.getText().toString();
                    String lastname = mEditContactFirstNameView.getText().toString();
                    String phonenumber = mEditContactFirstNameView.getText().toString();
                    String type = "TEST TYPE";
                    replyIntent.putExtra(EXTRA_REPLY, firstname);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
