package passive;

import java.util.Scanner;

import common.GameSpec;

public class InputPoint {

    public static void main(String[] args) {
        String secret = SecretGenerator.create();
        System.out.printf("Game started! My secret is: %s\n", secret);
        System.out.println("Enter your suggestion here:");
        Scanner in = new Scanner(System.in);
        String suggestion = in.nextLine();
        System.out.printf("You entered '%s'\n", suggestion);
        System.out.println(
            SuggestionValidator.isValid(suggestion)
                ? "Your suggestion is valid, going to check further"
                : "You entered incorrect suggestion, it must contains only " + GameSpec.LENGTH + " numbers"
        );
    }
}
