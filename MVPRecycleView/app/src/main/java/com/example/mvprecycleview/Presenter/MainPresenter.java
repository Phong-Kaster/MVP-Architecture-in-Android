package com.example.mvprecycleview.Presenter;

import com.example.mvprecycleview.Interface.MainInterfacePresenter;
import com.example.mvprecycleview.Model.Person;

import java.util.ArrayList;

public class MainPresenter {

    private ArrayList<Person> people = new ArrayList<>();

    /**
     * @Author Phong-Kaster
     * this interface "PresenterView" & its contructor is the way presenter gets in touch with MainActivity
     * */
    private MainInterfacePresenter.PresenterView presenterView;

    public MainPresenter(MainInterfacePresenter.PresenterView presenterView)
    {
        this.presenterView = presenterView;
    }




    /**
     * @author Phong-Kaster
     * this function create default records for RecycleView
     */
    public ArrayList<Person> setupDefaultRecord() {
        Person person1 = new Person("Phong", "0366523623");
        Person person2 = new Person("Kaster", "0794104124");
        Person person3 = new Person("Nguyen", "09748973476");

        people.add(person1);


        return people;
    }


    /**
     * @author Phong-Kaster
     * @param name is the name user enters
     * @param phone is the phone user provides
     * this function create a whole new person into the list
     */
    public void create(String name, String phone)
    {
        if( name.length() > 1 && phone.length() > 9)
        {
            Person person = new Person(name, phone);
            people.add(person);
            presenterView.createSuccessfully();
        }
        else
        {
            presenterView.createFailed();
        }
    }


    /**
     * @author Phong-Kaster
     * @param position tells Presenter where item is modified
     * @param name is the new name for this item
     * @param phone is the latest phone for element
     */
    public void modify(int position, String name, String phone)
    {
        if( position < 0 || name.length() < 0 || phone.length() < 5)
            presenterView.modifyFailed();


        people.get(position).setName(name);
        people.get(position).setPhone(phone);

        presenterView.modifySuccessfully();

    }

    /**
     * @author Phong-Kaster
     * @param position tell Presenter the position whose item is delete
     */
    public void eradicate(int position)
    {
        if( position < 0)
            presenterView.eradicateFailed();

        people.remove(position);
        presenterView.modifyFailed();
    }
}
