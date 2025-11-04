package JokerGame;

import javax.swing.*;
import java.awt.*;

// 카드 게임의 메인 창을 정의하는 클래스
public class CardGameFrame extends JFrame {

    // 각 패널 선언
    private ComputerGamePanel computerHandPanel; //컴퓨터 패널
    private PlayerGamePanel playerHandPanel; //플레이어 패널 
    private CenterGamePanel centerTablePanel;
    private JPanel mainPanel; // 메인패널

    public CardGameFrame(String title) {
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

        // 메인 패널 추가
        this.setContentPane(mainPanel);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CardGameFrame("Modular Basic Card Game");
        });
    }
}