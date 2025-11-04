package JokerGame;

import javax.swing.*;
import java.awt.*;

// 상대방 패를 나타내는 패널 클래스
public class ComputerGamePanel extends JPanel {

    public ComputerGamePanel() {
        // flowLayout, 가운데 정렬, 왼쪽부터 간격 10씩
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        this.setBackground(new Color(50, 50, 50));
        this.setPreferredSize(new Dimension(1000, 150)); 
        
        // 임시 표시
        this.setBorder(BorderFactory.createTitledBorder(
            "Computer's Hand"
        ));
    }
}
