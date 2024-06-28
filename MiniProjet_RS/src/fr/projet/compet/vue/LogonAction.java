package fr.projet.compet.vue;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;


public class LogonAction extends AbstractAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LogonDialog logonDialog;
	private UserData userData;
	
	public LogonAction(LogonDialog logonDialog, UserData userData) {
		this.logonDialog = logonDialog;
		this.userData = userData;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String nomUtilisateur = logonDialog.getNomUtilisateur();
		String motDePasse = logonDialog.getMotDePasse();
		
		if(nomUtilisateur.equals(userData.getUsername()) && motDePasse.equals(userData.getPassword())) {
			String message = "L'utilisateur "+nomUtilisateur+" est connecté avec le mot de passe "+motDePasse;
			JOptionPane.showMessageDialog((Component)logonDialog, message);
		}
		else {
			String error = "Nom d'utilisateur ou mot de passe incorrect";
			JOptionPane.showMessageDialog((Component)logonDialog, error);
		}
		
	}
}
