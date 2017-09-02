package com.onesoft.devicecontrol.click;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.onesoft.devicecontrol.R;
import com.onesoft.devicecontrol.changes.ChangeKurenie;
import com.onesoft.fero.answer.ParamFeroAllAnswer;
import com.onesoft.fero.answer.ParamFeroChangeKurenieAnswer;
import com.onesoft.param.to.ParametrFeroKotolTO;
import com.onesoft.param.to.ParametrFeroKurenieTO;
import com.onesoft.param.to.ParametrFeroVodaTO;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by exosj on 03.01.2017.
 */

public class ExternalOnClickLisener  implements View.OnClickListener{
    private PopupWindow mPopupWindow;
    Context mContext;
    ParamFeroChangeKurenieAnswer answerKurenieChange = new ParamFeroChangeKurenieAnswer();
    ParametrFeroKotolTO kotolBase = new ParametrFeroKotolTO();
    ParametrFeroKurenieTO kurenieBase = new ParametrFeroKurenieTO();
    ParametrFeroVodaTO vodaBase = new ParametrFeroVodaTO();
    ChangeKurenie changeKurenie;
    private RelativeLayout mRelativeLayout;
    TextView otazkaOkruhy , cislovac;
    LayoutInflater inflater;
    View  customView;
    public ExternalOnClickLisener(ParamFeroAllAnswer request,Context cmt) {
        ParamFeroAllAnswer answerAllParam = request;
        if (answerAllParam.getParamFeroAll()!=null){
            kurenieBase = answerAllParam.getParamFeroAll().getFeroKurenie();
            kotolBase = answerAllParam.getParamFeroAll().getFeroKotol();
            vodaBase = answerAllParam.getParamFeroAll().getFeroVoda();

        }
        changeKurenie = new ChangeKurenie();
         mContext = cmt;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {



            //Zmenove polia Kurenie
            // Inflate the custom layout/view


            //Akcia pre tlacitko zmena spracuje zmenove parametre a posle na server
            case R.id.changekurenieicon: {
                 inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                //Zmenove polia Kurenie
                // Inflate the custom layout/view
                  customView = inflater.inflate(R.layout.custom_layout, null);
                // Initialize a new instance of popup window
                mRelativeLayout = (RelativeLayout)customView.findViewById(R.id.root);
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

                //Nastavim zmenove polia zobrazenie
                TextView teploChangeCasProgramKurenie = (TextView) customView.findViewById(R.id.changeCasProgramKurenie);
                TextView teploChangeZiadanaPriestoruKurenie = (TextView) customView.findViewById(R.id.changeZiadanaPriestoruKurenie);
                final ToggleButton kurenieButtonStavChange = (ToggleButton) customView.findViewById(R.id.kurenieChodStopChange);
                //Nastavim zmenove polia
                final EditText changeCasProgramKurenie = (EditText) customView.findViewById(R.id.changeTempKurenieChange);
//                changeCasProgramKurenie.setText(String.format("%2d", kurenieBase.getCprog_uk1()));
                final EditText changeZiadanaPriestoruKurenie = (EditText) customView.findViewById(R.id.changeTempKureniePriestorChange);
//                changeZiadanaPriestoruKurenie.setText(String.format("%2d", kurenieBase.getTzmi_ctz_uk1()) + "°");
                //Naplnim zmenove polia

                teploChangeCasProgramKurenie.setText(String.format("%2d", kurenieBase.getCprog_uk1()));
                teploChangeZiadanaPriestoruKurenie.setText(String.format("%2d", kurenieBase.getTzmi_ctz_uk1()) + "°");
                if (kurenieBase.getChod_reg_uk1() == 1)
                    kurenieButtonStavChange.setChecked(true);
                else
                    kurenieButtonStavChange.setChecked(false);

                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);
                // Get a reference for the custom view zmena button
                Button zmenaButton = (Button) customView.findViewById(R.id.acceptchange);
                // Set a click listener for the popup window close button
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
                        Integer chodStop = null;
                        if (kurenieButtonStavChange.isChecked())
                            chodStop = 1;
                        else chodStop = 0;
                        if (!(changeCasProgrKur == kurenieBase.getCprog_uk1() && changeZiadPriesKur == kurenieBase.getTzmi_ctz_uk1() && chodStop == kurenieBase.getChod_reg_uk1())) {
                            answerKurenieChange = changeKurenie.changeKurenienow(changeCasProgrKur, changeZiadPriesKur, chodStop, kurenieBase.getCtz_uk1());
                            mPopupWindow.dismiss();
                        } // Dismiss the popup window


                    }
                });
                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events
            }
            case R.id.changekurenieotazka: {
                // Initialize a new instance of LayoutInflater service
                 inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                // Inflate the custom layout/view
                customView = inflater.inflate(R.layout.question, null);
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

                TextView   mTextStatus = (TextView) customView.findViewById(R.id.TEXT_STATUS_ID);
                ScrollView mScrollView = (ScrollView) customView.findViewById(R.id.SCROLLER_ID);
                mTextStatus.append(mContext.getString(R.string.kurenie_info) +
                        mContext.getString(R.string.kurenie_info_add));
                //Nastavenie head nadpisu v popup okne
                otazkaOkruhy = (TextView)customView.findViewById(R.id.questionhead);
                otazkaOkruhy.setText(R.string.kurenie_info_head);
                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);
                // Get a reference for the custom view zmena button

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events kotolinfoicon

            }
            case R.id.kotolinfoicon: {
                // Initialize a new instance of LayoutInflater service
                 inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                // Inflate the custom layout/view
                 customView = inflater.inflate(R.layout.question, null);
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

                TextView   mTextStatus = (TextView) customView.findViewById(R.id.TEXT_STATUS_ID);
                ScrollView   mScrollView = (ScrollView) customView.findViewById(R.id.SCROLLER_ID);
                mTextStatus.append(mContext.getString(R.string.kotol_info) +
                        mContext.getString(R.string.kotol_info_add));
                //Nastavenie head nadpisu v popup okne
                otazkaOkruhy = (TextView)customView.findViewById(R.id.questionhead);
                otazkaOkruhy.setText(R.string.kotol_info_head);
                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);
                // Get a reference for the custom view zmena button

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events vodainfoicon

            }
            case R.id.vodainfoicon: {
                // Initialize a new instance of LayoutInflater service
                 inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                // Inflate the custom layout/view
                 customView = inflater.inflate(R.layout.question, null);
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

                TextView   mTextStatus = (TextView) customView.findViewById(R.id.TEXT_STATUS_ID);
                ScrollView   mScrollView = (ScrollView) customView.findViewById(R.id.SCROLLER_ID);
                mTextStatus.append(mContext.getString(R.string.voda_info) +
                        mContext.getString(R.string.voda_info_add));
                //Nastavenie head nadpisu v popup okne
                otazkaOkruhy = (TextView)customView.findViewById(R.id.questionhead);
                otazkaOkruhy.setText(R.string.tuv_info_head);
                // Get a reference for the custom view close button
                Button closeButton = (Button) customView.findViewById(R.id.ib_close);
                // Get a reference for the custom view zmena button

                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                // Finally, show the popup window at the center location of root relative layout
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
                break;
                //handle multiple view click events

            }
        }
    }
    // Initialize a new instance of LayoutInflater service

}
