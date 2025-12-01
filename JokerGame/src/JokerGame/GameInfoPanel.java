package JokerGame;

import java.awt.*;
import javax.swing.*;

public class GameInfoPanel extends JPanel{
	private LabelContainer turnContainer;
	private LabelContainer playerNameContainer;
	private LabelContainer winLoseContainer;
	private LabelContainer playerHPContainer;
	private LabelContainer computerHPContainer;
	private LabelContainer messageContainer;
	   
	public GameInfoPanel(GameInfo info) {
		
		Color labelColor = new Color(100, 0, 32);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
		this.setBackground(labelColor);
	        
		// 턴 수 라벨
        turnContainer = new LabelContainer(
            new Font("SansSerif", Font.BOLD, 20), Color.RED, 45, labelColor);
        
        // 플레이어 정보
        playerNameContainer = new LabelContainer(
                new Font("SansSerif", Font.BOLD, 15), Color.WHITE, 45, labelColor);
        winLoseContainer = new LabelContainer(
                new Font("SansSerif", Font.BOLD, 15), Color.WHITE, 45, labelColor);
        
        // 플레이어 카드 수 라벨
        playerHPContainer = new LabelContainer(
            new Font("SansSerif", Font.PLAIN, 15), Color.LIGHT_GRAY, 30, labelColor);
        
        // 컴퓨터 카드 수 라벨
        computerHPContainer = new LabelContainer(
            new Font("SansSerif", Font.PLAIN, 15), Color.LIGHT_GRAY, 30, labelColor);
        
        // 메시지 라벨 (얘는 공간을 넉넉하게 해주기 위해 maxvalue 넣기)
        messageContainer = new LabelContainer(
        	    new Font("SansSerif", Font.BOLD, 17), Color.YELLOW, Integer.MAX_VALUE, labelColor);
        
        // 생성자에서 초기화 슛
        UpdateInfo(info);
        // 플레이어 정보는 한 번만
        playerNameContainer.getLabel().setText("플레이어 이름 : " + info.GetPlayerName());
        winLoseContainer.getLabel().setText(
        		"승리 : " + info.GetWinCount() + " / " +
        		"패배 : " + info.GetLoseCount());
        
        // X축 정렬
        turnContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerHPContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        computerHPContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 패널에 추가 
        this.add(Box.createVerticalStrut(10));
        this.add(turnContainer);
        this.add(Box.createVerticalStrut(20));
        this.add(playerNameContainer);
        this.add(Box.createVerticalStrut(10));
        this.add(winLoseContainer);
        this.add(Box.createVerticalStrut(20));
        this.add(playerHPContainer);
        this.add(Box.createVerticalStrut(10));
        this.add(computerHPContainer);
        this.add(Box.createVerticalStrut(20));
        this.add(messageContainer);
        
        this.add(Box.createVerticalGlue()); 
	}
	public void UpdateInfo(GameInfo info) {
		
		turnContainer.getLabel().setText("현재 라운드 : " + info.GetTurnCount());
        playerHPContainer.getLabel().setText("나의 체력: " + info.GetPlayerHp());
        computerHPContainer.getLabel().setText("상대 체력: " + info.GetComputerHp());
        messageContainer.getLabel().setText(info.GetMessage());
        
        winLoseContainer.getLabel().setText(
        		"승리 : " + info.GetWinCount() + " / " +
        		"패배 : " + info.GetLoseCount());
        
        //패널 다시 그리기
        this.revalidate();
        this.repaint();
	}
}
