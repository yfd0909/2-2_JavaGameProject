package JokerGame;

import javax.swing.*;
import java.util.List;
import java.awt.*;

// 플레이어 패널 클래스
public class PlayerGamePanel extends JPanel {
	
    public PlayerGamePanel() {
        // 플레이어 패 설정 
        this.setLayout(null); 
        this.setBackground(new Color(100, 100, 100));
        this.setPreferredSize(new Dimension(1000, 150));

        // 임시 표시
        this.setBorder(BorderFactory.createTitledBorder(
            "Your Hand"
        ));
    }
    // 추후 Card클래스로 변경 예정
    public void DisplayHand(List<TestCard> cardList) {
        this.removeAll(); // 기존 카드들을 모두 제거

        int panelWidth = this.getWidth();
        int cardWidth = 70;
        int offset = 40;
        int numCards = cardList != null ? cardList.size() : 0;
        
        int totalWidth = cardWidth + (numCards - 1) * offset;
        int xPos = (panelWidth - totalWidth) / 2;
        int yPos = 30;
        
        if (cardList != null) {
            for (TestCard card : cardList) {
                // 임시 카드 생성 지금은 카드 이름 띄우기
                JButton cardButton = new JButton(card.getType());
                cardButton.setPreferredSize(new Dimension(cardWidth, 100));
                cardButton.setBounds(xPos, yPos, cardWidth, 100);
                cardButton.setBackground(Color.WHITE);
                cardButton.setForeground(Color.BLACK);
                cardButton.setActionCommand(card.getType());
                
                this.add(cardButton); // 패널에 버튼 추가
                
                xPos += offset;
                this.setComponentZOrder(cardButton, 0);
            }
        }
        
        this.revalidate(); // 컴포넌트 추가 후 레이아웃을 다시 계산
        this.repaint(); // 컴포넌트 다시 그림
    }
}