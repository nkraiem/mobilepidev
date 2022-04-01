/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.capture.Capture;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.services.ServicesPublications;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;


public class FAddPublication extends BaseForm{
    
    
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.Label titreLabel = new com.codename1.ui.Label();
   
    private com.codename1.ui.Label contenuLabel = new com.codename1.ui.Label();
    

    private com.codename1.ui.TextField titreText = new com.codename1.ui.TextField("", "Titre");
    
    private com.codename1.ui.TextField contenuText = new com.codename1.ui.TextField("","Contenu");
    
    protected com.codename1.ui.Button btnCapture = new com.codename1.ui.Button("upload");
     protected Label lblimage=new Label();
     
   
     String path="";

    
    private com.codename1.ui.Button btnPubli = new com.codename1.ui.Button();
    private com.codename1.ui.Button btnAnnuler = new com.codename1.ui.Button();
    
    
     public FAddPublication() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public FAddPublication(com.codename1.ui.util.Resources resourceObjectInstance) {
       initGuiBuilderComponents(resourceObjectInstance);      
       
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
    }
   private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Publications");
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
        

       
        contenuLabel.setText("Contenu");
        
        titreLabel.setText("Titre");
        
        
        c2.addComponent(titreLabel);
        c2.addComponent(titreText);
       
        
        c5.addComponent(lblimage);
        c5.addComponent(btnCapture);
        
        c6.addComponent(contenuLabel);
        c6.addComponent(contenuText);
        
        

        gui_Component_Group_1.setName("Component_Group_1");
        
        gui_Component_Group_1.addComponent(c2);
        gui_Component_Group_1.addComponent(c6);
        
        gui_Component_Group_1.addComponent(c5);

        btnAnnuler.setText("Annuler");
        btnAnnuler.setName("btnAnnuler");
        btnPubli.setText("Confirmer");
        btnPubli.setName("btnPubli");
        gui_Container_1.addComponent(btnPubli);
        gui_Container_1.addComponent(btnAnnuler);

        gui_Component_Group_1.setName("Component_Group_1");

     
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new FPublication().show();

            }
        });

        btnPubli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!titreText.getText().isEmpty() &&  !contenuText.getText().isEmpty()  ) {
                   
                        Publication pub = new Publication();
                        pub.setTitre_pub(titreText.getText());
                       
                        pub.setContenu_pub(contenuText.getText());
                        
                        pub.setImagePub(path);
                        
                        pub.setAutheurPub(Statics.userconnecter.getName()+" "+ Statics.userconnecter.getLastName());

                        ServicesPublications su = ServicesPublications.getInstance();

                        if (su.add(pub)) {
                            Dialog.show("Publication", "Publication effectuée", new Command("OK"));
                        } else {
                            Dialog.show("Publication", "Erreur de publication", new Command("OK"));
                        }
                        new FPublication().show();
                   

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


   @Override
    protected boolean isCurrentForum() {
        return true;
    }

}
