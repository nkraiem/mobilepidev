/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.SpanLabel;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Font;
import com.codename1.ui.Image;
import com.codename1.ui.Label;

public class Accueil extends BaseForm{
    
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Container gui_Container_concatenation = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));

    
    public Accueil() {

        this(com.codename1.ui.util.Resources.getGlobalResources());

    }

    public Accueil(com.codename1.ui.util.Resources resourceObjectInstance) {
        
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
    } 
        private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Users");
        setName("users");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);

        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        
     
            
            
            Label nom=new Label( Statics.userconnecter.getName());
            Label bienvenue=new Label("Bienvenue");
            
               nom.getAllStyles().setFgColor(0xFF6347);
            Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
            nom.getAllStyles().setFont(mediumPlainSystemFont);
            bienvenue.getAllStyles().setFont(mediumPlainSystemFont);
            
            Image image = resourceObjectInstance.getImage("logo2.png").fill(getWidth(),680);
            
            SpanLabel text1=new SpanLabel("Gérez votre équipe de sport amateur comme un pro");
            SpanLabel text2=new SpanLabel("Notre application de référence pour organiser vos matchs et gérer votre équipe de foot");
            SpanLabel text3=new SpanLabel("Nous vous permettons de centraliser et mettre à jour toutes les informations sur vos membres, et d’y accéder sur ordinateur ou smartphone depuis n'importe où.");
            
            gui_Container_concatenation.add(bienvenue);
            gui_Container_concatenation.add(nom);
            gui_Container_1.add(gui_Container_concatenation); 
            gui_Container_1.add(text1);
            gui_Container_1.add(text2);
            gui_Container_1.add(image);
            gui_Container_1.add(text3);

       
        }
        @Override
         protected boolean isCurrentAccueil() {
        return true;
    }
}
