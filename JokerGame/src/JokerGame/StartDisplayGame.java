package JokerGame;

import javax.swing.SwingUtilities;

public class StartDisplayGame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartFrame startFrame = new StartFrame();
                startFrame.setVisible(true);
            }
        });
	}
}
