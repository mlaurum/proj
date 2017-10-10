public class OffByOne implements CharacterComparator{
	public boolean equalChars(char x, char y){
		int j = x-y;
		if (Math.abs(j)==1){
			return true;
		}
		return false;
	}
}

/*public class OffByN implements CharacterComparator{
	private int thresh;
	public OffByN(int N){
		thresh = N;
	}

	public boolean equalChars(char x, char y){
		int j = x-y;
		if (Math.abs(j)<=thresh){
			return true;
		}
		return false;
	}
}*/