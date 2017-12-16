package com.example.andy.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contactList;
    private ArrayAdapter<Contact> adapter;
    private ListView listView;
    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create database object
        dbHandler = new MyDBHandler(this, "contacts", null, 1);

        // Initialize contact list from database handler
        ArrayList<Contact> contactList = dbHandler.contactList();

        //Initialize adapter and connect contactList to it
        adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contactList);

        // Find list view, attach array to it, set adapter
        listView = (ListView) findViewById(R.id.contactList);
        listView.setAdapter(adapter);

        // Use ListViewOnClickListener class to make each item in list call ContactDetail activity
        listView.setOnItemClickListener(new ListViewOnClickListener());

        // Add new contact button
        FloatingActionButton addContact = (FloatingActionButton) findViewById(R.id.addContact);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ContactEdit.class));
            }
        });
    }

    public class ListViewOnClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // Grab index of chosen contact
            Contact contact = adapter.getItem(i);

            // Break object into variables
            int id = contact.getID();
            String firstName = contact.getFirstName();
            String lastName = contact.getLastName();
            String phone = contact.getPhone();
            String address = contact.getAddress();
            String email = contact.getEmail();

            Intent contactItem = new Intent(MainActivity.this, ContactDetail.class);
            // Send each variable to activity
            contactItem.putExtra("id", id);
            contactItem.putExtra("firstName", firstName);
            contactItem.putExtra("lastName", lastName);
            contactItem.putExtra("phone", phone);
            contactItem.putExtra("address", address);
            contactItem.putExtra("email", email);
            startActivity(contactItem);
        }
    }

    public void addContact(View view) {
        Intent i = new Intent(this, ContactEdit.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
