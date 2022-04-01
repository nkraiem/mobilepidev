/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.l10n.L10NManager;
import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Favoris;
import com.codename1.sport.entities.Match;
import com.codename1.sport.entities.Tournois;
import com.codename1.sport.services.ServiceEquipe;
import com.codename1.sport.services.ServiceMatch;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.List;

public class TAllMatchsbytournois extends BaseForm {

    static Tournois tournois = new Tournois();
    ServiceMatch servicematch = ServiceMatch.getInstance();
    Container containerglobal = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    public TAllMatchsbytournois() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TAllMatchsbytournois(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);

        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
        if(Statics.userconnecter.getRoles().equals("manager"))
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ADD_BOX, ev -> {
           TAddMatch.tr=tournois;
            new TAddMatch().show();
        });
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Matchs");
        setName("Matchs");

        addComponent(containerglobal);
        List<Match> matchs = servicematch.AllMatchsbytournois(tournois.getId());

        for (Match e : matchs) {

            Container containermatch = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            String dt1 = L10NManager.getInstance().formatDateLongStyle(e.getDate_match());
            containermatch.add(new Label("Date Match : " + dt1));

            Container containermatch2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            containermatch2.add(new Label(e.getEquipe1_nom()));
            if (e.getScore_a() != -1) {
                containermatch2.add(new Label("" + e.getScore_a()));
                containermatch2.add(new Label("    " + e.getScore_b()));
            } else {
                containermatch2.add(new Label(" - "));
            }
            CheckBox cb1 = new CheckBox("Favoris");
            for (Favoris f : Statics.matchsfavoris) {
                if (e.getRef_match().equals(f.getMatch().getRef_match())) {

                    cb1.setSelected(true);
                }
            }

            cb1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Favoris favoris = new Favoris();
                    favoris.setMatch(e);
                    if (cb1.isSelected()) {

                        Statics.matchsfavoris.add(favoris);
                        new TAllMatchsbytournois().show();
                    } else {
                        Statics.matchsfavoris.remove(favoris);
                        new TAllMatchsbytournois().show();
                    }
                }
            });

            containermatch2.add(new Label(e.getEquipe2_nom()));

            containermatch2.add(cb1);
            containermatch.add(containermatch2);

            Label gui_separator1 = new Label();
            //separateur
            gui_separator1.setShowEvenIfBlank(true);
            gui_separator1.setUIID("Separator");
            gui_separator1.setInlineStylesTheme(resourceObjectInstance);
            gui_separator1.setName("separator1");

            containerglobal.add(containermatch);
            containerglobal.addComponent(gui_separator1);

        }
    }
}
