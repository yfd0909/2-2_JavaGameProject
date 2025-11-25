package JokerGame;

//가현님 이거 테스트용 카드 클래스입니다
public class TestCard {
	private String cardType;
	private int cardNum;
	TestCard(String type, int num){
		this.cardType = type;
		this.cardNum = num;
	}
	String getType() {
		return this.cardType;
	}
	int getNum() {
		return this.cardNum;
	}
}
