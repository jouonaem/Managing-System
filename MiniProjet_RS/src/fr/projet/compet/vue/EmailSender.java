package fr.projet.compet.vue;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
public class EmailSender {
	public static void sendEmail(String recieverAddress, String message) {
		String fromEmail = "no.reply.devapps@gmail.com";
		String sujet = "Inscription Réussie!";
		// Configuration des propriétés de connexion au serveur de messagerie
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        
        // Authentification
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, "mnwv xqah vtvq ujce"); // Remplacez par votre email et mot de passe
            }
        });

        try {
            // Création du message
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(fromEmail));
            message1.setRecipient(Message.RecipientType.TO, new InternetAddress(recieverAddress));
            message1.setSubject(sujet);
            message1.setText(message);
            
            
            // Envoi de l'email
            Transport.send(message1);

            System.out.println("Email envoyé avec succès!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
}
