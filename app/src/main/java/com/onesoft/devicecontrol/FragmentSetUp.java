package com.onesoft.devicecontrol;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by exosj on 16.10.2016.
 */

public class FragmentSetUp extends Fragment {
    public FragmentSetUp(){

    }

    View view;
    String selected,key;
    private Spinner spinner1;
    EditText ipedittext,editTextportFirst;
    Button login;
    List<String> list = new ArrayList<String>();

    private View.OnClickListener loginLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selected == null ){

                Context context = view.getContext();
                CharSequence text ="Chyba volba zariadenia: ";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            }
            else {
                key = "device";
                FragmentSetUpNext objSetUp = new FragmentSetUpNext();
                Bundle args = new Bundle();
                args.putString(key,selected);
                objSetUp.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.root, objSetUp)
                        .addToBackStack(null)
                        .commit();
            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.firstsetuplogin,container,false);


        login = (Button) view.findViewById(R.id.buttonlogin);
        addItemsOnSpinner2(view);
        addListenerOnSpinnerItemSelection(view);
        login.setOnClickListener(loginLisenner);

        return view;

    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2(View view) {

        spinner1 = (Spinner) view.findViewById(R.id.spinnerzariadenietextview);

        list.add("Fero");
        list.add("Smart apartman 1");
        list.add("Apartman 1");
        list.add("Apartman 2");
        list.add("Apartman 3");
        list.add("Apartman 4");
        list.add("Apartman 5");
        list.add("Apartman 6");
        list.add("Apartman 7");
        list.add("Apartman 8");
        list.add("Apartman 9");
        list.add("Apartman 10");
        list.add("Apartman 11");
        list.add("Apartman 12");
        list.add("Apartman 13");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }
    public void addListenerOnSpinnerItemSelection(final View view) {
        spinner1 = (Spinner) view.findViewById(R.id.spinnerzariadenietextview);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                CharSequence text ="Zvolene ubytovanie: " + selected;
                int duration = Toast.LENGTH_SHORT;

                ipedittext = (EditText)  view.findViewById(R.id.ipedittext);
                editTextportFirst= (EditText)  view.findViewById(R.id.editTextportFirst);

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                switch (selected) {
                    case "Fero": {
                        ipedittext.setVisibility(View.VISIBLE);
                        editTextportFirst.setVisibility(View.VISIBLE);
                    }
                        break;
                    default:{
                        ipedittext.setVisibility(View.INVISIBLE);
                        editTextportFirst.setVisibility(View.INVISIBLE);
                    }
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }


}
