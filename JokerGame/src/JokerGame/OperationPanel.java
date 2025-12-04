package JokerGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OperationPanel extends JPanel {
	JButton submitButton; //라운드 시작 버튼
	JButton nextButton; //다음 라운드 넘어가기
	JButton ruleButton; //게임 설명서 버튼
	JButton reButton; //다시하기 버튼
	JButton quitButton; //종료 버튼
	
	ActionListener submitClickListener;
	ActionListener nextClickListener;
	ActionListener reClickListener;
	ActionListener quitClickListener;
	
	private MainGameFrame mainFrame;
	private GameManager manager;
	private RuleFrame ruleFrame; //게임 설명서
	
	public OperationPanel(MainGameFrame mainFrame, GameManager manager){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(27, 42, 48));
		
		this.mainFrame = mainFrame;
		this.manager = manager;
		
		//이미지 설정 변수
		String buttonImagePath = "/Images/game.png"; //연산자 박스 이미지
    	URL buttonImageUrl = MainGameFrame.class.getResource(buttonImagePath);
        ImageIcon buttonIcon = new ImageIcon(buttonImageUrl);
        
		//객체 생성
	    Dimension buttonSize = new Dimension(140, 40);// 버튼 크기

	    buttonImagePath = "/Images/play.png";
	    buttonImageUrl = MainGameFrame.class.getResource(buttonImagePath);
	    buttonIcon = new ImageIcon(buttonImageUrl);
	    submitButton = new JButton(buttonIcon);
	    submitButton.setPreferredSize(buttonSize);
	    submitButton.setMaximumSize(buttonSize); 
	    submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    buttonImagePath = "/Images/next.png";
	    buttonImageUrl = MainGameFrame.class.getResource(buttonImagePath);
	    buttonIcon = new ImageIcon(buttonImageUrl);
	    nextButton = new JButton(buttonIcon);
	    nextButton.setPreferredSize(buttonSize);
	    nextButton.setMaximumSize(buttonSize); 
	    nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    buttonImagePath = "/Images/rule.png";
	    buttonImageUrl = MainGameFrame.class.getResource(buttonImagePath);
	    buttonIcon = new ImageIcon(buttonImageUrl);
	    ruleButton = new JButton(buttonIcon);
	    ruleButton.setPreferredSize(buttonSize);
	    ruleButton.setMaximumSize(buttonSize);
	    ruleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    buttonImagePath = "/Images/re.png";
	    buttonImageUrl = MainGameFrame.class.getResource(buttonImagePath);
	    buttonIcon = new ImageIcon(buttonImageUrl);
	    reButton = new JButton(buttonIcon);
	    reButton.setPreferredSize(buttonSize);
	    reButton.setMaximumSize(buttonSize);
	    reButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    buttonImagePath = "/Images/exit2.png";
	    buttonImageUrl = MainGameFrame.class.getResource(buttonImagePath);
	    buttonIcon = new ImageIcon(buttonImageUrl);
	    quitButton = new JButton(buttonIcon);
	    quitButton.setPreferredSize(buttonSize);
	    quitButton.setMaximumSize(buttonSize);
	    quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    submitClickListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(manager.isRoundEnd == true || manager.isGameOver == true) //같은 카드로 계속 진행되는 불상사 방지 
            		return;
            	manager.RoundStart();
            }
        };
        nextClickListener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(manager.isRoundEnd == false || manager.isGameOver == true) //라운드 아직 안 끝났거나 게임 종료되면 리턴
        			return;
        		manager.NextRound();
        	}
        };
        ruleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ruleFrame != null) //이미 켜져있으면 리턴
            	{
            		ruleFrame.setVisible(true);
            		return;
            	}
            	//메인프레임 사이즈 계산
                int parentX = mainFrame.getX();
                int parentY = mainFrame.getY();
                int parentWidth = mainFrame.getWidth();
                //객체생성
                ruleFrame = new RuleFrame();
                //새 프레임 위치 계산
                int newX = parentX + parentWidth; 
                int newY = parentY;
                ruleFrame.setLocation(newX, newY);
                ruleFrame.setVisible(true);
            }
        });
        reClickListener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(manager.isGameOver == false) //게임종료되지 않았으면 다시하기 안 됨
        			return;
        		manager.ResetGame();
        	}
        };
        quitClickListener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(manager.isGameOver == false) //게임종료되지 않았으면 게임종료 안 됨
        			return;
        		System.exit(0);
        	}
        };
        
        submitButton.addActionListener(submitClickListener);
        nextButton.addActionListener(nextClickListener);
        reButton.addActionListener(reClickListener);
        quitButton.addActionListener(quitClickListener);
        
		//배치
		this.add(Box.createVerticalStrut(30));
		this.add(submitButton);
		this.add(Box.createVerticalStrut(20));
		this.add(nextButton);
		this.add(Box.createVerticalStrut(20));
		this.add(ruleButton);
		this.add(Box.createVerticalStrut(20));
		this.add(reButton);
		this.add(Box.createVerticalStrut(20));
		this.add(quitButton);
		this.add(Box.createVerticalStrut(30));
	}
}
