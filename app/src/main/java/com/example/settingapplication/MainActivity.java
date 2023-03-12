package com.example.settingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button black, red, blue, green, count;
    TextView number;
    LinearLayout layout;
    int save_color;
    String save_count;
    int total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout1);
        number = findViewById(R.id.tv_number);
        assignId(black, R.id.bt_black);
        assignId(red, R.id.bt_red);
        assignId(blue, R.id.bt_blue);
        assignId(green, R.id.bt_green);
        assignId(count, R.id.bt_count);

        String str = Integer.toString(total);
        number.setText(str);
    }

    public void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_black: {
                layout.setBackgroundColor(Color.BLACK);
                number.setTextColor(Color.WHITE);
                save_color = Color.BLACK;
                break;
            }
            case R.id.bt_red: {
                layout.setBackgroundColor(Color.RED);
                number.setTextColor(Color.WHITE);
                save_color = Color.RED;
                break;
            }
            case R.id.bt_blue: {
                layout.setBackgroundColor(Color.BLUE);
                number.setTextColor(Color.WHITE);
                save_color = Color.BLUE;
                break;
            }
            case R.id.bt_green: {
                layout.setBackgroundColor(Color.GREEN);
                number.setTextColor(Color.WHITE);
                save_color = Color.GREEN;
                break;
            }
            case R.id.bt_count: {
                total++;
                String result = Integer.toString(total);
                number.setText(result);
                save_count = result;
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        int col = sh.getInt("color",save_color);
        String cou = sh.getString("count",save_count);

        number.setText(cou);
        layout.setBackgroundColor(col);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putInt("color", save_color);
        myEdit.putString("count", save_count);
        myEdit.apply();
    }
}