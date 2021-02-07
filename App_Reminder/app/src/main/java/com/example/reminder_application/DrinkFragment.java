package com.example.reminder_application;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
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

public class DrinkFragment extends Fragment implements View.OnClickListener{
    Button setWater;
    EditText InputWaterGoal,InputWaterIntake;
    DBHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_drink,container,false);
        InputWaterGoal=v.findViewById(R.id.InputWaterGoal);
        InputWaterIntake=v.findViewById(R.id.InputWaterIntake);
        setWater=v.findViewById(R.id.setWaterReminder);
        db = new DBHelper(getActivity());
       Cursor present=db.getData();
        if(present.getCount()>0)
        {
            int presentGoal= db.getGoal();
            String s=Integer.toString(presentGoal);
            InputWaterGoal.setHint(s);
        }

        setWater.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        int goal,current;
        goal = Integer.parseInt(InputWaterGoal.getText().toString());
        current = Integer.parseInt(InputWaterIntake.getText().toString());
        //Cursor cu= db.getData();
        Boolean checkUpdate = db.updateData(goal,current);
        if(checkUpdate==true){
            Toast.makeText(getActivity(),"Updated",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getActivity(),"Update failed",Toast.LENGTH_LONG).show();
        }
    }
}
