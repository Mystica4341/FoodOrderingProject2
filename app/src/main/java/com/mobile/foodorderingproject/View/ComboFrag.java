package com.mobile.foodorderingproject.View;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.foodorderingproject.Adapter.CustomComboAdapter;
import com.mobile.foodorderingproject.Controller.ComboHandler;
import com.mobile.foodorderingproject.Model.Combo;
import com.mobile.foodorderingproject.Model.LuuHoaDon;
import com.mobile.foodorderingproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComboFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComboFrag extends Fragment {
    ImageView imgMenu;
    GridView gridCombo;
    SQLiteDatabase db;
    ArrayList<Combo> arrayListCombo = new ArrayList<>();
    CustomComboAdapter adapter;
    TextView tvName, tvDesc, tvPrice, tvNums;
    ImageButton btnMinus, btnPlus;

    ComboHandler comboHandler;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComboFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComboFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ComboFrag newInstance(String param1, String param2) {
        ComboFrag fragment = new ComboFrag();
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

    public ArrayList<Combo> initData(){
        ArrayList<Combo> lsData = new ArrayList<>();
        Combo combo = new Combo();
        lsData.add(combo);
        return(lsData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_combo, container, false);
        addControls(view);
        ComboActive();
        adapter = new CustomComboAdapter(requireActivity(), arrayListCombo);
        gridCombo.setAdapter(adapter);
        return view;
    }
    public void addControls(View view){
        btnMinus = (ImageButton)view.findViewById(R.id.btnImgMinus);
        btnPlus = (ImageButton)view.findViewById(R.id.btnImgPlus);
        tvDesc = (TextView)view.findViewById(R.id.tvDesc);
        tvName = (TextView)view.findViewById(R.id.tvName);
        tvPrice = (TextView)view.findViewById(R.id.tvPrice);
        tvNums = (TextView)view.findViewById(R.id.tvNums);
        imgMenu = (ImageView)view.findViewById(R.id.imgMenu);
        gridCombo = (GridView)view.findViewById(R.id.gridCombo);
    }
    public void addEvents(){

    }
    public void pushDataHoaDon(Bundle bundle, ArrayList<LuuHoaDon> arraylistLHD){
        bundle.putParcelableArrayList("LuuHoaDon",arraylistLHD);
        FragmentManager fm = getParentFragmentManager();
        fm.setFragmentResult("keyMain",bundle);
    }

    public void load(){
        arrayListCombo = ComboHandler.loadData();
        adapter = new CustomComboAdapter(requireActivity(),arrayListCombo);
        gridCombo.setAdapter(adapter);
    }

    public void ComboActive(){
        comboHandler = new ComboHandler(requireActivity(),ComboHandler.DB_NAME,null,1);
        comboHandler.onCreate(db);
        comboHandler.initData();
        load();
    }
}