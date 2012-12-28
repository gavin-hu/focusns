/*
 * Copyright (C) 2012 FocusSNS.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.focusns.web.modules.admin;

import org.focusns.model.blog.BlogTag;
import org.focusns.service.blog.BlogTagService;
import org.focusns.web.utils.ActionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/{projectCode}/{featureCode}")
public class AdminBlogController {
    
    @Autowired
    private BlogTagService blogTagService;
    
    @RequestMapping("/blog/tag-edit.action")
    public String editTag(@RequestParam String action,
            @PathVariable("projectCode") String projectCode,
            @PathVariable("featureCode") String featureCode, BlogTag blogTag) {
        //
        if(ActionHelper.isCreate(action)) {
            blogTagService.createBlogTag(blogTag);
        }
        if(ActionHelper.isModify(action)) {
            blogTagService.modifyBlogTag(blogTag);
        }
        if(ActionHelper.isRemove(action)) {
            blogTagService.removeBlogTag(blogTag);
        }
        //
        return "redirect:/"+projectCode+"/"+featureCode+"/blog/tag-edit";
    }
    
}
