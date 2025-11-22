package JokerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame{
	String name = "";
	
	public StartFrame(){
		setTitle("시작 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // 화면 중앙에 배치
        
        //게임 로고 패널
        JPanel logoPanel = new JPanel();
        
        JLabel logo = new JLabel();
        logo.setPreferredSize(new Dimension(300, 200));
        
        logoPanel.add(logo);
        
        //그 밑 텍스트, 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JTextField nameText = new JTextField(15);
        Dimension preferredSize = nameText.getPreferredSize();
        nameText.setMaximumSize(preferredSize);
        
        JButton startButton = new JButton("게임 시작");
        startButton.setPreferredSize(new Dimension(150, 40));
        
        JButton exitButton = new JButton("종료");
        exitButton.setPreferredSize(new Dimension(150, 40));
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String inputText = nameText.getText().trim(); 
            	if(inputText.isEmpty()){
            		JOptionPane.showMessageDialog(
            				null, 
            			    "이름을 입력해 주세요!",
            			    "경고",
            			    JOptionPane.WARNING_MESSAGE
            			);
            		return;
            	}
            	
                name = inputText;
                
            	//메인 게임 프레임으로 이동
                MainGameFrame mainFrame = new MainGameFrame("Joker Game", name);
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //시스템 종료시키기
			}
		});

        nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(nameText);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(exitButton);
        
        add(logoPanel);
        add(buttonPanel);
	}
}
