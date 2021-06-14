package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerview_students);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getStudents();
    }

    private void getStudents() {
        class GetStudents extends AsyncTask<Void, Void, List<Student>> {
            @Override
            protected List<Student> doInBackground(Void... voids) {
                List<Student> studentList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .studentDao()
                        .getAll();
                return studentList;
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                StudentsAdapter studentsAdapter = new StudentsAdapter(ListActivity.this, students);
                recyclerView.setAdapter(studentsAdapter);
            }
        }

        GetStudents gs = new GetStudents();
        gs.execute();
    }
}