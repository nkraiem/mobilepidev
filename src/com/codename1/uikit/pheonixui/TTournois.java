/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.l10n.L10NManager;
import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Tournois;
import com.codename1.sport.services.ServiceEquipe;
import com.codename1.sport.services.ServiceTournois;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import java.util.List;

public class TTournois extends BaseForm {

    ServiceTournois servicetournois = ServiceTournois.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public TTournois() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TTournois(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
        if(Statics.userconnecter.getRoles().equals("manager"))
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new TAddTournois().show();
        });
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Tournois");
        setName("Tournois");

        addComponent(containerglobal);

        List<Tournois> tournois = servicetournois.AllTournois();

        for (Tournois t : tournois) {
            Container containerequipe = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            String dt1 = L10NManager.getInstance().formatDateLongStyle(t.getDate_debut());
            String dt2 = L10NManager.getInstance().formatDateLongStyle(t.getDate_fin());
            containerequipe.add(new Label("Nom : " + t.getNom()));
            containerequipe.add(new Label("Date Début : " + dt1));
            containerequipe.add(new Label("Date Fin : " + dt2));
            containerequipe.add(new Label("Prime : " + t.getPrime()));

            Button btnModif = new Button("Modifier");
            Button btnSupp = new Button("Supprimer");
            Container containerbtnmodifsupp = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            
            
            Button btnMatch = new Button("Liste des Matchs");

            btnMatch.setUIID("Button6");


            btnMatch.getStyle().setFgColor(0xFF6347);
            btnMatch.getStyle().setUnderline(true);
            containerequipe.add(btnMatch);
            if(Statics.userconnecter.getRoles().equals("manager"))
            containerequipe.add(containerbtnmodifsupp);
            
            btnMatch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                   TAllMatchsbytournois.tournois=t;
                    new TAllMatchsbytournois().show();
                }
            });
            

            containerbtnmodifsupp.add(btnModif);
            containerbtnmodifsupp.add(btnSupp);

            Label gui_separator1 = new Label();
            //separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");
            gui_separator1.setInlineStylesTheme(resourceObjectInstance);
            gui_separator1.setName("separator1");

            containerglobal.add(containerequipe);
            containerglobal.addComponent(gui_separator1);

            btnModif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    TEditTournois.tournois = t;
                    new TEditTournois().show();
                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    servicetournois.Remove(t.getId());
                    new TTournois().show();
                }
            });
        }
    }

    @Override
    protected boolean isCurrentTournoi() {
        return true;
    }
}
