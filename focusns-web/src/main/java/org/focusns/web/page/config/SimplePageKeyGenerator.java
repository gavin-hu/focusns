package org.focusns.web.page.config;

import org.focusns.common.web.page.config.PageKeyGenerator;
import org.focusns.model.core.Project;
import org.focusns.service.core.ProjectService;
import org.focusns.web.Keys;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SimplePageKeyGenerator implements PageKeyGenerator, BeanFactoryAware {

    private static final String[] PARAM_NAMES_INCLUDE = new String[]{"category", "mode"};
    private static final Map<String, String>  SHORT_PARAM_NAMES_MAPPING = new HashMap<String, String>();

    static {
        SHORT_PARAM_NAMES_MAPPING.put("c", "category");
        SHORT_PARAM_NAMES_MAPPING.put("m", "mode");
    }

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public String generate(String name, Map<String, String> params) {
        //
        for(Map.Entry<String, String> entry : SHORT_PARAM_NAMES_MAPPING.entrySet()) {
            if(params.containsKey(entry.getKey())) {
                params.put(entry.getValue(), params.get(entry.getKey()));
            }
        }
        //
        String projectCode = params.get(Keys.PARAMETER_PROJECT_CODE);
        if(StringUtils.hasText(projectCode)) {
            ProjectService projectService = beanFactory.getBean(ProjectService.class);
            Project project = projectService.getProject(projectCode);
            Assert.notNull(project);
            //
            params.put("category", project.getCategory().getCode());
        }
        //
        StringBuilder pageKeyBuilder = new StringBuilder(name);
        int count = 0;
        for(String paramName : PARAM_NAMES_INCLUDE) {
            if(params.containsKey(paramName)) {
                count++;
                if(count==1) {
                    pageKeyBuilder.append("?");
                } else {
                    pageKeyBuilder.append("&");
                }
                pageKeyBuilder.append(paramName).append("=").append(params.get(paramName));
            }
        }
        return pageKeyBuilder.toString();
    }


}
