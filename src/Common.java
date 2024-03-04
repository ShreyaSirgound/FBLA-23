import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Generally used utilities.
 */
public class Common {

    public static final String VERSION = "1.3-2024-03-04";

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d yyyy");
	private static final DateTimeFormatter DATE_NO_YEAR_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d");
	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d yyyy h:mma");
	private static final DateTimeFormatter DATE_TIME_NO_YEAR_FORMAT = DateTimeFormatter.ofPattern(
			"MMM d h:mma");

	private static final String ASSETS_FOLDER = "assets";

	/**
	 * Formats a date and time.
	 *
	 * @param dateTime The date and time to format
	 * @return The formatted date, and time if applicable
	 */
	public static String formatDateTime(long dateTime) {

		String formattedDateTime;

		LocalDateTime localDate = new Date(dateTime)
				.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		boolean includeTime = !(localDate.getHour() == 0 && localDate.getMinute() == 0);

		if (localDate.getYear() == LocalDate.now().getYear()) {
			formattedDateTime = localDate.format(includeTime ? DATE_TIME_NO_YEAR_FORMAT : DATE_NO_YEAR_FORMAT);
		} else {
			formattedDateTime = localDate.format(includeTime ? DATE_TIME_FORMAT : DATE_FORMAT);
		}

		return formattedDateTime;
	}

	/**
	 * Gets an image from the assets folder and wraps it inside a JLabel.
	 *
	 * @param name The file name inside the assets folder
	 * @return The image, wrapped in a JLabel
	 */
	public static JLabel getImage(String name) {
		try {
			return new JLabel(
					new ImageIcon(ImageIO.read(new File(ASSETS_FOLDER + File.separator + name))));
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("Failed to read image: " + name);
			return null;
		}
	}

	public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return bytesToHex(digest);
    }
    
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x",b));
        }
        return hexString.toString();
    }
    
    public static boolean comparePassword(String enteredPassword, String hash) throws NoSuchAlgorithmException {
        String hashed = hashPassword(enteredPassword);
        return hashed.equals(hash);
    }
}