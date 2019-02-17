package app;

import java.util.Arrays;
import java.util.List;

public class JoinTest {
    public static void main(String[] args) {
        List<String> selected = Arrays.asList("2", "1", "34");
        String createText = new JoinTest().createText(selected);
        System.out.println(createText);
    }

    private String createText(List<String> selected) {
        StringBuilder sb = new StringBuilder();
        for (String string : selected) {
            sb.append(string);
            sb.append(", ");
        }
        return String.join(",", selected);
    }
}
