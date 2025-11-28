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
    
    class PlayerGamePanel extends JPanel {
    	
    	ActionListener cardClickListener;
    	MouseAdapter cardMouseAdapter;
    	
        public PlayerGamePanel() {
            // 플레이어 패 설정 
            this.setLayout(null); 
            this.setBackground(new Color(49, 69, 68));
            this.setPreferredSize(new Dimension(1000, 150));

            // 임시 표시
            this.setBorder(BorderFactory.createTitledBorder(
                "Your Hand"
            ));
        }
        // 추후 Card클래스로 변경 예정
        public void DisplayHand(List<TestCard> cardList) {
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
                    String clickedCardNum = e.getActionCommand();
                    cardInfoPanel.SetCardText(clickedCardNum, null);
                    
                    //카드 UI 업데이트 -> UpdateInfo()
                }
            };
            
            JButton cardButton;
            
            if (cardList != null) {
                for (TestCard card : cardList) {
                	// 카드 이미지 경로 설정
                	int cardNum =  card.getNum();
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
        public void DisplayHand(List<TestCard> cardList) {
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
                for (TestCard card : cardList) {
                	// 카드 이미지 경로 설정
                	int cardNum =  card.getNum();
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
            
            this.revalidate(); // 컴포넌트 추가 후 레이아웃을 다시 계산
            this.repaint(); // 컴포넌트 
        }
    }

    public MainGameFrame(String title, String playerName) {
        super(title);
        // 창 기본 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);
        
        // 메인 패널 초기화
        mainPanel = new JPanel(new BorderLayout());
        
        // 컴퓨터 패널 객체 생성 및 설정
        computerHandPanel = new ComputerGamePanel();
        computerHandPanel.setPreferredSize(new Dimension(getWidth(), 150));
        mainPanel.add(computerHandPanel, BorderLayout.NORTH);

        // 플레이어 패널 객체 생성 및 설정 
        playerHandPanel = new PlayerGamePanel();
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
        
        // 임시 카드 리스트 생성
        List<TestCard> playerStartHand = new ArrayList<>();
        playerStartHand.add(new TestCard("Clover", 2));
        playerStartHand.add(new TestCard("Clover", 6));
        playerStartHand.add(new TestCard("Clover", 4));
        playerStartHand.add(new TestCard("Clover", 8));
        
        List<TestCard> computerStartHand = new ArrayList<>();
        for(int i = 0; i< 6; i++) {
        	computerStartHand.add(new TestCard("Clover", 2));
        }
        
        // 플레이어, 컴퓨터 패널에 카드 표시
        computerHandPanel.DisplayHand(computerStartHand);
        playerHandPanel.DisplayHand(playerStartHand);
    }
}