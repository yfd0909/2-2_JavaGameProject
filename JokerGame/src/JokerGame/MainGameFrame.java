package JokerGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainGameFrame extends JFrame {

    // 각 패널 선언
    private ComputerGamePanel computerHandPanel; //컴퓨터 패널
    private PlayerGamePanel playerHandPanel; //플레이어 패널 
    private CenterGamePanel centerTablePanel; //중앙 패널
    private GameInfoUIPanel gameInfoUIPanel; //게임 인포 패널
    private JPanel mainPanel; // 메인패널
    
    //게임 인포
    private GameInfo info;

    public MainGameFrame(String title) {
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
        
        // (임시) 게임정보UI 패널 객체 생성
        info = new GameInfo(23, 23);
        gameInfoUIPanel = new GameInfoUIPanel(info);
        gameInfoUIPanel.setPreferredSize(new Dimension(200, getHeight()));
        mainPanel.add(gameInfoUIPanel, BorderLayout.WEST);

        // 메인 패널 추가
        this.setContentPane(mainPanel);
        setLocationRelativeTo(null); 
        setVisible(true);
        
        // 임시 카드 리스트 생성
        List<TestCard> playerStartHand = new ArrayList<>();
        playerStartHand.add(new TestCard("clover", "2"));
        playerStartHand.add(new TestCard("clover", "6"));
        playerStartHand.add(new TestCard("clover", "4"));
        playerStartHand.add(new TestCard("clover", "8"));
        
        // 카드 클릭 시 동작할 리스너
        ActionListener cardClickListener = new ActionListener() {
        		@Override
            public void actionPerformed(ActionEvent e) {
                String clickedCardName = e.getActionCommand();
                JOptionPane.showMessageDialog(MainGameFrame.this, clickedCardName + " 카드를 선택했습니다!");
                // 여기에 선택된 카드에 대한 게임 로직을 추가할 수 있습니다.
            }
        };
        // 플레이어 패널에 카드 표시
        playerHandPanel.DisplayPlayerHand(playerStartHand, cardClickListener);
    }
    
    public static void main(String[] args) {
    		new MainGameFrame("Modular Basic Card Game");
    }
}