
package org.focusns.service.msg;

import org.focusns.model.common.Page;
import org.focusns.model.msg.Message;

public interface MessageService {
    
    Message getMessage(long messageId);
    
    void createMessage(Message message);
    
    void modifyMessage(Message message);
    
    void removeMessage(Message message);
    
    Page<Message> fetchPageByBox(Page<Message> page, String box, long projectId);
    
}
