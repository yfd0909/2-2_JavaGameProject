package JokerGame;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CardInfoPanel extends JPanel {
	private JPanel cardImage;
	private LabelContainer cardTypeContainer;
	private LabelContainer cardNumContainer;
	
	public CardInfoPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
		this.setBackground(new Color(0, 50, 0));
		
		Color labelColor = new Color(128, 0, 32);
		
		cardImage = new JPanel();
		cardImage.setPreferredSize(new Dimension(105, 150));
		
		cardTypeContainer = new LabelContainer(
	            new Font("SansSerif", Font.PLAIN, 15), Color.WHITE, 45, labelColor);
		
		cardNumContainer = new LabelContainer(
	            new Font("SansSerif", Font.BOLD, 20), Color.MAGENTA, 45, labelColor);
        
		//배치
        this.add(Box.createVerticalStrut(20));
		this.add(cardImage);
		this.add(Box.createVerticalStrut(10));
		this.add(cardTypeContainer);
		this.add(Box.createVerticalStrut(10));
		this.add(cardNumContainer);
	}
	public void SetCardText(String type, String num) {
		//여기에 클릭된 카드 정보 텍스트 업데이트
		cardTypeContainer.getLabel().setText(type);
		cardNumContainer.getLabel().setText(num);
	}
}
