package lib;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BscUi {

    // Extract list name from the *vfor directive
    /**
     * Extracts the list name from the input string based on the *vfor pattern.
     *
     * @param input The input string containing the *vfor directive.
     * @return The extracted list name or null if no match is found.
     */
    public static String extractListName4vue(String input) {
        // Define the regex pattern to match *vfor=list and extract list name
        Pattern pattern = Pattern.compile("\\*vfor\\s*=\\s*(\\w+)");
        Matcher matcher = pattern.matcher(input);

        // Check for matches
        if (matcher.find()) {
            // Return the matched group
            return matcher.group(1);
        } else {
            // Return null if no match is found
            return null;
        }
    }

    // Get field from data map
    @SuppressWarnings("unchecked")
    private static <T> T getFieldV2(Map<String, Object> data, String key, T defaultValue) {
        return (T) data.getOrDefault(key, defaultValue);
    }

    // Render row template
    private static String renderRowTmplt(String rowTmplt, List<Map<String, Object>> list) {
        StringBuilder renderedTxt = new StringBuilder();
        for (Map<String, Object> rowData : list) {
            renderedTxt.append(renderTemplate4vue(rowTmplt, rowData));
        }
        return renderedTxt.toString();
    }
    /**
     * Replaces Mustache placeholders in the line with actual data values.
     *
     * @param data A map containing key-value pairs to replace placeholders.
     * @param line The input string containing Mustache placeholders.
     * @return The string with placeholders replaced by actual data values.
     */
    public static String rendMustacheFrmTmpltTxt(Map<String, Object> data, String line) {
        // Define the regex pattern to match Mustache placeholders
        Pattern pattern = Pattern.compile("\\{\\{(\\w+)\\}\\}");
        Matcher matcher = pattern.matcher(line);

        // Use a StringBuffer to construct the resulting string
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            // Extract the placeholder key
            String key = matcher.group(1);
            // Replace placeholder with corresponding value from data map
            String replacement = (data.containsKey(key)) ? data.get(key).toString() : "";
            matcher.appendReplacement(result, replacement);
        }

        // Append the remainder of the line
        matcher.appendTail(result);

        return result.toString();
    }


    // Main function to render template
    public static String renderTemplate4vue(String template, Map<String, Object> data) {
        StringBuilder txt = new StringBuilder();
        String[] lines = template.split("\n");
        int curIdx = 0;

        while (curIdx < lines.length) {
            String line = lines[curIdx].trim();

            if (line.contains("*vfor=")) {
                String key = extractListName4vue(line);
                int startIdx = line.indexOf("*vfor");
                String rowTmplt = line.substring(0, startIdx).trim() + "\n"; // if block mode

                List<Map<String, Object>> list = getFieldV2(data, key, new ArrayList<>());
                String renderedTxt = renderRowTmplt(rowTmplt, list);
                txt.append(renderedTxt);
                curIdx++;
                continue;
            }

            if (line.contains("{{")) {
                String renderedLine = rendMustacheFrmTmpltTxt(data, line) + "\n";
                txt.append(renderedLine);
                curIdx++;
                continue;
            }

            // Normal line
            txt.append(line).append("\n");
            curIdx++;
        }

        return txt.toString();
    }

}
