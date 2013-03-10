package org.focusns.web.console.cms;

/*
 * #%L
 * FocusSNS Web
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import org.focusns.model.blog.BlogCategory;
import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.blog.BlogCategoryService;
import org.focusns.service.blog.BlogPostService;
import org.focusns.web.widget.annotation.WidgetAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/console/cms")
public class ArticleWidget {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping("/article-list")
    public String doList(@RequestParam(required = false) Long categoryId, Model model) {
        //
        List<BlogCategory> articleCategories = blogCategoryService.getBlogCategories();
        if(!articleCategories.isEmpty()) {
            BlogCategory articleCategory = articleCategories.get(0);
            if(categoryId!=null) {
                for(BlogCategory tmp : articleCategories) {
                    if(categoryId.longValue()==tmp.getId()) {
                        articleCategory = tmp;
                    }
                }
            }
            //
            Page<BlogPost> page = new Page<BlogPost>(10);
            page = blogPostService.fetchPageByCategoryId(page, articleCategory.getId());
            //
            model.addAttribute("articleCategories", articleCategories);
            model.addAttribute("articleCategory", articleCategory);
            model.addAttribute("page", page);
        }
        //
        return "console/cms/article-list";
    }

    @RequestMapping("/article-edit")
    public String doEdit(@RequestParam(required = false) Long articleId,
                         @RequestParam(required = false) Long categoryId,
                         @WidgetAttribute ProjectUser user, Model model) {
        //
        List<BlogCategory> articleCategories = blogCategoryService.getBlogCategories();
        //
        BlogPost article = new BlogPost();
        article.setCategoryId(categoryId!=null?categoryId:0);
        article.setCreateById(user.getId());
        article.setModifyById(user.getId());
        if(articleId!=null) {
            article = blogPostService.getBlogPost(articleId);
        }
        //
        model.addAttribute("articleCategories", articleCategories);
        model.addAttribute("article", article);
        //
        return "console/cms/article-edit";
    }

    @RequestMapping("/article-modify")
    public void modifyAction(BlogPost article) {
        if(article.getId()>0) {
            blogPostService.modifyBlogPost(article);
        } else {
            blogPostService.createBlogPost(article);
        }
    }

}
