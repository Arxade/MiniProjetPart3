package interfaces;
import controleurs.ControleurPrincipal;
import controleurs.ControleurTransaction;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
        private ControleurTransaction ctrlTransaction;

	public FenetreAchat(String[] lesProduits, ControleurPrincipal ctrlPrincipal) {

		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� achet�e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);
                ctrlTransaction = ctrlPrincipal.createControleurTransaction();

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btAchat)
                {
                    ctrlTransaction.enregistrerAchat(combo.getSelectedItem().toString(), Integer.parseInt(txtQuantite.getText()) , FenetreAchat.this);
                }
	}

}
