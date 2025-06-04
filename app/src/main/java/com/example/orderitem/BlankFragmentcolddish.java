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
public class BlankFragmentcolddish extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public static BlankFragmentcolddish newInstance(String param1, String param2) {
        BlankFragmentcolddish fragment = new BlankFragmentcolddish();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BlankFragmentcolddish() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private MyRecycleViewAdapter myrecyckerViewAdapter;
    private RecyclerView recyclerView;
    private BlankFragmenthotdish.Dishes colddishes[]={
            new BlankFragmenthotdish.Dishes("捞汁鱿鱼虾",R.drawable.colddish1,"https://home.meishichina.com/recipe-663910.html"),
            new BlankFragmenthotdish.Dishes("柠檬生腌基围虾", R.drawable.colddish2,"https://home.meishichina.com/recipe-662339.html"),
            new BlankFragmenthotdish.Dishes("捞汁小海鲜",R.drawable.colddish3,"https://home.meishichina.com/recipe-661446.html"),
            new BlankFragmenthotdish.Dishes("凉拌苦瓜",R.drawable.colddish4,"https://home.meishichina.com/recipe-660500.html"),
            new BlankFragmenthotdish.Dishes("酸辣鸡丝", R.drawable.img,"https://home.meishichina.com/recipe-659632.html"),
            new BlankFragmenthotdish.Dishes("青瓜拌虾滑",R.drawable.img,"https://home.meishichina.com/recipe-658853.html"),
            new BlankFragmenthotdish.Dishes("蓝莓山药泥",R.drawable.img,"https://home.meishichina.com/recipe-658477.html"),
            new BlankFragmenthotdish.Dishes("菠萝香菜拌牛肉",R.drawable.img,"https://home.meishichina.com/recipe-663829.html"),
            new BlankFragmenthotdish.Dishes("家庭版碌鸭",R.drawable.img,"https://home.meishichina.com/recipe-664624.html")
    };
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank_fragmentcolddish,container,false);
        recyclerView=view.findViewById(R.id.colddishrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myrecyckerViewAdapter=new MyRecycleViewAdapter(colddishes);
        myrecyckerViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BlankFragmenthotdish.Dishes selectedDish=colddishes[position];
                Intent intent=new Intent(getActivity(),Detail_dish_show.class);
                intent.putExtra("dish_name",selectedDish.getName());
                intent.putExtra("dish_url",selectedDish.getDishurl());
                intent.putExtra("dish_image",selectedDish.getImage());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myrecyckerViewAdapter);
        return view;
    }
}