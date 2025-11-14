package JokerGame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CardInfoPanel extends JPanel {
	private JPanel cardImage;
	private JLabel cardText;
	
	public CardInfoPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
		this.setBackground(new Color(0, 50, 0));
		
		cardImage = new JPanel();
		cardImage.setBackground(Color.WHITE);
		cardImage.setLayout(new BorderLayout());
		cardImage.setPreferredSize(new Dimension(105, 150));
		cardImage.setMaximumSize(new Dimension(105, 150));
		
		cardText = new JLabel("Card Info");
        cardText.setForeground(Color.WHITE);
        cardText.setFont(new Font("SansSerif", Font.BOLD, 15));
        cardText.setHorizontalAlignment(SwingConstants.CENTER);
		
        cardImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardText.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		//배치
        this.add(Box.createVerticalStrut(20));
		this.add(cardImage);
		this.add(Box.createVerticalStrut(10));
		this.add(cardText);
		this.add(Box.createVerticalGlue());
	}
}
