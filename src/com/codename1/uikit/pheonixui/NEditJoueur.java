/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Joueur;
import com.codename1.sport.services.ServiceJoueur;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.List;

public class NEditJoueur extends BaseForm {

    public static Joueur joueur = new Joueur();

    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();

    private Label nomLabel = new Label("Nom Joueur");
    private Label prenomLabel = new Label("Prenom Joueur");
    private Label emailLabel = new Label("Email Joueur");
    private Label numeroLabel = new Label("Numero Joueur");
    private Label equipeLabel = new Label("Séléctionner équipe");

    private TextField nomtextfield = new TextField("", "Nom Joueur");
    private TextField prenomextfield = new TextField("", "Prenom Joueur");
    private TextField emailextfield = new TextField("", "Email Joueur");
    private TextField numeroextfield = new TextField("", "Numero Joueur");

    
    private Button btnAjouButton = new Button("Modifier");
    private Button btnannulButton = new Button("Annuler");

    public NEditJoueur() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public NEditJoueur(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        remplirJoueur();
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Add Joueur");
        setName("InscriForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");

        gui_Container_1.addComponent(gui_Component_Group_1);

        Container c1 = new Container(new FlowLayout(CENTER, CENTER));
        Container c2 = new Container(new FlowLayout(CENTER, CENTER));
        Container c3 = new Container(new FlowLayout(CENTER, CENTER));
        Container c4 = new Container(new FlowLayout(CENTER, CENTER));
       

        c1.addComponent(nomLabel);
        c1.addComponent(nomtextfield);

        c2.addComponent(prenomLabel);
        c2.addComponent(prenomextfield);

        c3.addComponent(emailLabel);
        c3.addComponent(emailextfield);

        c4.addComponent(numeroLabel);
        c4.addComponent(numeroextfield);

        gui_Component_Group_1.addComponent(c1);
        gui_Component_Group_1.addComponent(c2);
        gui_Component_Group_1.addComponent(c3);
        gui_Component_Group_1.addComponent(c4);
        

        
        

        //bouton ajouter
        gui_Container_1.add(btnAjouButton);
        btnAjouButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!nomtextfield.getText().equals("") && !prenomextfield.getText().equals("") && !emailextfield.getText().equals("") && !numeroextfield.getText().equals("")) {
                  

                        Joueur j = new Joueur(joueur.getId(),joueur.getEquipes_id(), nomtextfield.getText(), prenomextfield.getText(), emailextfield.getText(), Integer.parseInt(numeroextfield.getText().toString()));
                        ServiceJoueur dao = ServiceJoueur.getInstance();

                        if (dao.edit(j)) {
                            Dialog.show("Vérification", "Joueur modifié avec sucéés ", new Command("OK"));
                            new NAllJoueurs().show();
                        }
                    
                } else {
                    Dialog.show("Vérification", "Les champs ne doivent pas etre null ", new Command("OK"));
                }
            }
        });

        //bouton annuler
        gui_Container_1.add(btnannulButton);
        btnannulButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //new FDetailPublication(_pub).show();
            }
        });

    }

    public void remplirJoueur() {
        nomtextfield.setText(joueur.getNom());
        prenomextfield.setText(joueur.getPrenom());
        emailextfield.setText(joueur.getEmail());
        numeroextfield.setText(Integer.toString(joueur.getNumero()));
    }
    
    @Override
    protected boolean isCurrentEquipe() {
        return true;
    }
}
