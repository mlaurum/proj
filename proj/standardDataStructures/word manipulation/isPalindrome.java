public class isPalindrome {
	
	public static Deque<Character> wordToDeque(String word){
		LinkedListDequeSolution<Character> containr = new LinkedListDequeSolution<Character>();
		for (int i = 0; i<word.length(); i++){
			containr.addLast(word.charAt(i));
		}
		return containr;
	}

	public static boolean isPalindrome(String word){
		Deque<Character> containr = wordToDeque(word);
		int length = containr.size();
		containr.printDeque();
		for (int i=0; i<(word.length()/2); i++) {
			if(word.charAt(i) != containr.get(length-i-1)){
				return false;
			}
		}
		return true;

	}

	public static boolean isPalindrome(String word, CharacterComparator cc){
		Deque<Character> containr = wordToDeque(word);
		int length = containr.size();
		containr.printDeque();
		for (int i=0; i<(word.length()/2); i++) {
			if(!cc.equalChars(word.charAt(i), containr.get(length-i-1))){
				return false;
			}
		}
		return true;	
	}

	/*public static void main(String[] args) {
		isPalindrome j = new isPalindrome();
		boolean answ=  j.isPalindrome("anna");
		System.out.println(answ);

		isPalindrome a = new isPalindrome();
		OffByOne cc = new OffByOne();
		boolean ans=  a.isPalindrome("annc",cc);
		System.out.println(ans);


	}*/
}