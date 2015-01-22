package java8.spliterator.map_reduce;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public class JavaCodeFileConsumer implements Consumer<Path> {

    private static final String ENDLINE_TAB_CHARACTERS = "[\\t\\n\\r]";
    private static final String UNWANTED_CHARACTERS = "[<>.!-(){}]";
    public static final String WHITEBOARD_CHARACTER = " ";

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void accept(Path path) {
        try {
            List<String> strings = Files.readAllLines(path);
            strings.stream().forEach(line -> {
                String finalString = replaceUnimportantCharacters(line);
                stringBuilder.append(finalString).append(WHITEBOARD_CHARACTER);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String replaceUnimportantCharacters(String x) {
        return x.replaceAll(ENDLINE_TAB_CHARACTERS, WHITEBOARD_CHARACTER).replaceAll(UNWANTED_CHARACTERS, WHITEBOARD_CHARACTER);
    }

    public String getAllCode() {
        return stringBuilder.toString();
    }
}
