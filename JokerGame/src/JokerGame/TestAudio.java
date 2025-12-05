package JokerGame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

// 오디오 재생시키는 파일
public class TestAudio {
	private Clip clip;
	
	// private String song="audio/BackGround.wav"; 
	// 다른 쪽에서 loadAudio(song); 으로 호출
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
