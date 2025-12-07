package JokerGame;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.*;

public class RuleFrame extends JFrame{
	private JLabel ruleImage;
	public RuleFrame() {
        setSize(350, 600);
        setResizable(false);
        
        URL ruleUrl = MainGameFrame.class.getResource("/Images/ruleImage.png");
        ImageIcon originalIcon = new ImageIcon(ruleUrl);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(
        		330, 450, Image.SCALE_SMOOTH 
        );
        
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        ruleImage = new JLabel(scaledIcon);
        ruleImage.setPreferredSize(new Dimension(330, 500));
        
        this.add(ruleImage);
        this.setVisible(true);
	}
}
