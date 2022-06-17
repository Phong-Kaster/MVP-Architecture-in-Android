package com.example.mvprecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvprecycleview.Interface.MainInterfacePresenter;
import com.example.mvprecycleview.Model.Person;
import com.example.mvprecycleview.Presenter.MainPresenter;
import com.example.mvprecycleview.RecycleViewAdapter.PersonRecycleViewAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainInterfacePresenter.PresenterView {

    private RecyclerView recyclerView;
    private PersonRecycleViewAdapter adapter;

    private Button buttonCreate;
    private ArrayList<Person> objects = new ArrayList<>();

    private MainPresenter presenter;
    private int index = 0;

    /**
     * @author Phong-Kaster
     * WeakReference is the way we use to call MainActivity's function when we are standing on EditActivity
     * Systax: MainActivity.getmInstanceActivity.<>your method<>
     * Example: MainActivity.getmInstanceActivity.deleteStudent(position)
     * */
    public static WeakReference<MainActivity> weakActivity;
    public static MainActivity getmInstanceActivity() {
        return weakActivity.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Step 1 - declare global variable*/
        presenter = new MainPresenter(this);
        weakActivity = new WeakReference<>(MainActivity.this);

        /*Step 2 - execute essential functions*/
        setupComponent();
        setupRecycleView();
        setupEvent();

    }

    /**
     * @author Phong-Kaster
     * this function will map defined component in this activity with XML layout.
     * */
    private void setupComponent()
    {
        recyclerView = findViewById(R.id.mainRecycleView);
        buttonCreate = findViewById(R.id.buttonCreate);
    }




    /**
     * @author Phong-Kaster
     * this function maps defined component with XML layout
     */
    private void setupRecycleView()
    {
        objects = presenter.setupDefaultRecord();
        adapter = new PersonRecycleViewAdapter(this, objects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /**
     * @author Phong-Kaster
     * this function maps defined button with XML layout
     */
    @SuppressLint("NotifyDataSetChanged")
    private void setupEvent()
    {

        buttonCreate.setOnClickListener(view->{
            String name = "Phong " + index++;
            String phone = "0366253623";

            presenter.create(name, phone);
            adapter.notifyDataSetChanged();
        });
    }

    /***
     * @author Phong-Kaster
     * There are 6 function in this "PresenterView" interface:
     * 1.createSuccessfully
     * 2.createFailed
     * 3.modifySuccessfully
     * 4.modifyFailed
     * 5.eradicateSuccessfully
     * 6.eradicateFailed
     *
     * They are automatically showed below
     * we just re-define these functions which we need to need.
     */
    @Override
    public void createSuccessfully() {
        Toast.makeText(this, "Create successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createFailed() {
        Toast.makeText(this, "Create failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void modifySuccessfully() {
        Toast.makeText(this, "Modify successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void modifyFailed() {
        Toast.makeText(this, "Modify Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eradicateSuccessfully() {
        Toast.makeText(this, "Eradicated Successfully !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eradicateFailed() {
        Toast.makeText(this, "Eradicated Failed !", Toast.LENGTH_SHORT).show();
    }


    /**
     * @author Phong-Kaster
     * @param position is vacancy of the item modified
     * @param name is new name
     * @param phone is new phone
     */
    public void modify(int position, String name, String phone)
    {
        presenter.modify(position, name, phone);
        adapter.notifyItemChanged(position);
    }


    /***
     * @author Phong-Kaster
     * @param position is vacancy of the item eradicated
     * */
    public void eradicate(int position)
    {
        presenter.eradicate(position);
        adapter.notifyItemRemoved(position);
    }
}