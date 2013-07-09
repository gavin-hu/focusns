package org.focusns.common.mail.config.xml;

import org.focusns.common.mail.config.AbstractMailFactory;
import org.focusns.common.mail.config.MailConfig;
import org.focusns.common.xml.XmlParser;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class XmlMailFactory extends AbstractMailFactory implements ResourceLoaderAware {

    private String prefix = "classpath*:/mails";
    private String suffix = ".xml";
    //
    private XmlParser xmlParser = new XmlParser();
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    public XmlMailFactory() {
    }

    public XmlMailFactory(String mailConfigLocation) {
        this.prefix = mailConfigLocation;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    protected List<MailConfig> loadMails() throws Exception {
        //
        List<MailConfig> mailConfigHolder = new ArrayList<MailConfig>();
        //
        String locationPattern = String.format("%s/**/*%s", prefix, suffix);
        //
        ResourcePatternResolver rpr = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        Resource[] resources = rpr.getResources(locationPattern);
        for(Resource resource : resources) {
            //
            Document xmlDoc = xmlParser.parseAndValidate(resource);
            parse(xmlDoc.getDocumentElement(), mailConfigHolder);
        }
        //
        return mailConfigHolder;
    }

    public void parse(Element mailEle, List<MailConfig> mailConfigHolder) {
        //
        List<Element> messageEles = DomUtils.getChildElementsByTagName(mailEle, "message");
        for(Element messageEle : messageEles) {
            // message id
            String messageId = messageEle.getAttribute("id");
            // from
            Element fromEle = DomUtils.getChildElementByTagName(messageEle, "from");
            String from = DomUtils.getTextValue(fromEle).trim();
            // to
            String[] toArray = new String[0];
            List<Element> toEles = DomUtils.getChildElementsByTagName(messageEle, "to");
            for(Element toEle : toEles) {
                String to = DomUtils.getTextValue(toEle).trim();
                toArray = StringUtils.addStringToArray(toArray, to);
            }
            // cc
            String[] ccArray = new String[0];
            List<Element> ccEles = DomUtils.getChildElementsByTagName(messageEle, "cc");
            for(Element ccEle : ccEles) {
                String cc = DomUtils.getTextValue(ccEle).trim();
                ccArray = StringUtils.addStringToArray(ccArray, cc);
            }
            // subject
            Element subjectEle = DomUtils.getChildElementByTagName(messageEle, "subject");
            String subject = DomUtils.getTextValue(subjectEle).trim();
            // content
            Element contentEle = DomUtils.getChildElementByTagName(messageEle, "content");
            String content = DomUtils.getTextValue(contentEle).trim();
            //
            MailConfig mailConfig = new MailConfig();
            mailConfig.setMessageId(messageId);
            mailConfig.setFrom(from);
            mailConfig.setTo(toArray);
            mailConfig.setCc(ccArray);
            mailConfig.setSubject(subject);
            mailConfig.setContent(content);
            //
            mailConfigHolder.add(mailConfig);
        }
    }

}

