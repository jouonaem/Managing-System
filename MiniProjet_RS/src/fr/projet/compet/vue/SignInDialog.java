package fr.projet.compet.vue;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignInDialog extends JPanel implements InscriptionConnexion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnSignIn;
	private JButton btnLogon;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtBirthdate;
	private JPasswordField txtMdp;
	private JPasswordField txtConfMdp;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblBirthdate;
	private JLabel lblMdp;
	private JLabel lblConfMdp;
	private UserData userData;

	public SignInDialog() {
		setLayout(new GridLayout(8, 2));

		// Création des composants
		lblNom = new JLabel("Nom:");
		txtNom = new JTextField();
		lblPrenom = new JLabel("Prenom:");
		txtPrenom = new JTextField();
		lblEmail = new JLabel("Adresse Mail:");
		txtEmail = new JTextField();
		lblPhone = new JLabel("Téléphone:");
		txtPhone = new JTextField();
		lblBirthdate = new JLabel("Date de Naissance:");
		txtBirthdate = new JTextField();
		lblMdp = new JLabel("Mot de passe:");
		txtMdp = new JPasswordField();
		lblConfMdp = new JLabel("Confirmation mot de passe:");
		txtConfMdp = new JPasswordField();
		btnSignIn = new JButton("Inscription");
		btnLogon = new JButton("Connexion");
		
		//Création d'un utilisateur
		userData = new UserData();
		
		//Ajout des composants à l'interface graphique
		this.add(lblNom);
		this.add(txtNom);
		this.add(lblPrenom);
		this.add(txtPrenom);
		this.add(lblEmail);
		this.add(txtEmail);
		this.add(lblPhone);
		this.add(txtPhone);
		this.add(lblBirthdate);
		this.add(txtBirthdate);
		this.add(lblMdp);
		this.add(txtMdp);
		this.add(lblConfMdp);
		this.add(txtConfMdp);
		this.add(btnSignIn);
		this.add(btnLogon);
		
		//Ajout du bouton au ActionListener
		btnSignIn.addActionListener(new SignInAction(this));
		btnLogon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openLogonDialog();
			}
		});
	}
	
	protected void openLogonDialog() {
		// TODO Auto-generated method stub
		UserData userData = new UserData();
		userData.setUsername(getUserId());
		userData.setPassword(getConfMotDePasse());
		
		//Afficher la fenêtre LogonDialog
		LogonDialog lgD = new LogonDialog(userData);
		JFrame frame = new JFrame("Connexion");
		frame.getContentPane().add(lgD);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

	public String getNom() {
		return txtNom.getText();
	}
	
	public String getPrenom() {
		return txtPrenom.getText();
	}
	
	public String getEmail() {
		return txtEmail.getText();
	}
	
	public String getPhoneNumber() {
		return txtPhone.getText();
	}
	
	public String getBirthDate() {
		return txtBirthdate.getText();
	}
	
	public String getMotDePasse() {
		return new String(txtMdp.getPassword());
	}
	
	public String getConfMotDePasse() {
		return new String(txtConfMdp.getPassword());
	}
	
	public String userId(){
		String nom1;
		String prenom1;
		String name = getNom().toLowerCase();
		String surname = getPrenom().toLowerCase();
		if(name.length()>5) {
			nom1 = name.substring(0,5);
		}
		else {
			nom1 = name;
		}
		if(surname.length()>=3) {
			prenom1 = surname.substring(0,3);
		}
		else {
			prenom1 = surname;
		}
		//Extraction des deux chiffres de l'année de naissance
		String annee = String.valueOf(getBirthDate()).substring(2);
		//Suppression des espaces et des apostrophes
		String id = (nom1 + prenom1).replace(" ","").replace("'","")+annee;
		return id;
	}
	
	public String getUserId() {
		return userId();
	}
	
	public void saveUserData() {
		userData.setUsername(getUserId());
		userData.setPassword(getConfMotDePasse());
	}
	
	public static void main(String[] args) {
		//Création de la JFrame
		JFrame frame = new JFrame("Inscription");
		
		//Création de l'instance  de SignInDialog
		SignInDialog sgD = new SignInDialog();
		
		//Ajout de SignInDialog à la JFrame
		frame.getContentPane().add(sgD);
		
		//Configuration de la JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); //Centrer le fenêtre
		frame.pack();
		frame.setVisible(true);
	}
}
