package JokerGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 1-24가 적힌 카드를 플레이어와 딜러에게 각각 12장씩 나눠주기. 
public class DealerDeck {
	public List<Card> deck = new ArrayList<>(); // 24장 리스트
	

	private void initDeck() { // 24장 초기화
		
		for (int i=1; i<=24; i++) {
			Card c = new Card(String.valueOf(i)); // for 문 이용해서 카드에 1부터 24 string 으로 삽입
			deck.add(c);
		}
	}
	
	public void shuffleDeck() { 
		
		Collections.shuffle(deck); // shuffle 이용해서 deck 섞기.
		
	}
	
	public Card drawCard() {
		if (deck.isEmpty()) {
			// 덱이 비었으면 null 반환
			return null;
		}
		// remove 로 인덱스 0번의 카드를 제거후 반환.
		return deck.remove(0); 
	}
	
	public List<Card> shareCard(int sharedCard){
		List<Card> hand = new ArrayList<>(); // 손패 리스트로 넘겨주는 함수
		for (int i=0; i<sharedCard && !deck.isEmpty(); i++) {
			// sharedCard, 즉 나눠준 카드 갯수만큼 한장씩 뽑아서 hand에 추가한다.
			// isEmpty 함수로 덱이 빌 때까지 한장씩 추가한다.
			hand.add(drawCard());
		}
		// 각각 12장씩 넣고 리스트 반환
		return hand;
	}
	
	
}
