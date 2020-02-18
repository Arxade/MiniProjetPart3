package interfaces;
import controleurs.ControleurPrincipal;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controleurs.ControleurTransaction;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
        private ControleurTransaction ctrlTransaction;

	public FenetreVente(String[] lesProduits, ControleurPrincipal ctrlPrincipal) {
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

                ctrlTransaction = ctrlPrincipal.createControleurTransaction();
		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btVente)
                {
                    ctrlTransaction.enregistrerVente(combo.getSelectedItem().toString(), Integer.parseInt(txtQuantite.getText()), FenetreVente.this);
                }
	}

}
