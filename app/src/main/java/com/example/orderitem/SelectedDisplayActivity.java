package com.example.orderitem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectedDisplayActivity extends AppCompatActivity {

    private TextView selecteddishesshow;
    private Button selected_backbtn;
    String selecteddishes;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selected_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        selecteddishesshow=findViewById(R.id.seleceddishesshow);
        selected_backbtn=findViewById(R.id.selected_backbtn);
        selecteddishes=getIntent().getStringExtra("selected_dishes");
        selecteddishesshow.setBackgroundResource(R.drawable.border);
        selecteddishesshow.setText(selecteddishes);
        selected_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}