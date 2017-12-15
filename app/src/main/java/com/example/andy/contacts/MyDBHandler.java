package com.example.andy.contacts;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by Andy on 12/3/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsApp.db";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FIRSTNAME = "firstName";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "create table " + TABLE_CONTACTS +
                "(" + COLUMN_ID + " integer primary key autoincrement,"
                    + COLUMN_FIRSTNAME + " text,"
                    + COLUMN_LASTNAME + " text,"
                    + COLUMN_PHONE + " integer,"
                    + COLUMN_ADDRESS + " text"
                    + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, contact.getFirstName());
        values.put(COLUMN_LASTNAME, contact.getLastName());
        values.put(COLUMN_PHONE, contact.getPhone());
        values.put(COLUMN_ADDRESS, contact.getAddress());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void removeContact(Contact contact) {
        // Create String[] containing only the contact ID
        String[] whereArgs = new String[] {
                String.valueOf(contact.getID())
        };

        // Where clause for SQL string
        String whereClause = COLUMN_ID + "= ?";

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, whereClause, whereArgs);
        db.close();
    }

    public ArrayList<Contact> contactList() {
        ArrayList<Contact> contactList;

        String query = "select * from " + TABLE_CONTACTS + " order by " + COLUMN_LASTNAME + " asc, " + COLUMN_FIRSTNAME + " asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        contactList = new ArrayList<Contact>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME));
                String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME));
                String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                contactList.add(new Contact(id, firstName, lastName, phone, address));
            } while (cursor.moveToNext());
        }

        db.close();
        return contactList;
    }

    public ArrayList<Integer> selectId() {
        ArrayList<Integer> idList;
        String query = "select " + COLUMN_ID + " from " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        idList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                idList.add(id);
            } while (cursor.moveToNext());
        }
        db.close();
        return idList;
    }
}
