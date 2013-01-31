
package org.focusns.common.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.beans.factory.xml.PluggableSchemaResolver;
import org.springframework.core.io.Resource;
import org.springframework.util.ClassUtils;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.springframework.util.xml.XmlValidationModeDetector;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;

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
	
	public Document parse(Resource resource) throws Exception {
		return documentBuildFactory.newDocumentBuilder().parse(resource.getInputStream());
	}
	
	public Document parseAndValidate(Resource resource) throws Exception {
		InputSource inputSource = new InputSource(resource.getInputStream());
		int validationMode = validationModeDetector.detectValidationMode(resource.getInputStream());
		return documentLoader.loadDocument(inputSource, entityResolver, errorHandler, validationMode, false);
	}

}
