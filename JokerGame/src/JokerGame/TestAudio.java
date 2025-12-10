package JokerGame;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// 효과음 재생시키는 클래스
public class TestAudio {
	private Clip clip;
	
	public void SFXAudio (String pathName) {
		try {
			URL audioUrl = getClass().getResource(pathName); // 오디오 파일의 경로명
			final AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioUrl);
			
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();			
			
			clip.addLineListener(new LineListener() {
				public void update(LineEvent e) {
					if (e.getType() == LineEvent.Type.STOP) {
						clip.close(); // 효과음 반복재생 가능하도록 바로 끄기.
						try {
							audioStream.close();
						}catch(IOException e1) {
							e1.printStackTrace();
						}	
					}
				}
			});

			}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		
	}
	
}
