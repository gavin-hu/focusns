package org.focusns.common.image;

/*
 * #%L
 * FocusSNS Model
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

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

    public void setX(String xStr) {
        if (xStr.contains(".")) {
            xStr = xStr.substring(0, xStr.indexOf("."));
        }
        this.x = xStr;
    }

    public String getY() {
        return y;
    }

    public int getYInt() {
        return parseInt(y);
    }

    public void setY(String yStr) {
        if (yStr.contains(".")) {
            yStr = yStr.substring(0, yStr.indexOf("."));
        }
        this.y = yStr;
    }

    public String getW() {
        return w;
    }

    public int getWInt() {
        return parseInt(w);
    }

    public void setW(String wStr) {
        if (wStr.contains(".")) {
            wStr = wStr.substring(0, wStr.indexOf("."));
        }
        this.w = wStr;
    }

    public String getH() {
        return h;
    }

    public int getHInt() {
        return parseInt(h);
    }

    public void setH(String hStr) {
        if (hStr.contains(".")) {
            hStr = hStr.substring(0, hStr.indexOf("."));
        }
        this.h = hStr;
    }

    private int parseInt(String num) {
        return Integer.parseInt(num);
    }

}
