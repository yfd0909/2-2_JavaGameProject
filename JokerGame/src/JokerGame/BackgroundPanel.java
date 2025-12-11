package JokerGame;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            java.net.URL url = getClass().getResource(imagePath);
            if (url != null) {
                this.backgroundImage = new ImageIcon(url).getImage();
            } else {
                System.err.println("이미지 파일을 찾을 수 없습니다: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}