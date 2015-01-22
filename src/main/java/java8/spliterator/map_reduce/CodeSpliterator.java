package java8.spliterator.map_reduce;

import java.util.Spliterator;
import java.util.function.Consumer;

public class CodeSpliterator implements Spliterator<String> {

    private static final int MAX_STRING_LENGTH = 200;
    public static final String WHITEBOARD_CHARACTER = " ";

    private String codePart;
    private int currentIndex = 0;

    public CodeSpliterator(String codePart) {
        this.codePart = codePart.trim();
    }

    @Override
    public boolean tryAdvance(Consumer<? super String> action) {
        int nextWhiteSpaceRelativeToCurrentIndex = codePart.substring(currentIndex).indexOf(WHITEBOARD_CHARACTER);
        int nextWhiteSpaceFromStart = nextWhiteSpaceRelativeToCurrentIndex + currentIndex;

        String nextWord = findNextWord(nextWhiteSpaceFromStart, nextWhiteSpaceRelativeToCurrentIndex);
        action.accept(nextWord);

        currentIndex = nextWhiteSpaceFromStart;
        setCurrentIndexToNextNotWhiteboardCharacter();
        return hasMoreWordsToProcess(nextWhiteSpaceRelativeToCurrentIndex);
    }

    @Override
    public Spliterator<String> trySplit() {
        int currentSize = codePart.length() - currentIndex;
        if (currentSize > MAX_STRING_LENGTH) {
            for (int splitPosition = currentIndex + currentSize / 2; splitPosition < codePart.length(); splitPosition++) {
                if (Character.isWhitespace(codePart.charAt(splitPosition))) {
                    String codeForNextSpliterator = codePart.substring(currentIndex, splitPosition).trim();
                    CodeSpliterator codeSpliterator = new CodeSpliterator(codeForNextSpliterator);

                    currentIndex = splitPosition;
                    setCurrentIndexToNextNotWhiteboardCharacter();
                    return codeSpliterator;
                }
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return codePart.length() - currentIndex;
    }

    @Override
    public int characteristics() {
        return 0;
    }

    private String findNextWord(int nextWhiteSpaceFromStart, int nextWhiteSpaceRelative) {
        if (processingLastWordInCurrentCodePart(nextWhiteSpaceFromStart, nextWhiteSpaceRelative)) {
            return codePart.substring(currentIndex);
        } else {
            return codePart.substring(currentIndex, nextWhiteSpaceFromStart).trim();
        }
    }

    private void setCurrentIndexToNextNotWhiteboardCharacter() {
        while (currentIndex < codePart.length() && Character.isWhitespace(codePart.charAt(currentIndex))) {
            currentIndex++;
        }
    }

    private boolean processingLastWordInCurrentCodePart(int nextWhiteSpaceFromStart, int nextWhiteSpaceRelative) {
        return nextWhiteSpaceRelative == -1 || nextWhiteSpaceRelative >= codePart.length() || nextWhiteSpaceFromStart >= codePart.length();
    }

    private boolean hasMoreWordsToProcess(int nextWhiteSpaceRelativeToCurrentIndex) {
        return nextWhiteSpaceRelativeToCurrentIndex != -1 && currentIndex < codePart.length() - 1;
    }
}
