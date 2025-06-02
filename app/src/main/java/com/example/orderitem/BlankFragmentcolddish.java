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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentcolddish#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentcolddish extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragmentcolddish.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragmentcolddish newInstance(String param1, String param2) {
        BlankFragmentcolddish fragment = new BlankFragmentcolddish();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BlankFragmentcolddish() {
        // Required empty public constructor
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
            new BlankFragmenthotdish.Dishes("柠檬生腌基围虾", R.drawable.westernfooddish7,"https://home.meishichina.com/recipe-662339.html"),
            new BlankFragmenthotdish.Dishes("捞汁小海鲜",R.drawable.colddish3,"https://home.meishichina.com/recipe-661446.html"),
            new BlankFragmenthotdish.Dishes("凉拌苦瓜",R.drawable.colddish4,"https://home.meishichina.com/recipe-660500.html"),
            new BlankFragmenthotdish.Dishes("酸辣鸡丝", R.drawable.staplefood6,"https://home.meishichina.com/recipe-659632.html"),
            new BlankFragmenthotdish.Dishes("青瓜拌虾滑",R.drawable.colddish6,"https://home.meishichina.com/recipe-658853.html"),
            new BlankFragmenthotdish.Dishes("蓝莓山药泥",R.drawable.colddish7,"https://home.meishichina.com/recipe-658477.html")
    };
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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