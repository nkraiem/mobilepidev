/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.sport.entities.Equipe;
import com.codename1.sport.entities.Match;
import com.codename1.sport.entities.Tournois;
import com.codename1.sport.services.ServiceEquipe;
import com.codename1.sport.services.ServiceMatch;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.Date;
import java.util.List;

public class TAddMatch extends BaseForm {

    public static Tournois tr = new Tournois();

    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private Container gui_Component_Group_1 = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private Label dateLabel = new Label("Date Match");
    private Label refmatchLabel = new Label("Réference");
    private Label equip1Label = new Label("Equipe Domicile");
    private Label equip2Label = new Label("Equipe Exterieur");

    private Picker datepicker = new Picker();
    private com.codename1.ui.TextField reftextfield = new com.codename1.ui.TextField("", "Réference");
    private ButtonGroup radioEquipe1 = new ButtonGroup();
    private ButtonGroup radioEquipe2 = new ButtonGroup();

    private com.codename1.ui.Button btnconfirmer = new com.codename1.ui.Button("Ajouter");

    ServiceEquipe daoEquipe = ServiceEquipe.getInstance();

    public TAddMatch() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public TAddMatch(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {
        });
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            new TAllMatchsbytournois().show();
        });

    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Ajouter Match");
        setName("AjoutForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);

        Container c1 = new Container(new FlowLayout(CENTER, CENTER));

        Container containerfoorm = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Container containereference = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        containereference.addComponent(refmatchLabel);
        containereference.addComponent(reftextfield);
        containerfoorm.add(containereference);

        Container containerdate = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        containerdate.addComponent(dateLabel);
        containerdate.addComponent(datepicker);
        containerfoorm.add(containerdate);
        datepicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        Container containerradio1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        containerradio1.addComponent(equip1Label);
        List<Equipe> lsEquipedomicile = daoEquipe.AllEquipes();
        for (Equipe e : lsEquipedomicile) {
            RadioButton radio = new RadioButton(e.getNom());
            radio.setUIID(Integer.toString(e.getId()));
            radioEquipe1.add(radio);
            containerradio1.addComponent(radio);
        }
        containerfoorm.add(containerradio1);

        Container containerradio2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        containerradio2.addComponent(equip2Label);
        List<Equipe> lsEquipeVisiteur = daoEquipe.AllEquipes();
        for (Equipe e : lsEquipeVisiteur) {
            RadioButton radio = new RadioButton(e.getNom());
            radio.setUIID(Integer.toString(e.getId()));
            radioEquipe2.add(radio);
            containerradio2.addComponent(radio);
        }
        containerfoorm.add(containerradio2);

        containerfoorm.add(btnconfirmer);

        btnconfirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!reftextfield.getText().equals("") && radioEquipe1.getSelectedIndex() >= 0 && radioEquipe2.getSelectedIndex() >= 0) {
                    if (radioEquipe1.getSelectedIndex() != radioEquipe2.getSelectedIndex()) {
                        if (datepicker.getDate().getTime() > new Date().getTime()) {

                            Match match = new Match(tr.getId(), Integer.parseInt(radioEquipe1.getSelected().getUIID()), Integer.parseInt(radioEquipe2.getSelected().getUIID()), datepicker.getDate(), reftextfield.getText());
                            ServiceMatch daoMatch = ServiceMatch.getInstance();
                            if (daoMatch.add(match)) {
                                Dialog.show("Ajout", new Label("Match ajouté"), new Command("OK"));
                                new TAllMatchsbytournois().show();
                            } else {
                                Dialog.show("Ajout", new Label("Réference doit être unique"), new Command("OK"));
                            }

                        } else {
                            Dialog.show("Vérfication", new Label("Date doit etre supérieur à " + new Date().toString()), new Command("OK"));
                        }
                    } else {
                        Dialog.show("Vérfication", new Label("Veillez choisir deux equipes differentes"), new Command("OK"));
                    }

                } else {
                    Dialog.show("Vérfication", new Label("Les champs ne doivent pas etre vide"), new Command("OK"));
                }

            }
        });

        c1.add(containerfoorm);
        gui_Component_Group_1.add(c1);

    }

}
