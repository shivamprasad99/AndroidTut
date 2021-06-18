package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview_students);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        Bundle b = getArguments();
        if(b == null || b.getString("query").isEmpty())
            getStudents();
        else {
            String queryText = b.getString("query");
            getSimilarStudents(queryText);
        }

        // Inflate the layout for this fragment
        return rootView;
    }

    private void getStudents() {
        class GetStudents extends AsyncTask<Void, Void, List<Student>> {
            @Override
            protected List<Student> doInBackground(Void... voids) {
                return DatabaseClient
                        .getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .studentDao()
                        .getAll();
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                StudentsAdapter studentsAdapter = new StudentsAdapter(getActivity().getApplicationContext(), getActivity().getSupportFragmentManager(), students);
                recyclerView.setAdapter(studentsAdapter);
            }
        }

        GetStudents gs = new GetStudents();
        gs.execute();
    }

    private void getSimilarStudents(String queryText) {
        class GetSimilarStudents extends AsyncTask<Void, Void, List<Student>> {
            @Override
            protected List<Student> doInBackground(Void... voids) {
                return DatabaseClient
                        .getInstance(getActivity().getApplicationContext())
                        .getAppDatabase()
                        .studentDao()
                        .getSimilarNames(queryText);
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                super.onPostExecute(students);
                StudentsAdapter studentsAdapter = new StudentsAdapter(getActivity().getApplicationContext(), getActivity().getSupportFragmentManager(), students);
                recyclerView.setAdapter(studentsAdapter);
            }
        }
        GetSimilarStudents gss = new GetSimilarStudents();
        gss.execute();
    }
}