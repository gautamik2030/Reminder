package com.example.reminder_application;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BreakFragment extends Fragment implements View.OnClickListener{
    EditText InputStartTime,InputEndTime,InputBreakInterval;
    DBHelper db;
    Button setTimer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_break,container,false);
        InputStartTime =view.findViewById(R.id.InputStartTime);
        InputEndTime=view.findViewById(R.id.InputEndTime);
        InputBreakInterval=view.findViewById(R.id.InputBreakInterval);
        setTimer=view.findViewById(R.id.setTimer);
        db = new DBHelper(getActivity());
        Cursor present=db.getDataBreak();
        if(present.getCount()>0)
        {
            long presentS=db.getSTimeBreak();
            long presentE=db.getEtimeBreak();
            int presentI= db.getItimeBreak();
            String i=Integer.toString(presentI);
            String s=Long.toString(presentS);
            String e=Long.toString(presentE);
            InputStartTime.setHint(s);
            InputEndTime.setHint(e);
            InputBreakInterval.setHint(i);
        }

        setTimer.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        long sTime,eTime;
        int intervalTime;
        sTime=Long.parseLong(InputStartTime.getText().toString());
        eTime=Long.parseLong(InputEndTime.getText().toString());
        intervalTime=Integer.parseInt(InputBreakInterval.getText().toString());
        int result = db.updateDataBreak(sTime,eTime,intervalTime);
        if(result==1)
        {
            Toast.makeText(getActivity(),"Updated",Toast.LENGTH_LONG).show();
        }
        else if(result==2)
        {
            Toast.makeText(getActivity(),"Inserted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getActivity(),"Failed",Toast.LENGTH_LONG).show();
        }
    }
}
