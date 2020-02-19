package interfaces;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import controleurs.*;
import java.util.Arrays;

public class FenetreAccueil extends JFrame implements ActionListener, WindowListener {

    private JButton btAjouter, btSupprimer, btSelectionner;
    private JTextField txtAjouter;
    private JLabel lbNbCatalogues;
    private JComboBox cmbSupprimer, cmbSelectionner;
    private TextArea taDetailCatalogues;
    private ControleurPrincipal ctrl = new ControleurPrincipal();

    public FenetreAccueil() {
        setTitle("Catalogues");
        setBounds(500, 500, 200, 125);
        Container contentPane = getContentPane();
        JPanel panInfosCatalogues = new JPanel();
        JPanel panNbCatalogues = new JPanel();
        JPanel panDetailCatalogues = new JPanel();
        JPanel panGestionCatalogues = new JPanel();
        JPanel panAjouter = new JPanel();
        JPanel panSupprimer = new JPanel();
        JPanel panSelectionner = new JPanel();
        panNbCatalogues.setBackground(Color.white);
        panDetailCatalogues.setBackground(Color.white);
        panAjouter.setBackground(Color.gray);
        panSupprimer.setBackground(Color.lightGray);
        panSelectionner.setBackground(Color.gray);

        panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
        lbNbCatalogues = new JLabel();
        panNbCatalogues.add(lbNbCatalogues);

        taDetailCatalogues = new TextArea();
        taDetailCatalogues.setEditable(false);
        new JScrollPane(taDetailCatalogues);
        taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
        panDetailCatalogues.add(taDetailCatalogues);

        panAjouter.add(new JLabel("Ajouter un catalogue : "));
        txtAjouter = new JTextField(10);
        panAjouter.add(txtAjouter);
        btAjouter = new JButton("Ajouter");
        panAjouter.add(btAjouter);

        panSupprimer.add(new JLabel("Supprimer un catalogue : "));
        cmbSupprimer = new JComboBox();
        cmbSupprimer.setPreferredSize(new Dimension(100, 20));
        panSupprimer.add(cmbSupprimer);
        btSupprimer = new JButton("Supprimer");
        panSupprimer.add(btSupprimer);

        panSelectionner.add(new JLabel("Selectionner un catalogue : "));
        cmbSelectionner = new JComboBox();
        cmbSelectionner.setPreferredSize(new Dimension(100, 20));
        panSelectionner.add(cmbSelectionner);
        btSelectionner = new JButton("Selectionner");
        panSelectionner.add(btSelectionner);

        panGestionCatalogues.setLayout(new BorderLayout());
        panGestionCatalogues.add(panAjouter, "North");
        panGestionCatalogues.add(panSupprimer);
        panGestionCatalogues.add(panSelectionner, "South");

        panInfosCatalogues.setLayout(new BorderLayout());
        panInfosCatalogues.add(panNbCatalogues, "North");
        panInfosCatalogues.add(panDetailCatalogues, "South");

        contentPane.add(panInfosCatalogues, "North");
        contentPane.add(panGestionCatalogues, "South");
        pack();

        btAjouter.addActionListener(this);
        btSupprimer.addActionListener(this);
        btSelectionner.addActionListener(this);

        
        majAffichage();
        addWindowListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAjouter) {
            String texteAjout = txtAjouter.getText();
            if (!texteAjout.equals("")) {
                ctrl.addCatalogue(texteAjout);
                System.out.println("ajouter le catalogue " + texteAjout);
                majAffichage();
                txtAjouter.setText(null);
            }
        }
        if (e.getSource() == btSupprimer) {
            String texteSupprime = (String) cmbSupprimer.getSelectedItem();
            if (texteSupprime != null) {
                ctrl.removeCatalogue(texteSupprime);
                System.out.println("supprime catalogue " + texteSupprime);
                majAffichage();
            }
        }
        if (e.getSource() == btSelectionner) {
            String texteSelection = (String) cmbSelectionner.getSelectedItem();
            if (texteSelection != null) {
                System.out.println("selectionne catalogue " + texteSelection);
                ctrl.setCatalogue(texteSelection);
                new FenetrePrincipale(ctrl);
                this.setVisible(false);
            }
        }
    }

    private void modifierListesCatalogues(String[] nomsCatalogues) {
        cmbSupprimer.removeAllItems();
        cmbSelectionner.removeAllItems();
        if (nomsCatalogues != null) {
            for (int i = 0; i < nomsCatalogues.length; i++) {
                cmbSupprimer.addItem(nomsCatalogues[i]);
                cmbSelectionner.addItem(nomsCatalogues[i]);
            }
        }
    }

    private void modifierNbCatalogues(int nb) {
        lbNbCatalogues.setText(nb + " catalogues");
    }

    private void modifierDetailCatalogues(String[] detailCatalogues) {
        taDetailCatalogues.setText("");
        if (detailCatalogues != null) {
            for (int i = 0; i < detailCatalogues.length; i++) {
                taDetailCatalogues.append(detailCatalogues[i] + "\n");
            }
        }
    }
    
    public void windowClosing(WindowEvent arg0) {
        System.out.println("Au revoir");
        System.exit(0);
    }
    
    	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
    
    private void majAffichage()
    {
        String[] tab = ctrl.getNomsDesCatalogues();
        modifierListesCatalogues(tab);
        String[] tab2 = ctrl.getDetailsDesCatalogues();
        modifierDetailCatalogues(tab2);
        modifierNbCatalogues(ctrl.getNombreDeCatalogues());
    }

    public static void main(String[] args) {
        new FenetreAccueil();
    }
}
