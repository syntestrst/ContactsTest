package timefeel.com.contactstest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import timefeel.com.contactstest.R;




/**
 * Created by test on 16/02/2017.
 */
public class SecondFragment extends Fragment{

    private TextView mpassword;

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.second_fragment, container, false);

        TextView mgender = (TextView) rootView.findViewById(R.id.gender);
        TextView mnamefirst = (TextView) rootView.findViewById(R.id.namefirst);
        TextView mnametitle = (TextView) rootView.findViewById(R.id.nametitle);
        TextView mnamelast = (TextView) rootView.findViewById(R.id.namelast);
        TextView mlocation = (TextView) rootView.findViewById(R.id.location);
        TextView memail = (TextView) rootView.findViewById(R.id.emails);
        TextView musername = (TextView) rootView.findViewById(R.id.username);
        //mpassword = (TextView)  getview findViewById(R.id.mpassword);
        TextView mphone = (TextView) rootView.findViewById(R.id.phones);
        TextView mcell = (TextView) rootView.findViewById(R.id.cell);
        TextView mssn = (TextView) rootView.findViewById(R.id.ssn);
        //m = (ImageView) getview findViewById(R.id.m);

        mgender.setText(FirstFragment.contactsresponse.getMe().getGender());
        mnamefirst.setText(FirstFragment.contactsresponse.getMe().getName().getFirst());
        mnametitle.setText(FirstFragment.contactsresponse.getMe().getName().getTitle());
        mnamelast.setText(FirstFragment.contactsresponse.getMe().getName().getLast());
        mlocation.setText(FirstFragment.contactsresponse.getMe().getLocation().getCity());
        memail.setText(FirstFragment.contactsresponse.getMe().getEmail());
        musername.setText(FirstFragment.contactsresponse.getMe().getUsername());
        mphone.setText(FirstFragment.contactsresponse.getMe().getPhone());
        mcell.setText(FirstFragment.contactsresponse.getMe().getCell());
        mssn.setText(FirstFragment.contactsresponse.getMe().getSSN());

        return rootView;
        }

    }
