package JokerGame;

public class GameInfo {
	private int turnCount; //게임 턴 수
	private int computerHp; //컴퓨터 체력
	private int playerHp; //플레이어 체력
	private String message; //메세지 띄우기용
	
	FileManager fileManager;
	private String[] playerData;
	private String playerName;
	private int winCount; //파일 입출력으로 정보 가져오기
	private int loseCount;
	
	// UI초기화
	GameInfo(String name){
		//플레이어 정보 가져오기
		fileManager = new FileManager();
		playerData = fileManager.FindPlayerData(name);
		playerName = playerData[0];
		winCount = Integer.parseInt(playerData[1]);
		loseCount = Integer.parseInt(playerData[2]);
		
		//기본 정보 초기화
		this.turnCount = 1;
		this.computerHp = 3;
		this.playerHp = 3;
		this.message = "게임을 시작합니다.";
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
		fileManager.SavePlayerData(updatedPlayerData);
	}
	public void PlusLoseCount() {
		String[] updatedPlayerData = 
			{playerName, 
			String.valueOf(winCount),
			String.valueOf(++loseCount)};
		fileManager.SavePlayerData(updatedPlayerData);
	}
	public void ProgramEnd() {
		fileManager.close();
	}
}
