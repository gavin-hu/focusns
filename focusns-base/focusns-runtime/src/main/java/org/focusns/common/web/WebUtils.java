package org.focusns.common.web;

/*
 * #%L
 * FocusSNS Runtime
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

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public abstract class WebUtils {

    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        //
        Map<String, String> parameterMap = new LinkedHashMap<String, String>();
        //
        String requestUri = request.getRequestURI();
        if (requestUri.contains(";")) {
            String paramsString = requestUri.substring(requestUri.indexOf(";") + 1);
            String[] paramsPair = new String[] { paramsString };
            if (paramsString.contains(",")) {
                paramsPair = StringUtils.split(paramsString, ",");
            }
            for (String paramPair : paramsPair) {
                String[] nameAndValue = StringUtils.split(paramPair, "=");
                parameterMap.put(nameAndValue[0], nameAndValue[1]);
            }
        }
        return parameterMap;
    }

}
