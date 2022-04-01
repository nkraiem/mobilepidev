/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.sport.services.ServiceLike;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.sport.entities.Commantaires;
import com.codename1.sport.entities.Like;
import com.codename1.sport.entities.Publication;
import com.codename1.sport.entities.User;
import com.codename1.sport.services.ServiceCommentaires;
import com.codename1.sport.services.ServicesPublications;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class FDetailPublication extends BaseForm {

    
     ServicesPublications daopublication=ServicesPublications.getInstance();
     Button btnModif=new Button("Mettre à jour");
    Publication pub;
    Container containerAllcommentaires=new Container(new BoxLayout(BoxLayout.Y_AXIS));
    List<Commantaires> comments;
     
    public FDetailPublication(Publication publication) {
        this(com.codename1.ui.util.Resources.getGlobalResources(),publication);
    }
    
    public FDetailPublication(com.codename1.ui.util.Resources resourceObjectInstance,Publication publication) {
        pub=publication;
        initGuiBuilderComponents(resourceObjectInstance);
        
       
        
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        
         //barre de recherche 
           getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
   
    chercher(text,resourceObjectInstance);
}, 4);
        
       
    }
  private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("Trending");
        setName("TrendingForm");
        
        if(Statics.userconnecter.getRoles().equals("manager")){
        //Bouton Update
        Container containerbtnajouter = new Container(new com.codename1.ui.layouts.FlowLayout());
        containerbtnajouter.add(btnModif);
        btnModif.setPreferredW(getPreferredW());
        addComponent(containerbtnajouter);
        btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new FEditPublication(pub).show();
            }
        });
        }
        List<Publication> publications=  daopublication.AllPublications();
        
       
        Container gui_Container_hautimage = new Container(new com.codename1.ui.layouts.BorderLayout());
        MultiButton gui_Multi_Button_GaucheHaut_Image = new MultiButton();
        MultiButton gui_Multi_Button_DroiteHaut_Image = new MultiButton();
        Container gui_imagePublicationContainer = new Container(new com.codename1.ui.layouts.BorderLayout());
        Container gui_Container_DetailsPublication_BasImage = new Container(new com.codename1.ui.layouts.BorderLayout());
        Container gui_Container_Like_Dislike_addcomment = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.X_AXIS));
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
            Dialog.show("Détail", pub.getTitre_pub()+"\n"+pub.getContenu_pub(), new Command("OK"));

        });
        gui_Container_DetailsPublication_BasImage.setLeadComponent(myBtn);
        
        //separateur
        gui_separator1.setShowEvenIfBlank(true);        
        gui_separator1.setUIID("Separator");
                gui_separator1.setInlineStylesTheme(resourceObjectInstance);
        gui_separator1.setName("separator1");
         addComponent(gui_separator1);
        
       
      //like_dislike
      ServiceLike slike=ServiceLike.getInstance();
      Button btnlike=new Button("Like ("+slike.countLikebypublication(pub.getId())+")");
      btnlike.getAllStyles().setBorder(Border.createEmpty());
      btnlike.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
      
      Button btndislike=new Button("Dislike ("+slike.countDISLIKEbypublication(pub.getId())+")");
      btndislike.getAllStyles().setBorder(Border.createEmpty());
      btndislike.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
      
      
     //like btn
      btnlike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Like like=new Like(pub.getId(), 1);
                slike.addlike(like);
                new FDetailPublication(pub).show();
            }
        });
      
      //dislik ebtn
      btndislike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Like like=new Like(pub.getId(), 0);
                slike.addlike(like);
                new FDetailPublication(pub).show();
            }
        });
      
      if(Statics.userconnecter.getRoles().equals("manager")){
         btndislike.setEnabled(false);
         btnlike.setEnabled(false);
      }
      
      //add commentaire
       Button btnAddcomment=new Button("Commenter");
       
       btnAddcomment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new FAddCommentaire(pub).show();
            }
        });
      
      gui_Container_Like_Dislike_addcomment.add(btnlike);
      gui_Container_Like_Dislike_addcomment.add(btndislike);
      gui_Container_Like_Dislike_addcomment.add(btnAddcomment);
      addComponent(gui_Container_Like_Dislike_addcomment);

       //Charger Commentaires 
        ServiceCommentaires serviceComments=ServiceCommentaires.getInstance();
        comments= serviceComments.AllCommentsByPub(pub.getId());
      
       Label titreCommentaire = new Label("Commentaires");
       add(titreCommentaire);
       titreCommentaire.getStyle().setFgColor(0xFF6347);
       addComponent(containerAllcommentaires);
       for(Commantaires cm:comments){
           
           Container containercommentaire=new Container(new BoxLayout(BoxLayout.Y_AXIS));
           containerAllcommentaires.addComponent(containercommentaire);
           Label datecomments=new Label(L10NManager.getInstance().formatDateLongStyle(cm.getDate_commentaire()));
           SpanLabel contenucomment=new SpanLabel(cm.getContenu());
           
           datecomments.setUIID("uiid1");
           Label gui_separator = new Label();
           gui_separator.setShowEvenIfBlank(true);        
        gui_separator.setUIID("Separator");
                gui_separator.setInlineStylesTheme(resourceObjectInstance);
        gui_separator.setName("separator1");
        
        containercommentaire.add(datecomments);
         containercommentaire.add(contenucomment);

         containercommentaire.add(gui_separator);

       }
        
    }

    @Override
    protected boolean isCurrentForum() {
        return true;
    }

    List<Commantaires> listFiltrer;
    private void chercher(String mot,Resources resourceObjectInstance) {
        listFiltrer=new ArrayList<>();
         if (mot.equals("")){
           listFiltrer = comments; 
         }else{
        for(Commantaires c:comments){
            System.out.println(split(c.getContenu())[1]);
            if(split(c.getContenu())[1].contains(mot))
            {
                listFiltrer.add(c);
            }
        }
         }
         containerAllcommentaires.removeAll();
         for(Commantaires cm:listFiltrer){
           
           Container containercommentaire=new Container(new BoxLayout(BoxLayout.Y_AXIS));
           containerAllcommentaires.addComponent(containercommentaire);
           Label datecomments=new Label(L10NManager.getInstance().formatDateLongStyle(cm.getDate_commentaire()));
           SpanLabel contenucomment=new SpanLabel(cm.getContenu());
           
           datecomments.setUIID("uiid1");
           Label gui_separator = new Label();
           gui_separator.setShowEvenIfBlank(true);        
        gui_separator.setUIID("Separator");
                gui_separator.setInlineStylesTheme(resourceObjectInstance);
        gui_separator.setName("separator1");
        
        containercommentaire.add(datecomments);
         containercommentaire.add(contenucomment);

         containercommentaire.add(gui_separator);

       }

         containerAllcommentaires.revalidate();
    }
    public String[] split(String str)
{
    ArrayList<String> splitArray = new ArrayList<>();
    StringTokenizer arr = new StringTokenizer(str, ":");//split by commas
    while(arr.hasMoreTokens())
        splitArray.add(arr.nextToken());
    return splitArray.toArray(new String[splitArray.size()]);
}
}