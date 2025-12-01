package JokerGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public OperationPanel(MainGameFrame mainFrame, GameManager manager){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(0, 50, 0));
		
		this.mainFrame = mainFrame;
		this.manager = manager;
		
		//객체 생성
	    Dimension buttonSize = new Dimension(150, 50);// 버튼 크기

	    submitButton = new JButton("라운드 시작");
	    submitButton.setPreferredSize(buttonSize);
	    submitButton.setMaximumSize(buttonSize); 
	    submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    nextButton = new JButton("다음 라운드");
	    nextButton.setPreferredSize(buttonSize);
	    nextButton.setMaximumSize(buttonSize); 
	    nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    ruleButton = new JButton("게임 설명");
	    ruleButton.setPreferredSize(buttonSize);
	    ruleButton.setMaximumSize(buttonSize);
	    ruleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    reButton = new JButton("다시하기");
	    reButton.setPreferredSize(buttonSize);
	    reButton.setMaximumSize(buttonSize);
	    reButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    
	    quitButton = new JButton("종료");
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
        		mainFrame.ProgramEnd();
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
