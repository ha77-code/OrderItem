package com.example.orderitem;

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
 * Use the {@link BlankFragmentstaplefood#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentstaplefood extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragmentstaplefood() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragmentsoup.
     */
    // TODO: Rename and change types and number of parameters
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
            new BlankFragmenthotdish.Dishes("腊肠牛肉焖饭",R.drawable.staplefood3,"https://home.meishichina.com/recipe-662169.html"),
            new BlankFragmenthotdish.Dishes("煲仔饭",R.drawable.staplefood4,"https://home.meishichina.com/recipe-662221.html"),
            new BlankFragmenthotdish.Dishes("排骨腊肠蒸饭",R.drawable.staplefood5,"https://home.meishichina.com/recipe-661835.html"),
            new BlankFragmenthotdish.Dishes("紫菜虾仁炒饭",R.drawable.staplefood6,"https://home.meishichina.com/recipe-661261.html"),
            new BlankFragmenthotdish.Dishes("菠萝炒饭",R.drawable.staplefood7,"https://home.meishichina.com/recipe-659695.html")
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