package com.diploma.demo.utils;

import com.diploma.demo.view.mail.IncomingMailPage;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.mail.*;
import java.io.Console;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChekingMails {


    public static List<String> check(String host, String storeType, String user,
                             String password)
    {
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            List<String> messagesList = new ArrayList<>();
            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];

                messagesList.add("---------------------------------");
                messagesList.add("Email Number " + (i + 1));
                messagesList.add("Subject: " + message.getSubject());
                messagesList.add("From: " + message.getFrom()[0]);
                messagesList.add("Text: " + message.getContent().toString());
/*
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
*/
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();
            return messagesList;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;
    }
    public static List<String> credentials() {

        String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "diplomaTestMail@gmail.com";// change accordingly
        String password = "diplomaMail";// change accordingly

        return check(host, mailStoreType, username, password);

    }
}
