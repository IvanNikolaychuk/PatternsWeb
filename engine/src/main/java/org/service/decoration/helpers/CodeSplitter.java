package org.service.decoration.helpers;

import org.model.Code;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CodeSplitter {

    // Split-chars are: "=", ".", ";", "(", ")", "<", ">", "," and any number of spaces.
    private static final Pattern pattern = Pattern.compile("=|\\.|;|,|\\s+|\\n|\\(|\\)|<|>");


    public String[] split(Code code) {
        final String text = code.getCode();

        int lastMatch = 0;
        LinkedList<String> splitted = new LinkedList<>();

        Matcher m = pattern.matcher(text);

        while (m.find()) {
            addIfNotEmpty(splitted, text.substring(lastMatch, m.start()));
            addIfNotEmpty(splitted, m.group());
            lastMatch = m.end();
        }

        final String endString = text.substring(lastMatch);
        if (!endString.isEmpty()) {
            splitted.add(trim(endString));
        }

        return splitted.toArray(new String[splitted.size()]);
    }

    private void addIfNotEmpty(LinkedList<String> splitted, String string) {
        if (!string.isEmpty()) {
            splitted.add(string);
        }
    }

    private String trim(String string) {
        if (string.isEmpty()) {
            return string;
        }
        if (isNewLine(string)) {
            return "\n";
        }
        if (string.trim().isEmpty()) {
            return " ";
        }

        return string.trim();
    }

    private boolean isNewLine(String string) {
        return string.contains("\n") || string.contains("\r\n");
    }
}
