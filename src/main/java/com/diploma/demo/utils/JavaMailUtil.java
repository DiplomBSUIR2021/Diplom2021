package com.diploma.demo.utils;

import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;



public class JavaMailUtil {

    public static void sendMail(String recepient, String ccrecepient, String theme, String body) throws Exception {
        System.out.println("Starting...");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myAccountEmail = "diplomaTestMail@gmail.com";
        String password = "diplomaMail";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });


        Message message = prepareMessage(session,myAccountEmail,recepient,ccrecepient,theme,body);
        Transport.send(message);
        System.out.println("Success");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String ccrecepient, String title, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient) );
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccrecepient));
            message.setSubject(title);
            message.setText(text);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
