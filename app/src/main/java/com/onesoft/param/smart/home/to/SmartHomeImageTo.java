package com.onesoft.param.smart.home.to;

import java.util.List;

/**
 * Created by exosj on 12.06.2017.
 */

public class SmartHomeImageTo {

    String imagePodoris;
    List<String> imageIzby;
    List<Integer> imageSuradnice;
    String imagePopis;

    public SmartHomeImageTo() {
        super();
    }

    public SmartHomeImageTo(String imagePodoris, List<String> imageIzby, List<Integer> imageSuradnice, String imagePopis) {
        this.imagePodoris = imagePodoris;
        this.imageIzby = imageIzby;
        this.imageSuradnice = imageSuradnice;
        this.imagePopis = imagePopis;
    }

    public String getImagePodoris() {
        return imagePodoris;
    }

    public void setImagePodoris(String imagePodoris) {
        this.imagePodoris = imagePodoris;
    }

    public List<String> getImageIzby() {
        return imageIzby;
    }

    public void setImageIzby(List<String> imageIzby) {
        this.imageIzby = imageIzby;
    }

    public List<Integer> getImageSuradnice() {
        return imageSuradnice;
    }

    public void setImageSuradnice(List<Integer> imageSuradnice) {
        this.imageSuradnice = imageSuradnice;
    }

    public String getImagePopis() {
        return imagePopis;
    }

    public void setImagePopis(String imagePopis) {
        this.imagePopis = imagePopis;
    }
}
