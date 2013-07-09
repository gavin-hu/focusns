package org.focusns.common.mail;

import org.focusns.common.exception.ServiceException;
import org.focusns.common.exception.ServiceExceptionCode;
import org.focusns.common.mail.config.MailConfig;
import org.focusns.common.mail.config.MailFactory;
import org.springframework.context.expression.BeanExpressionContextAccessor;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import java.util.Map;

public class DefaultMailService implements MailService {

    private MailFactory mailFactory;
    private JavaMailSender mailSender;
    //
    private StandardEvaluationContext evaluationContext;
    private ExpressionParser expressionParser = new SpelExpressionParser();

    public void setMailFactory(MailFactory mailFactory) {
        this.mailFactory = mailFactory;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String messageId, Map<String, Object> context) {
        //
        send(messageId, null, context);
    }

    @Override
    public void send(String messageId, String[] to, Map<String, Object> context) {
        try {
            MailConfig mailConfig = mailFactory.find(messageId);
            if(to!=null) { // override default config
                mailConfig.setTo(to);
            }
            MimeMessage message = prepareMessage(mailConfig, context);
            //
            mailSender.send(message);
            //
        } catch (Exception e) {
            throw new ServiceException(ServiceExceptionCode.MAIL_SEND_EXCEPTION, "邮件发送异常...", e);
        }
    }

    protected MimeMessage prepareMessage(MailConfig mailConfig, Map<String, Object> context) throws Exception {
        //
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        // from
        if(StringUtils.hasText(mailConfig.getFrom())) {
            String from = evaluate(mailConfig.getFrom(), context);
            if(from.contains("|")) {
                String[] parts = StringUtils.split(from, "|");
                mimeMessageHelper.setFrom(parts[0], parts[1]);
            } else {
                mimeMessageHelper.setFrom(from);
            }
        }
        // to
        for(String to : mailConfig.getTo()) {
            to = evaluate(to, context);
            if(to.contains("|")) {
                String[] parts = StringUtils.split(to, "|");
                mimeMessageHelper.addTo(parts[0], parts[1]);
            } else {
                mimeMessageHelper.addTo(to);
            }
        }
        // cc
        for(String cc : mailConfig.getCc()) {
            cc = evaluate(cc, context);
            if(cc.contains("|")) {
                String[] parts = StringUtils.split(cc, "|");
                mimeMessageHelper.addTo(parts[0], parts[1]);
            } else {
                mimeMessageHelper.addTo(cc);
            }
        }
        // subject
        String subject = evaluate(mailConfig.getSubject(), context);
        mimeMessageHelper.setSubject(subject);
        // content
        String content = evaluate(mailConfig.getContent(), context);
        mimeMessageHelper.setText(content, true);
        //
        return mimeMessage;
    }

    protected String evaluate(String string, Map<String, Object> ctx) {
        Expression expression = expressionParser.parseExpression(string, ParserContext.TEMPLATE_EXPRESSION);
        EvaluationContext evaluationContext = getEvaluationContext();
        return expression.getValue(evaluationContext, ctx, String.class);
    }

    private EvaluationContext getEvaluationContext() {
        if (evaluationContext == null) {
            evaluationContext = new StandardEvaluationContext();
            evaluationContext.addPropertyAccessor(new BeanExpressionContextAccessor());
            evaluationContext.addPropertyAccessor(new MapAccessor());
        }
        return evaluationContext;
    }

}
