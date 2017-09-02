package com.onesoft.devicecontrol;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.onesoft.devicecontrol.changes.ChangeKurenie;
import com.onesoft.devicecontrol.changes.ChangeKurenieApartmanSmrek;
import com.onesoft.devicecontrol.click.ExternalOnClickLisener;
import com.onesoft.devicecontrol.enums.ChangeEnum;
import com.onesoft.devicecontrol.paramloop.SmrekApartmanLoop;
import com.onesoft.fero.answer.enums.AnswerStatusEnum;
import com.onesoft.param.smrek.to.KurenieSmrekTO;
import com.onesoft.services.SmrekServiceApartman;
import com.onesoft.smrek.answer.GetKurenieAnswer;
import com.onesoft.smrek.answer.ParamChangeKurenieAnswer;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * Created by exosj on 16.10.2016.
 */

public class FragmentApartman extends Fragment implements View.OnClickListener, ResultParamReciever.Receiver {
      public FragmentApartman(){

    }
    int device;
    String key = "device";
    private ResultParamReciever mReceiver;
    KurenieSmrekTO kurenieSmrek;
    //Click
    ExternalOnClickLisener clickAll;

    //Servise pre volanie api
    SmrekServiceApartman smrekServiceApartman;
    ChangeKurenie changeKurenie;
    ChangeKurenieApartmanSmrek changeKurenieApartmanSmrek;
    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    // Create the Handler object (on the main thread by default) Service pre job schedulin
    Handler handler = new Handler();
    Handler handlerProgresss;
    int timeLoop = 30000;
    private RelativeLayout mRelativeLayout;
    ImageView changeKurenieIcon,changeKotolIcon;
    ImageView kotolinfo;
    ImageView kurenieQuestionIcon;
    ImageView vodainfo;
    private PopupWindow mPopupWindow;
    private Context mContext;
    TextView otazkaOkruhy , cislovac;
    //Change
    TextView teploChangeCasProgramKurenie,changeTempKureniePriestor,changeTempKurenie;
    TextView teploChangeZiadanaPriestoruKurenie,zmenaHead;
    ToggleButton kurenieButtonStavChange = null;
    View customView,customImageView;
    LayoutInflater inflater,inflaterImage;
    Button closeButton,zmenaButton;
    EditText changeCasProgramKurenie,changeZiadanaPriestoruKurenie;
    LinearLayout secondRowfirst,secondRowsecond,changeTempKurenieChangeLayout;

