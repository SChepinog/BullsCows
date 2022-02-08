package game.passive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.common.GameSpec;

public class SuggestionValidator {

    public static boolean isValid(String suggestion) {
        if (suggestion == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{" + GameSpec.LENGTH + "}$");
        Matcher matcher = pattern.matcher(suggestion);
        return matcher.find();
    }
}
