package JokerGame;

public class GameInfo {
	private int turnCount; //게임 턴 수
	private int computerHp; //컴퓨터 체력
	private int playerHp; //플레이어 체력
	private String message; //메세지 띄우기용
	
	LoadDataThread loader;
	private String[] playerData;
	private String playerName;
	private int winCount; //파일 입출력으로 정보 가져오기
	private int loseCount;
	
	private GameInfoPanel infoPanel;
	// UI초기화
	GameInfo(String name, GameInfoPanel infoPanel){
		//파일 불러오는 스레드 실행
		loader = new LoadDataThread(this, name);
        loader.start();
        
        this.infoPanel = infoPanel;
        
		//기본 정보 초기화
		this.turnCount = 1;
		this.computerHp = 3;
		this.playerHp = 3;
		this.message = "게임을 시작합니다.";
	}
	//스레드에서 작업이 끝나면 데이터 반환하는 콜백 함수
	public void UpdateInfoAfterLoading(String[] loadedData) {
        this.playerData = loadedData;
        this.playerName = loadedData[0];
        this.winCount = Integer.parseInt(loadedData[1]);
        this.loseCount = Integer.parseInt(loadedData[2]);
        
        infoPanel.UpdateInfo(this);
    }
	public String GetPlayerName() {
		return this.playerName;
	}
	public int GetWinCount() {
		return this.winCount;
	}
	public int GetLoseCount() {
		return this.loseCount;
	}
	public void SetTurnCount(int count) {
		this.turnCount = count;
	}
	public int GetTurnCount() {
		return this.turnCount;
	}
	public int GetComputerHp() {
		return this.computerHp;
	}
	public int GetPlayerHp() {
		return this.playerHp;
	}
	public void SetComputerHp(int hp) {
		this.computerHp = hp;
	}
	public void SetPlayerHp(int hp) {
		this.playerHp = hp;
	}
	public String GetMessage() {
		return this.message;
	}
	public void SetMessage(String message) {
		String longMessage = message;
		String htmlMessage = "<html><center>" + longMessage.replace("\n", "<br>") + "</center></html>";
		this.message = htmlMessage;
	}
	// 턴 지날 때마다 호출하는 함수 / info.SetNextTurn() 하고 패널 UpdateInfo() 호출하기
	// 매개변수에 플레이어, 컴퓨터 각각 리스트 length 넣기
	public void UpdateHealthPoint(int playerHp, int computerHp) {
		SetPlayerHp(playerHp);
		SetComputerHp(computerHp);
	}
	public void ResetGame() {
		SetTurnCount(1);
		SetPlayerHp(3);
		SetComputerHp(3);
		SetMessage("게임 재시작!");
	}
	public void PlusWinCount() {
		String[] updatedPlayerData = 
			{playerName, 
			String.valueOf(++winCount),
			String.valueOf(loseCount)};
		loader.SaveData(updatedPlayerData);
	}
	public void PlusLoseCount() {
		String[] updatedPlayerData = 
			{playerName, 
			String.valueOf(winCount),
			String.valueOf(++loseCount)};
		loader.SaveData(updatedPlayerData);
	}
}
