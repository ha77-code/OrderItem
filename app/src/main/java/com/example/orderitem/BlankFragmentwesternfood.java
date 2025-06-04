package com.example.orderitem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class BlankFragmentwesternfood extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragmentwesternfood() {
    }
    public static BlankFragmentwesternfood newInstance(String param1, String param2) {
        BlankFragmentwesternfood fragment = new BlankFragmentwesternfood();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    private BlankFragmenthotdish.Dishes westernfooddishes[]={
            new BlankFragmenthotdish.Dishes("芝士焗虾",R.drawable.img,"https://home.meishichina.com/recipe-614320.html"),
            new BlankFragmenthotdish.Dishes("香煎牛排骨配蘑菇汁",R.drawable.img,"https://home.meishichina.com/recipe-608758.html"),
            new BlankFragmenthotdish.Dishes("奶油三文鱼汤",R.drawable.img,"https://home.meishichina.com/recipe-596930.html"),
            new BlankFragmenthotdish.Dishes("奶油培根香菇意面",R.drawable.img,"https://home.meishichina.com/recipe-568605.html"),
            new BlankFragmenthotdish.Dishes("经典早餐牛油果开放土司",R.drawable.img,"https://home.meishichina.com/recipe-586755.html"),
            new BlankFragmenthotdish.Dishes("盐烤三文鱼",R.drawable.img,"https://home.meishichina.com/recipe-583113.html"),
            new BlankFragmenthotdish.Dishes("西班牙海鲜藜麦饭",R.drawable.img,"https://home.meishichina.com/recipe-579939.html")
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank_fragmentwesternfood, container, false);
        recyclerView=view.findViewById(R.id.westernfoodrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myrecyckerViewAdapter=new MyRecycleViewAdapter(westernfooddishes);
        myrecyckerViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BlankFragmenthotdish.Dishes selecteddish=westernfooddishes[position];
                Intent intent=new Intent(getActivity(),Detail_dish_show.class);
                intent.putExtra("dish_name",selecteddish.getName());
                intent.putExtra("dish_url",selecteddish.getDishurl());
                intent.putExtra("dish_image",selecteddish.getImage());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myrecyckerViewAdapter);
        return view;
    }
}