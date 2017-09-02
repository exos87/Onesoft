package com.onesoft.param.smart.home.to;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.onesoft.devicecontrol.R;
import com.qozix.tileview.TileView;

import java.util.List;

/**
 * Created by exosj on 12.06.2017.
 */

public class SmartHomeTitleViewTO {
    TileView tileView;
    Context context;
    Integer imageSizeA;
    Integer imageSizeB;
    Integer scaleLimitA;
    Integer scaleLimitB;
    Boolean shouldRenderWhilePanning;
    Boolean SaveEnabled;
    Integer defineBounds1;
    Integer defineBounds2;
    Integer defineBounds3;
    Integer defineBounds4;
    List<PinTO> pins;
    public SmartHomeTitleViewTO(Context context) {
        this.tileView =  new TileView(context);
    }

    public SmartHomeTitleViewTO( Context context, Integer imageSizeA, Integer imageSizeB, Integer scaleLimitA, Integer scaleLimitB, Boolean shouldRenderWhilePanning, Boolean saveEnabled, Integer defineBounds1, Integer defineBounds2, Integer defineBounds3, Integer defineBounds4, List<PinTO> pins) {
        this.tileView =  new TileView(context);

    tileView.setSaveEnabled( saveEnabled );
    // size of original image at 100% mScale
    tileView.setSize( imageSizeA, imageSizeB );
    // small map, let's let it resize to 200%
    tileView.setScaleLimits( scaleLimitA, scaleLimitB );
    // we're running from assets, should be fairly fast decodes, go ahead and render asap
    tileView.setShouldRenderWhilePanning( shouldRenderWhilePanning );
    // detail levels
    tileView.addDetailLevel( 1.000f, "tiles/plans/prizemie/komplet/1000/%d_%d.png");
    tileView.addDetailLevel( 0.500f, "tiles/plans/prizemie/komplet/500/%d_%d.png");
    tileView.addDetailLevel( 0.250f, "tiles/plans/prizemie/komplet/250/%d_%d.png");
    tileView.addDetailLevel( 0.125f, "tiles/plans/prizemie/komplet/125/%d_%d.png");
    // let's use 0-1 positioning...
    tileView.defineBounds( defineBounds1, defineBounds2,defineBounds3, defineBounds4 );
    // center markers along both axes
    tileView.setMarkerAnchorPoints( -0.5f, -0.5f );
    // add a marker listener
        setContext(context);
    // add some pins...
        if (pins !=null && !pins.isEmpty()){
            for (PinTO pin : pins)
            {
                addPin( pin.getX(), pin.getY() ,pin.getIzba(),pin.getValue());
            }
        }
    // mScale it down to manageable size
    tileView.setScale( 0.2f );
        setTileView(tileView);

    }



    private void addPin( double x, double y,String izba ,Float value) {
        ImageView imageView = new ImageView( getContext() );
        TextView texttemperature = new TextView(getContext());//

        texttemperature.setBackgroundResource(R.drawable.vnutrodat_value_shape);
        texttemperature.setText(String.format("%.1f", value) + "°C");
        texttemperature.setTextSize(25);
        texttemperature.setTag(izba);
        imageView.setImageResource( R.drawable.push_pin );
        imageView.setTag(izba);
        tileView.addMarker( texttemperature, x, y, null, null );
    }

    public TileView getTileView() {
        return tileView;
    }

    public void setTileView(TileView tileView) {
        this.tileView = tileView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Integer getImageSizeA() {
        return imageSizeA;
    }

    public void setImageSizeA(Integer imageSizeA) {
        this.imageSizeA = imageSizeA;
    }

    public Integer getImageSizeB() {
        return imageSizeB;
    }

    public void setImageSizeB(Integer imageSizeB) {
        this.imageSizeB = imageSizeB;
    }

    public Integer getScaleLimitA() {
        return scaleLimitA;
    }

    public void setScaleLimitA(Integer scaleLimitA) {
        this.scaleLimitA = scaleLimitA;
    }

    public Integer getScaleLimitB() {
        return scaleLimitB;
    }

    public void setScaleLimitB(Integer scaleLimitB) {
        this.scaleLimitB = scaleLimitB;
    }

    public Boolean getShouldRenderWhilePanning() {
        return shouldRenderWhilePanning;
    }

    public void setShouldRenderWhilePanning(Boolean shouldRenderWhilePanning) {
        this.shouldRenderWhilePanning = shouldRenderWhilePanning;
    }

    public Boolean getSaveEnabled() {
        return SaveEnabled;
    }

    public void setSaveEnabled(Boolean saveEnabled) {
        SaveEnabled = saveEnabled;
    }

    public Integer getDefineBounds1() {
        return defineBounds1;
    }

    public void setDefineBounds1(Integer defineBounds1) {
        this.defineBounds1 = defineBounds1;
    }

    public Integer getDefineBounds2() {
        return defineBounds2;
    }

    public void setDefineBounds2(Integer defineBounds2) {
        this.defineBounds2 = defineBounds2;
    }

    public Integer getDefineBounds3() {
        return defineBounds3;
    }

    public void setDefineBounds3(Integer defineBounds3) {
        this.defineBounds3 = defineBounds3;
    }

    public Integer getDefineBounds4() {
        return defineBounds4;
    }

    public void setDefineBounds4(Integer defineBounds4) {
        this.defineBounds4 = defineBounds4;
    }

    public List<PinTO> getPins() {
        return pins;
    }

    public void setPins(List<PinTO> pins) {
        this.pins = pins;
    }
}
