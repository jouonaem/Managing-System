package fr.projet.compet.vue;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class SignInAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SignInDialog signInDialog;
	
	public SignInAction(SignInDialog signInDialog) {
		this.signInDialog = signInDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String nomProfil = signInDialog.getNom();
		String prenomProfil = signInDialog.getPrenom();
		String mdPprofil = signInDialog.getMotDePasse();
		String conf = signInDialog.getConfMotDePasse();
		String userId = signInDialog.userId();
		String adresseMail = signInDialog.getEmail();
		
		StringBuilder msg = new StringBuilder();
		/**
		 * ANTI-PLAGIAT : Ce stringbuilder est inspiré de chat GPT
		 */
		msg.append("Bonjour"+prenomProfil+",")
	       .append("\n\n")
	       .append("Merci de vous être inscrit(e) sur notre application. Nous sommes heureux de l’intérêt que vous portez à ce produit que nous avons développé. Vos identifiants sont les suivants :")
	       .append("\n\n")
	       .append("Nom d’utilisateur : "+userId)
	       .append("\n")
	       .append("Mot de passe : "+conf)
	       .append("\n\n")
	       .append("Ce mail contient vos identifiants de connexion et est strictement personnel. Pour prévenir toute tentative d’usurpation d’identité, veuillez enregistrer vos données dans un répertoire sécurisé et supprimer ce mail. Vos données sont protégées et vous serez notifié pour chaque mise à jour de l’application.")
	       .append("Vous pouvez dès maintenant vous connecter !")
	       .append("\n\n")
	       .append("Cordialement,")
	       .append("\n\n")
	       .append("L’équipe devApps.");

		EmailSender.sendEmail(adresseMail,msg.toString());

			
		if(!mdPprofil.equals(conf)) {
			String error = prenomProfil+" votre mot de passe et sa confirmation ne sont pas identiques!\n Veuillez recommencer svp!";
			JOptionPane.showMessageDialog((Component)signInDialog, error);
		}
		else if(verifyMail(adresseMail)==false) {
			String errorMail = prenomProfil+" votre adresse mail n'est pas conforme\nVoici un exemple : alex@exemple.com";
			JOptionPane.showMessageDialog((Component)signInDialog, errorMail);
		}
		else {
			String message = "Bonjour "+nomProfil+" "+prenomProfil+",\nVotre nom d'utilsateur est : "+userId+"\nMot de passe: "+mdPprofil;
			JOptionPane.showMessageDialog((Component)signInDialog, message);
		}
	}
	
	public boolean verifyMail(String mail) {
		String model = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern motif = Pattern.compile(model);
		Matcher matcher = motif.matcher(mail);
		return matcher.matches();
	}
	
}
