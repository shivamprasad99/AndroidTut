package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    private TextView textViewRollNo;
    private EditText editName, editSchool, editAddress;
    private Spinner classSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editName = findViewById(R.id.editName2);
        editSchool = findViewById(R.id.editSchool2);
        editAddress = findViewById(R.id.editAddress2);
        classSpinner = findViewById(R.id.classSpinner2);
        textViewRollNo = findViewById(R.id.textViewRollNo2);

        final Student student = (Student) getIntent().getSerializableExtra("student");
        loadStudent(student);

        findViewById(R.id.submitBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Updating", Toast.LENGTH_LONG).show();
                updateStudent(student);
            }
        });
    }

    private void loadStudent(Student student) {
        textViewRollNo.setText(student.getRollNo());
        editName.setText(student.getName());
        editSchool.setText(student.getSchool());;
        editAddress.setText(student.getAddress());
        classSpinner.setSelection(student.getStdClass()-1);
    }

    private void updateStudent(final Student student) {
        final String sName = editName.getText().toString().trim();
        final String sSchool = editSchool.getText().toString().trim();
        final int sClass = Integer.parseInt(classSpinner.getSelectedItem().toString());
        final String sAddress = editAddress.getText().toString().trim();

        // check the values are valid or not
        if(sName.isEmpty()){
            editName.setError("Name required");
            editName.requestFocus();
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


        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                student.setName(sName);
                student.setSchool(sSchool);
                student.setAddress(sAddress);
                student.setStdClass(sClass);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .studentDao()
                        .update(student);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
            }
        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }
}