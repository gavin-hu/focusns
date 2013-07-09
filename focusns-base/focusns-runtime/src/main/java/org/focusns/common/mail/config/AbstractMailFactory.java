package org.focusns.common.mail.config;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMailFactory implements MailFactory {

    private boolean cacheable;
    private Map<String, MailConfig> cache = new HashMap<String, MailConfig>();

    public void setCacheable(boolean cacheable) {
        this.cacheable = cacheable;
    }

    @Override
    public MailConfig find(String messageId) {
        try {
            if(cacheable==false || cache.isEmpty()) {
                //
                List<MailConfig> mailConfigList = loadMails();
                for(MailConfig mailConfig : mailConfigList) {
                    cache.put(mailConfig.getMessageId(), mailConfig);
                }
            }
            //
            return cache.get(messageId);
        } catch (Exception e) {
            throw new MailConfigExceptioin("邮件配置加载异常...", e);
        }
    }

    protected abstract List<MailConfig> loadMails() throws Exception;

}
