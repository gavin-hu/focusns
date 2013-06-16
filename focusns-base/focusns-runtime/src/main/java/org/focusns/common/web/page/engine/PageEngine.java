package org.focusns.common.web.page.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageEngine {

    void doRender(HttpServletRequest request, HttpServletResponse response) throws PageEngineException;

    void doAction(HttpServletRequest request, HttpServletResponse response) throws PageEngineException;

}
