public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque deque = new LinkedListDeque<String>();
        for (int i = 0; i < word.length(); i++) {
            char letter =  word.charAt(i);
            deque.addLast(letter);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque deque = wordToDeque(word);
        String reverse = "";
        for (int i = 0; i < word.length(); i++) {
            reverse += deque.removeLast();
        }
        if (reverse.equals(word)) {
            return true;
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        for (int i = 0; i < word.length()/2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
