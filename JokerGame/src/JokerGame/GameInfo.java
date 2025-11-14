package JokerGame;

public class GameInfo {
	private int turnCount; //게임 턴 수
	private int computerCardCount; //컴퓨터 남은 카드 수
	private int playerCardCount; //플레이어 남은 카드 수
	private String message; //메세지 띄우기용
	// UI초기화
	GameInfo(int computerCardCount, int playerCardCount){
		this.turnCount = 1;
		this.computerCardCount = computerCardCount;
		this.playerCardCount = playerCardCount;
		this.message = "게임을 시작합니다.";
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
	// 턴 지날 때마다 호출하는 함수
	// 매개변수에 플레이어, 컴퓨터 각각 리스트 length 넣기
	public void SetNextTurn(int computerCardCount, int playerCardCount) {
		turnCount += 1;
		this.computerCardCount = computerCardCount;
		this.playerCardCount = playerCardCount;
	}
}
