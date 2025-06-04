package com.example.orderitem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    Button hotdishesbtn,colddishesbtn,soupbtn,westernfoodbtn,certainbtn,inspectbtn;
    private TextView newmenu;
    String selecteddishes;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //相关菜品按钮
        hotdishesbtn=findViewById(R.id.hotdishesbtn);
        colddishesbtn=findViewById(R.id.colddishesbtn);
        soupbtn=findViewById(R.id.soupbtn);
        westernfoodbtn=findViewById(R.id.westernfoodbtn);
        certainbtn=findViewById(R.id.certainbtn);
        inspectbtn=findViewById(R.id.inspectbtn);
        //按钮点击事件
        hotdishesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new BlankFragmenthotdish());
            }
        });
        colddishesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new BlankFragmentcolddish());
            }
        });
        soupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new BlankFragmentstaplefood());
            }
        });
        westernfoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new BlankFragmentwesternfood());
            }
        });
        newmenu=findViewById(R.id.addmenueshow);
        newmenu.setBackgroundResource(R.drawable.border);
        loadMenu();
        certainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMenu();
            }
        });
        inspectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SelectedDisplayActivity.class);
                intent.putExtra("selected_dishes",selecteddishes);
                startActivity(intent);
            }
        });
    }
    //帧页面打开方法
    private void ShowFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentshow,fragment).commit();
    }
    //更行添加的菜品方法
    private void loadMenu(){
        SharedPreferences sharedPreferences=getSharedPreferences("add_dish",MODE_PRIVATE);
        String menu=sharedPreferences.getString("menu","请先添加菜品");
        newmenu.setText(menu);
        newmenu.setMovementMethod(new ScrollingMovementMethod());
    }
    //清空菜品方法
    private void resetMenu(){
        new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("是否选定菜品")
                .setPositiveButton("确定选择",((dialog, which) -> clearMenu()))
                .setNegativeButton("思考一下",null).show();
    }
    private void clearMenu(){
        SharedPreferences sharedPreferences=getSharedPreferences("add_dish",MODE_PRIVATE);
        selecteddishes=sharedPreferences.getString("menu","");
        sharedPreferences.edit().remove("menu").apply();
        loadMenu();
        Toast.makeText(this,"这次菜品已选定",Toast.LENGTH_SHORT).show();
    }
}