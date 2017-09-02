package com.onesoft.devicecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


/**
 * Created by exosj on 16.10.2016.
 */

public class FragmentLoginFirst extends Fragment {
    public FragmentLoginFirst(){

    }

    Button login,register;
    private View.OnClickListener loginLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String message = getString(R.string.message);
            Toast.makeText(getActivity()
                    ,message,Toast.LENGTH_SHORT).show();
        }
    };
    private View.OnClickListener registerLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String message = getString(R.string.message);
            Toast.makeText(getActivity()
                    ,message,Toast.LENGTH_SHORT).show();
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login,container,false);
        initBackgroundImage(view);
        login = (Button) view.findViewById(R.id.buttonlogin);
        login.setOnClickListener(loginLisenner);

        register = (Button) view.findViewById(R.id.buttonregister);
        register.setOnClickListener(registerLisenner);

        return view;



    }

    private void initBackgroundImage(View view){
        ImageView imageLogo = (ImageView) view.findViewById(R.id.logonesoft);
        Glide.with(this)
                .load(R.drawable.logoonesoft)
                .into(imageLogo);

    }

}
