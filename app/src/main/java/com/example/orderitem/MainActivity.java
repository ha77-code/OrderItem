package com.example.orderitem;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button hotdishesbtn,colddishesbtn,soupbtn,westernfoodbtn,certainbtn,inspectbtn;
    private TextView newmenu,textbeginadd;
    private ImageView imageshow;
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
        imageshow=findViewById(R.id.imageshow);
        imageshow.setImageResource(R.drawable.ic_launcher_background);
        textbeginadd=findViewById(R.id.textbeginadd);
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
                textbeginadd.setText("");
                ShowFragment(new BlankFragmenthotdish());
            }
        });
        colddishesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new BlankFragmentcolddish());
                textbeginadd.setText("");
            }
        });
        soupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new BlankFragmentstaplefood());
                textbeginadd.setText("");
            }
        });
        westernfoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowFragment(new BlankFragmentwesternfood());
                textbeginadd.setText("");
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
        String menu=sharedPreferences.getString("menu","请先选择菜品");
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
        if(sharedPreferences.getAll().isEmpty()){
            Toast.makeText(this,"未选择菜品，请先选择菜菜品",Toast.LENGTH_SHORT).show();
        }
        else{
            sharedPreferences.edit().remove("menu").apply();
            loadMenu();
            Toast.makeText(this,"这次菜品已选定,点击下方按钮可查看详情",Toast.LENGTH_SHORT).show();
        }
    }
}