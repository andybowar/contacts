package com.example.andy.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;

public class ContactEdit extends AppCompatActivity {

    EditText editFirst;
    EditText editLast;
    EditText editPhone;
    EditText editAddress;

    public int id;
    public String firstName = null;
    public String lastName = null;
    public String phone = null;
    public String address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        editFirst = (EditText) findViewById(R.id.editFirst);
        editLast = (EditText) findViewById(R.id.editLast);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editAddress = (EditText) findViewById(R.id.editAddress);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Retrieve each variable from main activity
            id = extras.getInt("id");
            firstName = extras.getString("firstName");
            lastName = extras.getString("lastName");
            phone = extras.getString("phone");
            address = extras.getString("address");
        }

        editFirst.setText(firstName);
        editLast.setText(lastName);
        editPhone.setText(phone);
        editAddress.setText(address);
    }

    public void saveContact (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

        // Cast content of fields to string, put in Contact constructor
        Contact contact = new Contact(id,
                                      editFirst.getText().toString(),
                                      editLast.getText().toString(),
                                      editPhone.getText().toString(),
                                      editAddress.getText().toString());

        ArrayList<Integer> idList = dbHandler.selectId();

        // If the selected contact ID exists in the database, remove it
        if (idList.contains(this.id)) {
            dbHandler.removeContact(contact);
        }

        // Add contact to database
        dbHandler.addContact(contact);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void clearForm (View view) {
        editFirst.setText("");
        editLast.setText("");
        editPhone.setText("");
        editAddress.setText("");
    }

    public void cancelContact(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void deleteContact(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        Contact contact = new Contact(id,
                editFirst.getText().toString(),
                editLast.getText().toString(),
                editPhone.getText().toString(),
                editAddress.getText().toString());
        dbHandler.removeContact(contact);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
