package com.chigov.cafe_order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {
    private TextView tvHello;
    private TextView tvAdditions;
    private CheckBox cbLemon;
    private CheckBox cbSugar;
    private CheckBox cbMilk;
    private Spinner spinner;
    private String drinkType;
    private String name;
    private String password;
    private StringBuilder builderAdditions;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }else{
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        drinkType = getString(R.string.tea);///////////////  by default !!!!!!!!!!!!
        tvHello = findViewById(R.id.tvHello);
        tvAdditions = findViewById(R.id.tvAdditions);
        cbLemon = findViewById(R.id.checkboxLemon);
        cbMilk = findViewById(R.id.checkboxMilk);
        cbSugar = findViewById(R.id.checkboxSugar);
        //spinnerCoffee = findViewById(R.id.spinnerCoffee);
        spinner = findViewById(R.id.spinnerTea);
        String additions = String.format(getString(R.string.what_add_to_your_drink),drinkType);
        tvAdditions.setText(additions);

        builderAdditions = new StringBuilder();

        String helloText = String.format(getString(R.string.hello_client_you_choose),name);
        tvHello.setText(helloText);

    }

    public void onClickChangeDrink(View view) {//в качестве параметра передается данная кнопка
        RadioButton button = (RadioButton) view;
        int id = button.getId();//какя кнопка нажата
        ArrayAdapter<?> adapter1 =ArrayAdapter.createFromResource(this,R.array.option_of_tea, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<?> adapter =ArrayAdapter.createFromResource(this,R.array.option_of_coffee, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (id == R.id.rbTea){
            drinkType = getString(R.string.tea);
            spinner.setAdapter(adapter1);
            cbLemon.setVisibility(View.VISIBLE);
        }else if (id == R.id.rb_Coffee){
            drinkType = getString(R.string.coffee);
            spinner.setAdapter(adapter);
            cbLemon.setVisibility(View.INVISIBLE);
        }
        String additions = String.format(getString(R.string.what_add_to_your_drink),drinkType);
        tvAdditions.setText(additions);
    }

    public void onClickSendOrder(View view) {
        //очистить builderAdditions
        builderAdditions.setLength(0);
        //пройтись по всем checkbox
        if (cbMilk.isChecked()){
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if (cbSugar.isChecked()){
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
        if (cbLemon.isChecked() && drinkType.equals(getString(R.string.tea))){
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
        String optionOfDrink = "";
        if (drinkType.equals(getString(R.string.tea))){
            optionOfDrink = spinner.getSelectedItem().toString();
        }else{
            optionOfDrink = spinner.getSelectedItem().toString();
        }

        String order = String.format(getString(R.string.order),name,password,drinkType,optionOfDrink);
        String additions;
        //добавляем строку с добавками
        if (builderAdditions.length() > 0){
            additions = "\n" + getString(R.string.necessary_adds) + builderAdditions.toString();
        }
        else{additions = "";
        }
        String fullOrder = order + additions;

        Intent intent = new Intent(this,OrderDetailActivity.class);
        intent.putExtra("order",fullOrder);
        startActivity(intent);


    }
}