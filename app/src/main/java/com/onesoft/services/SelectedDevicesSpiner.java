package com.onesoft.services;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by exosj on 25.02.2017.
 */

public class SelectedDevicesSpiner implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public String getDevice(AdapterView<?> parent, View view, int position, long id,List<String> listDevices){
        String device = new String();
        device = "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString();
      return device;
    }
}
