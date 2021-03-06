package interfaces;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controleurs.*;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
        private ControleurProduit ctrlProduit;

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(ControleurPrincipal ctrlPrincipal) {	

		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantit� en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
                
                ctrlProduit = ctrlPrincipal.createControleurProduit();
		setVisible(true);
	}
        
    public void actionPerformed(ActionEvent e) {
        if (!txtPrixHT.getText().trim().equals("") && !txtQte.getText().trim().equals("") && !txtNom.getText().trim().equals("")) {
            ctrlProduit.createProduit(txtNom.getText(), Double.parseDouble(txtPrixHT.getText()), Integer.parseInt(txtQte.getText()));
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
        }
    }

}