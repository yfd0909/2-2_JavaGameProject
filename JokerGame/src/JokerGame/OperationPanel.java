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
	
	// 오디오 추가
	private TestAudio audioPlayer;
	private String buttonSound = "Button_Pressed.wav";
	
	ActionListener submitClickListener;
	ActionListener nextClickListener;
	
	private MainGameFrame mainFrame;
	private GameManager manager;
	
	public OperationPanel(MainGameFrame mainFrame, GameManager manager){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(new Color(0, 50, 0));
		
		this.mainFrame = mainFrame;
		this.manager = manager;
		
		//객체 생성
	    Dimension buttonSize = new Dimension(150, 50);// 버튼 크기
	    audioPlayer = new TestAudio();

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
            	audioPlayer.SFXAudio(buttonSound);
            	if(manager.isRoundEnd == true) //같은 카드로 계속 진행되는 불상사 방지 
            		return;
            	manager.RoundStart();
            }
        };
        
        nextClickListener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	audioPlayer.SFXAudio(buttonSound);
        		if(manager.isRoundEnd == false) //라운드 아직 안 끝났으면 리턴
        			return;
        		manager.NextRound();
        	}
        };
	    
        submitButton.addActionListener(submitClickListener);
        nextButton.addActionListener(nextClickListener);
        
        // 액션 리스너 추가해서 버튼 눌리는 이벤트 있을 때마다 효과음 출력
        ruleButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		audioPlayer.SFXAudio(buttonSound);
        	}
        });
        
        reButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		audioPlayer.SFXAudio(buttonSound);
        	}
        });
        
        quitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		audioPlayer.SFXAudio(buttonSound);
        	}
        });
        
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
