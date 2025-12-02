package JokerGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterGamePanel extends JPanel {

    	private Color bgColor = new Color(54, 103, 84, 0);
    	
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
    	
    	private final String[] opImages = {
    		    "/Images/add.png", 
    		    "/Images/sub.png", 
    		    "/Images/mul.png", 
    		    "/Images/div.png",
    		    "/Images/remain.png"
    		};
    	private javax.swing.Timer rollTimer;
    	private int rollCount = 0;
    	private final int MAX_ROLL = 4;
    	private int currentImageIndex = 0;
    	
    	ActionListener battleCardClickListener;
    	private GameManager manager;
    	private MainGameFrame mainFrame;
    	
        public CenterGamePanel(MainGameFrame mainFrame, GameManager manager) {
        	//패널 3등분
        	setBackground(new Color(54, 103, 84));
            setLayout(new GridLayout(3, 1));
            
            this.manager = manager;
            this.mainFrame = mainFrame;
            
            //이미지 경로 설정 
        	String imagePath1 = "/Images/0.png"; 
        	URL imageUrl1 = MainGameFrame.class.getResource(imagePath1);
            ImageIcon battleCardIcon = new ImageIcon(imageUrl1);
            
            String imagePath2 = "/Images/random.png"; //연산자 박스 이미지
        	URL imageUrl2 = MainGameFrame.class.getResource(imagePath2);
            ImageIcon randomBoxIcon = new ImageIcon(imageUrl2);
            
            String imagePath3 = "/Images/random.png"; //연산자 박스 이미지
        	URL imageUrl3 = MainGameFrame.class.getResource(imagePath3);
            ImageIcon resultOpIcon = new ImageIcon(imageUrl3);
            
            
            // 공용 세팅
            comOperatorBox = new JLabel(randomBoxIcon);
            userOperatorBox = new JLabel(randomBoxIcon);
            comOperatorBox.setPreferredSize(new Dimension(64, 64));
            userOperatorBox.setPreferredSize(new Dimension(64, 64));
            
            //컴퓨터 연산자 박스
            comResultOperatorBox = new JLabel("=");
            comResultOperatorBox.setFont(new Font("SansSerif", Font.BOLD, 30));
            comResultOperatorBox.setPreferredSize(new Dimension(35, 35));
            comResultOperatorBox.setForeground(Color.WHITE);
            
            //유저 연산자 박스
            userResultOperatorBox = new JLabel("=");
            userResultOperatorBox.setFont(new Font("SansSerif", Font.BOLD, 30));
            userResultOperatorBox.setPreferredSize(new Dimension(35, 35));
            userResultOperatorBox.setForeground(Color.WHITE);
            
            //리스너 설정
            battleCardClickListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if(!manager.canCard)
                		return;
                	manager.DeleteBattleCard();
                	
                	//카드 UI 업데이트
                	UpdateBattleField();
                	mainFrame.UpdateCardField();
                }
            };
            
            // 윗부분 (컴퓨터 쪽)
            JPanel topPanel = new JPanel();
            topPanel.setBackground(bgColor);
            topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
            
            comCard1 = new JButton(battleCardIcon);
            comCard2 = new JButton(battleCardIcon);
            comResult = new JLabel("?");
            comCard1.setPreferredSize(new Dimension(70, 105));
            comCard2.setPreferredSize(new Dimension(70, 105));
            comResult.setPreferredSize(new Dimension(70, 70));
            comResult.setFont(new Font("SansSerif", Font.BOLD, 30));
            comResult.setForeground(Color.red);
            
            topPanel.add(comCard1);
            topPanel.add(comOperatorBox);
            topPanel.add(comCard2);
            topPanel.add(comResultOperatorBox);
            topPanel.add(comResult);
            
            // 중간부분 (VS글자)
            JPanel middlePanel = new JPanel();
            middlePanel.setBackground(bgColor);
            middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
            
            versusBox = new JLabel("VS");
            versusBox.setSize(35, 35);
            middlePanel.add(versusBox);
            
            // 아랫부분 (플레이어쪽)
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(bgColor);
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5)); //간격 설정하는 거
            
            userCard1 = new JButton(battleCardIcon);
            userCard2 = new JButton(battleCardIcon);
            userResult = new JLabel("?");
            userCard1.setPreferredSize(new Dimension(70, 105));
            userCard2.setPreferredSize(new Dimension(70, 105));
            userResult.setPreferredSize(new Dimension(70, 70));
            userResult.setFont(new Font("SansSerif", Font.BOLD, 30));
            userResult.setForeground(Color.red);
            
            //리스너 추가
            if(battleCardClickListener != null) {
            	userCard1.addActionListener(battleCardClickListener);
                userCard2.addActionListener(battleCardClickListener);
            }
            
            bottomPanel.add(userCard1);
            bottomPanel.add(userOperatorBox);
            bottomPanel.add(userCard2);
            bottomPanel.add(userResultOperatorBox);
            bottomPanel.add(userResult);
            
            add(topPanel);
            add(middlePanel);
            add(bottomPanel);
            
            UpdateBattleField();
            this.revalidate();
            this.repaint(); 
        }
        public void RollOp(String op) {
        	//이미 타이머 실행 중이면 리턴 (중복 방지)
            if (rollTimer != null && rollTimer.isRunning()) {
                return;
            }
            
            rollCount = 0;
            currentImageIndex = 0;
            
            rollTimer = new javax.swing.Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if (currentImageIndex == 0 && rollCount > 0) {
                        if (rollCount >= MAX_ROLL) {
                            rollTimer.stop();
                            
                            URL finalUrl = MainGameFrame.class.getResource("/Images/"+ op +".png");
                            ImageIcon finalIcon = new ImageIcon(finalUrl);
                            comOperatorBox.setIcon(finalIcon);
                            userOperatorBox.setIcon(finalIcon);
                            return;
                        }
                    }
                    
                    URL imageUrl = MainGameFrame.class.getResource(opImages[currentImageIndex]);
                    ImageIcon opIcon = new ImageIcon(imageUrl);
                    
                    comOperatorBox.setIcon(opIcon);
                    userOperatorBox.setIcon(opIcon);
                    CenterGamePanel.this.repaint();
                    
                    currentImageIndex = (currentImageIndex + 1) % opImages.length;
                    
                    if (currentImageIndex == 0) {
                        rollCount++;
                    }
                }
            });
            rollTimer.start();
        }
        public void ShowResult(String playerResult, String pcResult) {
        	userResult.setText(playerResult);
        	comResult.setText(pcResult);
        	//결과 출력과 동시에 라운드 종료 
        	manager.RoundEnd();
        }
        public void ResetRandomBox() {
        	String imagePath2 = "/Images/random.png"; //연산자 박스 이미지
        	URL imageUrl2 = MainGameFrame.class.getResource(imagePath2);
            ImageIcon randomBoxIcon = new ImageIcon(imageUrl2);
            
            userOperatorBox.setIcon(randomBoxIcon);
            comOperatorBox.setIcon(randomBoxIcon);
            userResult.setText("?");
            comResult.setText("?");
        }
        public void UpdateBattleField() {
        	// 카드 이미지 경로 설정, 유저용
        	int cardNum1 =  manager.playerCh[0];
        	String imagePath1 = "/Images/" + cardNum1 + ".png"; 
        	URL imageUrl1 = MainGameFrame.class.getResource(imagePath1);
            ImageIcon cardIcon1 = new ImageIcon(imageUrl1);
            
            int cardNum2 =  manager.playerCh[1];
        	String imagePath2 = "/Images/" + cardNum2 + ".png"; 
        	URL imageUrl2 = MainGameFrame.class.getResource(imagePath2);
            ImageIcon cardIcon2 = new ImageIcon(imageUrl2);
            
            //컴퓨터용
            int cardNum3 =  manager.pcCh[0];
        	String imagePath3 = "/Images/" + cardNum3 + ".png"; 
        	URL imageUrl3 = MainGameFrame.class.getResource(imagePath3);
            ImageIcon cardIcon3 = new ImageIcon(imageUrl3);
            
            int cardNum4 =  manager.pcCh[1];
        	String imagePath4 = "/Images/" + cardNum4 + ".png"; 
        	URL imageUrl4 = MainGameFrame.class.getResource(imagePath4);
            ImageIcon cardIcon4 = new ImageIcon(imageUrl4);
            
            userCard1.setIcon(cardIcon1);
            userCard2.setIcon(cardIcon2);
            comCard1.setIcon(cardIcon3);
            comCard2.setIcon(cardIcon4);
        }
    }