/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.services.ServiceEquipe;
import com.codename1.sport.services.ServicesPublications;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;


public class NEditEquipe extends BaseForm {
    static  Equipe equipe=new Equipe();
    
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.Label nomLabel = new com.codename1.ui.Label();
   
    
    

    private com.codename1.ui.TextField nomText = new com.codename1.ui.TextField("", "nom");
    
    
    
   
     protected Label lblimage=new Label();
     private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
    private com.codename1.ui.Button btnAnnuler = new com.codename1.ui.Button();
    
    
    public NEditEquipe() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public NEditEquipe(com.codename1.ui.util.Resources resourceObjectInstance) {
       initGuiBuilderComponents(resourceObjectInstance);      
              remplirEquipe();
              getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new NEquipes().show();
        });

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
         setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Ajouter Equipe");
        setName("AjoutForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        Container c1 = new Container(new FlowLayout(CENTER, CENTER));
        Container c2 = new Container(new FlowLayout(CENTER, CENTER));
        Container c3 = new Container(new FlowLayout(CENTER, CENTER));
        Container c4 = new Container(new FlowLayout(CENTER, CENTER));
        
        
Image image = resourceObjectInstance.getImage("equipe.jpg");
image.fill(100,100);


        c1.add(image);
       
        
        
        nomLabel.setText("Nom");
        
        
        c2.addComponent(nomLabel);
        c2.addComponent(nomText);
       
        
        
        
       
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(c1);
        gui_Component_Group_1.addComponent(c2);
        

        btnAnnuler.setText("Annuler");
        btnAnnuler.setName("btnAnnuler");
        btnconfirmer.setText("Confirmer");
        btnconfirmer.setName("btnPubli");
        gui_Container_1.addComponent(btnconfirmer);
        gui_Container_1.addComponent(btnAnnuler);

        gui_Component_Group_1.setName("Component_Group_1");
        
        
        btnconfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!nomText.getText().isEmpty()  ) {
                   
                        Equipe _equipe = new Equipe();
                        _equipe.setNom(nomText.getText());
                        _equipe.setId(equipe.getId());
                       
                        
                        
                        

                        ServiceEquipe su = ServiceEquipe.getInstance();

                        if (su.edit(_equipe)) {
                            Dialog.show("Equipe", "Equipe modifiée", new Command("OK"));
                        } else {
                            Dialog.show("Equipe", "Erreur de modification", new Command("OK"));
                        }
                        new NEquipes().show();
                   

                } else {
                    Dialog.show("Equipe", "Les champs ne doivent pas être vide", new Command("OK"));
                }
            }
        });

    }
     public void remplirEquipe() {
        
      nomText.setText(equipe.getNom());
      
    }
     
     @Override
     protected boolean isCurrentEquipe() {
        return false;
    }
    
}

