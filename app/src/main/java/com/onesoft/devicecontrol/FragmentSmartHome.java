package com.onesoft.devicecontrol;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.onesoft.devicecontrol.changes.ChangeKurenie;
import com.onesoft.devicecontrol.changes.ChangeKurenieApartmanSmrek;
import com.onesoft.devicecontrol.paramloop.SmrekApartmanLoop;
import com.onesoft.param.smart.home.to.PinTO;
import com.onesoft.param.smart.home.to.SmartHomeImageTo;
import com.onesoft.param.smart.home.to.SmartHomeTitleViewTO;
import com.onesoft.param.smrek.to.KurenieSmrekTO;
import com.onesoft.services.SmrekServiceApartman;
import com.onesoft.smrek.answer.GetKurenieAnswer;
import com.qozix.tileview.TileView;
import com.qozix.tileview.markers.MarkerLayout;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


/**
 * Created by exosj on 16.10.2016.
 */

public class FragmentSmartHome extends Fragment implements View.OnClickListener, ResultParamReciever.Receiver  {


    public FragmentSmartHome(){
    }
    private Context mContext;
    private PopupWindow mPopupWindow;
    int device;
    String key = "device";
    KurenieSmrekTO kurenieSmrek;
    //Servise pre volanie api
    SmrekServiceApartman smrekServiceApartman;
    ChangeKurenie changeKurenie;
    ChangeKurenieApartmanSmrek changeKurenieApartmanSmrek;
    SmartHomeImageTo smartimages;
    // Create the Handler object (on the main thread by default) Service pre job schedulin
    Handler handler = new Handler();
    int timeLoop = 30000;
    private RelativeLayout mRelativeLayout;
    //Change
    LayoutInflater inflater;
    Button pages;
    private static final String TAG1 = "Kurenie vo vnutry su ";
    Intent intent;
    LinearLayout titleview;
    LinearLayout kurenieLoad,layoutimagemap,kurenieikonkalayout;
    View view, customView;
    ImageView kurenieikonka;
    TileView tileView;
    private ResultParamReciever mReceiver;
    GetKurenieAnswer results;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        view = inflater.inflate(R.layout.housefirst,container,false);

        init(view);

        return view;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case SmrekApartmanLoop.STATUS_RUNNING:

                break;
            case SmrekApartmanLoop.STATUS_FINISHED:
                results = resultData.getParcelable("result");
                setResults(results);
//                updateUi(results);
                break;
            case SmrekApartmanLoop.STATUS_ERROR:
                String error1 = getString(R.string.errorconnection);
                Toast.makeText(view.getContext(), error1, Toast.LENGTH_LONG).show();
                break;
        }
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

    @Override
      public void onClick(View view) {
        // Initialize a new instance of LayoutInflater service
        switch (view.getId()) {
            case R.id.kurenieikonka: {
                    if (kurenieikonkalayout.getTag().toString().equals("OFF")) {
                        kurenieikonkalayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.vyplsnprehodnotyon));
                        kurenieikonkalayout.setTag("ON");
                        if (titleview.getParent()!=null)
                        {
                            titleview.removeView(getTileView());
                            titleview.addView( getTileView( new SmartHomeTitleViewTO(getContext(),475,475,0,5,true,true,0,0,1,1,getPins())) );
                        }

                        titleview.setVisibility(View.VISIBLE);
                    } else {
                        if (kurenieikonkalayout.getTag().toString().equals("ON")) {
                            kurenieikonkalayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.vnutrodat_value_shape));
                            kurenieikonkalayout.setTag("OFF");
                            if (titleview.getParent()!=null)
                            {
                                titleview.removeView(getTileView());
                                titleview.addView( getTileView( new SmartHomeTitleViewTO(getContext(),475,475,0,5,true,true,0,0,1,1,null)) );
                            }
                            titleview.setVisibility(View.VISIBLE);
                        }
                    }

            }
        }
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

    public void init (View view){
        kurenieikonka= (ImageView) view.findViewById (R.id.kurenieikonka);
        kurenieikonka.setOnClickListener(this);
         setmContext( getActivity().getApplicationContext());
        //  setContentView( tileView );
        titleview = (LinearLayout) view.findViewById(R.id.titleview);
       titleview.addView( getTileView( new SmartHomeTitleViewTO(getContext(),475,475,0,5,true,true,0,0,1,1,null)) );
        titleview.setVisibility(View.VISIBLE);
        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.root);
        smartimages = new SmartHomeImageTo();

        mReceiver = new ResultParamReciever(new Handler());
        mReceiver.setReceiver(this);
        Bundle bundle = this.getArguments();
        device = bundle.getInt(key);
        intent = new Intent(Intent.ACTION_SYNC, null, view.getContext(), SmrekApartmanLoop.class);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("cisloApartmanu",device);

        kurenieSmrek = new KurenieSmrekTO();
        // Get the widgets reference from XML layout
        smrekServiceApartman = new SmrekServiceApartman();
        changeKurenie = new ChangeKurenie();
        changeKurenieApartmanSmrek = new ChangeKurenieApartmanSmrek();

        kurenieLoad = (LinearLayout) view.findViewById(R.id.clicable_parametre_smart_houme);
        layoutimagemap = (LinearLayout) view.findViewById(R.id.layoutimagemap);
        kurenieikonkalayout= (LinearLayout) view.findViewById(R.id.kurenieikonkalayout);
        kurenieikonkalayout.setTag("OFF");
    }

    private MarkerLayout.MarkerTapListener mMarkerTapListener = new MarkerLayout.MarkerTapListener() {
        @Override
        public void onMarkerTap( View v, int x, int y ) {
            String tag = (String) v.getTag();
            switch (tag){
                case "kupelka": {
                    // Initialize a new instance of LayoutInflater service
                    inflater = (LayoutInflater) getmContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    //Zmenove polia Kurenie
                    // Inflate the custom layout/view
                    if (view.getId() == R.id.kurenieikonka ){//|| view.getId() == R.id.changekotol || view.getId() == R.id.changeTuv
                        customView = inflater.inflate(R.layout.apartmanmainpage, null);
                    }
                    else{
                        customView = inflater.inflate(R.layout.testhodnoty, null);
                    }
                    // Initialize a new instance of popup window
                    mPopupWindow = new PopupWindow(
                            customView,
                            ActionBar.LayoutParams.MATCH_PARENT,
                            ActionBar.LayoutParams.WRAP_CONTENT
                    );
                    mPopupWindow.setFocusable(true);
                    mPopupWindow.update();
                    mPopupWindow.getContentView();
                    if (Build.VERSION.SDK_INT >= 21) {
                        mPopupWindow.setElevation(5.0f);
                    }
                   updateUi(getResults(),customView);
                    mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                }
            }
            Toast.makeText( getContext(), "Vybraná izba : " + v.getTag(), Toast.LENGTH_LONG ).show();
        }
    };

