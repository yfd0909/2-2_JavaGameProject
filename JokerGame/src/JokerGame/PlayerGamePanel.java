package JokerGame;

import javax.swing.*;
import java.awt.*;

// 플레이어 패널 클래스
public class PlayerGamePanel extends JPanel {

    public PlayerGamePanel() {
        // 플레이어 패 설정 
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        this.setBackground(new Color(100, 100, 100));
        this.setPreferredSize(new Dimension(1000, 150));

        // 임시 표시
        this.setBorder(BorderFactory.createTitledBorder(
            "Your Hand (Clickable Cards)"
        ));
    }
}