package JokerGame;

import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel{
	private LabelContainer turnContainer;
	private LabelContainer playerCardCountContainer;
	private LabelContainer computerCardCountContainer;
	private LabelContainer messageContainer;
	   
	public GameInfoPanel(GameInfo info) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
		this.setBackground(new Color(0, 50, 0));
	        
		// 턴 수 라벨
        turnContainer = new LabelContainer(
            new Font("SansSerif", Font.BOLD, 20), Color.WHITE, 45);
        
        // 플레이어 카드 수 라벨
        playerCardCountContainer = new LabelContainer(
            new Font("SansSerif", Font.PLAIN, 15), Color.LIGHT_GRAY, 30);
        
        // 컴퓨터 카드 수 라벨
        computerCardCountContainer = new LabelContainer(
            new Font("SansSerif", Font.PLAIN, 15), Color.LIGHT_GRAY, 30);
        
        // 메시지 라벨
        messageContainer = new LabelContainer(
            new Font("SansSerif", Font.BOLD, 17), Color.YELLOW, 40);
        
        // 생성자에서 초기화 한 번 슛
        UpdateInfo(info);
        
        // X축 정렬
        turnContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerCardCountContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        computerCardCountContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 패널에 추가 
        this.add(Box.createVerticalStrut(10));
        this.add(turnContainer);
        this.add(Box.createVerticalStrut(20));
        this.add(playerCardCountContainer);
        this.add(playerCardCountContainer);
        this.add(Box.createVerticalStrut(10));
        this.add(computerCardCountContainer);
        this.add(Box.createVerticalStrut(20));
        this.add(messageContainer);
        
        this.add(Box.createVerticalGlue()); 
	}
	public void UpdateInfo(GameInfo info) {
		
		turnContainer.getLabel().setText("현재 턴 " + info.GetTurnCount());
        playerCardCountContainer.getLabel().setText("내 카드 수: " + info.GetPlayerCardCount());
        computerCardCountContainer.getLabel().setText("상대 카드 수: " + info.GetComputerCardCount());
        messageContainer.getLabel().setText(info.GetMessage());
        
        //패널 다시 그리기
        this.revalidate();
        this.repaint();
	}
}
