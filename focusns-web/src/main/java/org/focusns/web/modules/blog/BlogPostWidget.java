package org.focusns.web.modules.blog;

import java.util.Map;

import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.service.blog.BlogPostService;
import org.focusns.web.widget.annotation.Bind;
import org.focusns.web.widget.annotation.Bind.Scope;
import org.focusns.web.widget.annotation.Widget;
import org.springframework.beans.factory.annotation.Autowired;

@Widget
public class BlogPostWidget {

    @Autowired
    private BlogPostService blogPostService;
    
	public String view(Map<String, Object> model,
                       @Bind(value = "id", scope = Scope.PARAMETER) long postId) {
        //
        BlogPost blogPost = blogPostService.getBlogPost(postId);
        model.put("blogPost", blogPost);
        model.put("blogPost", blogPost);
        //
		return "modules/blog/post-view";
	}
	
	public String list(Map<String, Object> model,
            @Bind(value = "project", scope = Scope.SESSION) Project project,
            @Bind(value = "categoryId", scope = Scope.PARAMETER) long categoryId) {
        //
        Page<BlogPost> page = new Page<BlogPost>(20);
        if(categoryId > 0) {
            page = blogPostService.fetchPageByCategoryId(page, categoryId);
        } else {
            page = blogPostService.fetchPageByProjectId(page, project.getId());
        }
        model.put("page", page);
        //
		return "modules/blog/post-list";
	}
	
}
