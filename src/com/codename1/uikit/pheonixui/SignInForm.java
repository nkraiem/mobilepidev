
package com.codename1.uikit.pheonixui;

import com.codename1.sport.entities.User;
import com.codename1.sport.services.ServicesUsers;
import com.codename1.sport.utils.Statics;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;


/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class SignInForm extends com.codename1.ui.Form {

    public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new WalkthruForm().show());
        getContentPane().setUIID("SignInForm");
    }


    protected com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    protected com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    protected TextField textfieldemail = new TextField("","Email");
    protected com.codename1.ui.TextField textfieldpassword = new TextField("", "Password", 40, TextField.PASSWORD);
    protected com.codename1.ui.Button btnConnecter = new com.codename1.ui.Button();
     
   
    protected com.codename1.ui.Button btnInscrire = new com.codename1.ui.Button();


    
// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        btnConnecter.addActionListener(callback);
        btnInscrire.addActionListener(callback);

    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();

            if(sourceComponent.getParent().getLeadParent() != null && (sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.MultiButton || sourceComponent.getParent().getLeadParent() instanceof com.codename1.components.SpanButton)) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == btnConnecter) {
                onButton_2ActionEvent(ev);
            }
            
             if(sourceComponent == btnInscrire) {
                new Inscription().show();
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        
        setTitle("Sign In");
        setName("SignInForm");
        gui_Container_1.setScrollableY(true);
        
           
        gui_Container_1.setName("Container_1");
        
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Label_1.setUIID("CenterLabel");
                
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
                
       
        btnConnecter.setText("Sign In");
               
        btnConnecter.setName("Button_2");
        btnInscrire.setText("Pas De compte? S'inscrire");
        btnInscrire.setUIID("CenterLabelSmall");
                
        btnInscrire.setName("Button_3");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        
             
        textfieldemail.setText("");
        
        gui_Component_Group_1.addComponent(textfieldemail);
        gui_Component_Group_1.addComponent(textfieldpassword);
       
      textfieldemail.setUIID("TextField");
      textfieldpassword.setUIID("TextField");
      
      textfieldemail.getStyle().setBgColor(0xFFA833);
      textfieldemail.getStyle().setFgColor(0xFFFFFF);
      textfieldemail.getSelectedStyle().setBgColor(0xFFA833);
      
      textfieldpassword.getStyle().setBgColor(0xFFA833);
      textfieldpassword.getStyle().setFgColor(0xFFFFFF);
      textfieldpassword.getSelectedStyle().setBgColor(0xFFA833);
      
      
        gui_Container_1.addComponent(btnConnecter);
        gui_Container_1.addComponent(btnInscrire);
        
    }// </editor-fold>


    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        //Dialog.show("salm", "ff", new Command("ss"));
        if (!textfieldemail.getText().isEmpty() && !textfieldpassword.getText().isEmpty()) {
            ServicesUsers su = ServicesUsers.getInstance();
            User u = su.ConnexionMobile(textfieldemail.getText(), textfieldpassword.getText());
            if (u != null) {
                Statics.userconnecter=u;
                Statics.matchsfavoris=new ArrayList<>();
                new Accueil().show();
            } else {
                Dialog.show("Connexion échouée", "vérifier votre mail et le mot de passe", new Command("OK"));
            }

        } else {
            Dialog.show("Connexion échouée", "  le nom d'utilisateur et le mot de passe ne doivent pas être vide", new Command("OK"));
        }
    }
}
