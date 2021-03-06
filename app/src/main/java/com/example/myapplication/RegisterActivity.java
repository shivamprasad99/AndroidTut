package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText editName, editRollNo, editSchool, editAddress;
    private Spinner classSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName);
        editRollNo = findViewById(R.id.editRollNo);
        editSchool = findViewById(R.id.editSchool);
        editAddress = findViewById(R.id.editAddress);
        classSpinner = findViewById(R.id.classSpinner);

        findViewById(R.id.submitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask(){
        final String sName = editName.getText().toString().trim();
        final String sRollNo = editRollNo.getText().toString().trim();
        final String sSchool = editSchool.getText().toString().trim();
        final int sClass = Integer.parseInt(classSpinner.getSelectedItem().toString());
        final String sAddress = editAddress.getText().toString().trim();

        // check the values are valid or not
        if(sName.isEmpty()){
            editName.setError("Name required");
            editName.requestFocus();
            return;
        }
        if(sRollNo.isEmpty()){
            editRollNo.setError("Roll no. required");
            editRollNo.requestFocus();
            return;
        }
        if(sSchool.isEmpty()){
            editSchool.setError("School required");
            editSchool.requestFocus();
            return;
        }
        if(sAddress.isEmpty()){
            editAddress.setError("Address required");
            editAddress.requestFocus();
            return;
        }

        // asynchronously update the database
        class SaveStudent extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids){

                // create a Student
                Student student = new Student();
                student.setAddress(sAddress);
                student.setName(sName);
                student.setRollNo(sRollNo);
                student.setSchool(sSchool);
                student.setStdClass(sClass);

                // create entry in database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .studentDao()
                        .insert(student);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveStudent ss = new SaveStudent();
        ss.execute();
    }
}