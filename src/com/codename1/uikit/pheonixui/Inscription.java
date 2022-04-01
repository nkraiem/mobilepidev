/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.capture.Capture;
import com.codename1.sport.entities.User;
import com.codename1.sport.services.ServicesUsers;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ComponentStateChangeEvent;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;




public class Inscription extends com.codename1.ui.Form {

    
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label mailLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Label nomLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Label lastnomLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Label passwordLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Label passwordreLabel = new com.codename1.ui.Label();

    private com.codename1.ui.TextField mailText = new com.codename1.ui.TextField("", "Adresse Mail");
    private com.codename1.ui.TextField nomText = new com.codename1.ui.TextField("","Nom");
    private com.codename1.ui.TextField lastnomText = new com.codename1.ui.TextField("","Prénom");
    private com.codename1.ui.TextField passwordText = new com.codename1.ui.TextField("", "Password", 20, TextField.PASSWORD);
    private com.codename1.ui.TextField passwordreText = new com.codename1.ui.TextField("", "Confirm Password", 20, TextField.PASSWORD);
    
    protected com.codename1.ui.Button btnCapture = new com.codename1.ui.Button("upload");
     protected Label lblimage=new Label();
     
    private boolean usernameErreur = false;
    private boolean mailErreur = false;
    private boolean passwordErreur = false;
     String path="";

    
    private com.codename1.ui.Button btnInscri = new com.codename1.ui.Button();
    private com.codename1.ui.Button btnAnnuler = new com.codename1.ui.Button();
    
    
    public Inscription() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public Inscription(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("InscriTitle");
        
        getContentPane().setUIID("SignInForm");
    }

    

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Inscription");
        setName("InscriForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        Container c1 = new Container(new FlowLayout(CENTER, CENTER));
        Container c2 = new Container(new FlowLayout(CENTER, CENTER));
        Container c3 = new Container(new FlowLayout(CENTER, CENTER));
        Container c4 = new Container(new FlowLayout(CENTER, CENTER));
        Container c5 = new Container(new FlowLayout(CENTER, CENTER));
        Container c6 = new Container(new FlowLayout(CENTER, CENTER));
        

        nomLabel.setText("nom d'utilisateur");
        lastnomLabel.setText("prenom d'utilisateur");
        
        mailLabel.setText("adresse mail");
        passwordLabel.setText("mot de passe");
        passwordreLabel.setText("confirmer votre mot de passe");
        c1.addComponent(nomLabel);
        c1.addComponent(nomText);
        
        c2.addComponent(mailLabel);
        c2.addComponent(mailText);
        c3.addComponent(passwordLabel);
        c3.addComponent(passwordText);
        c4.addComponent(passwordreLabel);
        c4.addComponent(passwordreText);
        
        c5.addComponent(lblimage);
        c5.addComponent(btnCapture);
        
        c6.addComponent(lastnomLabel);
        c6.addComponent(lastnomText);
        
        

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(c1);
        gui_Component_Group_1.addComponent(c6);
        gui_Component_Group_1.addComponent(c2);
        gui_Component_Group_1.addComponent(c3);
        gui_Component_Group_1.addComponent(c4);
        gui_Component_Group_1.addComponent(c5);

        btnAnnuler.setText("Annuler");
        btnAnnuler.setName("btnAnnuler");
        btnInscri.setText("Confirmer");
        btnInscri.setName("btnInscri");
        gui_Container_1.addComponent(btnInscri);
        gui_Container_1.addComponent(btnAnnuler);

        gui_Component_Group_1.setName("Component_Group_1");

     
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new SignInForm().show();

            }
        });

        btnInscri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!mailText.getText().isEmpty() && !nomText.getText().isEmpty() && !lastnomText.getText().isEmpty() && !passwordreText.getText().isEmpty() && !passwordreText.getText().isEmpty() && !path.equals("") ) {
                    if (!mailErreur) {
                        if(passwordText.getText().equals(passwordreText.getText())){
                        User user = new User();
                        user.setEmail(mailText.getText());
                        user.setName(nomText.getText());
                        user.setLastName(lastnomText.getText());
                        user.setPassword(passwordText.getText());
                        user.setImage(path);

                        ServicesUsers su = ServicesUsers.getInstance();

                        if (su.Inscrire(user)) {
                            Dialog.show("Inscription", "Inscription effectuée", new Command("OK"));
                        } else {
                            Dialog.show("Inscription", "Erreur d'inscription", new Command("OK"));
                        }
                        new SignInForm().show();
                    }else{
                         Dialog.show("Vérification", "Les 2 mots de passe sont différents ", new Command("OK"));   
                        } 
                    }
                        else {
                        Dialog.show("Vérification", "Adresse mail d'utilisateur existant", new Command("OK"));
                    }

                } else {
                    Dialog.show("Inscription", "Les champs ne doivent pas être vide", new Command("OK"));
                }
            }
        });
        
        btnCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                path= Capture.capturePhoto(c5.getWidth(),btnCapture.getHeight()*5);
            if (path!=null){
                   try {
                       Image img=Image.createImage(path);
                       lblimage.setIcon(img);
                       c5.revalidate();
                       
                       
                       
                       
                   } catch (IOException ex) {
                     
                   }
            }
            }
        });
    }


    

}
