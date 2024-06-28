package fr.projet.compet.vue;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LogonDialog extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnLogon;
	private JTextField txtNomUtilisateur;
	private JPasswordField txtMotDePasse;
	private JLabel lblNomUtilisateur;
	private JLabel lblMotDePasse;
	private static UserData userData;
	
	public LogonDialog(UserData userData) {
		//Configuration du gestionnaire de répartition
		setLayout(new GridLayout(3,2));
		this.userData = userData;
		
		//Création des composants
		lblNomUtilisateur = new JLabel("Nom d'Utilisateur:");
		txtNomUtilisateur = new JTextField();
		lblMotDePasse = new JLabel("Mot de passe:");
		txtMotDePasse = new JPasswordField();
		btnLogon = new JButton("Connexion");
		
		//ajout de composants à l'interface graphique
		this.add(lblNomUtilisateur);
		this.add(txtNomUtilisateur);
		this.add(lblMotDePasse);
		this.add(txtMotDePasse);
		this.add(btnLogon);
		
		
		//Ajout du bouton au ActionListener
		btnLogon.addActionListener(new LogonAction(this, userData));
	}
	
	public String getNomUtilisateur() {
		return txtNomUtilisateur.getText();
	}
	
	public String getMotDePasse() {
		return new String(txtMotDePasse.getPassword());
	}
	
	public static void main(String[] args) {
		
	}
}
