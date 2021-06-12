package com.example.phonecallgeeks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewContact extends AppCompatActivity {


    private EditText nameEdt, nameEdtlast, phoneEdt;
    private Button addContactEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);

        androidx.appcompat.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
        nameEdt = findViewById(R.id.etName);

        nameEdtlast = findViewById(R.id.etlastName);
        phoneEdt = findViewById(R.id.etphone);
        addContactEdt = findViewById(R.id.idBtnAddContact);


        addContactEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nameEdt.getText().toString().isEmpty() && !nameEdtlast.getText().toString().isEmpty() &&
                        !phoneEdt.getText().toString().isEmpty()) {
Intent intent=new Intent(Intent.ACTION_INSERT);
intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
intent.putExtra(ContactsContract.Intents.Insert.NAME,nameEdt.getText().toString()+" "+ nameEdtlast.getText().toString());
intent.putExtra(ContactsContract.Intents.Insert.PHONE,phoneEdt.getText().toString());
if (intent.resolveActivity(getPackageManager()) !=null) {
    startActivity(intent);

                } else {
                    Toast.makeText(CreateNewContact.this, "There is no app that support this action",
                            Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(CreateNewContact.this, "Please fill all the fields",
                        Toast.LENGTH_SHORT).show();
            }

            }

        });
    }
}
