package com.codename1.uikit.pheonixui;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;


public class SplashForm extends Form  {
    public SplashForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    protected com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    protected com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
    protected com.codename1.components.InfiniteProgress gui_Infinite_Progress_1 = new com.codename1.components.InfiniteProgress();
    protected com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setInlineStylesTheme(resourceObjectInstance);
        setUIID("Splash");
                setInlineStylesTheme(resourceObjectInstance);
        setTitle("");
        setName("SplashForm");
        ((com.codename1.ui.layouts.BorderLayout)getLayout()).setCenterBehavior(com.codename1.ui.layouts.BorderLayout.CENTER_BEHAVIOR_CENTER);
                gui_Container_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_1.setName("Container_1");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
                gui_Container_2.setInlineStylesTheme(resourceObjectInstance);
        gui_Container_2.setName("Container_2");
        ((com.codename1.ui.layouts.FlowLayout)gui_Container_2.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
                gui_Label_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("logo.png"));
        gui_Container_1.addComponent(gui_Container_2);
                gui_Infinite_Progress_1.setInlineStylesTheme(resourceObjectInstance);
        gui_Infinite_Progress_1.setName("Infinite_Progress_1");
        gui_Container_2.addComponent(gui_Infinite_Progress_1);
        gui_Container_1.addComponent(gui_Label_1);
    }// </editor-fold>
//-- DON'T EDIT ABOVE THIS LINE!!!
}
