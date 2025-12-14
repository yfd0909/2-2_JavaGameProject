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
	private MenuBar menuBar;
    private ComputerGamePanel computerHandPanel; //컴퓨터 패널
    private PlayerGamePanel playerHandPanel; //플레이어 패널 
    private CenterGamePanel centerTablePanel; //중앙 패널
    private GameInfoPanel gameInfoPanel; //게임 인포 패널
    private OperationPanel operationPanel; //우측 카드 인포 패널
    private JPanel mainPanel; // 메인패널
    
    //게임 인포
    private GameInfo info;
    private GameManager manager;
    
    public MainGameFrame(String title, String playerName) {
        super(title);
        // 창 기본 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(false);
        
        // 메인 패널 초기화
        mainPanel = new JPanel(new BorderLayout());
        
        //게임 인포 출력 패널 
        gameInfoPanel = new GameInfoPanel();
        gameInfoPanel.setPreferredSize(new Dimension(250, getHeight()));
        info = new GameInfo(playerName, gameInfoPanel);
        mainPanel.add(gameInfoPanel, BorderLayout.WEST);
        
        manager = new GameManager(this, info); //실게임 관리 매니저 생성
        
        //메뉴바 생성
        menuBar = new MenuBar(this);
        
        // 컴퓨터 패널 객체 생성 및 설정
        computerHandPanel = new ComputerGamePanel();
        computerHandPanel.setPreferredSize(new Dimension(getWidth(), 150));
        mainPanel.add(computerHandPanel, BorderLayout.NORTH);

        // 플레이어 패널 객체 생성 및 설정 
        playerHandPanel = new PlayerGamePanel(this, manager);
        playerHandPanel.setPreferredSize(new Dimension(getWidth(), 150));
        mainPanel.add(playerHandPanel, BorderLayout.SOUTH);
        
        // 중앙 패널 객체 생성 및 설정
        centerTablePanel = new CenterGamePanel(this, manager);
        mainPanel.add(centerTablePanel, BorderLayout.CENTER);
        
        
        operationPanel = new OperationPanel(this, manager);
        operationPanel.setPreferredSize(new Dimension(250, getHeight()));
        mainPanel.add(operationPanel, BorderLayout.EAST);

        // 메인 패널 추가
        this.setContentPane(mainPanel);
        setLocationRelativeTo(null); 
        setVisible(true);
        
        
        // 플레이어, 컴퓨터 패널에 카드 표시
        computerHandPanel.DisplayHand(manager.pcHand);
        playerHandPanel.DisplayHand(manager.playerHand);
        
    }
    //연산자 애니메이션 재생
    public void RollCenterBattleField(String op) {
    	centerTablePanel.RollOp(op);
    }
    //결과보여주기
    public void ShowGameResult(int playerResult, int pcResult) {
    	Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	//3초후에 딱 실행
            	centerTablePanel.ShowResult(String.valueOf(playerResult), String.valueOf(pcResult));
            	if(playerResult > pcResult) {
            		centerTablePanel.middlePanel.setOpaque(true);
            		centerTablePanel.middlePanel.setBackground(new Color(0, 153, 0));
            		centerTablePanel.versusBox.setText("WIN!");
            	}
            	else if(playerResult <= pcResult) {
            		centerTablePanel.middlePanel.setOpaque(true);
            		centerTablePanel.middlePanel.setBackground(Color.RED);
            		centerTablePanel.versusBox.setText("LOSE...");
            	}
                ((Timer)e.getSource()).stop();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    public void UpdateGameInfoPanel() {
    	gameInfoPanel.UpdateInfo(info);
    }
    public void UpdateCenterBattleField(boolean isNewRound) {
    	if(isNewRound) {
    		centerTablePanel.ResetRandomBox();
    		centerTablePanel.middlePanel.setBackground(new Color(0,0,0,0));;
    		centerTablePanel.versusBox.setText("VS");
    	}
        centerTablePanel.UpdateBattleField(); 
        centerTablePanel.revalidate();
        centerTablePanel.repaint();
    }
    public void UpdateCardField() {
    	playerHandPanel.DisplayHand(manager.playerHand);
    	computerHandPanel.DisplayHand(manager.pcHand);
    }
    //JMenu에서 테마 바꾸기용 메소드
    public void SetPanelTheme(Color fieldColor, Color centerColor, Color rightColor, Color leftColor) {
    	playerHandPanel.setBackground(fieldColor);
    	computerHandPanel.setBackground(fieldColor);
    	centerTablePanel.setBackground(centerColor);
    	gameInfoPanel.setBackground(leftColor);
    	operationPanel.setBackground(rightColor);
    }
}