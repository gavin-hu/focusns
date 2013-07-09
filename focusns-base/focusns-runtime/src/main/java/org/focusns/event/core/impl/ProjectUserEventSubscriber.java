package org.focusns.event.core.impl;

import org.focusns.common.event.annotation.Event;
import org.focusns.common.event.annotation.EventSubscriber;
import org.focusns.common.event.support.EventContext;
import org.focusns.dao.core.ProjectCategoryDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectFeatureDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.ProjectUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@EventSubscriber
public class ProjectUserEventSubscriber {

    private ProjectDao projectDao;
    private ProjectUserDao projectUserDao;
    private ProjectFeatureDao projectFeatureDao;
    private ProjectCategoryDao projectCategoryDao;

    @Autowired
    public void setProjectUserDao(ProjectUserDao projectUserDao) {
        this.projectUserDao = projectUserDao;
    }

    @Autowired
    public void setProjectFeatureDao(ProjectFeatureDao projectFeatureDao) {
        this.projectFeatureDao = projectFeatureDao;
    }

    @Autowired
    public void setProjectCategoryDao(ProjectCategoryDao projectCategoryDao) {
        this.projectCategoryDao = projectCategoryDao;
    }

    @Event(on = "CREATE_PROJECT_USER", point = Event.Point.AFTER)
    public void afterCreateProjectUser(EventContext ctx) {
        //
        ProjectUser projectUser = (ProjectUser) ctx.getArguments()[0];
        long createAtTime = projectUser.getCreatedAt().getTime();

    }

    /*protected void createProject(ProjectUser projectUser) {
        ProjectCategory category = projectCategoryDao.selectByCode("people");
        //
        Date now = new Date();
        Project project = new Project();
        project.setCode(user.getUsername());
        project.setTitle(user.getUsername());
        project.setDescription("");
        project.setCategoryId(category.getId());
        project.setCreatedAt(now);
        project.setModifiedAt(now);
        project.setCreatedById(user.getId());
        project.setModifiedById(user.getId());
        //
        projectDao.insert(project);
        user.setProjectId(project.getId());
        projectUserDao.update(user);
        //
        ProjectFeature profileFeature = new ProjectFeature();
        profileFeature.setLabel("主页");
        profileFeature.setCode("profile");
        profileFeature.setLevel(5);
        profileFeature.setProjectId(project.getId());
        //
        ProjectFeature blogFeature = new ProjectFeature();
        blogFeature.setLabel("日志");
        blogFeature.setCode("blog");
        blogFeature.setLevel(10);
        blogFeature.setProjectId(project.getId());
        //
        ProjectFeature photoFeature = new ProjectFeature();
        photoFeature.setLabel("相册");
        photoFeature.setCode("photo");
        photoFeature.setLevel(15);
        photoFeature.setProjectId(project.getId());
        //
        ProjectFeature teamFeature = new ProjectFeature();
        teamFeature.setLabel("人脉");
        teamFeature.setCode("team");
        teamFeature.setLevel(20);
        teamFeature.setProjectId(project.getId());
        //
        ProjectFeature msgFeature = new ProjectFeature();
        msgFeature.setLabel("私信");
        msgFeature.setCode("msg");
        msgFeature.setLevel(25);
        msgFeature.setProjectId(project.getId());
        //
        ProjectFeature settingFeature = new ProjectFeature();
        settingFeature.setLabel("设置");
        settingFeature.setCode("admin");
        settingFeature.setLevel(30);
        settingFeature.setProjectId(project.getId());
        //
        projectFeatureDao.insert(profileFeature);
        projectFeatureDao.insert(blogFeature);
        projectFeatureDao.insert(photoFeature);
        projectFeatureDao.insert(teamFeature);
        projectFeatureDao.insert(msgFeature);
        projectFeatureDao.insert(settingFeature);
    }*/

}
