package org.focusns.common.dao.mybatis;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean implements ResourceLoaderAware {

    private String mapperBasePackage;
    private ResourcePatternResolver resourcePatternResolver;

    public void setMapperBasePackage(String mapperBasePackage) {
        this.mapperBasePackage = mapperBasePackage;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //
        Assert.hasText(mapperBasePackage, "Mapper base package can not be null!");
        //
        String mapperLocationPattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(mapperBasePackage) + "/**/*Mapper.xml";
        Resource[] mapperLocations = resourcePatternResolver.getResources(mapperLocationPattern);
        Assert.notEmpty(mapperLocations, String.format("Mapper Locations is empty under package %s!", mapperBasePackage));
        //
        super.setMapperLocations(mapperLocations);
        //
        super.afterPropertiesSet();
    }
}
