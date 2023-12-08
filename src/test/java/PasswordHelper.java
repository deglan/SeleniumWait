import java.util.Random;

public class PasswordHelper {
    public String generateRandomEmail() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder email = new StringBuilder();
        Random random = new Random();
        int length = 7;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            email.append(allowedChars.charAt(index));
        }

        email.append(random.nextInt(100));
        email.append("@example.com");
        return email.toString();
    }
}
