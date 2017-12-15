package com.example.andy.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    public int id;
    public String firstName = null;
    public String lastName = null;
    public String phone = null;
    public String address = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            // Retrieve each variable from main activity
            id = extras.getInt("id");
            firstName = extras.getString("firstName");
            lastName = extras.getString("lastName");
            phone = extras.getString("phone");
            address = extras.getString("address");
        }

        TextView firstNameField = (TextView) findViewById(R.id.firstName);
        TextView lastNameField = (TextView) findViewById(R.id.lastName);
        TextView phoneField = (TextView) findViewById(R.id.phone);
        TextView addressField = (TextView) findViewById(R.id.address);

        firstNameField.setText(String.format(firstName));
        lastNameField.setText(String.format(lastName));
        phoneField.setText(String.format(phone));
        addressField.setText(String.format(address));
    }

    public void editContact (View view){

        Intent contactEdit = new Intent(this, ContactEdit.class);
        contactEdit.putExtra("id", id);
        contactEdit.putExtra("firstName", firstName);
        contactEdit.putExtra("lastName", lastName);
        contactEdit.putExtra("phone", phone);
        contactEdit.putExtra("address", address);
        startActivity(contactEdit);
    }


}
