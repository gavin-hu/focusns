package org.focusns.model.cms;

/*
 * #%L
 * focusns-model
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


import org.focusns.model.common.Id;

import java.util.Date;

public class Article extends Id {

    private String title;
    private String summary;
    private String content;
    private String keywords;
    //
    private String from;
    private String link;
    //
    private Date createAt;
    private Date modifyAt;
    private Date removeAt;
    //
    private long createById;
    private long modifyById;
    private long removeById;
    //
    private long categoryId;



}
