package com.onesoft.devicecontrol;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import com.onesoft.devicecontrol.click.ExternalOnClickLisener;
import com.onesoft.devicecontrol.enums.ChangeEnum;
import com.onesoft.devicecontrol.paramloop.AllParamsLoop;
import com.onesoft.fero.answer.ParamFeroAllAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKotolAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKurenieAnswer;
import com.onesoft.fero.answer.ParamFeroChangeTuvAnswer;
import com.onesoft.fero.answer.enums.AnswerStatusEnum;
import com.onesoft.param.to.ParametrFeroKotolTO;
import com.onesoft.param.to.ParametrFeroKurenieTO;
import com.onesoft.param.to.ParametrFeroVodaTO;
import com.onesoft.services.SmrekService;

/**
 * Created by exosj on 16.10.2016.
 */

public class Home extends AppCompatActivity implements View.OnClickListener, ResultParamReciever.Receiver {
    private ResultParamReciever mReceiver;
    //Odpoved na volanie api uvodna obrazovka nacitanie dat
    ParamFeroAllAnswer answerAllParam = new ParamFeroAllAnswer();
    //Odpoved na zmenu
    ParamFeroChangeKurenieAnswer answerKurenieChange = new ParamFeroChangeKurenieAnswer();
    ParamFeroChangeKotolAnswer answerKotolChange = new ParamFeroChangeKotolAnswer();
    ParamFeroChangeTuvAnswer answerTuvChange = new ParamFeroChangeTuvAnswer();
    //Click
    ExternalOnClickLisener clickAll;
    //Inicializacia transfer objekov pre jednotlive  layouty kotol kurenie voda
    ParametrFeroKotolTO kotolBase = new ParametrFeroKotolTO();
    ParametrFeroKurenieTO kurenieBase = new ParametrFeroKurenieTO();
    ParametrFeroVodaTO vodaBase = new ParametrFeroVodaTO();
    //Servise pre volanie api
    SmrekService smrekService;
    ChangeKurenie changeKurenie;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) throws SQLException {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.layautfirstpage);
        //setup images


        mReceiver = new ResultParamReciever(new Handler());
        mReceiver.setReceiver(this);
        intent = new Intent(Intent.ACTION_SYNC, null, this, AllParamsLoop.class);
        intent.putExtra("receiver", mReceiver);
        //Loop v backgrounde pre  all parametre

        //handler.post(getParamAll);
        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.root);
        // Get the application context
        mContext = getApplicationContext();
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        mProgressBar.setMax(maxValue);
        mProgressBar.setProgress(curValue);
        cislovac = (TextView)findViewById(R.id.cislovac);
        handlerProgresss = new Handler();
        handlerProgresss.post(UpdateProgess);
        //incializovanie potrebnych tried
        smrekService = new SmrekService();
        changeKurenie = new ChangeKurenie();
        //Nastavenie info vei k parametrom vysvetlivky
        otazkaOkruhy = (TextView)findViewById(R.id.questionhead);
        //Change polia
        changeKotolIcon=(ImageView)findViewById(R.id.changekotol);
        changeKotolIcon.setOnClickListener(this);
        changeKurenieIcon = (ImageView)findViewById(R.id.changekurenieicon);
        changeKurenieIcon.setOnClickListener(this);
        changeTuv = (ImageView)findViewById(R.id.changeTuv);
        changeTuv.setOnClickListener(this);

        kurenieQuestionIcon= (ImageView)findViewById(R.id.changekurenieotazka);
        kurenieQuestionIcon.setOnClickListener(this);

        kotolinfo= (ImageView)findViewById(R.id.kotolinfoicon);
        kotolinfo.setOnClickListener(this);

        vodainfo= (ImageView)findViewById(R.id.vodainfoicon);
        vodainfo.setOnClickListener(this);

        kurenieLoad = (LinearLayout) findViewById(R.id.clicable_parametre_uk_layout);
        kurenieprogram = (LinearLayout)findViewById(R.id.kurenieprogram);
        kurenieicony = (LinearLayout)findViewById(R.id.kurenieicony);
        kurenieInvis = (LinearLayout)findViewById(R.id.kurenieshowlayout);
        kotolshowlayout = (LinearLayout)findViewById(R.id.kotolshowlayout);
        kotolinvis = (LinearLayout)findViewById(R.id.kotolinvis);
        kotolicony = (LinearLayout)findViewById(R.id.kotolicony);
        tuvlayoutshow = (LinearLayout)findViewById(R.id.tuvlayoutshow);
        tuvinvis = (LinearLayout)findViewById(R.id.tuvinvis);
        tuvicony = (LinearLayout)findViewById(R.id.tuvicony);
        tuvcasprogram = (LinearLayout)findViewById(R.id.tuvcasprogram);


    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case AllParamsLoop.STATUS_RUNNING:

                break;
            case AllParamsLoop.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                ParamFeroAllAnswer results = resultData.getParcelable("result");
                initBackgroundImage();
                /* Update ListView with result */
                updateUi(results);
                break;
            case AllParamsLoop.STATUS_ERROR:
                initBackgroundImageDataLoad();
                handlerProgresss.removeCallbacks(UpdateProgess);
