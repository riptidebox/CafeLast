package com.chigov.cafe_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView tvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        tvOrder = findViewById(R.id.tvOrder);
        Intent intent  = getIntent();
        if(intent.hasExtra("order")){
            String order = intent.getStringExtra("order");
            tvOrder.setText(order);
        }else{
            Intent backToLogin = new Intent(this,LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}