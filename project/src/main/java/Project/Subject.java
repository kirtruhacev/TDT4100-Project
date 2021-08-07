package Project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subject {
	private String subject;
	
	public Subject(String subject) {
		if (subject == null) {
			throw new IllegalArgumentException("You have to write valid subject!");	 
		}
		
		if (subject.length() < 2) {
			 throw new IllegalArgumentException("Your subject is too short, you have to include more letters!");
		}
		
		if (subject.length() > 45) {
			 throw new IllegalArgumentException("Your subject is too long!");
		}
		
		Pattern my_pattern = Pattern.compile("[^a-z0-9\\-' 'øåæØÅÆ]", Pattern.CASE_INSENSITIVE);
	    Matcher my_match = my_pattern.matcher(subject);
	    boolean check = my_match.find();

		if (check) {
			throw new IllegalArgumentException("Your subject can not contain symbols except '-' and numbers.");
		}
		
		if (subject.matches("[0-9]+")) {
			throw new IllegalArgumentException("Your subject name can not contain only digits.");
		}
		
		else {
			this.subject = subject;
		}
	}

	
	public String getSubject() {
		return subject;
	}
	
	public String toString() {
		return subject;
	}
}
