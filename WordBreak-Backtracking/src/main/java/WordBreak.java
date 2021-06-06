import javafx.scene.control.TextArea;

import java.util.Set;

public class WordBreak {

    private final TextArea textArea;
    private final Set<String> dict;

    public WordBreak(TextArea textArea, Set<String> dict) {
        this.textArea = textArea;
        this.dict = dict;
    }

    public void wordBreak(int n, String s) {
        this.textArea.clear();
        String ans = "";
        wordBreakUtil(n, s, dict, ans);
    }

    public void wordBreakUtil(int n, String s, Set<String> dict, String result) {
        for (int i = 1; i <= n; i++) {

            // Extract substring from 0 to i in prefix
            String prefix = s.substring(0, i);

            // If dictionary contains this prefix, then
            // we check for remaining string. Otherwise
            // we ignore this prefix (there is no else for
            // this if) and try next
            if (dict.contains(prefix)) {
                // If no more elements are there, print it
                if (i == n) {

                    // Add this element to previous prefix
                    result += prefix;

                    System.out.println(result);
                    this.textArea.appendText(result + "\n");
                    return;
                }
                wordBreakUtil(n - i, s.substring(i, n), dict, result + prefix + " ");
            }
        }
    }
}