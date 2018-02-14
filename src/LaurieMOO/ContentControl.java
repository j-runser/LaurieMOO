/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LaurieMOO;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author josephrunser
 */
public class ContentControl {
    
    private final static ArrayList<JLabel> MOO_LIST = new ArrayList<>();
    private final static java.util.Random RND_GEN = new java.util.Random();
    
    // Applications specific, it adds an integer number of moos to the JPane
    public void Panel_addMooToPane(int numMoo, String mooType, Font fnt, JLayeredPane pane) {
        for(int i = 0; i < numMoo; i++) {
            Panel_addStringRandom(mooType, fnt, pane);
        }
    }
    
    // This will clean the input so there is no possiblity for letters sneaking 
    // though. The only current issue is if there are no numbers in the String it
    // turns the output into all 0's.
    public int Input_clean(String tmpStr) {
        
        int tmpInt;
        tmpStr = tmpStr.replaceAll("\\D+", "");
        tmpStr = tmpStr.replaceFirst("0", "");

        if(!(tmpStr.isEmpty())) { 
            tmpInt = Integer.parseInt(tmpStr);
            return tmpInt;
        } 
        else { return 0; }
        
    }
    
    // Adds a string to the center of the JPane and brings it to the front
    public void Panel_addStringCenter(String str, Font fnt, JLayeredPane pane) {
        
        str = " " + str + " ";
        JLabel lbl = new JLabel(str);
        
        int width = lbl.getFontMetrics(fnt).stringWidth(str);
        int height = lbl.getFontMetrics(fnt).getHeight();
        
        int x = pane.getWidth() / 2 - (width / 2);
        int y = pane.getHeight() / 2 - (height / 2);
        
        lbl.setOpaque(false);
        lbl.setForeground(Color.BLACK);
        lbl.setFont(fnt);
        
        lbl.setBounds(x, y, width, height);
        
        pane.add(lbl, -1);
        pane.moveToFront(lbl);
        
        MOO_LIST.add(lbl);
    }
    
    // Adds a string to a random location on the JPane and brings it to the front
    private void Panel_addStringRandom(String str, Font fnt, JLayeredPane pane) {
        
        str = " " + str + " ";
        JLabel lbl = new JLabel(str);
        
        int width = lbl.getFontMetrics(fnt).stringWidth(str);
        int height = lbl.getFontMetrics(fnt).getHeight();
        
        int x = RND_GEN.nextInt((pane.getWidth() - width));
        int y = RND_GEN.nextInt((pane.getHeight() - height));
        
        lbl.setOpaque(false);
        lbl.setForeground(Color.BLACK);
        lbl.setBackground(Color.WHITE);
        lbl.setFont(fnt);
        
        lbl.setBounds(x, y, width, height);
        
        pane.add(lbl, -1);
        pane.moveToFront(lbl);
        
        MOO_LIST.add(lbl);
    }
    
    // This is applications specific and it sets up the 'New Game' button
    public void Panel_newGameButton(JButton btn, Font fnt, JLayeredPane pane) {
        
        String str = " New Game ";
        
        int width = btn.getFontMetrics(fnt).stringWidth(str);
        int height = btn.getFontMetrics(fnt).getHeight();
        
        int x = pane.getWidth() / 2 - (width / 2);
        int y = pane.getHeight() / 2 + (height / 2);
        
        btn.setVisible(false);
        btn.setBounds(x, y, width, height);
        btn.setText(str);
    }
    
    // recenter a button on the pane
    public void Panel_recenterButton(JButton btn, Font fnt, JLayeredPane pane) {
        
        String str = " New Game ";
        
        int width = btn.getFontMetrics(fnt).stringWidth(str);
        int height = btn.getFontMetrics(fnt).getHeight();
        
        int x = pane.getWidth() / 2 - (width / 2);
        int y = pane.getHeight() / 2 + (height / 2);
        
        btn.setLocation(x, y);
    }
        
    // Author: Dr. Estell, will be used to clear the pane.
    public void Panel_clear(JLayeredPane pane) {
        for(JLabel lbl: MOO_LIST) {
            lbl.setVisible(false);
            pane.remove(lbl);
        }
        
        MOO_LIST.removeAll(MOO_LIST);
    }
}
