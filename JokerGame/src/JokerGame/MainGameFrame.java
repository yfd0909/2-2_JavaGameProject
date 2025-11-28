package JokerGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.util.ArrayList;
import java.util.List;

public class MainGameFrame extends JFrame {
	
    // 각 패널 선언
    private ComputerGamePanel computerHandPanel; //컴퓨터 패널
    private PlayerGamePanel playerHandPanel; //플레이어 패널 
    private CenterGamePanel centerTablePanel; //중앙 패널
    private GameInfoPanel gameInfoPanel; //게임 인포 패널
    private CardInfoPanel cardInfoPanel; //우측 카드 인포 패널
    private JPanel mainPanel; // 메인패널
    
    //게임 인포
    private GameInfo info;
    private GameManager manager;
    
    class PlayerGamePanel extends JPanel {
    	
    	ActionListener cardClickListener;
    	MouseAdapter cardMouseAdapter;
    	GameManager manager;
    	
        public PlayerGamePanel(GameManager manager) {
            // 플레이어 패 설정 
            this.setLayout(null); 
            this.setBackground(new Color(49, 69, 68));
            this.setPreferredSize(new Dimension(1000, 150));
            this.manager = manager;

            // 임시 표시
            this.setBorder(BorderFactory.createTitledBorder(
                "Your Hand"
            ));
        }
        // 추후 Card클래스로 변경 예정
        public void DisplayHand(List<Card> cardList) {
            this.removeAll(); // 기존 카드들을 모두 제거

            int panelWidth = this.getWidth();
            int cardWidth = 70;
            int offset = 60;
            int numCards = cardList != null ? cardList.size() : 0;
            
            int totalWidth = cardWidth + (numCards - 1) * offset;
            int xPos = (panelWidth - totalWidth) / 2;
            int yPos = 30;
            
            //카드 클릭 시 동작할 마우스어댑터
            cardMouseAdapter = new MouseAdapter() {
            	@Override
                public void mouseEntered(MouseEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    sourceButton.setBorder(new LineBorder(Color.MAGENTA, 4));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    JButton sourceButton = (JButton) e.getSource();
                    sourceButton.setBorder(UIManager.getBorder("Button.border"));
                }
            };
            // 카드 클릭 시 동작할 액션리스너
            cardClickListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	JButton clickedButton = (JButton) e.getSource();
                    int clickedCardIndex = (int) clickedButton.getClientProperty("index");
                    manager.AddBattleCard(clickedCardIndex);
                    
                    //카드 UI 업데이트
                    DisplayHand(manager.playerHand);
                    MainGameFrame.this.UpdateCenterBattleField();
                }
            };
            
            JButton cardButton;
            
            if (cardList != null) {
            	int cardIndex = 0;
                for (Card card : cardList) {
                	// 카드 이미지 경로 설정
                	int cardNum =  card.getCardNum();
                	String imagePath = "/Images/" + cardNum + ".png"; 
                	URL imageUrl = MainGameFrame.class.getResource(imagePath);
                    ImageIcon cardIcon = new ImageIcon(imageUrl);
                	
                    // 임시 카드 생성 지금은 카드 이름 띄우기
                    cardButton = new JButton(cardIcon);
                    cardButton.setPreferredSize(new Dimension(cardWidth, 100));
                    cardButton.setBounds(xPos, yPos, cardWidth, 100);
                    cardButton.setBackground(Color.WHITE);
                    cardButton.setForeground(Color.BLACK);
                    
                    //카드마다 고유 index 할당 (나중에 클릭할 때 몇 번째 카드인지 확인용)
                    cardButton.putClientProperty("index", cardIndex);
                    cardIndex++;
                    
                    this.add(cardButton); // 패널에 버튼 추가
                    
                    // 카드에 액션리스너, 마우스어댑터 추가
                    if (cardClickListener != null) {
                        cardButton.addActionListener(cardClickListener);
                        cardButton.addMouseListener(cardMouseAdapter);
                    }
                    
                    xPos += offset;
                    this.setComponentZOrder(cardButton, 0);
                }
            }
            
