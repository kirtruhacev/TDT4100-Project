package Project;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public interface GradeCalculatorFileSupport {
	
	SubjectList load(String fileName) throws IOException;	
	
	SubjectList load(FileReader input) throws IOException;
	
	void save(SubjectList subjectList, FileWriter os) throws IOException;

	void save(SubjectList subjectList) throws IOException;
	
}
