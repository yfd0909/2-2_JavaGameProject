package JokerGame;

public class Shuffle {
	public class Card {
		// 무늬, 숫자or문자로 구분?
		private String cardNum; // A,2 ...., K까지
		
		
		public Card(String Num) {
			this.cardNum = Num;
		}
		
		
		public String getCardNum() {
			return cardNum;
		}
		
	}
}
