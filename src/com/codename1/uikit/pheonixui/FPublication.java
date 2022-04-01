/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.services.ServicesPublications;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.List;


public class FPublication extends BaseForm {

    
     ServicesPublications daopublication=ServicesPublications.getInstance();
     Button btnAjout=new Button("Ajouter Nouvelle");
     Button btnStat=new Button("Statistique");
    
    public FPublication() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public FPublication(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        
       
        
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        
       
    }


    
    

// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Trending");
        setName("TrendingForm");
         
        if(Statics.userconnecter.getRoles().equals("manager")){
        //Bouton Ajouter
        Container containerbtnajouter = new Container(new BoxLayout(BoxLayout.X_AXIS));
        containerbtnajouter.add(btnAjout);
        btnAjout.setPreferredW(getPreferredW()/2-5);
         btnStat.setPreferredW(getPreferredW()/2-5);
       
        addComponent(containerbtnajouter);
        btnAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new FAddPublication().show();
            }
        });
        //statistique
                containerbtnajouter.add(btnStat);
        btnStat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
               StatEvent().show();
                }
            });
        
        }
        List<Publication> publications=  daopublication.AllPublications();
        
        for(Publication pub:publications)
        {
        Container gui_Container_hautimage = new Container(new com.codename1.ui.layouts.BorderLayout());
        MultiButton gui_Multi_Button_GaucheHaut_Image = new MultiButton();
        MultiButton gui_Multi_Button_DroiteHaut_Image = new MultiButton();
        Container gui_imagePublicationContainer = new Container(new com.codename1.ui.layouts.BorderLayout());
        Container gui_Container_DetailsPublication_BasImage = new Container(new com.codename1.ui.layouts.BorderLayout());
        TextArea gui_Text_Area_1 = new TextArea();
        Button gui_Button_1 = new Button();
        Label gui_separator1 = new Label();
        
        //dessus de l image de publication
        gui_Container_hautimage.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_hautimage.setName("Container_1");
        addComponent(gui_Container_hautimage);
        
        
         //dessus image publication  a gauche
         
        gui_Multi_Button_GaucheHaut_Image.setUIID("Label");
                gui_Multi_Button_GaucheHaut_Image.setInlineStylesTheme(resourceObjectInstance);
        gui_Multi_Button_GaucheHaut_Image.setName("Multi_Button_1");
        gui_Multi_Button_GaucheHaut_Image.setIcon(resourceObjectInstance.getImage("contact-c.png"));
        gui_Multi_Button_GaucheHaut_Image.setPropertyValue("line1", pub.getAutheurPub());
        gui_Multi_Button_GaucheHaut_Image.setPropertyValue("line2", pub.getTitre_pub());
        gui_Multi_Button_GaucheHaut_Image.setPropertyValue("uiid1", "Label");
       
        gui_Container_hautimage.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_GaucheHaut_Image);
       
        
        //dessus image publication a droite
         FontImage.setMaterialIcon(gui_Multi_Button_DroiteHaut_Image, FontImage.MATERIAL_LOCATION_ON);
        gui_Multi_Button_DroiteHaut_Image.setIconPosition(BorderLayout.EAST);
        gui_Multi_Button_DroiteHaut_Image.setUIID("Label");
        gui_Multi_Button_DroiteHaut_Image.setInlineStylesTheme(resourceObjectInstance);
        gui_Multi_Button_DroiteHaut_Image.setName("LA");
        
        String dt = L10NManager.getInstance().formatDateLongStyle(pub.getDatePub());
        gui_Multi_Button_DroiteHaut_Image.setPropertyValue("line1", dt);        
        gui_Multi_Button_DroiteHaut_Image.setPropertyValue("uiid1", "SlightlySmallerFontLabel");      
        gui_Container_hautimage.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Multi_Button_DroiteHaut_Image);
        
        
        //image publication
        ScaleImageLabel imagePublication;
        try {
                
                Image img=Image.createImage(pub.getImagePub());
                 imagePublication = new ScaleImageLabel(img); 
            } catch (IOException ex) {
               imagePublication = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg")); 
            }
        
        imagePublication.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imagePublicationContainer.add(BorderLayout.CENTER, imagePublication);        
        imagePublication.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imagePublicationContainer.setInlineStylesTheme(resourceObjectInstance);
        gui_imagePublicationContainer.setName("imageContainer1");
        addComponent(gui_imagePublicationContainer);
        
        
        //dessous image publication 
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
        gui_Text_Area_1.setText(pub.getContenu_pub());
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Text_Area_1.setName("Text_Area_1");       
        
        //button dessous image publication
        gui_Button_1.setText("");
        gui_Button_1.setUIID("Label");
                gui_Button_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Button_1.setName("Button_1");
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1,"\ue5c8".charAt(0));
        gui_Container_DetailsPublication_BasImage.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_DetailsPublication_BasImage.setName("Container_2");
        gui_imagePublicationContainer.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_DetailsPublication_BasImage);
        
        
        gui_Container_DetailsPublication_BasImage.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_DetailsPublication_BasImage.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
       Button myBtn = new Button();
        myBtn.addActionListener(e -> {
           new FDetailPublication(pub).show();

        });
        gui_Container_DetailsPublication_BasImage.setLeadComponent(myBtn);
        
        //separateur
        gui_separator1.setShowEvenIfBlank(true);        
        gui_separator1.setUIID("Separator");
                gui_separator1.setInlineStylesTheme(resourceObjectInstance);
        gui_separator1.setName("separator1");
         addComponent(gui_separator1);
        
       

        
        
        }
        
    }

    
    
    public Form StatEvent() {

        FStatPublication a = new FStatPublication();
        Form stats_Form = a.execute();
        SpanLabel test_SpanLabel = new SpanLabel("DUUUU");
        Class cls = FStatPublication.class;
        stats_Form.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new FPublication().show();
        });

        return stats_Form;
    }
    @Override
    protected boolean isCurrentForum() {
        return true;
    }
}
