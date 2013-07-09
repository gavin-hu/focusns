package org.focusns.security.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: Gavin Hu
 * Date: 13-7-9
 * Time: 下午10:12
 * To change this template use File | Settings | File Templates.
 */
public class DefaultSessioinListener extends SessionListenerAdapter {

    @Override
    public void onStart(Session session) {
        System.out.println("session started");
    }
}
