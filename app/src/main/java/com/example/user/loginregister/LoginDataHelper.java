package com.example.user.loginregister;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//loginDataHelper ini untuk menyambungkan/proses pembuatan database
public class LoginDataHelper extends SQLiteOpenHelper {

//    variable ini digunakan untuk menyimpan database name
    private static final String DATABASE_NAME = "login.db";
//    variable ini digunakan untuk menyimpan database version
    private static final int DATABASE_VERSION = 1;
//    variable ini digunakan untuk menyimpan data data yang terdapat pada database
    private static final String TABLE_USER = "user";
    private static final String KEY_NO = "no";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

//    perintah sql yang digunakan membuat table user
    private static final String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_USER+"("
            +KEY_NO+" INTEGER AUTO_INCREMENT,"
            +KEY_USERNAME+" TEXT NULL,"
            +KEY_EMAIL+" TEXT NULL,"
            +KEY_PASSWORD+" TEXT NULL)";

//    fungsi pertama yang dipanggil pada saat membuat object LoginDataHelper
    public LoginDataHelper(Context context)
    {
//        memanggil constructor super class yaitu class SQLiteOpenHelper
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //TODO Auto-generated constructor stub
    }

//    fungsi yang dipanggil pada saat pertama kali memanggil object LoginDataHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
//        mengeksekusi query create table user
        db.execSQL(CREATE_TABLE_USER);
    }

    //
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
// fungsi untuk menambahkan user, yang akan dipanggil saat melakukan registrasi
    public void addUser(String username, String email, String password)
    {
//        memanggil database dengan permision write(menulis)
        SQLiteDatabase db = getWritableDatabase();
//        perintah sql yang digunakan untuk tambah user
        String sql = "INSERT INTO "+TABLE_USER+" ("+KEY_USERNAME+","+KEY_EMAIL+","+KEY_PASSWORD+") values ('"+username+"','"+email+"','"+password+"')";
        //  mengeksekusi sql diatas
        db.execSQL(sql);
    }

//    fungsi untuk melakukan autentikasi yang akan di panggil saat melakukan login
    public boolean autentikasi(String username, String password){
//        kolom yang akan di panggil
        String[] columns = {
                KEY_USERNAME
        };

// memanggil database dengan permition read(membaca)
        SQLiteDatabase db = this.getReadableDatabase();
//        menambahkan filter menurut username dan password
        String selection = KEY_USERNAME + " = ?" + " AND " + KEY_PASSWORD + " = ?";
// menyimpan variable yang telah diinputkan
        String[] selectionArgs = {username, password};
//eksekusi perintah select
//        perintah dibawah ini sama seperti sql = "select username from user where username="usernameyangdiinputkan" AND password="passwordyangdiinputkan"
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

//        membuat variable jumlahrecord dengan cursor.getcount();
        int cursorCount = cursor.getCount();

//        menutup cursor dan db
        cursor.close();
        db.close();

//        jika jumlahrecord > 0 maka login berhasil
        if (cursorCount > 0) {
            return true;
        }

//        jika tidak maka akan gagal login
        return false;
    }
}
