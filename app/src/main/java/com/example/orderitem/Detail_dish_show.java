package com.example.orderitem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Detail_dish_show extends AppCompatActivity {

    private TextView methodshow,mainshow,secondshow,thirdshow,one,two,three,foru;
    private ImageView dishimage;
    private String TAG="Detail_dish_show";
    private Button backbtn,addbtn;

    @SuppressLint({"MissingInflatedId", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_dish_show);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        one=findViewById(R.id.one);
        one.setTextColor(Color.parseColor("#FF000000"));
        two=findViewById(R.id.two);
        two.setTextColor(Color.parseColor("#FF000000"));
        three=findViewById(R.id.three);
        three.setTextColor(Color.parseColor("#FF000000"));
        foru=findViewById(R.id.frou);
        foru.setTextColor(Color.parseColor("#FF000000"));
        methodshow=findViewById(R.id.methodshow);
        methodshow.setBackgroundResource(R.drawable.border);
        mainshow=findViewById(R.id.mainshow);
        mainshow.setBackgroundResource(R.drawable.border);
        secondshow=findViewById(R.id.secondshow);
        secondshow.setBackgroundResource(R.drawable.border);
        thirdshow=findViewById(R.id.thirdshow);
        thirdshow.setBackgroundResource(R.drawable.border);
        methodshow.setMovementMethod(ScrollingMovementMethod.getInstance());
        dishimage=findViewById(R.id.dishimage);
        backbtn=findViewById(R.id.backbtn);
        Log.i(TAG,"0");
        Thread t=new Thread(()->{
            String getUrl=getIntent().getStringExtra("dish_url");
            Log.i(TAG,"1");
            try {
                Document doc= Jsoup.connect(getUrl).get();
                Log.i(TAG,doc.text());
                Element mainmateril = doc.getElementsByTag("fieldset").get(0);
                Log.i(TAG,"2");
                Elements mainmaterils = mainmateril.getElementsByTag("b");
                Log.i(TAG,"3");
                StringBuilder first=new StringBuilder();
                for(Element main:mainmaterils){
                    first.append(main.text()).append("\n");
                    Log.i(TAG,main.text());
                }
                //获取主料
                runOnUiThread(()->mainshow.append(first));
                mainshow.setTextColor(Color.parseColor("#FF000000"));
                Element secondmateril=doc.getElementsByTag("fieldset").get(1);
                Elements secondmaterils=secondmateril.getElementsByTag("b");
                StringBuilder second=new StringBuilder();
                for(Element main:secondmaterils){
                    second.append(main.text()).append("\n");
                    Log.i(TAG,main.text());
                }
                //获取辅料
                runOnUiThread(()->secondshow.append(second));
                secondshow.setTextColor(Color.parseColor("#FF000000"));
                Element thrdmateril=doc.getElementsByTag("fieldset").get(2);
                Elements thrdmaterils=thrdmateril.getElementsByTag("b");
                StringBuilder third=new StringBuilder();
                for(Element main:thrdmaterils){
                    third.append(main.text()).append("\n");
                    Log.i(TAG,main.text());
                }
                //获取调料
                runOnUiThread(()->thirdshow.append(third));
                thirdshow.setTextColor(Color.parseColor("#FF000000"));
                Elements mainmethods=doc.select("div.recipeStep ul li");
                if(mainmethods.isEmpty()){
                    runOnUiThread(()->methodshow.append("没有找到步骤"));
                }
                StringBuilder method=new StringBuilder();
                for(Element step:mainmethods){
                    String steptext=step.select("div.recipeStep_word").text();
                    if(!steptext.contains("成品")){
                        method.append(steptext).append("\n");
                        Log.i(TAG,steptext);
                    }
                }
                runOnUiThread(()->methodshow.append(method));
                methodshow.setTextColor(Color.parseColor("#FF000000"));
            } catch (IOException e) {
                runOnUiThread(()->methodshow.setText("网络请求失败："+e.getMessage()));
            }catch (Exception e){
                runOnUiThread(()->methodshow.setText("解析错误："+e.getMessage()));
            }
        });
        t.start();
        backbtn.setOnClickListener(v -> {
            finish();
        });
        addbtn=findViewById(R.id.addbtn);
        //点击按钮将相关菜品传递给主界面的菜单
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName=getIntent().getStringExtra("dish_name");
                SharedPreferences sharedPreferences=getSharedPreferences("add_dish",MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                String addeddish=sharedPreferences.getString("menu"," ");
                String newdish=addeddish.isEmpty()?getName:addeddish+"\n"+getName;
                editor.putString("menu",newdish);
                editor.apply();
                Intent intent=new Intent(Detail_dish_show.this, MainMenuActivity.class);
                intent.putExtra("add_dish_name",getName);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"这次菜品已选定",Toast.LENGTH_SHORT).show();
            }
        });
    }
}