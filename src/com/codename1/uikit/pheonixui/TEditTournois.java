/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Tournois;
import com.codename1.sport.services.ServiceEquipe;
import com.codename1.sport.services.ServiceTournois;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;


public class TEditTournois extends BaseForm {
    
    static  Tournois tournois=new Tournois();
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private com.codename1.ui.Container gui_Component_Group_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label nomLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField nomText = new com.codename1.ui.TextField("", "nom");
    
    private com.codename1.ui.Label datedebutLabel = new com.codename1.ui.Label();
    private com.codename1.ui.Label datefinLabel = new com.codename1.ui.Label();
    Picker datePickerdebut = new Picker();
    Picker datePickerfin = new Picker();

    private com.codename1.ui.Label primeLabel = new com.codename1.ui.Label();
    private com.codename1.ui.TextField primeText = new com.codename1.ui.TextField("", "prime");
    
    
     private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button();
    
    
    
    public TEditTournois() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TEditTournois(com.codename1.ui.util.Resources resourceObjectInstance) {
       initGuiBuilderComponents(resourceObjectInstance);      
       
        installSidemenu(resourceObjectInstance);
        remplirTournois();
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new TTournois().show();
        });
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        datePickerdebut.setType(Display.PICKER_TYPE_CALENDAR);
       datePickerfin.setType(Display.PICKER_TYPE_CALENDAR);
       
       
        
         setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Edit Tournois");
        setName("Edit");
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
        
      
        
        nomLabel.setText("Nom");
        primeLabel.setText("Prime");
        datedebutLabel.setText("Date Début");
        datefinLabel.setText("Date Fin");
        
        
        c2.addComponent(nomLabel);
        c2.addComponent(nomText);
        c3.addComponent(datedebutLabel);
        c3.addComponent(datePickerdebut);
        c4.addComponent(datefinLabel);
        c4.addComponent(datePickerfin);
        c5.addComponent(primeLabel);
        c5.addComponent(primeText);
        
        
        
       
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(c1);
        gui_Component_Group_1.addComponent(c2);
        gui_Component_Group_1.addComponent(c3);
        gui_Component_Group_1.addComponent(c4);
        gui_Component_Group_1.addComponent(c5);
        

       
        btnconfirmer.setText("Confirmer");
        btnconfirmer.setName("btnPubli");
        gui_Container_1.addComponent(btnconfirmer);
        

        gui_Component_Group_1.setName("Component_Group_1");
        
        
        
        btnconfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!nomText.getText().isEmpty() && !datePickerdebut.getText().isEmpty() && !datePickerfin.getText().isEmpty() && !primeText.getText().isEmpty()) {
                   
                        Tournois t = new Tournois();
                        t.setNom(nomText.getText());
                        t.setId(tournois.getId());
                        
                       t.setDate_debut(datePickerdebut.getDate());
                               t.setDate_fin(datePickerfin.getDate());
                               t.setPrime(Integer.parseInt(primeText.getText().toString()));
                       
                        
                        
                        

                        ServiceTournois su = ServiceTournois.getInstance();

                        if (su.edit(t)) {
                            Dialog.show("Tournois", "Tournois modifié", new Command("OK"));
                        } else {
                            Dialog.show("Tournois", "Erreur de modification", new Command("OK"));
                        }
                        new TTournois().show();
                   

                } else {
                    Dialog.show("Equipe", "Les champs ne doivent pas être vide", new Command("OK"));
                }
            }
        });

        
       
    }
    
    public void remplirTournois() {
        
      nomText.setText(tournois.getNom());
      datePickerdebut.setDate(tournois.getDate_debut());
      datePickerfin.setDate(tournois.getDate_fin());
      primeText.setText(Integer.toString(tournois.getPrime()));
    }
    
     @Override
    protected boolean isCurrentTournoi() {
        return true;
    }
}
