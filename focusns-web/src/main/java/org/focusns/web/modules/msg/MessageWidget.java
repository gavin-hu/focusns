

package org.focusns.web.modules.msg;

import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectUser;
import org.focusns.model.msg.Message;
import org.focusns.service.msg.MessageService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResultUtils;

import java.util.Map;

@Widget
public class MessageWidget {

    @Autowired
    private MessageService messageService;

    public String alert() {

        return "modules/msg/message-alert";
    }

    public String action() {

        return "modules/msg/message-action";
    }

    public String box() {

        return "modules/msg/message-box";
    }

    public String list(Map<String, Object> model, @Bind(value="box") String box,
                       @Bind(value="project", scope= Bind.Scope.SESSION) Project project) {
        //
        Page<Message> page = new Page<Message>(10);
        page = messageService.fetchPageByBox(page, box, project.getId());
        model.put("page", page);
        //
        return "modules/msg/message-list";
    }

    public String view() {

        return "modules/msg/message-view";
    }

    public String edit(Map<String, Object> model,
                       @Bind(value="user", scope= Bind.Scope.SESSION) ProjectUser user,
                       @Bind(value="project", scope= Bind.Scope.SESSION) Project project) {
        //
        Message message = new Message();
        message.setCreateById(user.getId());
        message.setProjectId(project.getId());
        message.setToProjectId(project.getId());
        model.put("message", message);
        //
        return "modules/msg/message-edit";
    }
}
