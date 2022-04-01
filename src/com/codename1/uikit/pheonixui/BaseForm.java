package com.codename1.uikit.pheonixui;

import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");

        Image tournoiImage = null;
        if (isCurrentTournoi()) {
            tournoiImage = selection;
        }

        Image FavorisImage = null;
        if (isCurrentFavoris()) {
            FavorisImage = selection;
        }

        Image accueilImage = null;
        if (isCurrentAccueil()) {
            accueilImage = selection;
        }

        Image equipeImage = null;
        if (isCurrentEquipe()) {
            equipeImage = selection;
        }

        Image joueurImage = null;
        if (isCurrentJoueur()) {
            joueurImage = selection;
        }

        Image forumImage = null;
        if (isCurrentForum()) {
            forumImage = selection;
        }

        Image calendarImage = null;
        if (isCurrentCalendar()) {
            calendarImage = selection;
        }

        Image profilImage = null;
        if (isCurrentProfils()) {
            profilImage = selection;
        }

        Image userImage = null;
        if (isCurrentUsers()) {
            userImage = selection;
        }

        Button inboxButton = new Button("Accueil", accueilImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton,
                new Label("*", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new Accueil().show());
        getToolbar().addComponentToSideMenu(inbox);

        if (Statics.userconnecter != null && Statics.userconnecter.getRoles().equals("manager")) {
            getToolbar().addCommandToSideMenu("Users", userImage, e -> new UListUsers().show());
        }

        getToolbar().addCommandToSideMenu("Equipe", equipeImage, e -> new NEquipes(res).show());
        if (Statics.userconnecter != null && Statics.userconnecter.getRoles().equals("manager")) {
            getToolbar().addCommandToSideMenu("Joueur", joueurImage, e -> new NAddJoueur(res).show());
        }
        getToolbar().addCommandToSideMenu("Forum", forumImage, e -> new FPublication(res).show());
        getToolbar().addCommandToSideMenu("Tournoi", tournoiImage, e -> new TTournois(res).show());
        getToolbar().addCommandToSideMenu("Calendrier", calendarImage, e -> new TCalendarMatchs(res).show());
       // if (Statics.userconnecter != null && !Statics.userconnecter.getRoles().equals("manager")) {
            getToolbar().addCommandToSideMenu("Favoris ("+Statics.matchsfavoris.size()+")", joueurImage, e -> new TFavoris(res).show());
       // }
        getToolbar().addCommandToSideMenu("Deconnexion", null, e -> new SignInForm(res).show());

        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));

        getToolbar().addComponentToSideMenu(new Label(res.getImage("teamwork126.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label(Statics.userconnecter.getName() + " " + Statics.userconnecter.getLastName(), "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label(Statics.userconnecter.getEmail(), "SideCommandSmall"));
        getToolbar().addCommandToSideMenu("Profil", profilImage, e -> new UFrontProfilUpdate(res, Statics.userconnecter.getEmail()).show());
    }

    protected boolean isCurrentAccueil() {
        return false;
    }

    protected boolean isCurrentForum() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentProfils() {
        return false;
    }

    protected boolean isCurrentUsers() {
        return false;
    }

    protected boolean isCurrentEquipe() {
        return false;
    }

    protected boolean isCurrentJoueur() {
        return false;
    }

    protected boolean isCurrentTournoi() {
        return false;
    }

    protected boolean isCurrentFavoris() {
        return false;
    }

}
