package JokerGame;

public class GameInfo {
	private int turnCount; //게임 턴 수
	private String name;
	private int winCount; //파일 입출력으로 정보 가져오기
	private int loseCount;
	private int computerHp; //컴퓨터 체력
	private int playerHp; //플레이어 체력
	private String message; //메세지 띄우기용
	// UI초기화
	GameInfo(String name){
		this.turnCount = 1;
		
		//플레이어 정보
		this.name = name; //플레이어 이름
		if(HasName(this.name)) { //전적
			winCount = 0;
			loseCount = 0;
		}
		
		this.computerHp = 3;
		this.playerHp = 3;
		this.message = "게임을 시작합니다.";
	}
	public String GetPlayerName() {
		return this.name;
	}
	public int GetWinCount() {
		return this.winCount;
	}
	public int GetLoseCount() {
		return this.loseCount;
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
	private boolean HasName(String name) {
		return true;
	}
	// 턴 지날 때마다 호출하는 함수 / info.SetNextTurn() 하고 패널 UpdateInfo() 호출하기
	// 매개변수에 플레이어, 컴퓨터 각각 리스트 length 넣기
	public void UpdateHealthPoint(int playerHp, int computerHp) {
		SetPlayerHp(playerHp);
		SetComputerHp(computerHp);
	}
	public void SetNextTurn() {
		turnCount += 1;
	}
}
