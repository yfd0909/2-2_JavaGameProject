package JokerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class PlayerGamePanel extends JPanel {
	
	ActionListener cardClickListener;
	MouseAdapter cardMouseAdapter;
	
	private GameManager manager;
	private MainGameFrame mainFrame;
	
	private TestAudio audioPlayer;
	private String cardSound = "clickCard.wav";
	
    public PlayerGamePanel(MainGameFrame mainFrame, GameManager manager) {
        // 플레이어 패 설정 
        this.setLayout(null); 
        this.setBackground(new Color(49, 69, 68));
        this.setPreferredSize(new Dimension(1000, 150));
        
        //참조
        this.mainFrame = mainFrame;
        this.manager = manager;

        // 임시 표시
        this.setBorder(BorderFactory.createTitledBorder(
            "Your Hand"
        ));
    }
    public void DisplayHand(List<Card> cardList) {
        this.removeAll(); // 기존 카드들을 모두 제거

        int panelWidth = this.getWidth();
        int cardWidth = 70;
        int offset = 60;
        int numCards = cardList != null ? cardList.size() : 0;
        
        int totalWidth = cardWidth + (numCards - 1) * offset;
        int xPos = (panelWidth - totalWidth) / 2;
        int yPos = 30;
        
        //카드 클릭 시 동작할 마우스어댑터
        cardMouseAdapter = new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setBorder(new LineBorder(Color.MAGENTA, 4));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setBorder(UIManager.getBorder("Button.border"));
            }
        };
        // 카드 클릭 시 동작할 액션리스너
        cardClickListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	audioPlayer.SFXAudio(cardSound);
            	JButton clickedButton = (JButton) e.getSource();
                int clickedCardIndex = (int) clickedButton.getClientProperty("index");
                manager.AddBattleCard(clickedCardIndex);
                
                //카드 UI 업데이트
                DisplayHand(manager.playerHand);
                mainFrame.UpdateCenterBattleField(false);
            }
        };
        
        JButton cardButton;
        
        if (cardList != null) {
        	int cardIndex = 0;
            for (Card card : cardList) {
            	// 카드 이미지 경로 설정
            	int cardNum =  card.getCardNum();
            	String imagePath = "/Images/" + cardNum + ".png"; 
            	URL imageUrl = MainGameFrame.class.getResource(imagePath);
                ImageIcon cardIcon = new ImageIcon(imageUrl);
            	
                // 임시 카드 생성 지금은 카드 이름 띄우기
                cardButton = new JButton(cardIcon);
                cardButton.setPreferredSize(new Dimension(cardWidth, 100));
                cardButton.setBounds(xPos, yPos, cardWidth, 100);
                cardButton.setBackground(Color.WHITE);
                cardButton.setForeground(Color.BLACK);
                
                //카드마다 고유 index 할당 (나중에 클릭할 때 몇 번째 카드인지 확인용)
                cardButton.putClientProperty("index", cardIndex);
                cardIndex++;
                
                this.add(cardButton); // 패널에 버튼 추가
                
                // 카드에 액션리스너, 마우스어댑터 추가
                if (cardClickListener != null) {
                    cardButton.addActionListener(cardClickListener);
                    cardButton.addMouseListener(cardMouseAdapter);
                }
                
                xPos += offset;
                this.setComponentZOrder(cardButton, 0);
            }
        }
        
        this.revalidate(); // 컴포넌트 추가 후 레이아웃을 다시 계산
        this.repaint(); // 컴포넌트 다시 그림
    }
}