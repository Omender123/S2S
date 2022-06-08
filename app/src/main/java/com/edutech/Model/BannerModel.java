package com.edutech.Model;

import android.graphics.drawable.Drawable;

public class BannerModel {
    String txt,txt1,btnTxt,btnTxtColor;
    Integer icon;
    Drawable backImg;

    public BannerModel(String txt, String txt1, String btnTxt, String btnTxtColor, Integer icon,Drawable backImg) {
        this.txt = txt;
        this.txt1 = txt1;
        this.btnTxt = btnTxt;
        this.btnTxtColor = btnTxtColor;
        this.icon = icon;
        this.backImg = backImg;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getBtnTxt() {
        return btnTxt;
    }

    public void setBtnTxt(String btnTxt) {
        this.btnTxt = btnTxt;
    }

    public String getBtnTxtColor() {
        return btnTxtColor;
    }

    public void setBtnTxtColor(String btnTxtColor) {
        this.btnTxtColor = btnTxtColor;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Drawable getBackImg() {
        return backImg;
    }

    public void setBackImg(Drawable backImg) {
        this.backImg = backImg;
    }
}