//                String error = resultData.getString(Intent.EXTRA_TEXT);
                String error1 = getString(R.string.errorconnection);
                Toast.makeText(this, error1, Toast.LENGTH_LONG).show();
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
        tuvcasprogram.setVisibility(View.VISIBLE);

        ImageView imagebacground = (ImageView) findViewById(R.id.kuernieschematransparent);
        Glide.with(this).load(R.drawable.kurenieschema_transparent).into(imagebacground);
        ImageView imageTeplomerKurenieZiadana = (ImageView) findViewById(R.id.teplomerimagekurenieziadana);
        Glide.with(this).load(R.drawable.temperatureicon).into(imageTeplomerKurenieZiadana);

        int weight = 1250;
        int height = 2000;
        final RelativeLayout main = (RelativeLayout) findViewById(R.id.root);
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
        tuvcasprogram.setVisibility(View.INVISIBLE);
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
                startService(intent);
            }
                handler.postDelayed(getParamAll, timeLoop);
            }});

    public void updateUi(ParamFeroAllAnswer request){
        //Inicializovanie TextView okien v  layoute
        //Kurenie TextView polia  pre hodnoty
        TextView teploKurenieTZiadana = (TextView) findViewById(R.id.kurenieTZiadana);
        TextView teploKurenieTPriestor = (TextView) findViewById(R.id.kurenieTPriestor);
        TextView teploKurenieTRadiator = (TextView) findViewById(R.id.kurenieTRadiator);
        TextView teploKurenieCasProgram = (TextView) findViewById(R.id.kurenieCasProgram);

        ImageView kurenieStatus = (ImageView) findViewById(R.id.kurenieStatus);//android:state_checked="true"
        //Kotol TextView polia  pre hodnoty
        TextView teploKotolTZiadana = (TextView) findViewById(R.id.kotolTZiadana);
        TextView teploKotolTMerana = (TextView) findViewById(R.id.kotolTMerana);
        TextView teploKotolTVonkajsia = (TextView) findViewById(R.id.kotolTVonkajsia);
        ImageView kotolStatus = (ImageView) findViewById(R.id.kotolStatus);//android:state_checked="true"
        //Tepla voda polia pre hodnoty
        TextView teploVodaTZiadana = (TextView) findViewById(R.id.vodaTZiadana);
        TextView teploVodaMerana = (TextView) findViewById(R.id.vodaMerana);
        TextView teploVodaCasProgram = (TextView) findViewById(R.id.vodaCasProgram);
        ImageView vodaStatus = (ImageView) findViewById(R.id.vodastatus);//android:state_checked="true"
        //Kurenie Fero Naplnenie hodnout do poly pre zobrazenie
        if (request.getParamFeroAll().getFeroKurenie() != null) {
            kurenieBase = request.getParamFeroAll().getFeroKurenie();
            setKurenieBase(kurenieBase);
            if (kurenieBase.getChod_reg_uk1() == 1) {

                kurenieStatus.setImageResource(R.drawable.swichbuttonkureniechod);
            } else {

               kurenieStatus.setImageResource(R.drawable.swichbuttonkureniestop);
            }
            teploKurenieTZiadana.setText(kurenieBase.getTzmi_ctz_uk1() + "°C");
            teploKurenieTPriestor.setText(String.format("%.1f", kurenieBase.getTref_uk1()) + "°C");
            teploKurenieTRadiator.setText(String.format("%.1f", kurenieBase.getTuk1v()) + "°C");
            teploKurenieCasProgram.setText(String.format("%2d", kurenieBase.getCprog_uk1()));
        } else
            Toast.makeText(Home.this, "Nenacitane parametre z db", Toast.LENGTH_SHORT).show();

        //Kotol Fero Naplnenie hodnout do poly pre zobrazenie
        if (request.getParamFeroAll().getFeroKotol() != null) {
            kotolBase = request.getParamFeroAll().getFeroKotol();
            if (kotolBase.getN_zia_kot() == 1) {
                kotolStatus.setImageResource(R.drawable.swichbuttonkurenieeqi);
            } else {
                kotolStatus.setImageResource(R.drawable.swichbuttonkureniesnt);
            }
            //teploKotolStav.setText(String.format("%3d",kotolBase.getN_zia_kot()));
            teploKotolTZiadana.setText(String.format("%.1f", kotolBase.getTzine()) + "°C");
            teploKotolTMerana.setText(String.format("%.1f", kotolBase.getTkotv()) + "°C");
            teploKotolTVonkajsia.setText(String.format("%.1f", kotolBase.getTvon_uk1()) + "°C");
        } else
            Toast.makeText(Home.this, "Nenacitane parametre z db", Toast.LENGTH_SHORT).show();

        //Voda Fero Naplnenie hodnout do poly pre zobrazenie
        if (request.getParamFeroAll().getFeroVoda() != null) {
            vodaBase = request.getParamFeroAll().getFeroVoda();
            if (vodaBase.getChod_reg_tuv() == 1) {
                vodaStatus.setImageResource(R.drawable.swichbuttonkureniechod);
            } else {
                vodaStatus.setImageResource(R.drawable.swichbuttonkureniestop);
            }
            //teploVodaChodStop.setText(String.format("%2d",vodaBase.getChod_reg_tuv()));
            teploVodaTZiadana.setText(vodaBase.getTzmi_ctz_tuv() + "°C");
            teploVodaMerana.setText(String.format("%.1f", vodaBase.getTtuv_1()) + "°C");
            teploVodaCasProgram.setText(String.format("%2d", vodaBase.getCprog_tuv()));

        } else
            Toast.makeText(Home.this, "Nenacitane parametre z db", Toast.LENGTH_SHORT).show();
    }
    public ParametrFeroKurenieTO getKurenieBase() {
        return kurenieBase;
    }

    public void setKurenieBase(ParametrFeroKurenieTO kurenieBase) {
        this.kurenieBase = kurenieBase;
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
                        Integer changeCasProgrKur = kurenieBase.getCprog_uk1();
                        Integer changeZiadPriesKur = kurenieBase.getTzmi_ctz_uk1();
                        try {
                            changeCasProgrKur = Integer.parseInt(changeCasProgramKurenie.getText().toString());
                        } catch (Exception e) {
                            changeCasProgramKurenie.setText(String.format("%2d", kurenieBase.getCprog_uk1()));
                        }
                        try {
                            changeZiadPriesKur = Integer.parseInt(changeZiadanaPriestoruKurenie.getText().toString());
                        } catch (Exception e) {
                            changeZiadanaPriestoruKurenie.setText(String.format("%2d", kurenieBase.getTzmi_ctz_uk1()) + "°");
                        }

                        if (kurenieButtonStavChange.isChecked())
                            setChodStop(1);
                        else   setChodStop(0);
                        if (!(changeCasProgrKur == kurenieBase.getCprog_uk1() && changeZiadPriesKur == kurenieBase.getTzmi_ctz_uk1() && chodStop == kurenieBase.getChod_reg_uk1())) {
                            answerKurenieChange = changeKurenie.changeKurenienow(changeCasProgrKur, changeZiadPriesKur, chodStop, kurenieBase.getCtz_uk1());
                            if (answerKurenieChange.equals(AnswerStatusEnum.OK)) {
                                String message = getString(R.string.messageZmena);
                                Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                            }
                            String message = getString(R.string.messageZmena);
                            Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                            mPopupWindow.dismiss();
                        } // Dismiss the popup window
                        else {
                            String message = getString(R.string.messageZmenaNoChange);
                            Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events
            }
            case R.id.changekotol: {
                //Nastavim zmenove polia zobrazenie
                changeTempKurenieChangeLayout = (LinearLayout) customView.findViewById(R.id.changeTempKurenieChangeLayout);
                zmenaHead = (TextView) customView.findViewById(R.id.zmenaHead);
                teploChangeCasProgramKurenie = (TextView) customView.findViewById(R.id.changeCasProgramKurenie);
                changeTempKureniePriestor = (TextView) customView.findViewById(R.id.changeTempKureniePriestor);
                changeTempKurenie = (TextView) customView.findViewById(R.id.changeTempKurenie);
                teploChangeZiadanaPriestoruKurenie = (TextView) customView.findViewById(R.id.changeZiadanaPriestoruKurenie);
                kurenieButtonStavChange = (ToggleButton) customView.findViewById(R.id.kurenieChodStopChange);
                secondRowsecond = (LinearLayout) customView.findViewById(R.id.secondRowsecond);
                secondRowfirst = (LinearLayout) customView.findViewById(R.id.secondRowfirst);
                //Nastavim zmenove polia
                changeCasProgramKurenie = (EditText) customView.findViewById(R.id.changeTempKurenieChange);
                changeZiadanaPriestoruKurenie = (EditText) customView.findViewById(R.id.changeTempKureniePriestorChange);
                //Naplnim zmenove polia
                setUpChangViews(customView,changeEnum.KOTOL);
                closeButton = (Button) customView.findViewById(R.id.ib_close);
                zmenaButton = (Button) customView.findViewById(R.id.acceptchange);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });
                kurenieButtonStavChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (kurenieButtonStavChange.isChecked()) {
                            changeTempKurenieChangeLayout.setVisibility(customView.VISIBLE);
                            setChodStop(0);
                        }
                        else {
                            changeTempKurenieChangeLayout.setVisibility(customView.GONE);
                            setChodStop(1);
                        }
                    }
                });
                zmenaButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (kurenieButtonStavChange.isChecked())
                            setChodStop(0);
                        else   setChodStop(1);
                        Float changeZiadPriesKur = kotolBase.getTzine();
                        Integer changeCasProgrKurInt = Math.round(changeZiadPriesKur);
                        Integer status = kotolBase.getN_zia_kot();
                        try {
                            changeCasProgrKurInt = Integer.parseInt(changeCasProgramKurenie.getText().toString());
                        } catch (Exception e) {
                            changeCasProgramKurenie.setText(String.format("%.1f", kotolBase.getTzine()));
                        }
                        if (!(changeCasProgrKurInt == Math.round(kotolBase.getTzine()) && getChodStop() == kotolBase.getN_zia_kot())) {
                            answerKotolChange = changeKurenie.changeKotolnow(changeCasProgrKurInt, getChodStop(),status);
                            if (answerKurenieChange.equals(AnswerStatusEnum.OK)) {
                                String message = getString(R.string.messageZmena);
                                Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                            }
                            String message = getString(R.string.messageZmena);
                            Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                            mPopupWindow.dismiss();
                        } // Dismiss the popup window
                        else {
                            String message = getString(R.string.messageZmenaNoChange);
                            Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events
            }
            case R.id.changeTuv: {
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
                setUpChangViews(customView,changeEnum.VODA);
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
                        Integer changeCasProgrKur = vodaBase.getCprog_tuv();
                        Integer changeZiadPriesKur = vodaBase.getTzmi_ctz_tuv();
                        try {
                            changeCasProgrKur = Integer.parseInt(changeCasProgramKurenie.getText().toString());
                        } catch (Exception e) {
                            changeCasProgramKurenie.setText(String.format("%2d", vodaBase.getCprog_tuv()));
                        }
                        try {
                            changeZiadPriesKur = Integer.parseInt(changeZiadanaPriestoruKurenie.getText().toString());
                        } catch (Exception e) {
                            changeZiadanaPriestoruKurenie.setText(String.format("%2d", vodaBase.getTzmi_ctz_tuv()) + "°");
                        }

                        if (kurenieButtonStavChange.isChecked())
                            setChodStop(1);
                        else setChodStop(0);
                        if (!(changeCasProgrKur == vodaBase.getCprog_tuv() && changeZiadPriesKur == vodaBase.getTzmi_ctz_tuv() && getChodStop() == vodaBase.getChod_reg_tuv())) {
                            answerTuvChange = changeKurenie.changeTuvnow(changeCasProgrKur, changeZiadPriesKur, getChodStop(), vodaBase.getCtz_tuv());
                            if (answerTuvChange.equals(AnswerStatusEnum.OK)) {
                                String message = getString(R.string.messageZmena);
                                Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                            }
                            String message = getString(R.string.messageZmena);
                            Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
                            mPopupWindow.dismiss();
                        } // Dismiss the popup window
                        else {
                            String message = getString(R.string.messageZmenaNoChange);
                            Toast.makeText(Home.this, message, Toast.LENGTH_SHORT).show();
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
                ScrollView   mScrollView = (ScrollView) customView.findViewById(R.id.SCROLLER_ID);
                mTextStatus.append(getString(R.string.kurenie_info) +
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
                mTextStatus.append(getString(R.string.kotol_info) +
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
               teploChangeCasProgramKurenie.setText(String.format("%2d", kurenieBase.getCprog_uk1()));
               teploChangeZiadanaPriestoruKurenie.setText(String.format("%2d", kurenieBase.getTzmi_ctz_uk1()) + "°");
               if (kurenieBase.getChod_reg_uk1() == 1)
                   kurenieButtonStavChange.setChecked(true);
               else
                   kurenieButtonStavChange.setChecked(false);
               break;
           }
           case KOTOL: {
               kurenieButtonStavChange.setBackgroundResource(R.drawable.checkkotol);
               zmenaHead.setText(R.string.kotolChangeTextViewHead);
               changeTempKurenie.setText(R.string.kotolChangeTextView);
               teploChangeCasProgramKurenie.setText(String.format("%.1f", kotolBase.getTzine()));
               secondRowsecond.setVisibility(customView.INVISIBLE);
               secondRowfirst.setVisibility(customView.INVISIBLE);
               if (kotolBase.getN_zia_kot() == 0) {
                   kurenieButtonStavChange.setChecked(true);
               }
               else {
                   kurenieButtonStavChange.setChecked(false);
                   changeTempKurenieChangeLayout.setVisibility(customView.GONE);
               }
               break;
           }
           case VODA: {
               zmenaHead.setText(R.string.vodaChangeTextViewHead);
               teploChangeCasProgramKurenie.setText(String.format("%2d", vodaBase.getCprog_tuv()));
               teploChangeZiadanaPriestoruKurenie.setText(String.format("%2d", vodaBase.getTzmi_ctz_tuv()) + "°");
               if (vodaBase.getChod_reg_tuv() == 1)
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