    ImageView changeTuv;
    int maxValue=30;
    int curValue=0;
    private static final String TAG1 = "Kurenie vo vnutry su ";
    Intent intent;
    ChangeEnum changeEnum;
    Integer chodStop = null;
    LinearLayout kurenieInvis,kurenieicony,kurenieprogram;
    LinearLayout kurenieLoad;
    LinearLayout kotolshowlayout,kotolinvis,kotolicony;
    LinearLayout tuvlayoutshow,tuvinvis,tuvicony,tuvcasprogram;
    LinearLayout bcklayoutsetup;
    LinearLayout firstRow,secondRow,threerow;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        view = inflater.inflate(R.layout.apartmanmainpage,container,false);
        Bundle bundle = this.getArguments();
        device = bundle.getInt(key);
        mReceiver = new ResultParamReciever(new Handler());
        mReceiver.setReceiver(this);
        intent = new Intent(Intent.ACTION_SYNC, null, view.getContext(), SmrekApartmanLoop.class);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("cisloApartmanu",device);
        kurenieSmrek = new KurenieSmrekTO();
        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.root);
        // Get the application context
        mContext = getActivity().getApplicationContext();
              mProgressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        mProgressBar.setMax(maxValue);
        mProgressBar.setProgress(curValue);
        cislovac = (TextView) view.findViewById(R.id.cislovac);
        handlerProgresss = new Handler();
        handlerProgresss.post(UpdateProgess);
        //incializovanie potrebnych tried
        smrekServiceApartman = new SmrekServiceApartman();
        changeKurenie = new ChangeKurenie();
        changeKurenieApartmanSmrek = new ChangeKurenieApartmanSmrek();
        //Nastavenie info vei k parametrom vysvetlivky
        otazkaOkruhy = (TextView) view.findViewById(R.id.questionhead);
        //Change polia
        changeKotolIcon=(ImageView) view.findViewById(R.id.changekotol);
        changeKotolIcon.setOnClickListener(this);
        changeKurenieIcon = (ImageView) view.findViewById(R.id.changekurenieicon);
        changeKurenieIcon.setOnClickListener(this);
        changeTuv = (ImageView) view.findViewById(R.id.changeTuv);
        changeTuv.setOnClickListener(this);

        kurenieQuestionIcon= (ImageView) view.findViewById(R.id.changekurenieotazka);
        kurenieQuestionIcon.setOnClickListener(this);

        kotolinfo= (ImageView) view.findViewById(R.id.kotolinfoicon);
        kotolinfo.setOnClickListener(this);

        vodainfo= (ImageView) view.findViewById(R.id.vodainfoicon);
        vodainfo.setOnClickListener(this);

        kurenieLoad = (LinearLayout) view.findViewById(R.id.clicable_parametre_uk_layout);
        kurenieprogram = (LinearLayout) view.findViewById(R.id.kurenieApartmanCas);
        kurenieicony = (LinearLayout) view.findViewById(R.id.kurenieicony);
        kurenieInvis = (LinearLayout) view.findViewById(R.id.kurenieshowlayout);
        kotolshowlayout = (LinearLayout) view.findViewById(R.id.kotolshowlayout);
        kotolinvis = (LinearLayout) view.findViewById(R.id.kotolinvis);
        kotolicony = (LinearLayout) view.findViewById(R.id.kotolicony);
        tuvlayoutshow = (LinearLayout) view.findViewById(R.id.tuvlayoutshow);
        tuvinvis = (LinearLayout) view.findViewById(R.id.tuvinvis);
        tuvicony = (LinearLayout) view.findViewById(R.id.tuvicony);
      //  tuvcasprogram = (LinearLayout) view.findViewById(R.id.tuvcasprogram);
        return view;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case SmrekApartmanLoop.STATUS_RUNNING:

                break;
            case SmrekApartmanLoop.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                GetKurenieAnswer results = resultData.getParcelable("result");
                initBackgroundImage();
                /* Update ListView with result */
               updateUi(results);
                break;
            case SmrekApartmanLoop.STATUS_ERROR:
                initBackgroundImageDataLoad();
                handlerProgresss.removeCallbacks(UpdateProgess);

                String error1 = getString(R.string.errorconnection);
                Toast.makeText(view.getContext(), error1, Toast.LENGTH_LONG).show();
                break;
        }
    }
    //Sluzi na progress bar do buducna preprogramovat na  funkciu pre zobrazovanie delay v hendleroch
    Runnable UpdateProgess=new Runnable(){
        public void run(){
            if (maxValue != curValue){
                curValue++;
                mProgressBar.setProgress(curValue);
                cislovac.setText(String.format("%2d",curValue));
            }
            else
            {
                curValue = 0;
                mProgressBar.setProgress(0);
            }
            //delay 1s before next call
            handler.postDelayed(this, 1000);

        }
    };
    private void initBackgroundImage() {

        kurenieInvis.setVisibility(View.VISIBLE);
        kurenieicony.setVisibility(View.VISIBLE);
        kurenieprogram.setVisibility(View.VISIBLE);
        kotolinvis.setVisibility(View.VISIBLE);
        kotolicony.setVisibility(View.VISIBLE);
        tuvinvis.setVisibility(View.VISIBLE);
        tuvicony.setVisibility(View.VISIBLE);



        int weight = 1250;
        int height = 2000;
        final RelativeLayout main = (RelativeLayout) view.findViewById(R.id.root);
        Glide.with(this).load(R.drawable.bcglogin).asBitmap().into(new SimpleTarget<Bitmap>(weight, height)
        {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                Drawable drawable = new BitmapDrawable(bitmap);
                main.setBackground(drawable);
            }
        });
        kurenieLoad.setBackgroundDrawable( getResources().getDrawable(R.drawable.vnutrodat_shape) );
        kotolshowlayout.setBackgroundDrawable( getResources().getDrawable(R.drawable.vnutrodat_shape) );
        tuvlayoutshow.setBackgroundDrawable( getResources().getDrawable(R.drawable.vnutrodat_shape) );

    }
    private void initBackgroundImageDataLoad() {
        kurenieInvis.setVisibility(View.INVISIBLE);
        kurenieicony.setVisibility(View.GONE);
        kurenieprogram.setVisibility(View.INVISIBLE);
        kotolinvis.setVisibility(View.INVISIBLE);
        kotolicony.setVisibility(View.GONE);
        tuvinvis.setVisibility(View.INVISIBLE);
        tuvicony.setVisibility(View.GONE);
        //tuvcasprogram.setVisibility(View.INVISIBLE);
        int weight = 1250;
        int height = 2000;
        Glide.with(this).load(R.drawable.nointernetconnection).asBitmap().into(new SimpleTarget<Bitmap>(weight, height)
        {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                Drawable drawable = new BitmapDrawable(bitmap);
                kurenieLoad.setBackground(drawable);
                kotolshowlayout.setBackground(drawable);
                tuvlayoutshow.setBackground(drawable);
            }
        });
    }
    // Refresuje a prevolava databazu kazdych 30 s
    Thread getParamAll = new Thread(new Runnable() {
        @Override
        public void run() {
            {
                getActivity().startService(intent);
            }
            handler.postDelayed(getParamAll, timeLoop);
         }});

    public void updateUi(GetKurenieAnswer request){
        TextView apartmanKurenieCisloApartmanu = (TextView) view.findViewById(R.id.kurenieApartmanCislo);
        TextView apartmanKureniePodlahovka = (TextView) view.findViewById(R.id.podlahovkaApartmanKurenie);
        TextView apartmanKurenieVystup = (TextView) view.findViewById(R.id.apartmanKurenieVystup);
        TextView apartmanKureniePriestor = (TextView) view.findViewById(R.id.kurenieApartmanPriestor);
        TextView apartmanKureniePrepinac = (TextView) view.findViewById(R.id.apartmanKureniePrepinac);
        TextView apartmanKurenieVonkajsia = (TextView) view.findViewById(R.id.apartmanKurenieVonkajsia);
        TextView apartmanKurenieZiadana = (TextView) view.findViewById(R.id.apartmanKurenieZiadana);
        TextView apartmanKurenieProgram = (TextView) view.findViewById(R.id.kurenieApartmanProgram);
        ImageView kurenieStatus = (ImageView) view.findViewById(R.id.kurenieStatus);//android:state_checked="true"
        for (KurenieSmrekTO param : request.getKurenieSmrekList()){
            kurenieSmrek = param;
        }
        //Kurenie Fero Naplnenie hodnout do poly pre zobrazenie
        if (kurenieSmrek != null) {
             if (kurenieSmrek.getChod_reg_uk1() == 1) {
                kurenieStatus.setImageResource(R.drawable.swichbuttonkureniechod);
            } else {
                kurenieStatus.setImageResource(R.drawable.swichbuttonkureniestop);
            }
            apartmanKurenieCisloApartmanu.setText(String.format("%2d", device));
            apartmanKureniePodlahovka.setText(String.format("%.1f", kurenieSmrek.getTzuk1()) + "°C");
            apartmanKurenieVystup.setText(String.format("%.1f", kurenieSmrek.getTuk1v()) + "°C");
            apartmanKureniePriestor.setText(String.format("%.1f", kurenieSmrek.getTuk1r()) + "°C");
            apartmanKureniePrepinac.setText(String.format("%.1f", kurenieSmrek.getTuk1k()) + "°C");
            apartmanKurenieVonkajsia.setText(String.format("%2d", kurenieSmrek.getT_vonk_disp()) + "°C");
            apartmanKurenieZiadana.setText(String.format("%2d", kurenieSmrek.getTzmi_ctz_uk1()) + "°C");
            apartmanKurenieProgram.setText(String.format("%2d", kurenieSmrek.getCprog_uk1()));
        } else
            Toast.makeText(view.getContext(), "Nenacitane parametre z db", Toast.LENGTH_SHORT).show();
    }

    private void initBackgroundImageChange(View view) {
        bcklayoutsetup = (LinearLayout)view.findViewById(R.id.bcklayoutsetup);
        bcklayoutsetup.setBackgroundDrawable( getResources().getDrawable(R.drawable.buttonlogin_shape) );
        firstRow  = (LinearLayout)view.findViewById(R.id.firstRow);
        firstRow.setBackgroundDrawable( getResources().getDrawable(R.drawable.vyplsnprezmeny) );
        secondRow = (LinearLayout)view.findViewById(R.id.secondRow);
        secondRow.setBackgroundDrawable( getResources().getDrawable(R.drawable.vyplsnprezmeny) );
        threerow = (LinearLayout)view.findViewById(R.id.threerow);
        threerow.setBackgroundDrawable( getResources().getDrawable(R.drawable.vyplsnprezmeny) );
    }
    @Override
    public void onClick(View view) {
        // Initialize a new instance of LayoutInflater service
        inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        //Zmenove polia Kurenie
        // Inflate the custom layout/view
        if (view.getId() == R.id.changekurenieicon || view.getId() == R.id.changekotol || view.getId() == R.id.changeTuv ){
            customView = inflater.inflate(R.layout.custom_layout, null);
            initBackgroundImageChange(customView);
        }
        else{
            customView = inflater.inflate(R.layout.question, null);
        }

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(
                customView,
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
        );
        mPopupWindow.setFocusable(true);
        mPopupWindow.update();
        mPopupWindow.getContentView();
        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }
        switch (view.getId()) {
            //Akcia pre tlacitko zmena spracuje zmenove parametre a posle na server
            case R.id.changekurenieicon: {
                //Nastavim zmenove polia zobrazenie
                changeTempKurenie = (TextView) customView.findViewById(R.id.changeTempKurenie);
                zmenaHead = (TextView) customView.findViewById(R.id.zmenaHead);
                teploChangeCasProgramKurenie = (TextView) customView.findViewById(R.id.changeCasProgramKurenie);
                teploChangeZiadanaPriestoruKurenie = (TextView) customView.findViewById(R.id.changeZiadanaPriestoruKurenie);
                kurenieButtonStavChange = (ToggleButton) customView.findViewById(R.id.kurenieChodStopChange);
                //Nastavim zmenove polia
                changeCasProgramKurenie = (EditText) customView.findViewById(R.id.changeTempKurenieChange);
                changeZiadanaPriestoruKurenie = (EditText) customView.findViewById(R.id.changeTempKureniePriestorChange);
                //Naplnim zmenove polia
                setUpChangViews(customView,changeEnum.KURENIE);
                closeButton = (Button) customView.findViewById(R.id.ib_close);
                zmenaButton = (Button) customView.findViewById(R.id.acceptchange);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                zmenaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer changeCasProgrKur = kurenieSmrek.getCprog_uk1();
                        Integer changeZiadPriesKur = kurenieSmrek.getTzmi_ctz_uk1();
                        try {
                            changeCasProgrKur = Integer.parseInt(changeCasProgramKurenie.getText().toString());
                        } catch (Exception e) {
                            changeCasProgramKurenie.setText(String.format("%2d", kurenieSmrek.getCprog_uk1()));
                        }
                        try {
                            changeZiadPriesKur = Integer.parseInt(changeZiadanaPriestoruKurenie.getText().toString());
                        } catch (Exception e) {
                            changeZiadanaPriestoruKurenie.setText(String.format("%2d", kurenieSmrek.getTzmi_ctz_uk1()) + "°");
                        }

                        if (kurenieButtonStavChange.isChecked())
                            setChodStop(1);
                        else   setChodStop(0);
                        if (!(changeCasProgrKur == kurenieSmrek.getCprog_uk1() && changeZiadPriesKur == kurenieSmrek.getTzmi_ctz_uk1() && chodStop == kurenieSmrek.getChod_reg_uk1())) {
                            ParamChangeKurenieAnswer answerKurenieChange = changeKurenieApartmanSmrek.changeKurenienow(changeCasProgrKur, changeZiadPriesKur, chodStop, kurenieSmrek.getCtz_uk1(),device);
                            if (answerKurenieChange.equals(AnswerStatusEnum.OK)) {
                                String message = getString(R.string.messageZmena);
                                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                            }
                            String message = getString(R.string.messageZmena);
                            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                            mPopupWindow.dismiss();
                        } // Dismiss the popup window
                        else {
                            String message = getString(R.string.messageZmenaNoChange);
                            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events
            }

            case R.id.changekurenieotazka: {
                TextView   mTextStatus = (TextView) customView.findViewById(R.id.TEXT_STATUS_ID);
                ScrollView mScrollView = (ScrollView) customView.findViewById(R.id.SCROLLER_ID);
                mTextStatus.append(getString(R.string.second_info_apartman) +
                        getString(R.string.kurenie_info_add));
                //Nastavenie head nadpisu v popup okne
                otazkaOkruhy = (TextView)customView.findViewById(R.id.questionhead);
                otazkaOkruhy.setText(R.string.kurenie_info_head);
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events kotolinfoicon
            }
            case R.id.kotolinfoicon: {
                TextView   mTextStatus = (TextView) customView.findViewById(R.id.TEXT_STATUS_ID);
                ScrollView   mScrollView = (ScrollView) customView.findViewById(R.id.SCROLLER_ID);
                mTextStatus.append(getString(R.string.second_info_apartman) +
                        getString(R.string.kotol_info_add));
                //Nastavenie head nadpisu v popup okne
                otazkaOkruhy = (TextView)customView.findViewById(R.id.questionhead);
                otazkaOkruhy.setText(R.string.kotol_info_head);
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
            }
            case R.id.vodainfoicon: {
                TextView   mTextStatus = (TextView) customView.findViewById(R.id.TEXT_STATUS_ID);
                ScrollView   mScrollView = (ScrollView) customView.findViewById(R.id.SCROLLER_ID);
                mTextStatus.append(getString(R.string.voda_info) +
                        getString(R.string.voda_info_add));
                //Nastavenie head nadpisu v popup okne
                otazkaOkruhy = (TextView)customView.findViewById(R.id.questionhead);
                otazkaOkruhy.setText(R.string.tuv_info_head);
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
            }
        }
    }
    public void setUpChangViews(View view , ChangeEnum change) {
        switch (change) {
            case KURENIE: {
                zmenaHead.setText(R.string.kurenieChangeTextViewHead);
                teploChangeCasProgramKurenie.setText(String.format("%2d", kurenieSmrek.getCprog_uk1()));
                teploChangeZiadanaPriestoruKurenie.setText(String.format("%2d", kurenieSmrek.getTzmi_ctz_uk1()) + "°");
                if (kurenieSmrek.getChod_reg_uk1() == 1)
                    kurenieButtonStavChange.setChecked(true);
                else
                    kurenieButtonStavChange.setChecked(false);
                break;
            }

        }
    }

    public Integer getChodStop() {
        return chodStop;
    }

    public void setChodStop(Integer chodStop) {
        this.chodStop = chodStop;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(getParamAll);

    }
    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(getParamAll);
    }
    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(getParamAll);
    }


}
