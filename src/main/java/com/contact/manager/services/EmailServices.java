package com.contact.manager.services;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class EmailServices {

    public boolean sendEmail(String subject, String message, String receiver) {
        
        boolean flag = false;
        
        // Host for Gmail SMTP server
        String host = "smtp.gmail.com";
        
        // Sender email address
        String sender = "harveer.2125ec1067@kiet.edu";
        
        // Getting system properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // Step 1: Get the session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {                
                return new PasswordAuthentication("harveer.2125ec1067@kiet.edu", "bsep cwka oect htqc");
            }            
        });
        
        // Enable debugging output
        session.setDebug(true);
        
        // Step 2: Compose the message (text or multimedia)
        MimeMessage mimeMessage = new MimeMessage(session);
        
        try {
            // Set sender email address
            mimeMessage.setFrom(new InternetAddress(sender));
            // Add recipient to the message
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            // Set subject of the message
            mimeMessage.setSubject(subject);
            // Set text content of the message
            mimeMessage.setText(message);
            
            // Step 3: Send the message using Transport class
            Transport.send(mimeMessage);
            
            System.out.println("Email sent successfully.");
            
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
                    
        return flag;
    }
}
