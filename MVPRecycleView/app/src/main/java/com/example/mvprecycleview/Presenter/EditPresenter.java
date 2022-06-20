package com.example.mvprecycleview.Presenter;

import com.example.mvprecycleview.Interface.EditView;
import com.example.mvprecycleview.MainActivity;

public class EditPresenter {

    private EditView presenter;

    public EditPresenter(EditView presenter)
    {
        this.presenter = presenter;
    }

    /***
     * @author Phong-Kaster
     * @param position tells Presenter where item is modified
     * @param name is the new name for this item
     * @param phone is the latest phone for element
     */
    public void modify(String position, String name, String phone)
    {
        int integerPosition = Integer.parseInt(position);
        if( integerPosition < 0 || name.length() < 1 || phone.length() < 10)
        {
            presenter.modifyFailed();
        }
        else
        {
            MainActivity.getmInstanceActivity().modify(integerPosition, name, phone);
        }
    }

    /**
     * @author Phong-Kaster
     * @param pos tell Presenter the position whose item is delete
     */
    public void eradicate(String pos)
    {
        int position = Integer.parseInt(pos);

        if( position < 0)
        {
            presenter.eradicateFailed();
        }
        else
        {
            MainActivity.getmInstanceActivity().eradicate(position);
        }
    }
}
