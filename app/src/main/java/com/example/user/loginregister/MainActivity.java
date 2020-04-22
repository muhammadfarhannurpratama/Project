package com.example.user.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//membuat variable untuk menyimpan komponen TextView
    TextView txtHello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mengambil komponen dari layout dengan id txtHello
        txtHello = findViewById(R.id.txtHello);

//        mendapatkan intent
        Intent i = getIntent();

//        mengambil extra string dengan nama "username"
        String username = i.getStringExtra("username");

//        merubah tulisan txtHello menjadli "Hello ,<username yang diinputkan>"
        txtHello.setText("Hello ,"+username);
    }
}
