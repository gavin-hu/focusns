package org.focusns.subscriber.core.impl;

import org.focusns.common.event.annotation.Event;
import org.focusns.common.event.annotation.EventSubscriber;
import org.focusns.common.event.support.EventContext;
import org.focusns.common.exception.ServiceException;
import org.focusns.common.exception.ServiceExceptionCode;
import org.focusns.common.xml.XmlParser;
import org.focusns.dao.core.ProjectAuthorityDao;
import org.focusns.dao.core.ProjectCategoryDao;
import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectFeatureDao;
import org.focusns.dao.core.ProjectPermissionDao;
import org.focusns.dao.core.ProjectRoleDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectAttribute;
import org.focusns.model.core.ProjectAuthority;
import org.focusns.model.core.ProjectCategory;
import org.focusns.model.core.ProjectFeature;
import org.focusns.model.core.ProjectPermission;
import org.focusns.model.core.ProjectRole;
import org.focusns.model.core.ProjectTemplate;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EventSubscriber
@Transactional
public class ProjectUserEventSubscriber {
    private static final String RESOURCE_PATTERN = "classpath:projects/project-%s.xml";
    //
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectFeatureDao projectFeatureDao;
    @Autowired
    private ProjectCategoryDao projectCategoryDao;
    //
    @Autowired
    private ProjectRoleDao projectRoleDao;
    @Autowired
    private ProjectAuthorityDao projectAuthorityDao;
    @Autowired
    private ProjectPermissionDao projectPermissionDao;
    @Autowired
    private ProjectMemberService projectMemberService;

    private XmlParser xmlParser = new XmlParser();
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Event(on = "CREATE_PROJECT_USER", point = Event.Point.AFTER)
    public void afterCreateProjectUser(EventContext ctx) throws Exception {
        //
        ProjectUser projectUser = (ProjectUser) ctx.getArguments()[0];
        ProjectCategory category = projectCategoryDao.selectByCode("people");
        ProjectTemplate projectTemplate = getProjectTemplate(category.getCode());
        //
        String projectCode = String.valueOf(10000000 + projectUser.getId());
        //
        Date now = new Date();
        Project project = new Project();
        project.setCode(projectCode);
        project.setTitle(projectUser.getNickname());
        project.setDescription(projectTemplate.getDescription());
        project.setCategoryId(category.getId());
        project.setCreatedAt(now);
        project.setModifiedAt(now);
        project.setCreatedById(projectUser.getId());
        project.setModifiedById(projectUser.getId());
        //
        projectDao.insert(project);
        // 更新 注册用户的 project id
        projectUser.setProjectId(project.getId());
        projectUserDao.update(projectUser);
        //
        for(ProjectFeature projectFeature : projectTemplate.getProjectFeatures()) {
            projectFeature.setProjectId(project.getId());
            projectFeatureDao.insert(projectFeature);
        }
        //
        for(ProjectRole projectRole : projectTemplate.getProjectRoles()) {
            // project role
            projectRole.setProjectId(project.getId());
            projectRoleDao.insert(projectRole);
            for(ProjectAuthority projectAuthority : projectTemplate.getProjectAuthorities(projectRole)) {
                // project authority
                ProjectAuthority _projectAuthority = projectAuthorityDao.selectByCode(projectAuthority.getCode());
                // project permission
                ProjectPermission projectPermission = new ProjectPermission();
                projectPermission.setProjectId(project.getId());
                projectPermission.setProjectRoleId(projectRole.getId());
                projectPermission.setProjectAuthorityId(_projectAuthority.getId());
                projectPermission.setEnabled(projectAuthority.isEnabled());
                //
                projectPermissionDao.insert(projectPermission);
            }
        }
        //

    }

    public ProjectTemplate getProjectTemplate(String categoryCode) throws Exception {
        //
        Assert.hasText(categoryCode);
        String resourceLocation = String.format(RESOURCE_PATTERN, categoryCode);
        Resource resource = resourceLoader.getResource(resourceLocation);
        //
        if(resource.exists()==false) {
            throw new ServiceException(ServiceExceptionCode.PROJECT_CATEGORY_UNSUPPORTED,
                    String.format("Unsupported project category %s", categoryCode));
        }
        //
        return parse(resource);
    }

    protected ProjectTemplate parse(Resource resource) throws Exception {
        Document xmlDoc = xmlParser.parse(resource);
        Element projectEle = xmlDoc.getDocumentElement();
        //
        ProjectTemplate projectTemplate = new ProjectTemplate();
        Element descriptionEle = DomUtils.getChildElementByTagName(projectEle, "description");
        projectTemplate.setDescription(DomUtils.getTextValue(descriptionEle));
        // project features
        Element featuresEle = DomUtils.getChildElementByTagName(projectEle, "features");
        List<Element> featureEles = DomUtils.getChildElementsByTagName(featuresEle, "feature");
        for(Element featureEle : featureEles) {
            ProjectFeature projectFeature = new ProjectFeature();
            projectFeature.setCode(featureEle.getAttribute("code"));
            projectFeature.setLabel(featureEle.getAttribute("label"));
            projectFeature.setEnabled(true);
            String level = featureEle.getAttribute("level");
            if(StringUtils.hasText(level)) {
                projectFeature.setLevel(Integer.parseInt(level));
            }
            projectTemplate.addProjectFeature(projectFeature);
        }
        // project attributes
        Element attributesEle = DomUtils.getChildElementByTagName(projectEle, "attributes");
        List<Element> attributeEles = DomUtils.getChildElementsByTagName(featuresEle, "attribute");
        for(Element attributeEle : attributeEles) {
            ProjectAttribute projectAttribute = new ProjectAttribute();
            projectAttribute.setName(attributeEle.getAttribute("name"));
            projectAttribute.setValue(attributeEle.getAttribute("value"));
            projectAttribute.setType(attributeEle.getAttribute("type"));
            String level = attributeEle.getAttribute("level");
            if(StringUtils.hasText(level)) {
                projectAttribute.setLevel(Integer.parseInt(level));
            }
            projectTemplate.addProjectAttribute(projectAttribute);
        }
        // project roles
        Element rolesEle = DomUtils.getChildElementByTagName(projectEle, "roles");
        List<Element> roleEles = DomUtils.getChildElementsByTagName(rolesEle, "role");
        for(Element roleEle : roleEles) {
            // project role
            ProjectRole projectRole = new ProjectRole();
            projectRole.setLabel(roleEle.getAttribute("label"));
            String label = roleEle.getAttribute("level");
            if(StringUtils.hasText(label)) {
                projectRole.setLevel(Integer.parseInt(label));
            }
            // project authorities
            List<ProjectAuthority> projectAuthorities = new ArrayList<ProjectAuthority>();
            List<Element> _authorityEles = DomUtils.getChildElementsByTagName(roleEle, "authority");
            for(Element authorityEle : _authorityEles) {
                // project authority
                ProjectAuthority projectAuthority = new ProjectAuthority();
                projectAuthority.setCode(authorityEle.getAttribute("code"));
                projectAuthority.setEnabled("true".equals(authorityEle.getAttribute("enabled")));
                projectAuthorities.add(projectAuthority);
            }
            //
            projectTemplate.addProjectRoleAuthorities(projectRole, projectAuthorities);
        }
        //
        return projectTemplate;
    }

}
