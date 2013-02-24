package org.focusns.model.cms;

import java.util.Date;

import org.focusns.model.common.Id;

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
