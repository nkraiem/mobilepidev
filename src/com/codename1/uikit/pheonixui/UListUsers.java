/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.pheonixui;

import com.codename1.components.SpanLabel;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.sport.entities.User;
import com.codename1.sport.services.ServicesUsers;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.tree.Tree;
import com.codename1.ui.tree.TreeModel;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author ramij
 */
public class UListUsers extends BaseForm {

    public UListUsers() {

        this(com.codename1.ui.util.Resources.getGlobalResources());

    }

    public UListUsers(com.codename1.ui.util.Resources resourceObjectInstance) {

        initGuiBuilderComponents(resourceObjectInstance);
        installSidemenu(resourceObjectInstance);

        //barre de recherche 
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();

            chercher(text);
        }, 4);
    }

    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    private Container gui_Component_Group_1 = new Container(new FlowLayout(CENTER, CENTER));
    private Container gui_Component_Group_2 = new Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private Container gui_Component_Group_3 = new Container(new FlowLayout(CENTER, CENTER));
    private com.codename1.ui.Container ContainerCmd = new Container(new FlowLayout());
    private Button btnPdf = new Button("Générer PDF");

    boolean __click = false;

    private com.codename1.ui.Container ContainerProduits = new Container(new FlowLayout());

    TableModel model;
    Table table;
    List<User> listUsers;
    List<User> listFiltrer;
    ServicesUsers dao = ServicesUsers.getInstance();

    private Object[][] remplirUsers(List<User> _users) {

        Object[][] lista = new String[_users.size()][3];
        for (int i = 0; i < lista.length; i++) {

            lista[i][0] = _users.get(i).getEmail();
            lista[i][1] = _users.get(i).getName();

            lista[i][2] = _users.get(i).getRoles();
        }
        return lista;
    }

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {

        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Users");
        setName("users");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);

        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_3);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Container_1.addComponent(gui_Component_Group_2);

        listUsers = dao.AllUsers();
        model = new DefaultTableModel(new String[]{"Email", "Nom", "Roles"}, remplirUsers(listUsers)) {
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };

        table = new Table(model) {
            int selectedRow = -1;

            @Override
            protected Component createCell(Object value, int row, int column, boolean editable) {
                Component cell;
                if (row < 0) {

                    cell = super.createCell(value, row, column, editable);
                    cell.getAllStyles().setFgColor(0xFF6347);

                } else {
                    cell = new Button(value.toString());
                    cell.setUIID("TableCell");
                    cell.getAllStyles().setFgColor(0xffffff);
                    ((Button) cell).addActionListener(e -> {
                        selectedRow = row;
                        setModel(getModel());
                    });
                }

                if (selectedRow > -1 && selectedRow == row) {

                    if (__click) {
                        cell.getAllStyles().setBgColor(0xff0000);
                        cell.getAllStyles().setBgTransparency(100);
                        if (column == 0) {
                            new UUpdateUser(value.toString()).show();
                        }
                    }

                    if (column == 2) {
                        __click = true;
                    }

                }
                return cell;
            }

        };

        gui_Component_Group_3.add(new SpanLabel("Vous pouvez consulter la liste des utilisateurs et générer un pdf détailler sur les utilisateurs"));

        gui_Component_Group_1.setName("Component_Group_1");
        Image image = resourceObjectInstance.getImage("10.jpg");


        Label imagelabel = new Label(image);


        
        gui_Component_Group_1.add(imagelabel);

        gui_Component_Group_2.addComponent(btnPdf);
        gui_Component_Group_2.addComponent(new Label("Liste des utilisateurs"));
        gui_Component_Group_2.addComponent(table);

        btnPdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                dao.SendPdf(Statics.userconnecter.getEmail());
            }
        });

    }

    public void chercher(String mot) {

        __click = false;
        listFiltrer = new ArrayList<>();
        if (mot.equals("")) {
            listFiltrer = listUsers;
        } else {
            for (User u : listUsers) {
                if (u.getEmail().contains(mot) || u.getName().contains(mot) || u.getRoles().contains(mot)) {
                    listFiltrer.add(u);
                }
            }
        }

        TableModel modelfiltrer;
        modelfiltrer = new DefaultTableModel(new String[]{"Email", "Nom", "Roles"}, remplirUsers(listFiltrer)) {
            public boolean isCellEditable(int row, int col) {
                return col != 0;
            }
        };

        table.setModel(modelfiltrer);

        table.revalidate();
        gui_Component_Group_2.revalidate();
        revalidate();
    }

    @Override
    protected boolean isCurrentUsers() {
        return true;
    }

}