            this.revalidate(); // 컴포넌트 추가 후 레이아웃을 다시 계산
            this.repaint(); // 컴포넌트 다시 그림
        }
    }
    
    class ComputerGamePanel extends JPanel {
    	
        public ComputerGamePanel() {
            this.setLayout(null); 
            this.setBackground(new Color(49, 69, 68));
            this.setPreferredSize(new Dimension(1000, 150));
            
            // 임시 표시
            this.setBorder(BorderFactory.createTitledBorder(
                "Computer's Hand"
            ));
        }
        // 추후 Card클래스로 변경 예정
        public void DisplayHand(List<Card> cardList) {
            this.removeAll(); // 기존 카드들을 모두 제거
            
            int panelWidth = this.getWidth();
            int cardWidth = 70;
            int offset = 60;
            int numCards = cardList != null ? cardList.size() : 0;
            
            int totalWidth = cardWidth + (numCards - 1) * offset;
            int xPos = (panelWidth - totalWidth) / 2;
            int yPos = 30;
            int zIndex = 0;
            
            JButton cardButton;
            
            if (cardList != null) {
                for (Card card : cardList) {
                	// 카드 이미지 경로 설정
                	int cardNum =  card.getCardNum();
                	String imagePath = "/Images/" + cardNum + ".png"; 
                	URL imageUrl = MainGameFrame.class.getResource(imagePath);
                    ImageIcon cardIcon = new ImageIcon(imageUrl);
                	
                    // 임시 카드 생성 지금은 카드 이름 띄우기
                    cardButton = new JButton(cardIcon);
                    cardButton.setPreferredSize(new Dimension(cardWidth, 100));
                    cardButton.setBounds(xPos, yPos, cardWidth, 100);
                    cardButton.setBackground(Color.WHITE);
                    cardButton.setForeground(Color.BLACK);
                    
                    this.add(cardButton); // 패널에 버튼 추가
                    
                    xPos += offset;
                    this.setComponentZOrder(cardButton, zIndex);
                    zIndex++;
                }
            }
            
            this.revalidate();
            this.repaint(); 
        }
    }
    // 중앙 카드더미 및 게임판 패널
    class CenterGamePanel extends JPanel {

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
    	
    	ActionListener battleCardClickListener;
    	
        public CenterGamePanel() {
        	//패널 3등분
            setLayout(new GridLayout(3, 1));
            
            // 공용 세팅
            comOperatorBox = new JLabel();
            comResultOperatorBox = new JLabel();
            userOperatorBox = new JLabel();
            userResultOperatorBox = new JLabel();
            comOperatorBox.setPreferredSize(new Dimension(35, 35));
            comResultOperatorBox.setPreferredSize(new Dimension(35, 35));
            userOperatorBox.setPreferredSize(new Dimension(35, 35));
            userResultOperatorBox.setPreferredSize(new Dimension(35, 35));
            
        	String imagePath = "/Images/0.png"; 
        	URL imageUrl = MainGameFrame.class.getResource(imagePath);
            ImageIcon battleCardIcon = new ImageIcon(imageUrl);
            
            //리스너 설정
            battleCardClickListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	manager.DeleteBattleCard();
                	
                	//카드 UI 업데이트
                	UpdateBattleField();
                	MainGameFrame.this.UpdatePlayerField();
                }
            };
            
            // 윗부분 (컴퓨터 쪽)
            JPanel topPanel = new JPanel();
            topPanel.setBackground(bgColor);
            comCard1 = new JButton(battleCardIcon);
            comCard2 = new JButton(battleCardIcon);
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
            versusBox = new JLabel();
            versusBox.setSize(35, 35);
            middlePanel.add(versusBox);
            
            // 아랫부분 (플레이어쪽)
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBackground(bgColor);
            userCard1 = new JButton(battleCardIcon);
            userCard2 = new JButton(battleCardIcon);
            userResult = new JLabel("?");
            userCard1.setPreferredSize(new Dimension(70, 105));
            userCard2.setPreferredSize(new Dimension(70, 105));
            userResult.setPreferredSize(new Dimension(70, 70));
            userResult.setFont(new Font("SansSerif", Font.BOLD, 15));
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
        public void UpdateBattleField() {
        	// 카드 이미지 경로 설정
        	int cardNum1 =  manager.playerCh[0];
        	String imagePath1 = "/Images/" + cardNum1 + ".png"; 
        	URL imageUrl1 = MainGameFrame.class.getResource(imagePath1);
            ImageIcon cardIcon1 = new ImageIcon(imageUrl1);
            
            int cardNum2 =  manager.playerCh[1];
        	String imagePath2 = "/Images/" + cardNum2 + ".png"; 
        	URL imageUrl2 = MainGameFrame.class.getResource(imagePath2);
            ImageIcon cardIcon2 = new ImageIcon(imageUrl2);
            
            userCard1.setIcon(cardIcon1);
            userCard2.setIcon(cardIcon2);
        }
    }

    public MainGameFrame(String title, String playerName) {
        super(title);
        // 창 기본 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);
        
        manager = new GameManager(); //실게임 관리 매니저 생성
        
        // 메인 패널 초기화
        mainPanel = new JPanel(new BorderLayout());
        
        // 컴퓨터 패널 객체 생성 및 설정
        computerHandPanel = new ComputerGamePanel();
        computerHandPanel.setPreferredSize(new Dimension(getWidth(), 150));
        mainPanel.add(computerHandPanel, BorderLayout.NORTH);

        // 플레이어 패널 객체 생성 및 설정 
        playerHandPanel = new PlayerGamePanel(manager);
        playerHandPanel.setPreferredSize(new Dimension(getWidth(), 150));
        mainPanel.add(playerHandPanel, BorderLayout.SOUTH);
        
        // 중앙 패널 객체 생성 및 설정
        centerTablePanel = new CenterGamePanel();
        mainPanel.add(centerTablePanel, BorderLayout.CENTER);
        
        info = new GameInfo(playerName);
        gameInfoPanel = new GameInfoPanel(info);
        gameInfoPanel.setPreferredSize(new Dimension(250, getHeight()));
        mainPanel.add(gameInfoPanel, BorderLayout.WEST);
        
        cardInfoPanel = new CardInfoPanel();
        cardInfoPanel.setPreferredSize(new Dimension(250, getHeight()));
        mainPanel.add(cardInfoPanel, BorderLayout.EAST);
        

        // 메인 패널 추가
        this.setContentPane(mainPanel);
        setLocationRelativeTo(null); 
        setVisible(true);
        
        
        // 플레이어, 컴퓨터 패널에 카드 표시
        computerHandPanel.DisplayHand(manager.pcHand);
        playerHandPanel.DisplayHand(manager.playerHand);
        
    }
    public void UpdateCenterBattleField() {
        centerTablePanel.UpdateBattleField(); 
        centerTablePanel.revalidate();
        centerTablePanel.repaint();
    }
    public void UpdatePlayerField() {
    	playerHandPanel.DisplayHand(manager.playerHand);
    }
}