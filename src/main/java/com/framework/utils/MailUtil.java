package com.framework.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

import com.framework.base.ConfigManager;

public class MailUtil {

    public static void sendReport(String env) {
        try {
            // Read from existing config-<env>.properties
            String recipients = ConfigManager.get("mail.recipients");
            String subject = ConfigManager.get("mail.subject");
            String sender = ConfigManager.get("mail.sender");
            String username = ConfigManager.get("mail.smtp.username");
            String password = ConfigManager.get("mail.smtp.password");
            String host = ConfigManager.get("mail.smtp.host");
            String port = ConfigManager.get("mail.smtp.port");

            // Mail server props
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

            // Auth session
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Compose message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));

            for (String recipient : recipients.split(",")) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.trim()));
            }
            message.setSubject(subject);

            // Body
            MimeBodyPart body = new MimeBodyPart();
            body.setText("Hi Team,\n\nPlease find attached the automation report for "
                    + env.toUpperCase() + ".\n\nRegards,\nAutomation Bot");

            // Allure report attachment
            MimeBodyPart attachment = new MimeBodyPart();
            File report = new File("target/allure-report/index.html");
            if (report.exists()) {
                attachment.attachFile(report);
            }

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);

            // Send
            Transport.send(message);
            System.out.println("✅ Mail sent successfully to: " + recipients);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to send mail", e);
        }
    }
}

