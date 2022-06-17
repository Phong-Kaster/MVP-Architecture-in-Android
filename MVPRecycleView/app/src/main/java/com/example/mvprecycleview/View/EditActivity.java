package com.example.mvprecycleview.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvprecycleview.Interface.MainInterfacePresenter;
import com.example.mvprecycleview.MainActivity;
import com.example.mvprecycleview.Model.Person;
import com.example.mvprecycleview.Presenter.MainPresenter;
import com.example.mvprecycleview.R;

public class EditActivity extends AppCompatActivity {

    private Button buttonEdit;
    private Button buttonRemove;

    private EditText txtName;
    private EditText txtPhone;

    private String position;
    private String name;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        /*Step 1 - declare global variable*/

        Intent intent = getIntent();
        position =  intent.getStringExtra("position");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");


        /*Step 2 - run essential functions*/
        setupComponent();
        setupEvent();
        setupScreen();

    }

    /**
     * @author Phong-Kaster
     * this function maps defined components with XML layout
     */
    private void setupComponent()
    {
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonRemove = findViewById(R.id.buttonRemove);

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
    }


    /**
     * @author Phong-Kaster
     * this function sets event depending on what button is clicked by users;
     */
    private void setupEvent()
    {
        buttonEdit.setOnClickListener(view->{
            /*Step 1 - get data that user enters*/
            name = txtName.getText().toString().trim();
            phone = txtPhone.getText().toString().trim();

            /*Step 2 - modify the target with MainActivity's modify functions*/
            MainActivity.getmInstanceActivity().modify(
                    Integer.parseInt(position), name, phone);
        });


        buttonRemove.setOnClickListener(view ->{
            MainActivity.getmInstanceActivity().eradicate(Integer.parseInt(position));
        });

    }


    /**
     * @author Phong-Kaster
     * this function set name & phone that is sent from MainActivity
     */
    private void setupScreen() {

        txtName.setText( name.trim() );
        txtPhone.setText( phone.trim() );
    }



}