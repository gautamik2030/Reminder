package com.example.reminder_application;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HomeFragment extends Fragment {
    ProgressBar progressBar;
    TextView litresintake,breakTime;
    DBHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        progressBar=view.findViewById(R.id.progressBar);
        litresintake=view.findViewById(R.id.litresintake);

        breakTime=view.findViewById(R.id.breakTime);

        db = new DBHelper(getActivity());
        Cursor present=db.getData();
        if(present.getCount()>0)
        {

            present.moveToFirst();
            int g = present.getInt(present.getColumnIndex("goal"));
            int c = present.getInt(present.getColumnIndex("current"));
            int gc=g-c;
            String gs=Integer.toString(g);

            String cs = Integer.toString(gc);
            String s = cs+" of "+gs+" ";
            litresintake.setText(s);

        }

        return view;
    }


}
