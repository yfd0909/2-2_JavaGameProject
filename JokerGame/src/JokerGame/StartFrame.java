package JokerGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StartFrame extends JFrame{
	String name = "";
	
	private BackgroundPanel startPanel;
	
	// 효과음
	private TestAudio audioPlayer;
	private String FrameChange = "frameChange.wav";
	
	public StartFrame(){
		// 노래재생
		loadAudio(song);
		audioPlayer = new TestAudio();
		
		setTitle("시작 화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null); // 화면 중앙에 배치
        
        //부모 패널
        startPanel = new BackgroundPanel("/Images/bg.jpg");
        startPanel.setLayout(new GridLayout(2, 1));
        
        //게임 로고 패널
        URL logoUrl = MainGameFrame.class.getResource("/Images/logo.png");
        ImageIcon originalIcon = new ImageIcon(logoUrl);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(
        		400, 400, Image.SCALE_SMOOTH 
        );
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        JPanel logoPanel = new JPanel();
        JLabel logo = new JLabel(scaledIcon);
        
        logoPanel.setOpaque(false);
        logo.setPreferredSize(new Dimension(400,340));
        logoPanel.add(logo);
        
        //그 밑 텍스트, 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JTextField nameText = new JTextField(15);
        Dimension textFieldSize = new Dimension(150, 30);
        nameText.setPreferredSize(textFieldSize );
        nameText.setMaximumSize(textFieldSize);
        
        
        //버튼 세팅
        Dimension buttonSize = new Dimension(140, 40);
        
        String imagePath2 = "/Images/start.png"; 
    		URL imageUrl2 = MainGameFrame.class.getResource(imagePath2);
        ImageIcon buttonIcon1 = new ImageIcon(imageUrl2);
        JButton startButton = new JButton(buttonIcon1);
        startButton.setPreferredSize(buttonSize);
        startButton.setMaximumSize(buttonSize);
        
        String imagePath3 = "/Images/exit.png"; 
        URL imageUrl3 = MainGameFrame.class.getResource(imagePath3);
        ImageIcon buttonIcon2 = new ImageIcon(imageUrl3);
        JButton exitButton = new JButton(buttonIcon2);
        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	audioPlayer.SFXAudio(FrameChange);
            	String inputText = nameText.getText().trim(); 
            	if(inputText.isEmpty()){
            		JOptionPane.showMessageDialog(
            				null, 
            			    "이름을 입력해 주세요!",
            			    "경고",
            			    JOptionPane.WARNING_MESSAGE
            			);
            		return;
            	}
            	
                name = inputText;
                
            	//메인 게임 프레임으로 이동
                MainGameFrame mainFrame = new MainGameFrame("Joker Game", name);
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); //시스템 종료시키기
			}
		});

        nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        buttonPanel.add(Box.createVerticalStrut(40));
        buttonPanel.add(nameText);
        buttonPanel.add(Box.createVerticalStrut(40));
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(exitButton);
        
        startPanel.add(logoPanel);
        startPanel.add(buttonPanel);        
        add(startPanel);
	}
	
	// 오디오 부분
	private Clip clip;
	private String song = "StartMusic.wav";
	public void loadAudio (String pathName) {
		try {
			File audioFile = new File(pathName); // 오디오 파일의 경로명
			final AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			
			clip = AudioSystem.getClip();
			clip.addLineListener(new LineListener() {
				public void update(LineEvent e) {
					if (e.getType() == LineEvent.Type.STOP) {
						// clip.stop()이 호출되거나 재생이 끝났을 때
						
						try {
							System.out.println("재생 끝");
							audioStream.close();
						}catch(IOException e1) {
							e1.printStackTrace();
						}	
					}
				}
			});
			clip.open(audioStream);
			clip.start();
			}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}

}
}
