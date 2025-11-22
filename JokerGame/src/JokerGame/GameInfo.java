package JokerGame;

public class GameInfo {
	private int turnCount; //게임 턴 수
	private String name;
	private int winCount; //파일 입출력으로 정보 가져오기
	private int loseCount;
	private int computerCardCount; //컴퓨터 남은 카드 수
	private int playerCardCount; //플레이어 남은 카드 수
	private String message; //메세지 띄우기용
	// UI초기화
	GameInfo(int computerCardCount, int playerCardCount, String name){
		this.turnCount = 1;
		
		//플레이어 정보
		this.name = name; //플레이어 이름
		if(HasName(this.name)) { //전적
			winCount = 0;
			loseCount = 0;
		}
		
		this.computerCardCount = computerCardCount;
		this.playerCardCount = playerCardCount;
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
	public int GetComputerCardCount() {
		return this.computerCardCount;
	}
	public int GetPlayerCardCount() {
		return this.playerCardCount;
	}
	public String GetMessage() {
		return this.message;
	}
	private boolean HasName(String name) {
		return true;
	}
	// 턴 지날 때마다 호출하는 함수 / info.SetNextTurn() 하고 패널 UpdateInfo() 호출하기
	// 매개변수에 플레이어, 컴퓨터 각각 리스트 length 넣기
	public void SetNextTurn(int computerCardCount, int playerCardCount) {
		turnCount += 1;
		this.computerCardCount = computerCardCount;
		this.playerCardCount = playerCardCount;
	}
}
