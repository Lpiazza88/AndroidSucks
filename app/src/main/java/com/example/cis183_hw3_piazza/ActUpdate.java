package com.example.cis183_hw3_piazza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActUpdate extends AppCompatActivity {
    Button cancel;
    Button save;
    TextView uname;
    EditText fname;
    EditText lname;
    EditText pass;
    EditText email;
    EditText age;

    Intent mainActivity;

    Employ employPassed;
    DatabaseHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.employee_list);

        dbHelp=new DatabaseHelper(this);
        mainActivity=new Intent(ActUpdate.this, MainActivity.class);

        save=(Button) findViewById(R.id.btn_save);
        cancel=(Button) findViewById(R.id.btn_cancel);
        fname=(EditText) findViewById(R.id.fname_ET);
        lname=(EditText) findViewById(R.id.lname_ET);
        pass=(EditText) findViewById(R.id.pass_ET);
        email=(EditText) findViewById(R.id.email_ET);
        age=(EditText) findViewById(R.id.age_ET);
        uname=(TextView) findViewById(R.id.username_TV);

        Intent cameFrom = getIntent();

        employPassed = (Employ) cameFrom.getSerializableExtra("Employ");

        uname.setText(employPassed.getUname());
        fname.setText(employPassed.getFname());
        lname.setText(employPassed.getLname());
        pass.setText(employPassed.getPass());
        email.setText(employPassed.getEmail());
        age.setText(employPassed.getAge());

        saveEvent();
        cancelEvent();
    }

    public void saveEvent(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employPassed.setAge(age.getText().toString());
                employPassed.setEmail(email.getText().toString());
                employPassed.setPass(pass.getText().toString());
                employPassed.setFname(fname.getText().toString());
                employPassed.setLname(lname.getText().toString());

                dbHelp.EmpChange(employPassed);

                startActivity(mainActivity);
            }
        });
    }
    public void cancelEvent(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainActivity);
            }
        });
    }
}
