package org.focusns.common.html;

import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

import java.util.Iterator;

public abstract class HtmlUtils {

    public static String extractText(String html) {
        //
        Source source = new Source(html);
        return source.getTextExtractor().toString();
    }

}
