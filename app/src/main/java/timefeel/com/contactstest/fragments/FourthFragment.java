package timefeel.com.contactstest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import timefeel.com.contactstest.R;

/**
 * Created by test on 16/02/2017.
 */
public class FourthFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ///////////////////////////////////////
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fourth_fragment, container, false);

        return rootView;
    }
}
