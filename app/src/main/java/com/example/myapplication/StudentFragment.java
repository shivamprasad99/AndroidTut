package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StudentFragment extends Fragment {

    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView textViewRollNo, textViewName,
                textViewClass, textViewSchool, textViewAddress;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_student, container, false);
        textViewRollNo = rootView.findViewById(R.id.textViewRollNo3);
        textViewName = rootView.findViewById(R.id.textViewName2);
        textViewClass = rootView.findViewById(R.id.textViewClass2);
        textViewSchool = rootView.findViewById(R.id.textViewSchool2);
        textViewAddress = rootView.findViewById(R.id.textViewAddress2);

        Bundle b = getArguments();
        assert b != null;
        Student student = (Student)b.getSerializable("student");
        textViewRollNo.setText(textViewRollNo.getText().toString() + student.getRollNo());
        textViewName.setText(textViewName.getText().toString() + student.getName());
        textViewClass.setText(textViewClass.getText().toString() + Integer.toString(student.getStdClass()));
        textViewSchool.setText(textViewSchool.getText().toString() + student.getSchool());
        textViewAddress.setText(textViewAddress.getText().toString() + student.getAddress());


        return rootView;
    }
}