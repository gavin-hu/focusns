package org.focusns.service.core;

import java.util.Date;

import org.focusns.dao.core.UserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.User;
import org.focusns.service.AbstractServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Ignore
public class ProjectServiceTest extends AbstractServiceTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ProjectCategoryService projectCategoryService;
	
	@Test
	public void testCreateProject() {
		ProjectCategory category = new ProjectCategory();
		category.setCode("People");
		category.setLabel("人员");
		projectCategoryService.createCategory(category);
		//
		User user = new User();
		user.setUsername("haozhonghu");
		user.setPassword("123456");
		user.setEmail("haozhonghu@hotmail.com");
		userDao.insert(user);
		//
		Date now = new Date();
		Project project = new Project();
		project.setCode("gavin");
		project.setTitle("Gavin Hu");
		project.setDescription("This is gavin's profile!");
		//
		project.setCreateAt(now);
		project.setModifyAt(now);
		project.setCreateById(user.getId());
		project.setModifyById(user.getId());
		project.setCategoryId(category.getId());
		//
		projectService.createProject(project);
	}
    
	@Test
	public void testDeleteProject() {
		Project project = projectService.getProject("gavin");
		//
		projectService.removeProject(project);
		//
		userDao.delete(project.getCreateById());
		//
		ProjectCategory category = projectCategoryService.getCategory(project.getCategoryId());
		projectCategoryService.removeCategory(category);
		
	}
	
}
