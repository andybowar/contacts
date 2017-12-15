package com.example.andy.contacts;

/**
 * Created by Andy on 12/6/2017.
 */

public class Contact {
    private int _id;
    private String _firstName;
    private String _lastName;
    private String _phone;
    private String _address;

    public Contact (int id, String firstName, String lastName, String phone, String address) {
        this._id = id;
        this._firstName = firstName;
        this._lastName = lastName;
        this._phone = phone;
        this._address = address;
    }

    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + " " + this.getPhone();
    }

    public void setID (int ID) {
        this._id = ID;
    }
    public void setFirstName (String firstName) {
        this._firstName = firstName;
    }
    public void setLastName (String lastName) {
        this._lastName = lastName;
    }
    public void setPhone (String phone) {
        this._phone = phone;
    }
    public void setAddress (String address) {
        this._address = address;
    }

    public int getID() {
        return this._id;
    }
    public String getFirstName() {
        return this._firstName;
    }
    public String getLastName() {
        return this._lastName;
    }
    public String getPhone() {
        return this._phone;
    }
    public String getAddress() {
        return this._address;
    }

}
