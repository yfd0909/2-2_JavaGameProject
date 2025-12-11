package JokerGame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	// 만들어야할 것
	/*
	 * 게임규칙 최소 3라운드 - 5라운드 카드 종류는 숫자 1-24 플레이어와 컴퓨터의 체력은 각각 3 각 플레이어와 컴퓨터에게 12장씩
	 * 무작위로 나눠주기. 라운드 시작 시 플레이어는 순서대로 카드 두장을 선택해서 카드 칸에 집어넣음
	 * 
	 * 선택이 완료되면 중간의 연산자가 덧뺄곱나 중에 하나로 결정 선택한 두 카드를 해당 연산자로 연산한 결과가 나온다. 연산자는 컴퓨터와
	 * 플레이어 둘다 같고, 선택한 뒤에 결과를 알 수 있다.
	 * 
	 * 숫자가 적은 쪽의 체력 -1 한 쪽이 체력이 0이 되면 패배.
	 */

	/*
	 * 필드 a 플레이어가 낸 카드 리스트 (카드 클래스 배열 2개짜리) b 플레이어 패 카드 리스트 (카드 클래스 배열 12개짜리)
	 * 
	 * c 컴퓨터가 낼 카드 리스트 (카드 클래스 배열 2개짜리) d 컴퓨터 패 카드 리스트 (카드 클래스 배열 12개짜리) 연산자 결정할
	 * int형 난수 (rand)
	 */

	/*
	 * -메서드 1.게임 시작할 때 b d 에 카드 나눠주는 함수 b 리스트 중 매개변수 인덱스 값에 있는 놈 a 배열에 넣는 함수 (매개변수
	 * int형으로 1개) -> a 안 꽉찼으면 a.add(b[매개변수]); a 꽉찼으면 그냥 리턴 이런 식으로..
	 * 
	 * 반대로 a 리스트 중에 하나 골라서 b배열에 다시 넣는 함수 위에랑 동일하게 (플레이어 전용**) 결정된 카드로 계산하는 함수 (반환형
	 * int) -> 난수 1~4 중에 랜덤으로 값 넣고 값에 따라 +, -, *, / 연산해서 결과값 반환하기 난수가 1이면 a[0] +
	 * a[1] 반환 (물론 카드 객체의 카드값 불러와야함) 2이면 a[0] - a[1] 반환 이런 식?
	 * 
	 * (컴퓨터 전용**) 4번이랑 같은 계산함수인데 얘는 그냥 갖고있는 카드 0번쨰 1번째를 내는 걸로 합시다. 똑같이 결과값 반환 -> 대신
	 * 얘는 c.add(d[0]) 랑 c.add(d[1]) 먼저 해준 다음에 c[0] 랑 c[1]을 연산
	 */

	// 오디오
	
	private TestAudio audioPlayer;
	private String Operation = "/Sounds/roulette.wav";
	private String Victory = "/Sounds/Victory.wav";
	private String Lose = "/Sounds/Lose.wav";

	Random rand = new Random();
	DealerDeck CardDeck = new DealerDeck(); // 카드 만들고 섞은 거 가져오기.
	private MainGameFrame mainFrame;
	private GameInfo info;

	List<Card> playerHand = CardDeck.shareCard(12); // DealerDeck 클래스 안에있는 shareCard 정수 12를 넘겨서 무작위 카드 12장을 받는다.
	List<Card> pcHand = CardDeck.shareCard(12); // 위와 마찬가지

	int[] playerCh = { 0, 0 }; // 플레이어 선택 카드 두개 (값이 0이면 없는 걸로 판단)
	int[] pcCh = { 0, 0 }; // PC 선택 카드 두개

	// pc 는 알아서 선택, 계산
	private int pcResult;

	private List<String> ops = new ArrayList();
	private int opIndex;

	boolean isPlayerWin = false; // 승패 확인용
	boolean isGameOver = false; // 게임 종료 트리거 (승패)
	boolean isRoundEnd = false; // 라운드 종료 트리거
	boolean canCard = true; // 카드패 조작 트리거

	public GameManager(MainGameFrame mainFrame, GameInfo info) {
		this.mainFrame = mainFrame;
		this.info = info;
    
		ResetOps();
		ops.clear();
		ops.add("add");
		ops.add("sub");
		ops.add("mul");
		ops.add("div");
		ops.add("remain");
	}
	public void RoundEnd() {
		isRoundEnd = true;
		
		int currentPlayerHp = info.GetPlayerHp();
		int currentComHp = info.GetComputerHp();
		
		//연산자 목록 업데이트
		String opList = "연산자 목록 :";
		for(int i = 0; i < ops.size(); i++) {
			if(ops.get(i).equals("add"))
				opList += " + ";
			else if(ops.get(i).equals("sub"))
				opList += " - ";
			else if(ops.get(i).equals("mul"))
				opList += " * ";
			else if(ops.get(i).equals("div"))
				opList += " / ";
			else if(ops.get(i).equals("remain"))
				opList += " % ";
		}
		info.SetOpList(opList);

		if (isPlayerWin)// 플레이어가 이겼으면
		{
			audioPlayer.SFXAudio(Victory);
			info.SetMessage("플레이어 승리!\n컴퓨터의 체력을 1 깎습니다.");
			currentComHp--;
		} else {
			audioPlayer.SFXAudio(Lose);
			info.SetMessage("컴퓨터의 승리..\n플레이어의 체력을 1 깎습니다.");
			currentPlayerHp--;
		}

		info.UpdateHealthPoint(currentPlayerHp, currentComHp); // 게임 체력정보 업데이트
		mainFrame.UpdateGameInfoPanel();
		if(info.GetPlayerHp() == 0 || info.GetComputerHp() == 0) { //둘 중 한 명이라도 죽으면 게임종료 
			boolean isWin = info.GetPlayerHp() != 0;
			GameEnd(isWin);
		}
	}
	public void GameEnd(boolean isWin) {
		isGameOver = true; //게임종료
		//승패 기록, 승패에 따른 UI 업데이트 (사운드, 색깔등등)
		if(isWin) { //플레이어 최종승리
			info.SetMessage("플레이어가 최종 승리!\n대단해요!\n\n오른쪽에 있는 다시하기 버튼이나\n게임 종료 버튼을 눌러주세요.");
			info.PlusWinCount();
			mainFrame.UpdateGameInfoPanel();
		}
		else { //컴퓨터 최종승리
			info.SetMessage("컴퓨터가 최종 승리!\n아쉽군요..\n\n오른쪽에 있는 다시하기 버튼이나\n게임 종료 버튼을 눌러주세요.");
			info.PlusLoseCount();
			mainFrame.UpdateGameInfoPanel();
		}
  }

	public void NextRound() {
		// 승부 패 모두 비우고 업데이트
		playerCh[0] = 0;
		playerCh[1] = 0;
		pcCh[0] = 0;
		pcCh[1] = 0;
		mainFrame.UpdateCenterBattleField(true);
    
		//인포 업데이트
		int currentTurn = info.GetTurnCount();
		info.SetTurnCount(++currentTurn);
		info.SetMessage(info.GetTurnCount() + "라운드 시작!\n카드를 내고 라운드 시작\n버튼을 눌러주세요!");
		mainFrame.UpdateGameInfoPanel();

		canCard = true; // 카드패 조작 가능
		isRoundEnd = false; // 라운드 재시작
	}
  
	public void ResetGame() {
		//기본 정보 초기화
		info.ResetGame();
		ResetOps();
		playerCh[0] = 0; playerCh[1] = 0;
		pcCh[0] = 0; pcCh[1] = 0;
		
		// 카드 나눠주기
		CardDeck.initDeck();
		playerHand = CardDeck.shareCard(12);
		pcHand = CardDeck.shareCard(12);
		
		//UI 업데이트
		mainFrame.UpdateGameInfoPanel();
		mainFrame.UpdateCenterBattleField(true);
		mainFrame.UpdateCardField();
		
		//트리거 업데이트
		isRoundEnd = false;
		isGameOver = false;
		canCard = true;
	}
  
	public void RoundStart() {
		audioPlayer = new TestAudio();
		if (playerCh[1] == 0) // 2장을 다 안 냈으면 리턴
			return;

		audioPlayer.SFXAudio(Operation);
		// 라운드 끝날 때까지 카드패 조작 불가능하도록 만들기
		canCard = false;

		// 컴퓨터쪽 답안도 계산
		Random pcRandom = new Random();
		int pcRandIndex;
		pcRandIndex = pcRandom.nextInt(pcHand.size());
		pcCh[0] = pcHand.get(pcRandIndex).getCardNum();
		pcHand.remove(pcRandIndex);
		pcRandIndex = pcRandom.nextInt(pcHand.size());
		pcCh[1] = pcHand.get(pcRandIndex).getCardNum();
		pcHand.remove(pcRandIndex);

		// 컴퓨터쪽 카드가 제출됐으니 업데이트 해주기
		mainFrame.UpdateCardField();
		mainFrame.UpdateCenterBattleField(false);

		int playerResult = operator(playerCh[0], playerCh[1], pcCh[0], pcCh[1]);
		mainFrame.ShowGameResult(playerResult, pcResult);

		isPlayerWin = playerResult > pcResult ? true : false; // 해당 라운드 승패 저장
	}
  
	private void ResetOps() {
		ops.clear();
		ops.add("add");
		ops.add("sub");
		ops.add("mul");
		ops.add("div");
		ops.add("remain");
	}
  
	// a, b의 정보를 받아와서 그걸 계산하는 것.
	private int operator(int user1, int user2, int pc1, int pc2) {
		opIndex = rand.nextInt(ops.size());
		// 센터 패널에 연산자 띄우기
		mainFrame.RollCenterBattleField(ops.get(opIndex));
		
		// 연산자 하나 썼으면 다음에는 다른 놈 나오도록 리스트에서 삭제
		if (ops.get(opIndex).equals("add")) {
			ops.remove(opIndex);
			pcResult = pc1 + pc2;
			return user1 + user2;
		}
		if (ops.get(opIndex).equals("sub")) {
			ops.remove(opIndex);
			pcResult = pc1 - pc2;
			return user1 - user2;
		}
		if (ops.get(opIndex).equals("mul")) {
			ops.remove(opIndex);
			pcResult = pc1 * pc2;
			return user1 * user2;
		}
		if (ops.get(opIndex).equals("div")) {
			ops.remove(opIndex);
			pcResult = pc1 / pc2;
			return user1 / user2;
		}
		if (ops.get(opIndex).equals("remain")) {
			ops.remove(opIndex);
			pcResult = pc1 % pc2;
			return user1 % user2;
		}
		return 0;
	}

	public void AddBattleCard(int index) {
		// 플레이어가 카드 클릭 시 2배열 리스트에
		// 승부할 카드 번호를 저장하는 함수
		if (playerCh[1] != 0) // 가득 찼으면 리턴
			return;
		if (playerCh[0] != 0) {
			playerCh[1] = playerHand.get(index).getCardNum();
			playerHand.remove(index);
			return;
		}
		playerCh[0] = playerHand.get(index).getCardNum();
		playerHand.remove(index);
	}

	public void DeleteBattleCard() {
		// 승부 패의 카드를 다시 돌려갖는 함수
		if (playerCh[0] == 0) // 애초에 없으면 리턴
			return;
		if (playerCh[1] != 0) {
			Card newCard = new Card(playerCh[1]);
			playerHand.add(newCard);
			playerCh[1] = 0;
			return;
		}
		Card newCard = new Card(playerCh[0]);
		playerHand.add(newCard);
		playerCh[0] = 0;
	}

	// 카드 12개 중 두개 선택 시 정보 받아옴.
	/*
	 * public void playInConsole() {
	 * 
	 * Scanner sc = new Scanner(System.in); int opInt = rand.nextInt(4) + 1; // 연산자
	 * 결정할 변수 (1~4) for (int i = 0; i < playerHand.size(); i++) { Card c =
	 * playerHand.get(i); System.out.print(c.getCardNum() + "  ");
	 * 
	 * }
	 * 
	 * System.out.println(" "); System.out.print("첫번째 카드 인덱스 선택>> "); int index1 =
	 * sc.nextInt();
	 * 
	 * System.out.print("두번째 카드 인덱스 선택>> "); int index2 = sc.nextInt();
	 * 
	 * int player1 = playerHand.get(index1).getCardNum(); int player2 =
	 * playerHand.get(index2).getCardNum();
	 * 
	 * int pc1 = pcHand.get(0).getCardNum(); int pc2 = pcHand.get(1).getCardNum();
	 * 
	 * int playerResult = operator(player1, player2); int pcResult = operator(pc1,
	 * pc2);
	 * 
	 * if (playerResult > pcResult) { System.out.println("컴퓨터: " + pc1 + "와" + " " +
	 * pc2 + " 계산 결과: " + pcResult); System.out.println("플레이어: " + player1 + "와" +
	 * " " + player2 + " 계산결과: " + playerResult); System.out.println("플레이어 승리");
	 * System.out.println(); pcHP--;
	 * 
	 * System.out.println("컴퓨터 HP: " + pcHP); System.out.println("플레이어 HP: " +
	 * playerHP); System.out.println();
	 * 
	 * playerWin++; pcLose++;
	 * 
	 * }
	 * 
	 * else if (playerResult < pcResult)
	 * 
	 * { System.out.println("컴퓨터: " + pc1 + "와" + " " + pc2 + " 계산 결과: " +
	 * pcResult); System.out.println("플레이어: " + player1 + "와" + " " + player2 +
	 * " 계산결과: " + playerResult); System.out.println("컴퓨터 승리");
	 * System.out.println(); playerHP--;
	 * 
	 * System.out.println("컴퓨터 HP: " + pcHP); System.out.println("플레이어 HP: " +
	 * playerHP); System.out.println();
	 * 
	 * pcWin++; playerLose++;
	 * 
	 * }
	 * 
	 * else { System.out.println("컴퓨터: " + pc1 + "와" + " " + pc2 + " 계산 결과: " +
	 * pcResult); System.out.println("플레이어: " + player1 + "와" + " " + player2 +
	 * " 계산결과: " + playerResult); System.out.println("동점입니다.");
	 * System.out.println(); }
	 * 
	 * }
	 * 
	 */

	// 콘솔 창에서 임시로 할 수 있도록 만듬
	// 근데 코드가 좀 더럽네요..
	// Um.............
	/*
	 * public static void main(String[] args) { Scanner sc = new Scanner(System.in);
	 * int gameCount = 0;
	 * 
	 * while (true) { while (gameCount != 5 && playerHP != 0 && pcHP != 0) {
	 * GameManager GM = new GameManager(); GM.playInConsole(); gameCount++; }
	 * 
	 * // ㅡㅡㅡㅡ if (pcWin > playerWin) { System.out.println("LOSE"); }
	 * 
	 * else if (pcWin < playerWin) { System.out.println("VICTORY"); }
	 * 
	 * else { System.out.println("컴퓨터와 한몸 이시군요"); }
	 * 
	 * System.out.print("다시하시겠습니까? (y/n) >>"); String Restart = sc.next();
	 * 
	 * if (Restart.equals("y")) { playerHP=3; pcHP=3; gameCount=0;
	 * System.out.println(); } else break;
	 * 
	 * } }
	 */

}
