
package org.focusns.service.msg;

import org.focusns.model.msg.MessageBox;

import java.util.List;

public interface MessageBoxService {
    
    void createMessageBox(MessageBox msgBox);
    
    void modifyMessageBox(MessageBox msgBox);
    
    void removeMessageBox(MessageBox msgBox);
    
    List<MessageBox> listMessageBoxes(long projectId);
    
}
