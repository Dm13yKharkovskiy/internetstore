package service.impl;

import model.Code;
import org.apache.log4j.Logger;
import service.MessageService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MessageServiceImpl implements MessageService {
    private Logger logger = Logger.getLogger(MessageServiceImpl.class);
    private final String username = "webstore.helper@gmail.com";
    private final String password = "mymmiaksjpfmyyej";


    @Override
    public void sendMessage(Code code, String email) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Подтверждение заказа в магазине Web-store");
            message.setText("Ваш заказа №" + code.getIdOrder() + "\nКод подтверждения: " + code.getCode());

            Transport.send(message);

            logger.info("Confirm code from order " + code.getIdOrder() + " send to email success");

        } catch (
                MessagingException e) {
            logger.info("Confirm code from order " + code.getIdOrder() + " send to email failure");
        }
    }
}
