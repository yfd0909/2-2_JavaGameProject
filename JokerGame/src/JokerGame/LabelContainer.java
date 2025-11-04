package JokerGame;

import javax.swing.*;
import java.awt.*;

class LabelContainer extends JPanel {
    
    private JLabel contentLabel;
    
    public LabelContainer(Font font, Color foreColor, int height) {
        this.setLayout(new BorderLayout()); 
        this.setBackground(new Color(0, 50, 0));
        
        // JLabel 초기화
        contentLabel = new JLabel("", SwingConstants.CENTER);
        contentLabel.setFont(font);
        contentLabel.setForeground(foreColor);
        
        this.add(contentLabel, BorderLayout.CENTER);
        
        // 공간 넉넉하게 설정
        this.setPreferredSize(new Dimension(Integer.MAX_VALUE, height));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
    }
    
    public JLabel getLabel() {
        return contentLabel;
    }
}
