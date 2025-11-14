package JokerGame;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

// 상대방 패를 나타내는 패널 클래스
public class ComputerGamePanel extends JPanel {

	ActionListener cardClickListener;
	MouseAdapter cardMouseAdapter;
	
    public ComputerGamePanel() {
        this.setLayout(null); 
        this.setBackground(new Color(50, 50, 50));
        this.setPreferredSize(new Dimension(1000, 150));
        
        // 임시 표시
        this.setBorder(BorderFactory.createTitledBorder(
            "Computer's Hand"
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
        int zIndex = 0;
        
        JButton cardButton;
        //카드 클릭 시 동작할 마우스어댑터
        cardMouseAdapter = new MouseAdapter() {
        	@Override
            public void mouseEntered(MouseEvent e) {
                // 이벤트가 발생한 소스 얻어옴
                JButton sourceButton = (JButton) e.getSource();
                sourceButton.setBorder(new LineBorder(Color.RED, 3));
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
            	//내 턴일 때만 클릭 가능하게. 추후 게임매니저에 boolean 변수 isMyTurn 추가
            	//if(gm.isMyTurn == true) 로 진행
                String clickedCardName = e.getActionCommand();
                
                //덱 리스트에서 카드 삭제 함수 호출 + 내 카드에 추가
                //같은 카드 삭제 등 게임 진행
                //카드인포 패널에 선택한 카드 정보 출력
                //다음 턴 진행, gm.isMyTurn = false;
                
                //카드 UI 업데이트 -> UpdateInfo()
            }
        };
        
        if (cardList != null) {
            for (TestCard card : cardList) {
                // 임시 카드 생성 지금은 카드 이름 띄우기
                cardButton = new JButton(card.getType());
                cardButton.setPreferredSize(new Dimension(cardWidth, 100));
                cardButton.setBounds(xPos, yPos, cardWidth, 100);
                cardButton.setBackground(Color.WHITE);
                cardButton.setForeground(Color.BLACK);
                cardButton.setActionCommand(card.getType());
                cardButton.setOpaque(true);
                
                // 카드 클릭 리스너 추가
                if (cardClickListener != null) {
                    cardButton.addActionListener(cardClickListener);
                    cardButton.addMouseListener(cardMouseAdapter);
                }
                
                this.add(cardButton); // 패널에 버튼 추가
                
                xPos += offset;
                this.setComponentZOrder(cardButton, zIndex);
                zIndex++;
            }
        }
        
        this.revalidate(); // 컴포넌트 추가 후 레이아웃을 다시 계산
        this.repaint(); // 컴포넌트 
    }
}
