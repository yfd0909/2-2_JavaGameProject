package JokerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartFrame extends JFrame{
	String name = "";
	
	private JPanel startPanel = new JPanel();
	
	public StartFrame(){
		setTitle("시작 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null); // 화면 중앙에 배치
        
        startPanel.setLayout(new GridLayout(2, 1));
        
        //게임 로고 패널
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(54, 103, 84));
        
        String imagePath = "/Images/logo.png"; 
    	URL imageUrl = StartFrame.class.getResource(imagePath);
        ImageIcon logoIcon = new ImageIcon(imageUrl);
        JLabel logo = new JLabel(logoIcon);
        logo.setPreferredSize(new Dimension(500,300));
        
        logoPanel.add(logo);
        
        //그 밑 텍스트, 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(54, 103, 84));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JTextField nameText = new JTextField(15);
        Dimension preferredSize = nameText.getPreferredSize();
        nameText.setMaximumSize(preferredSize);
        
        
        //버튼 세팅
        Dimension buttonSize = new Dimension(140, 40);
        
        String imagePath2 = "/Images/start.png"; 
    	URL imageUrl2 = MainGameFrame.class.getResource(imagePath2);
        ImageIcon buttonIcon1 = new ImageIcon(imageUrl2);
        JButton startButton = new JButton(buttonIcon1);
        startButton.setPreferredSize(buttonSize);
        startButton.setMaximumSize(buttonSize);
        
        String imagePath3 = "/Images/exit.png"; 
    	URL imageUrl3 = MainGameFrame.class.getResource(imagePath3);
        ImageIcon buttonIcon2 = new ImageIcon(imageUrl3);
        JButton exitButton = new JButton(buttonIcon2);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        
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
        
        buttonPanel.add(Box.createVerticalStrut(40));
        buttonPanel.add(nameText);
        buttonPanel.add(Box.createVerticalStrut(40));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(exitButton);
        
        startPanel.add(logoPanel);
        startPanel.add(buttonPanel);        
        add(startPanel);
	}
}
