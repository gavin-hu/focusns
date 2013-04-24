package org.focusns.common.xml;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.beans.factory.xml.PluggableSchemaResolver;
import org.springframework.util.ClassUtils;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.springframework.util.xml.XmlValidationModeDetector;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public final class XmlParser {

    private static final Log log = LogFactory.getLog(XmlParser.class);

    private DocumentBuilderFactory documentBuildFactory = DocumentBuilderFactory.newInstance();
    //
    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    private ErrorHandler errorHandler = new SimpleSaxErrorHandler(log);

    private XmlValidationModeDetector validationModeDetector = new XmlValidationModeDetector();

    private EntityResolver entityResolver;

    public XmlParser() {
        this(ClassUtils.getDefaultClassLoader());
    }

    public XmlParser(ClassLoader classLoader) {
        this.entityResolver = new PluggableSchemaResolver(classLoader);
    }

    public Document parse(InputStream inputStream) throws Exception {
        return documentBuildFactory.newDocumentBuilder().parse(inputStream);
    }

    public Document parseAndValidate(InputStream inputStream) throws Exception {
        InputSource inputSource = new InputSource(inputStream);
        int validationMode = validationModeDetector.detectValidationMode(inputStream);
        return documentLoader.loadDocument(inputSource, entityResolver, errorHandler, validationMode, false);
    }

}
