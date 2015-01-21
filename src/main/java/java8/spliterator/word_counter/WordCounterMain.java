package java8.spliterator.word_counter;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounterMain {

    public static void main(String[] args) {

        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita mi ritrovai in una selva oscura ché la dritta via era smarrita ";

        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

        System.out.println("Found " + countWords(stream) + " words");

    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }

    private static int countWordsIteratively(String sentence) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : sentence.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }


}
