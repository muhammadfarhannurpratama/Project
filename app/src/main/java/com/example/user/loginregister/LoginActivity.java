package com.example.user.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

// kuranglebih seperti yang sebelumnya namun terdapat perbedaan dengan yang sebelumnya yaitu untuk mengisi data pada database, sekarang untuk mengambil data
    EditText edtUsername, edtPassword;
    Button buttonLogin,buttonRegister;
    LoginDataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editUsername);
        edtPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        dataHelper = new LoginDataHelper(this);



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();


                if(username.equals("") || username.trim().isEmpty() || password.equals("") || password.trim().isEmpty() ) {
                    Toast.makeText(LoginActivity.this,"Username Password harus diisi",Toast.LENGTH_LONG).show();
                }
                else
                {
// disini perbedaannya yaitu memanggil fungsi autentikasi yang mengembalikan return boolean
//                    jika username dan password yang di inputkan ada pada database maka return true, jika tidak return false;
                    if(dataHelper.autentikasi(username,password)){
// pindah activity mainactivity
                        Intent mIntent = new Intent(getApplicationContext(),
                                MainActivity.class);
//                        memberikan extra data username
                        mIntent.putExtra("username",username);
//                        berpindah activity
                        startActivity(mIntent);
                    }

                    else{
//                        jika tidak terdaftar menampilkan toast "Login Gagal, username belum terdaftar"
                        Toast.makeText(LoginActivity.this,"Login Gagal , username belum terdaftar",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
