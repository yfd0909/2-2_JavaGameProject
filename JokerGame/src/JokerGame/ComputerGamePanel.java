package JokerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

class ComputerGamePanel extends JPanel {
	
    public ComputerGamePanel() {
        this.setLayout(null); 
        this.setBackground(new Color(49, 69, 68));
        this.setPreferredSize(new Dimension(1000, 150));
        
        // 임시 표시
        this.setBorder(BorderFactory.createTitledBorder(
            "Computer's Hand"
        ));
    }
    // 추후 Card클래스로 변경 예정
    public void DisplayHand(List<Card> cardList) {
        this.removeAll(); // 기존 카드들을 모두 제거
        
        int panelWidth = this.getWidth();
        int cardWidth = 70;
        int offset = 60;
        int numCards = cardList != null ? cardList.size() : 0;
        
        int totalWidth = cardWidth + (numCards - 1) * offset;
        int xPos = (panelWidth - totalWidth) / 2;
        int yPos = 30;
        int zIndex = 0;
        
        JButton cardButton;
        
        if (cardList != null) {
            for (Card card : cardList) {
            	// 카드 이미지 경로 설정
            	int cardNum =  card.getCardNum();
            	String imagePath = "/Images/" + cardNum + ".png"; 
            	URL imageUrl = MainGameFrame.class.getResource(imagePath);
                ImageIcon cardIcon = new ImageIcon(imageUrl);
            	
                // 임시 카드 생성 지금은 카드 이름 띄우기
                cardButton = new JButton(cardIcon);
                cardButton.setPreferredSize(new Dimension(cardWidth, 105));
                cardButton.setBounds(xPos, yPos, cardWidth, 105);
                cardButton.setBackground(Color.WHITE);
                cardButton.setForeground(Color.BLACK);
                
                this.add(cardButton); // 패널에 버튼 추가
                
                xPos += offset;
                this.setComponentZOrder(cardButton, zIndex);
                zIndex++;
            }
        }
        
        this.revalidate();
        this.repaint(); 
    }
}