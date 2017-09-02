package com.onesoft.devicecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by exosj on 16.10.2016.
 */

public class FragmentRegister extends Fragment {
    public FragmentRegister(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.registration,container,false);
        initBackgroundImage(view);
        return view;



    }

    private void initBackgroundImage(View view){
        ImageView imageLogo = (ImageView) view.findViewById(R.id.logonesoft);
        Glide.with(this)
                .load(R.drawable.logoonesoft)
                .into(imageLogo);

    }

}
