package com.example.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.content.ContentUris;
import android.database.SQLException;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class ContactProvider extends ContentProvider {
    private final static  String DATABASE_TABLE="contacts";
    static final String PROVIDER_NAME = "com.example.mycontentprovider.ContactProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/*";
    static final Uri CONTENT_URI = Uri.parse(URL);
    public static final   String row_id= "_id";
    public static  final  String key_name="person_name";
    public static final  String key_cell="_cell";
    private final String DATABASE_NAME="ContactsDB";

    private final int DATABASE_VERSION=1;


    static final int PERSON = 1;
    static final int PERSON_ID = 2;

    private SQLiteDatabase ourDatabase;
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "contacts", PERSON);
        uriMatcher.addURI(PROVIDER_NAME, "contacts/*", PERSON_ID);
    }
    private static HashMap<String, String> PERSON_PROJECTION_MAP;



    private class DBhelper extends SQLiteOpenHelper {
        public DBhelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            System.out.println("DATABASE NAME:   " + DATABASE_TABLE);
            String sqlcode = "CREATE TABLE " + DATABASE_TABLE + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + key_name + " TEXT NOT NULL," +
                    key_cell + " TEXT NOT NULL);";


            db.execSQL(sqlcode);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);

        }
    }


    @Override
    public boolean onCreate()
    {
        Context context = getContext();
        DBhelper ourhelpler = new DBhelper(context);




        ourDatabase=ourhelpler.getWritableDatabase();
        //ourhelpler.onCreate(ourDatabase);
        return (ourDatabase == null)? false:true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DATABASE_TABLE);

        switch (uriMatcher.match(uri)) {
            case PERSON:
                qb.setProjectionMap(PERSON_PROJECTION_MAP);
                break;

            case PERSON_ID:
                qb.appendWhere( row_id + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = key_name;
        }

        Cursor c = qb.query(ourDatabase,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case PERSON:
                return "vnd.android.cursor.dir/vnd.example.contacts";
            /**
             * Get a particular student
             */
            case PERSON_ID:
                return "vnd.android.cursor.item/vnd.example.contacts";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }


    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long rowID = ourDatabase.insert(DATABASE_TABLE, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);


    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSON:
                count = ourDatabase.delete(DATABASE_TABLE, selection, selectionArgs);
                break;

            case PERSON_ID:
                String id = uri.getPathSegments().get(1);
                count = ourDatabase.delete( DATABASE_TABLE, row_id +  " = " + id +
                                (!TextUtils.isEmpty(selection) ?
                       " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

            int count = 0;
            switch (uriMatcher.match(uri)) {
                case PERSON:
                    count = ourDatabase.update(DATABASE_TABLE, values, selection, selectionArgs);
                    break;

                case PERSON_ID:
                    count = ourDatabase.update(DATABASE_TABLE, values,
                            row_id + " = " + uri.getPathSegments().get(1) +
                                    (!TextUtils.isEmpty(selection) ?
                            "AND (" +selection + ')' : ""), selectionArgs);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI " + uri );
            }

            getContext().getContentResolver().notifyChange(uri, null);
            return count;
    }
}
