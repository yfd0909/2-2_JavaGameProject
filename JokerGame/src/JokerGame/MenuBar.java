package JokerGame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class MenuBar {
	MainGameFrame mainFrame;
	JMenuBar menuBar;
	
	//테마 바꾸기 메뉴들
	JMenu theme;
	JMenuItem originTheme;
	JMenuItem blackTheme;
	JMenuItem redTheme;
	JMenuItem pastelTheme;
	
	ActionListener themeListener;
	
	public MenuBar(MainGameFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		//JMenu 초기화
		menuBar = new JMenuBar();
        menuBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        //프레임에 추가
        mainFrame.setJMenuBar(menuBar);
        
        //액션 리스너 정의
        themeListener = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String command = e.getActionCommand();
        		switch(command) {
        			case "Original":
        				mainFrame.SetPanelTheme(new Color(49, 69, 68),
        						new Color(54, 103, 84),
        						new Color(27, 42, 48),
        						new Color(27, 42, 48));
        				break;
        			case "Black":
        				mainFrame.SetPanelTheme(new Color(17, 18, 12),
        						new Color(216, 209, 189),
        						new Color(87, 85, 73),
        						new Color(87, 85, 73));
        				break;
        			case "Red":
        				mainFrame.SetPanelTheme(new Color(89, 0, 40),
        						new Color(217, 59, 98),
        						new Color(127, 0, 57),
        						new Color(127, 0, 57));
        				break;
        			case "Pastel":
        				mainFrame.SetPanelTheme(new Color(202, 185, 217),
        						new Color(255, 221, 226),
        						new Color(239, 197, 215),
        						new Color(195, 196, 244));
        				break;
        			default:
        				break;
        		}
        	}
        };
        
        originTheme = new JMenuItem("Original");
        blackTheme = new JMenuItem("Black");
        redTheme = new JMenuItem("Red");
        pastelTheme = new JMenuItem("Pastel");
        
        //리스너 추가
        originTheme.addActionListener(themeListener);
        blackTheme.addActionListener(themeListener);
        redTheme.addActionListener(themeListener);
        pastelTheme.addActionListener(themeListener);
        
        theme = new JMenu("Theme");
        theme.add(originTheme);
        theme.add(blackTheme);
        theme.add(redTheme);
        theme.add(pastelTheme);
        
        menuBar.add(theme);
	}
}