public TileView getTileView (SmartHomeTitleViewTO tileView1){
    tileView = tileView1.getTileView();
    tileView.setId( R.id.tileview_id );
    tileView.setMarkerTapListener( mMarkerTapListener );
    setTileView(tileView);
    return tileView;
}


public void updateUi(GetKurenieAnswer request,View view){
//        TextView apartmanKurenieCisloApartmanu = (TextView) view.findViewById(R.id.kurenieApartmanCislo);
//        TextView apartmanKureniePodlahovka = (TextView) view.findViewById(R.id.podlahovkaApartmanKurenie);
//        TextView apartmanKurenieVystup = (TextView) view.findViewById(R.id.apartmanKurenieVystup);
//        TextView apartmanKureniePriestor = (TextView) view.findViewById(R.id.kurenieApartmanPriestor);
//        TextView apartmanKureniePrepinac = (TextView) view.findViewById(R.id.apartmanKureniePrepinac);
//        TextView apartmanKurenieVonkajsia = (TextView) view.findViewById(R.id.apartmanKurenieVonkajsia);
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
          /*  apartmanKurenieCisloApartmanu.setText(String.format("%2d", device));
            apartmanKureniePodlahovka.setText(String.format("%.1f", kurenieSmrek.getTzuk1()) + "°C");
            apartmanKurenieVystup.setText(String.format("%.1f", kurenieSmrek.getTuk1v()) + "°C");
            apartmanKureniePriestor.setText(String.format("%.1f", kurenieSmrek.getTuk1r()) + "°C");
            apartmanKureniePrepinac.setText(String.format("%.1f", kurenieSmrek.getTuk1k()) + "°C");
            apartmanKurenieVonkajsia.setText(String.format("%2d", kurenieSmrek.getT_vonk_disp()) + "°C");*/
            apartmanKurenieZiadana.setText(String.format("%2d", kurenieSmrek.getTzmi_ctz_uk1()) + "°C");
            apartmanKurenieProgram.setText(String.format("%2d", kurenieSmrek.getCprog_uk1()));
        } else
            Toast.makeText(view.getContext(), "Nenacitane parametre z db", Toast.LENGTH_SHORT).show();
    }
    public List<PinTO> getPins(){
        List<PinTO> result = new ArrayList<>();
        PinTO pin1 = new PinTO( 0.15, 0.25 ,"kupelka",getResults().getKurenieSmrekList().get(0).getTuk1v());
        result.add(pin1);
      /*  PinTO pin2 = new PinTO( 0.25, 0.75 ,"spalna");
        result.add(pin2);
        PinTO pin3 = new PinTO( 0.75, 0.25,"kuchyna" );
        result.add(pin3);
        PinTO pin4 = new PinTO( 0.75, 0.75 ,"obyvacka");
        result.add(pin4);
        PinTO pin5 = new PinTO( 0.25, 0.95 ,"zahrada");
        result.add(pin5);*/

       return result;
    }

    public TileView getTileView() {
        return tileView;
    }

    public void setTileView(TileView tileView) {
        this.tileView = tileView;
    }


    public GetKurenieAnswer getResults() {
        return results;
    }

    public void setResults(GetKurenieAnswer results) {
        this.results = results;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
