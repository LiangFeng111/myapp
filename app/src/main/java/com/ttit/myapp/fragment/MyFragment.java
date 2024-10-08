package com.ttit.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ttit.myapp.R;
import com.ttit.myapp.activity.UsbCardAcivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btn_open_scan;

    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();

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
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        btn_open_scan = view.findViewById(R.id.btn_open_scan);
        btn_open_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UsbCardAcivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}