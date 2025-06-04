package com.example.orderitem;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class BlankFragmentstaplefood extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public BlankFragmentstaplefood() {
    }
    public static BlankFragmentstaplefood newInstance(String param1, String param2) {
        BlankFragmentstaplefood fragment = new BlankFragmentstaplefood();
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
    private BlankFragmenthotdish.Dishes soupdishes[]={
            new BlankFragmenthotdish.Dishes("肥牛芝士焗饭",R.drawable.staplefood1,"https://home.meishichina.com/recipe-662563.html"),
            new BlankFragmenthotdish.Dishes("板栗牛肋条焖饭",R.drawable.staplefood2,"https://home.meishichina.com/recipe-663729.html"),
            new BlankFragmenthotdish.Dishes("腊肠牛肉焖饭",R.drawable.img,"https://home.meishichina.com/recipe-662169.html"),
            new BlankFragmenthotdish.Dishes("煲仔饭",R.drawable.img,"https://home.meishichina.com/recipe-662221.html"),
            new BlankFragmenthotdish.Dishes("排骨腊肠蒸饭",R.drawable.img,"https://home.meishichina.com/recipe-661835.html"),
            new BlankFragmenthotdish.Dishes("紫菜虾仁炒饭",R.drawable.img,"https://home.meishichina.com/recipe-661261.html"),
            new BlankFragmenthotdish.Dishes("菠萝炒饭",R.drawable.img,"https://home.meishichina.com/recipe-659695.html"),
            new BlankFragmenthotdish.Dishes("春笋焖饭",R.drawable.img,"https://home.meishichina.com/recipe-663524.html"),
            new BlankFragmenthotdish.Dishes("槐花麦饭",R.drawable.img,"https://home.meishichina.com/recipe-664563.html")
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank_fragmentstaplefood,container,false);
        recyclerView=view.findViewById(R.id.souprecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myrecyckerViewAdapter=new MyRecycleViewAdapter(soupdishes);
        myrecyckerViewAdapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BlankFragmenthotdish.Dishes selecteddish=soupdishes[position];
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