/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.SpanLabel;
import com.codename1.sport.entities.Equipe;
import com.codename1.sport.services.ServiceEquipe;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import java.util.List;

public class NEquipes extends BaseForm {

    ServiceEquipe serviceEquipe = ServiceEquipe.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public NEquipes() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public NEquipes(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });

        if(Statics.userconnecter.getRoles().equals("manager"))
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
            new NAddEquipe().show();
        });
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Equipes");
        setName("Equipes");

        addComponent(containerglobal);

        Image image = resourceObjectInstance.getImage("10.jpg");
        

        containerglobal.add(image);

        List<Equipe> equipes = serviceEquipe.AllEquipes();

        for (Equipe e : equipes) {
            Container containerequipe = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            containerequipe.add(new Label("Nom : " + e.getNom()));
            containerequipe.add(new Label("Victoire : " + e.getNbr_vic()));
            containerequipe.add(new Label("Nulle : " + e.getNbr_null()));
            containerequipe.add(new Label("Perd : " + e.getNbr_per()));

            Button btnStat = new Button("Statistique");
            Button btnModif = new Button("Modifier");
            Button btnSupp = new Button("Supprimer");

            Button btnJoueurs = new Button("Liste des Joueurs");

            btnJoueurs.setUIID("Button6");

            btnJoueurs.getStyle().setBorder(Border.createGrooveBorder(2));
            btnJoueurs.getStyle().setFgColor(0xFF6347);
            btnJoueurs.getStyle().setUnderline(true);

            //banner/active
            Button btnSuspension = new Button();
            btnSuspension.setUIID("Button6");

            if (e.getSuspension() == false) {
                btnSuspension.setText("Active");
                btnSuspension.getStyle().setFgColor(0x00FF00);

            } else {
                btnSuspension.setText("Suspendue");
                btnSuspension.getStyle().setFgColor(0xFF6347);

            }

            btnSuspension.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String messagesuspension = "";
                    int susp;
                    if (e.getSuspension() == false) {
                        susp=1;
                        messagesuspension = "Voulez vous vraiment suspendre cette équipe?";
                    } else {
                        susp=0;
                        messagesuspension = "Voulez vous activer cette équipe?";
                    }
                    if (Dialog.show("Confirm", messagesuspension, "OK", "Cancel")) {
                        serviceEquipe.active_suspend(e.getId(), susp);
                         Dialog.show("Equipe", "C'est fait", new Command("OK"));
                    new NEquipes().show();
                    }
                }
            });

            if(Statics.userconnecter.getRoles().equals("manager"))
            containerequipe.add(btnSuspension);

            containerequipe.add(btnJoueurs);

            btnJoueurs.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    NAllJoueurs.equipe = e;
                    new NAllJoueurs().show();
                }
            });

            Container containerbtnmodifsupp = new Container(new BoxLayout(BoxLayout.X_AXIS));

            btnModif.setPreferredW(getPreferredW() / 2 - 5);
            btnSupp.setPreferredW(getPreferredW() / 2 - 5);
            containerequipe.add(btnStat);
            containerequipe.addComponent(containerbtnmodifsupp);

            if(Statics.userconnecter.getRoles().equals("manager")){
            containerbtnmodifsupp.add(btnModif);
            containerbtnmodifsupp.add(btnSupp);
            }
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
                    NEditEquipe.equipe = e;
                    new NEditEquipe().show();
                }
            });

            btnSupp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    serviceEquipe.Remove(e.getId());
                    Dialog.show("Equipe", "Equipe supprimée", new Command("OK"));
                    new NEquipes().show();
                }
            });
            btnStat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    NStatEquipeVictoire.equipe = e;
                    StatEvent().show();
                }
            });
        }
    }

    public Form StatEvent() {

        NStatEquipeVictoire a = new NStatEquipeVictoire();
        Form stats_Form = a.execute();
        SpanLabel test_SpanLabel = new SpanLabel("DUUUU");
        Class cls = NStatEquipeVictoire.class;
        stats_Form.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new NEquipes().show();
        });

        return stats_Form;
    }

    @Override
    protected boolean isCurrentEquipe() {
        return true;
    }
}
