package com.example.mvprecycleview.Interface;

import com.example.mvprecycleview.Model.Person;
import com.example.mvprecycleview.RecycleViewAdapter.PersonRecycleViewAdapter;

import java.util.ArrayList;

public interface MainInterfacePresenter {

   interface PresenterView{
       void createSuccessfully();
       void createFailed();
       void modifySuccessfully();
       void modifyFailed();

       void eradicateSuccessfully();
       void eradicateFailed();
   }

}
