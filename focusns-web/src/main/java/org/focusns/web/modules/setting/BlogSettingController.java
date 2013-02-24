package org.focusns.web.modules.setting;

import org.focusns.model.blog.BlogCategory;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectFeature;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.service.blog.BlogPostService;
import org.focusns.web.helper.ActionHelper;
import org.focusns.web.helper.WebRequestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class BlogSettingController {
    
    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogCategoryService blogCategoryService;
     
    @RequestMapping("/blog/post/edit")
    public String editPost(@RequestParam String action, BlogPost blogPost, WebRequest webRequest) {
        Project project = WebRequestHelper.getProject(webRequest);
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        if(ActionHelper.isCreate(action)) {
            blogPostService.createBlogPost(blogPost);
        }
        if(ActionHelper.isModify(action)) {
            blogPostService.modifyBlogPost(blogPost);
        }
        if(ActionHelper.isRemove(action)) {
            blogPostService.removeBlogPost(blogPost);
        }
        //
        return "redirect:/"+project.getCode()+"/"+feature.getCode()+"/blog/post-edit";
    }
    @RequestMapping("/blog/category/edit")
    public String editCategory(@RequestParam String action, BlogCategory blogCategory, WebRequest webRequest) {
        //
        Project project = WebRequestHelper.getProject(webRequest);
        ProjectFeature feature = WebRequestHelper.getProjectFeature(webRequest);
        //
        if(ActionHelper.isCreate(action)) {
            blogCategoryService.createBlogCategory(blogCategory);
        }
        if(ActionHelper.isModify(action)) {
            blogCategoryService.modifyBlogCategory(blogCategory);
        }
        if(ActionHelper.isRemove(action)) {
            blogCategoryService.removeBlogCategory(blogCategory);
        }
        //
        return "redirect:/"+project.getCode()+"/"+feature.getCode()+"/blog/category-edit";
    }
    
}
