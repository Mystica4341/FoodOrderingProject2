package com.mobile.foodorderingproject.View;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.foodorderingproject.Adapter.CustomDessertAdapter;
import com.mobile.foodorderingproject.Controller.DessertHandler;
import com.mobile.foodorderingproject.Controller.FoodHandler;
import com.mobile.foodorderingproject.Model.Dessert;
import com.mobile.foodorderingproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DessertFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DessertFrag extends Fragment {
    ImageButton btnImgPlus, btnImgMinus;
    TextView tvNums, tvPrice, tvName;
    SQLiteDatabase db;
    GridView gridDessert;
    CustomDessertAdapter adapter;
    ArrayList<Dessert> dessertArrayList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DessertFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DesertFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static DessertFrag newInstance(String param1, String param2) {
        DessertFrag fragment = new DessertFrag();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_desert,container,false);
        addControls(view);
        DessertActive();
        adapter=new CustomDessertAdapter(requireActivity(),dessertArrayList);
        gridDessert.setAdapter(adapter);
        return view;
    }
    public void addControls(View view){
        gridDessert = (GridView) view.findViewById(R.id.gridDesert);
        btnImgPlus=(ImageButton) view.findViewById(R.id.btnImgPlus);
        btnImgMinus=(ImageButton)view.findViewById(R.id.btnImgMinus);
        tvNums=(TextView)view.findViewById(R.id.tvNums);
        tvName=(TextView)view.findViewById(R.id.tvName);
        tvPrice=(TextView) view.findViewById(R.id.tvPrice);
    }
    public void load(){
        dessertArrayList= DessertHandler.loadData();
        adapter=new CustomDessertAdapter(requireActivity(),dessertArrayList);
        gridDessert.setAdapter(adapter);
    }
    public void DessertActive(){
        DessertHandler dessertHandler= new DessertHandler(requireActivity(),DessertHandler.DB_NAME,null,1);
        dessertHandler.onCreate(db);
        dessertHandler.initData();
        load();
    }
}