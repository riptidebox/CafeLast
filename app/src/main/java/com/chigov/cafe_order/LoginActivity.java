package com.chigov.cafe_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etName = findViewById(R.id.etPersonName);
        etPassword = findViewById(R.id.etPassword);
    }

    public void onClickCreateOrder(View view) {
        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (!name.isEmpty() && !password.isEmpty()){
        Intent intent = new Intent(this,CreateOrderActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("password",password);
        startActivity(intent);
        }else if(!name.isEmpty() && password.isEmpty()){
            Toast toast = Toast.makeText(this, R.string.toast_messege_warning1, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        else if(name.isEmpty() && !password.isEmpty()){
            Toast toast = Toast.makeText(this, R.string.toast_messege_warning2, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(this, R.string.toast_messege_warning3, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }
}