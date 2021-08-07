package Project;

public class Grade {
	
	private int grade;
	
	public Grade(int grade) {
		if (grade > 6 || grade < 1) {
			 throw new IllegalArgumentException("This grade does not exist! Grade shoulde be in range 1-6!");
		}
			
		else {
			this.grade = grade;
		}
		
	}
	
	public int getGrade() {
		return grade;
	}
	
	public String toString() {
		return String.valueOf(grade);
	}
	
}
