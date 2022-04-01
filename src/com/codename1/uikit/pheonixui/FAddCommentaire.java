/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.SpanLabel;
import com.codename1.sport.entities.Commantaires;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.services.ServiceCommentaires;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class FAddCommentaire extends BaseForm{
    
     private SpanLabel publicationcontenu=new SpanLabel();
     private TextField commentairecontenu=new TextField();
     private Button btnAjouButton=new Button("Commenter");
     private Button btnannulButton=new Button("Annuler");
     private Container container_1=new Container(new BoxLayout(BoxLayout.Y_AXIS));
     private Publication _pub;
    
     public FAddCommentaire(Publication publ) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),publ);
    }
     
    

    public FAddCommentaire(com.codename1.ui.util.Resources resourceObjectInstance,Publication publ) {
       _pub=publ;
        initGuiBuilderComponents(resourceObjectInstance);      
       
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
       setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Inscription");
        setName("InscriForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, container_1);
        
        publicationcontenu.setText(_pub.getContenu_pub());
        
        btnAjouButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               ServiceCommentaires service=ServiceCommentaires.getInstance();
               Commantaires cm=new Commantaires();
               cm.setContenu(Statics.userconnecter.getName()+" "+Statics.userconnecter.getLastName()+" : "+commentairecontenu.getText());
               cm.setPublication_id(_pub.getId());
               service.add(cm);
               new FDetailPublication(_pub).show();
           }
       });
        
        btnannulButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
              new FDetailPublication(_pub).show();
           }
       });
        
        container_1.add(publicationcontenu);
        container_1.add(commentairecontenu);
        container_1.add(btnAjouButton);
        container_1.add(btnannulButton);
        
        
    }
    
    @Override
    protected boolean isCurrentForum() {
        return true;
    }
    
    
    
}
