package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {
    private Context mCtx;
    private FragmentManager fragmentManager;
    private List<Student> studentList;
    public Student t;

    public StudentsAdapter(Context mCtx, FragmentManager fragmentManager, List<Student> studentList) {
        this.mCtx = mCtx;
        this.fragmentManager = fragmentManager;
        this.studentList = studentList;
    }

    @Override
    public StudentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_students, parent, false);
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentsViewHolder holder, int position) {
        t = studentList.get(position);
        holder.textViewName.setText(t.getName());
        holder.textViewRollNo.setText(t.getRollNo());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewName, textViewRollNo;

        public StudentsViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRollNo = itemView.findViewById(R.id.textViewRollNo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Student student = studentList.get(getAdapterPosition());
            StudentFragment studentFragment = new StudentFragment();
            Bundle b = new Bundle();
            b.putSerializable("student",student);
            studentFragment.setArguments(b);
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.bodyFragment, studentFragment, null)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
