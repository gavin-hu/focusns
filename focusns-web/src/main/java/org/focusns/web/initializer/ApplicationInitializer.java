/*
 * Copyright (C) 2011-2013 FocusSNS.
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
package org.focusns.web.initializer;

import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectFeature;
import org.focusns.model.core.User;
import org.focusns.service.core.ProjectCategoryService;
import org.focusns.service.core.ProjectFeatureService;
import org.focusns.service.core.ProjectService;
import org.focusns.service.core.UserService;
import org.springframework.context.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.Assert;

import java.util.Date;

public class ApplicationInitializer 
	implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	public void initialize(ConfigurableApplicationContext applicationContext) {
		applicationContext.addApplicationListener(new ApplicationListener<ApplicationEvent>() {

			public void onApplicationEvent(ApplicationEvent event) {
				if(event instanceof ContextRefreshedEvent) {
					ApplicationSetup(((ContextRefreshedEvent) event).getApplicationContext());
				}
			}
			
		});
	}

	protected void ApplicationSetup(ApplicationContext applicationContext) {
		//
		setupCategory(applicationContext);
		//
		setupAdmin(applicationContext);
		//
		setupProject(applicationContext);
		//
		setupProjectFeatures(applicationContext);
	}
	
	private void setupCategory(ApplicationContext applicationContext) {
		ProjectCategoryService categoryService = applicationContext.getBean(ProjectCategoryService.class);
		ProjectCategory category = categoryService.getCategory("people");
		if(category==null) {
			category = new ProjectCategory();
			category.setCode("people");
			category.setEnabled(true);
			category.setLabel("成员");
			//
			categoryService.createCategory(category);
		}
	}
	
	private void setupAdmin(ApplicationContext applicationContext) {
		UserService userService = applicationContext.getBean(UserService.class);
		User admin = userService.getUser("admin");
		if(admin==null) {
			admin = new User();
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin.setEmail("admin@focusns.org");
			//
			userService.createUser(admin);
		}
	}
	
	private void setupProject(ApplicationContext applicationContext) {
		ProjectCategoryService categoryService = applicationContext.getBean(ProjectCategoryService.class);
		ProjectCategory category = categoryService.getCategory("people");
		Assert.notNull(category, "System category can not be null!");
		//
		ProjectService projectService = applicationContext.getBean(ProjectService.class);
		Project project = projectService.getProject("focusns");
		UserService userService = applicationContext.getBean(UserService.class);
		User admin = userService.getUser("admin");
		if(project==null) {
			Date now = new Date();
			//
			project = new Project();
            project.setCode("focusns");
			project.setTitle("FocusSNS");
			project.setDescription("Welcome to FocusSNS!");
			project.setCreateAt(now);
			project.setModifyAt(now);
			project.setCreateById(admin.getId());
			project.setModifyById(admin.getId());
			project.setCategoryId(category.getId());
			//
			projectService.createProject(project);
            //
            admin.setProjectId(project.getId());
            userService.modifyUser(admin);
		}
	}
	
	private void setupProjectFeatures(ApplicationContext applicationContext) {
		ProjectService projectService = applicationContext.getBean(ProjectService.class);
		Project project = projectService.getProject("focusns");
		Assert.notNull(project, "Focusns project can not be null!");
		//
		setupProjectFeature(applicationContext, project, "profile", "主页");
		//
		setupProjectFeature(applicationContext, project, "blog", "博客");
		//
		setupProjectFeature(applicationContext, project, "forum", "论坛");
        //
        setupProjectFeature(applicationContext, project, "admin", "管理");
	}
	
	private void setupProjectFeature(ApplicationContext applicationContext, Project project, String code, String label) {
		// 
		ProjectFeatureService featureService = applicationContext.getBean(ProjectFeatureService.class);
		ProjectFeature feature = featureService.getProjectFeature(project.getId(), code);
		if(feature==null) {
			feature = new ProjectFeature();
			feature.setCode(code);
			feature.setLabel(label);
			feature.setProjectId(project.getId());
			//
			featureService.createProjectFeature(feature);
		}
	}
}
