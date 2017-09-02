package com.onesoft.devicecontrol;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

import static com.onesoft.devicecontrol.R.id.root;


/**
 * Created by exosj on 16.10.2016.
 */

public class FragmentLogin extends Fragment {
    public FragmentLogin(){

    }
    Button login;
    private View.OnClickListener loginLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentSetUp objSetUp = new FragmentSetUp();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(root, objSetUp)
                    .addToBackStack(null)
                    .commit();



        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginnext,container,false);
      //  initBackgroundImage(view);
        login = (Button) view.findViewById(R.id.buttonlogin);
        login.setOnClickListener(loginLisenner);
        return view;



    }

    private void initBackgroundImage(View view){
        int weight = 1250;
        int height = 2000;
        ImageView imageLogo = (ImageView) view.findViewById(R.id.logonesoft);
        Glide.with(this).load(R.drawable.logoonesoft).into(imageLogo);
        ImageView imageBackground = (ImageView) view.findViewById(R.id.kuernieschematransparent);
        Glide.with(this).load(R.drawable.kurenieschema_transparent).into(imageBackground);
        final RelativeLayout main = (RelativeLayout) view.findViewById(R.id.root);
        /*Glide.with(this).load(R.drawable.bcglogin).asBitmap().into(new SimpleTarget<Bitmap>(weight,height)
        {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                Drawable drawable = new BitmapDrawable(bitmap);
                main.setBackground(drawable);
            }
        });*/
    }

}
