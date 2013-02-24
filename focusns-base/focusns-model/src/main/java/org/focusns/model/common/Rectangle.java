package org.focusns.model.common;

public class Rectangle {

    private String x; // x 
    private String y; // y
    private String w; // width
    private String h; // height

    public String getX() {
        return x;
    }
    
    public int getXInt() {
        return parseInt(x);
    }
    
    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public int getYInt() {
        return parseInt(y);
    }
    public void setY(String y) {
        this.y = y;
    }

    public String getW() {
        return w;
    }

    public int getWInt() {
        return parseInt(w);
    }
    
    public void setW(String w) {
        this.w = w;
    }

    public String getH() {
        return h;
    }
    
    public int getHInt() {
        return parseInt(h);
    }
    
    public void setH(String h) {
        this.h = h;
    }
    
    private int parseInt(String num) {
        if(num.contains(".")) {
            num = num.substring(0, num.indexOf("."));
        }
        //
        return Integer.parseInt(num);
    }
    
}
