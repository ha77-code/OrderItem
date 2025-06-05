package com.example.orderitem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class BlankFragmenthotdish extends Fragment{
    public BlankFragmenthotdish() {
    }
    public static BlankFragmenthotdish newInstance(String param1, String param2) {
        BlankFragmenthotdish fragment = new BlankFragmenthotdish();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    private Dishes hotdishes[]={
            new Dishes("肉沫皮蛋炒青椒",R.drawable.img1,"https://home.meishichina.com/recipe-663860.html"),
            new Dishes("麻椒炒猪肝", R.drawable.img1,"https://home.meishichina.com/recipe-664406.html"),
            new Dishes("红焖牛肉", R.drawable.img1,"https://home.meishichina.com/recipe-664244.html"),
            new Dishes("蜜汁烤排骨",R.drawable.img1,"https://home.meishichina.com/recipe-663691.html"),
            new Dishes("猪肚鸡",R.drawable.img1,"https://home.meishichina.com/recipe-663964.html"),
            new Dishes("西洋参炖鸽子",R.drawable.img1,"https://home.meishichina.com/recipe-663475.html"),
            new Dishes("咕噜虾仁",R.drawable.img1,"https://home.meishichina.com/recipe-662752.html"),
            new Dishes("减脂版菠萝咕咾肉（鸡胸肉不油炸）",R.drawable.img1,"https://home.meishichina.com/recipe-664633.html"),
            new Dishes("皮蛋猪肝菠菜汤",R.drawable.img1,"https://home.meishichina.com/recipe-664042.html"),
            new Dishes("菠萝牛肉粒",R.drawable.img1,"https://home.meishichina.com/recipe-664035.html")
    };
    private RecyclerView recyclerView;
    private MyRecycleViewAdapter myrecyckerViewAdapter;
    @SuppressLint("ResourceType")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragmenthotdish, container, false);
        recyclerView = view.findViewById(R.id.hotdishrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myrecyckerViewAdapter = new MyRecycleViewAdapter(hotdishes);
        myrecyckerViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Dishes selectedDish = hotdishes[position];
                Intent intent = new Intent(getActivity(), Detail_dish_show.class);
                intent.putExtra("dish_name", selectedDish.getName());
                intent.putExtra("dish_url", selectedDish.getDishurl());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myrecyckerViewAdapter);
        return view;
    }
    //创建数据类储存热菜的名字和图片
    static class Dishes{
        private String name;
        private int image;
        private String dishurl;
        public Dishes(String name,int image,String dishurl){
            this.name=name;
            this.image=image;
            this.dishurl=dishurl;
        }
        public String getName(){
            return name;
        }
        public int getImage(){
            return image;
        }
        public String getDishurl(){
            return dishurl;
        }
    }
}