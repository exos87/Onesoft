package com.onesoft.devicecontrol;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


public class FirstLoginActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Button login,register;
    private View.OnClickListener loginLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            String message = getString(R.string.message);
//            Toast.makeText(FirstLoginActivity.this
//                    ,message,Toast.LENGTH_SHORT).show();

            FragmentLogin objFragmentLogin = new FragmentLogin();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.root,objFragmentLogin)
                    .addToBackStack(null)
                    .commit();

        }
    };
    private View.OnClickListener registerLisenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            String message = getString(R.string.message);
//            Toast.makeText(FirstLoginActivity.this
//                    ,message,Toast.LENGTH_SHORT).show();
            FragmentRegister objFragmentLoginFirst = new FragmentRegister();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.root,objFragmentLoginFirst)
                    .addToBackStack(null)
                    .commit();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = (Button) findViewById(R.id.buttonlogin);
        login.setOnClickListener(loginLisenner);

       /* register = (Button) findViewById(R.id.buttonregister);
        register.setOnClickListener(registerLisenner);*/

      //  initBackgroundImage();

        String message = getString(R.string.message);
        Toast.makeText(FirstLoginActivity.this,message,Toast.LENGTH_SHORT).show();

    }

    private void initBackgroundImage(){
        ImageView imageLogo = (ImageView) findViewById(R.id.logonesoft);
        Glide.with(this).load(R.drawable.logoonesoft).into(imageLogo);
        final LinearLayout main = (LinearLayout) findViewById(R.id.loginbckmain);
         int weight = 1250;
        int height = 2000;
        Glide.with(this).load(R.drawable.bcglogin).asBitmap().into(new SimpleTarget<Bitmap>(weight, height)
        {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                Drawable drawable = new BitmapDrawable(bitmap);
                main.setBackground(drawable);
            }
        });

    }

}
