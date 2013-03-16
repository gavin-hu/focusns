package org.focusns.web.portal.config;

/*
 * #%L
 * FocusSNS Web
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


import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PositionConfig {

    private String name;
    private String grid;
    private int rows;
    private int columns;
    //
    private List<WidgetConfig> widgetConfigList = new ArrayList<WidgetConfig>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        if(!StringUtils.hasText(grid)) {
            return ;
        }
        //
        if(!grid.matches("^\\d+,\\d+$")) {
            return ;
        }
        //
        this.grid = grid;
        //
        this.rows = Integer.parseInt(grid.substring(0, grid.indexOf(",")));
        this.columns = Integer.parseInt(grid.substring(grid.indexOf(",")+1));
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public List<WidgetConfig> getWidgetConfigList() {
        return widgetConfigList;
    }

    public void addWidgetConfig(WidgetConfig widgetConfig) {
        this.widgetConfigList.add(widgetConfig);
    }

}
