package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {
    private Context mCtx;
    private List<Student> studentList;

    public StudentsAdapter(Context mCtx, List<Student> studentList) {
        this.mCtx = mCtx;
        this.studentList = studentList;
    }

    @Override
    public StudentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_students, parent, false);
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentsViewHolder holder, int position) {
        Student t = studentList.get(position);
        holder.textViewName.setText(t.getName());
        holder.textViewRollNo.setText(t.getRollNo());
//        holder.textViewClass.setText(String.valueOf(t.getStdClass()));
//        holder.textViewSchool.setText(t.getSchool());
//        holder.textViewAddress.setText(t.getAddress());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewName, textViewRollNo, textViewClass, textViewSchool, textViewAddress;

        public StudentsViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewRollNo = itemView.findViewById(R.id.textViewRollNo);
//            textViewClass = itemView.findViewById(R.id.textViewClass);
//            textViewSchool = itemView.findViewById(R.id.textViewSchool);
//            textViewAddress = itemView.findViewById(R.id.textViewAddress);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Student student = studentList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateActivity.class);
            intent.putExtra("student", student);
            mCtx.startActivity(intent);
        }
    }
}
