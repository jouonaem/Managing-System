package fr.projet.compet.vue;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;

public class EmailResponder {
	public static void respondToEmail(Message receivedMessage, Session smtpSession) {
        try {
            // Récupérer les informations du message reçu
            String recipient = ((InternetAddress) receivedMessage.getFrom()[0]).getAddress();
            String subject = receivedMessage.getSubject();
            String content = getTextFromMessage(receivedMessage);

            // Créer un nouveau message de réponse
            MimeMessage response = new MimeMessage(smtpSession);  //receivedMessage.getSession()
            response.setFrom(new InternetAddress("no.reply.devapps@gmail.com"));
            response.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            response.setSubject("RE: " + subject);
            response.setText("Bonjour,\n\nCeci est un mail automatique. Veuillez ne pas répondre.\n\nCordialement,\nL'équipe devApps");

            // Envoyer le message de réponse
            Transport.send(response);
            System.out.println("Réponse envoyée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            MimeBodyPart bodyPart = (MimeBodyPart) mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent().toString());
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                // Vous pouvez utiliser une bibliothèque comme jsoup pour convertir HTML en texte brut si nécessaire
                result.append(html);
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws MessagingException { 
    	 // Configurer les propriétés SMTP
        Properties smtpProps = new Properties();
        smtpProps.put("mail.smtp.host", "smtp.gmail.com");
        smtpProps.put("mail.smtp.port", "587");
        smtpProps.put("mail.smtp.auth", "true");
        smtpProps.put("mail.smtp.starttls.enable", "true");

        // Créer une session SMTP
        Session smtpSession = Session.getInstance(smtpProps, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("no.reply.devapps@gmail.com", "mnwv xqah vtvq ujce");
            }
        });
        
     // Configuration pour le serveur IMAP
        Properties imapProps = new Properties();
        imapProps.put("mail.store.protocol", "imaps");
        imapProps.put("mail.imaps.host", "imap.gmail.com");
        imapProps.put("mail.imaps.port", "993");
        imapProps.put("mail.imaps.ssl.enable", "true");

        // Connexion au serveur de messagerie et récupération du message reçu
        Session imapSession = Session.getInstance(imapProps, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("no.reply.devapps@gmail.com", "mnwv xqah vtvq ujce");
            }
        });
        Store store = imapSession.getStore("imaps");
        store.connect();
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);
        Message[] messages = inbox.getMessages();

        
        if(messages.length>0) {
        	  // Répondre au dernier message reçu
            respondToEmail(messages[messages.length - 1], smtpSession);
        }
        else {
        	System.out.println("Aucun message trouvé dans la boîte de réception.");
        }
        
     // Fermer les connexions
        inbox.close(false);
        store.close();
    }
}
