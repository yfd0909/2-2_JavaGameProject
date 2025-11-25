package JokerGame;

import javax.swing.*;
import java.awt.*;

// 중앙 카드더미 및 게임판 패널
public class CenterGamePanel extends JPanel {

	private Color bgColor = new Color(54, 103, 84);
	
	private JLabel comOperatorBox;
	private JLabel comResultOperatorBox;
	private JLabel userOperatorBox;
	private JLabel userResultOperatorBox;
	private JLabel versusBox;
	
	private JButton comCard1;
	private JButton comCard2;
	private JLabel comResult;
	
	private JButton userCard1;
	private JButton userCard2;
	private JLabel userResult;
	
    public CenterGamePanel() {
    	//패널 3등분
        setLayout(new GridLayout(3, 1));
        
        // 공용 세팅
        comOperatorBox = new JLabel();
        comResultOperatorBox = new JLabel();
        userOperatorBox = new JLabel();
        userResultOperatorBox = new JLabel();
        versusBox = new JLabel();
        comOperatorBox.setPreferredSize(new Dimension(35, 35));
        comResultOperatorBox.setPreferredSize(new Dimension(35, 35));
        userOperatorBox.setPreferredSize(new Dimension(35, 35));
        userResultOperatorBox.setPreferredSize(new Dimension(35, 35));
        versusBox.setPreferredSize(new Dimension(35, 35));
        
        // 윗부분 (컴퓨터 쪽)
        JPanel topPanel = new JPanel();
        topPanel.setBackground(bgColor);
        comCard1 = new JButton("?");
        comCard2 = new JButton("?");
        comResult = new JLabel("?");
        comCard1.setPreferredSize(new Dimension(70, 105));
        comCard2.setPreferredSize(new Dimension(70, 105));
        comResult.setPreferredSize(new Dimension(70, 70));
        comResult.setFont(new Font("SansSerif", Font.BOLD, 15));
        comResult.setForeground(Color.red);
        topPanel.add(comCard1);
        topPanel.add(comOperatorBox);
        topPanel.add(comCard2);
        topPanel.add(comResultOperatorBox);
        topPanel.add(comResult);
        
        // 중간부분 (VS글자)
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(bgColor);
        middlePanel.add(versusBox);
        
        // 아랫부분 (플레이어쪽)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(bgColor);
        userCard1 = new JButton("?");
        userCard2 = new JButton("?");
        userResult = new JLabel("?");
        userCard1.setPreferredSize(new Dimension(70, 105));
        userCard2.setPreferredSize(new Dimension(70, 105));
        userResult.setPreferredSize(new Dimension(70, 70));
        userResult.setFont(new Font("SansSerif", Font.BOLD, 15));
        userResult.setForeground(Color.red);
        bottomPanel.add(userCard1);
        bottomPanel.add(userOperatorBox);
        bottomPanel.add(userCard2);
        bottomPanel.add(userResultOperatorBox);
        bottomPanel.add(userResult);
        
        add(topPanel);
        add(middlePanel);
        add(bottomPanel);
    }
}