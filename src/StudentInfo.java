import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudentInfo {
	protected String fileName;
	protected static int numOfUsers;
	protected static String[][] accounts = new String[5][100000]; //First name, last name, grade, password, points
	
	private void saveUsers() throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
		for(int i = 0; i < numOfUsers; i++) { //last name
			out.write(accounts[0][i] + " ");
		}
		out.newLine();

		for(int i = 0; i < numOfUsers; i++) { //first name
			out.write(accounts[1][i] + " ");
		}
		out.newLine();
		
		for(int i = 0; i < numOfUsers; i++) { //grade
			out.write(accounts[2][i] + " ");
		}
		out.newLine();
		
		for(int i = 0; i < numOfUsers; i++) { //password
			out.write(accounts[3][i] + " ");
		}
		out.newLine();
		
		for(int i = 0; i < numOfUsers; i++) { //points
			out.write(accounts[4][i] + " ");
		}
		out.newLine();
		
		out.close();
	}
}
