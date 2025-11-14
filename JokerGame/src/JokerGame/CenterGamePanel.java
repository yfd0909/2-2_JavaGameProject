package JokerGame;

import javax.swing.*;
import java.awt.*;

// 중앙 카드더미 및 게임판 패널
public class CenterGamePanel extends JPanel {

    public CenterGamePanel() {
        this.setBackground(new Color(0, 100, 0));
        this.setLayout(null);

        // 카드 더미 이미지 나중에 직접 그리거나 이미지로 대체
        JLabel deckPlaceholder = new JLabel();
        deckPlaceholder.setForeground(Color.WHITE);
        deckPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        deckPlaceholder.setBounds(400, 200, 200, 100);
        this.add(deckPlaceholder);
    }
}