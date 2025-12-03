package JokerGame;

import javax.swing.SwingUtilities; // UI 업데이트용

public class LoadDataThread extends Thread {
    
    // 최종적으로 로드된 데이터를 저장할 변수
    private String[] playerData;
    private String playerName;
    
    // 로딩 완료 후 알림을 받을 GameInfo 객체
    private GameInfo gameInfo; 
    
    FileManager fileManager;

    public LoadDataThread(GameInfo info, String name) {
        super("LoadDataThread");
        this.gameInfo = info;
        this.playerName = name;
    }

    @Override
    public void run() {
    	//스레드에서 파일매니저 객체 생성함으로써 EDT에는 무리가 안 감
        fileManager = new FileManager();
        this.playerData = fileManager.FindPlayerData(playerName);
        fileManager.close(); //파일 닫아주기
        
        //작업 완료되면 데이터 전달
        SwingUtilities.invokeLater(() -> {
            gameInfo.UpdateInfoAfterLoading(this.playerData);
        });
    }
    public void SaveData(String[] data) {
    	fileManager.SavePlayerData(data);
    }
}