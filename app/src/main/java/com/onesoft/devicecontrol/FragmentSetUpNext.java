package com.onesoft.devicecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by exosj on 16.10.2016.
 */

public class FragmentSetUpNext extends Fragment {
    public FragmentSetUpNext() {

    }
    String device;
    String key = "device";
    Button login;
    TextView spiner;
    ImageView test;
    private View.OnClickListener loginLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
    if (device.equals("Fero"))
    {
        Intent i = new Intent(getActivity(), Home.class);
        startActivity(i);
        getActivity().finish();
    }else if (device.equals("Smart apartman 1"))
    {
        FragmentSmartHome objSetUp = new FragmentSmartHome();
        Bundle args = new Bundle();
        args.putInt(key, getCisloApartmanu(device));
        objSetUp.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.root, objSetUp)
                .addToBackStack(null)
                .commit();
    }
    else {
        FragmentApartman objSetUp = new FragmentApartman();
        Bundle args = new Bundle();
        args.putInt(key, getCisloApartmanu(device));
        objSetUp.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.root, objSetUp)
                .addToBackStack(null)
                .commit();
    }
        }
    };
  /*  private View.OnClickListener loginLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), Home.class);
            startActivity(i);
            getActivity().finish();
        }
    };*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstsetup, container, false);
        Bundle bundle = this.getArguments();
        device = bundle.getString(key);
        login = (Button) view.findViewById(R.id.buttonlogin);
        login.setOnClickListener(loginLisenner);
        spiner = (TextView) view.findViewById(R.id.spinnerzariadenietextview);
        spiner.setText(device);
        return view;
    }
    public Integer getCisloApartmanu (String request){
        int answer;
        switch (request) {
            case "Fero": {answer =0;}break;
            case "Smart apartman 1": {answer =1;}break;
            case "Apartman 1": {answer =1;}break;
            case "Apartman 2": {answer =2;}break;
            case "Apartman 3": {answer =3;}break;
            case "Apartman 4": {answer =4;}break;
            case "Apartman 5": {answer =5;}break;
            case "Apartman 6": {answer =6;}break;
            case "Apartman 7": {answer =7;}break;
            case "Apartman 8": {answer =8;}break;
            case "Apartman 9": {answer =9;}break;
            case "Apartman 10": {answer =10;}break;
            case "Apartman 11": {answer =11;}break;
            case "Apartman 12": {answer =12;}break;
            case "Apartman 13": {answer =13;}break;
            default:{answer =0; }break;
        }
        return answer;
    }
}
