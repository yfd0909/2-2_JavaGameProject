package JokerGame;

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

	Random rand = new Random();
	DealerDeck CardDeck = new DealerDeck(); // 카드 만들고 섞은 거 가져오기.

	List<Card> playerHand = CardDeck.shareCard(12); // DealerDeck 클래스 안에있는 shareCard 정수 12를 넘겨서 무작위 카드 12장을 받는다.
	List<Card> pcHand = CardDeck.shareCard(12); // 위와 마찬가지

	int[] playerCh = new int[2]; // 플레이어 선택 카드 두개
	int[] pcCh = new int[2]; // PC 선택 카드 두개

	static int pcWin = 0;
	static int pcLose = 0;

	static int playerWin = 0;
	static int playerLose = 0;

	private static int playerHP = 3;
	private static int pcHP = 3;

	// a, b의 정보를 받아와서 그걸 계산하는 것.
	public int operator(int a, int b, int opInt) {
		switch (opInt) {
		case 1:
			return a + b;

		case 2:
			return a - b;

		case 3:
			return a * b;

		case 4:
			return a / b;

		}
		return 0;
	}

	// 카드 12개 중 두개 선택 시 정보 받아옴.
	public void playInConsole() {

		Scanner sc = new Scanner(System.in);
		int opInt = rand.nextInt(4) + 1; // 연산자 결정할 변수 (1~4)
		for (int i = 0; i < playerHand.size(); i++) {
			Card c = playerHand.get(i);
			System.out.print(c.getCardNum() + "  ");

		}

		System.out.println(" ");
		System.out.print("첫번째 카드 인덱스 선택>> ");
		int index1 = sc.nextInt();

		System.out.print("두번째 카드 인덱스 선택>> ");
		int index2 = sc.nextInt();

		int player1 = playerHand.get(index1).getCardNum();
		int player2 = playerHand.get(index2).getCardNum();

		int pc1 = pcHand.get(0).getCardNum();
		int pc2 = pcHand.get(1).getCardNum();

		int playerResult = operator(player1, player2, opInt);
		int pcResult = operator(pc1, pc2, opInt);

		if (playerResult > pcResult) {
			System.out.println("컴퓨터: " + pc1 + "와" + " " + pc2 + " 계산 결과: " + pcResult);
			System.out.println("플레이어: " + player1 + "와" + " " + player2 + " 계산결과: " + playerResult);
			System.out.println("플레이어 승리");
			System.out.println();
			pcHP--;

			System.out.println("컴퓨터 HP: " + pcHP);
			System.out.println("플레이어 HP: " + playerHP);
			System.out.println();

			playerWin++;
			pcLose++;

		}

		else if (playerResult < pcResult)

		{
			System.out.println("컴퓨터: " + pc1 + "와" + " " + pc2 + " 계산 결과: " + pcResult);
			System.out.println("플레이어: " + player1 + "와" + " " + player2 + " 계산결과: " + playerResult);
			System.out.println("컴퓨터 승리");
			System.out.println();
			playerHP--;

			System.out.println("컴퓨터 HP: " + pcHP);
			System.out.println("플레이어 HP: " + playerHP);
			System.out.println();

			pcWin++;
			playerLose++;

		}

		else {
			System.out.println("컴퓨터: " + pc1 + "와" + " " + pc2 + " 계산 결과: " + pcResult);
			System.out.println("플레이어: " + player1 + "와" + " " + player2 + " 계산결과: " + playerResult);
			System.out.println("동점입니다.");
			System.out.println();
		}

	}

	
	
	// 콘솔 창에서 임시로 할 수 있도록 만듬
	// 근데 코드가 좀 더럽네요..
	// Um.............
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int gameCount = 0;

		while (true) {
			while (gameCount != 5 && playerHP != 0 && pcHP != 0) {
				GameManager GM = new GameManager();
				GM.playInConsole();
				gameCount++;
			}

			// ㅡㅡㅡㅡ
			if (pcWin > playerWin) {
				System.out.println("LOSE");
			}

			else if (pcWin < playerWin) {
				System.out.println("VICTORY");
			}

			else {
				System.out.println("컴퓨터와 한몸 이시군요");
			}

			System.out.print("다시하시겠습니까? (y/n) >>");
			String Restart = sc.next();

			if (Restart.equals("y")) {
				playerHP=3;
				pcHP=3;
				gameCount=0;
				System.out.println();
			}
			else
				break;

		}
	}

}
