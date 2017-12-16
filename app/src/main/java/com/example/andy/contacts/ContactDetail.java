package com.example.andy.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ContactDetail extends FragmentActivity implements OnMapReadyCallback {

    public int id;
    public String firstName = null;
    public String lastName = null;
    public String phone = null;
    public String address = null;
    public String email = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // Implement map fragment components for Google Maps
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Retrieve each variable from main activity
            id = extras.getInt("id");
            firstName = extras.getString("firstName");
            lastName = extras.getString("lastName");
            phone = extras.getString("phone");
            address = extras.getString("address");
            email = extras.getString("email");
        }

        TextView firstNameField = (TextView) findViewById(R.id.firstName);
        TextView lastNameField = (TextView) findViewById(R.id.lastName);
        TextView phoneField = (TextView) findViewById(R.id.phone);
        TextView addressField = (TextView) findViewById(R.id.address);
        TextView emailField = (TextView) findViewById(R.id.email);

        firstNameField.setText(String.format(firstName));
        lastNameField.setText(String.format(lastName));
        phoneField.setText(String.format(phone));
        addressField.setText(String.format(address));
        emailField.setText(String.format(email));
    }

    public void call(View view) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phone));
        startActivity(callIntent);
    }

    public void email(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(emailIntent);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }

    public void editContact(View view) {

        Intent contactEdit = new Intent(this, ContactEdit.class);
        contactEdit.putExtra("id", id);
        contactEdit.putExtra("firstName", firstName);
        contactEdit.putExtra("lastName", lastName);
        contactEdit.putExtra("phone", phone);
        contactEdit.putExtra("address", address);
        contactEdit.putExtra("email", email);
        startActivity(contactEdit);
    }
}


