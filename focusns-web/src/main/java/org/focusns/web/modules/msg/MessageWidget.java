

package org.focusns.web.modules.msg;

/*
 * #%L
 * FocusSNS Web
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
