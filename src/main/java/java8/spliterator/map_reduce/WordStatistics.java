package java8.spliterator.map_reduce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.*;

public class WordStatistics {

    private static final String MAIN_FOLDER_URI = ".";
    private static final String JAVA_FILE_EXTENSION = ".java";

    public static void main(String[] args) {
        try {
            JavaCodeFileConsumer javaCodeFileConsumer = new JavaCodeFileConsumer();
            Files.walk(Paths.get(MAIN_FOLDER_URI)).filter(x -> x.toString().endsWith(JAVA_FILE_EXTENSION)).forEach(javaCodeFileConsumer);

            String wholeCode = javaCodeFileConsumer.getAllCode();

            Map<String, Long> wordsCountSortedByKeys = getWordsCountStatistics(wholeCode);

            Comparator<Entry<String, Long>> wordCountEntryComparator = (entry1, entry2) -> Long.compare(entry1.getValue(), entry2.getValue());
            Map<String, Long> wordStatisticsSortedByOccurrences = sortedWordCharacteristicsMap(getWordsCountStatistics(wholeCode), wordCountEntryComparator.reversed());

            Map<String, Long> normalWordCharacteristics = Arrays.stream(wholeCode.split(" ")).filter(x -> x != null).collect(groupingBy(x -> x, counting()));
            assert normalWordCharacteristics.equals(wordsCountSortedByKeys);
            assert normalWordCharacteristics.equals(wordStatisticsSortedByOccurrences);

            printWordStatistics(wordStatisticsSortedByOccurrences);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Long> getWordsCountStatistics(String allCode) {
        Stream<String> stream = StreamSupport.stream(new CodeSpliterator(allCode), true);
        return stream.collect(groupingBy(x -> x, TreeMap::new, counting()));
    }

    private static Map<String, Long> sortedWordCharacteristicsMap(Map<String, Long> wordsCount, Comparator<Entry<String, Long>> wordCountEntryComparator) {
        return wordsCount.entrySet().stream().sorted(wordCountEntryComparator).collect(toMap(Entry::getKey, Entry::getValue, (Long a, Long a1) -> a + a1, LinkedHashMap::new));
    }

    private static void printWordStatistics(Map<String, Long> wordStatistics) {
        wordStatistics.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
