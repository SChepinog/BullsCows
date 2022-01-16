package passive;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.GameSpec;

public class SuggestionValidator {

    static boolean isValid(String suggestion) {
        if (suggestion == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d{" + GameSpec.LENGTH + "}$");
        Matcher matcher = pattern.matcher(suggestion);
        return matcher.find();
    }
}
