package Project;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class SaveHandler implements GradeCalculatorFileSupport{
	
	public final static String SAVE_FOLDER = "gradeCalculator";
	
	private Path getFolderPath() {
        return Path.of(System.getProperty("user.home"), "tdt4100", "gradeCalculator");
    }

    private boolean ensureUserFolder() {
        try {
            Files.createDirectories(getFolderPath());
            return true;
        } catch (IOException ioe) {
            return false;
        }
       
    }

    private Path getSubjectListPath(String filename) {
        return getFolderPath().resolve(filename + "." + SAVE_FOLDER);
    }
		
	public SubjectList load(FileReader input) throws IOException {
		SubjectList subjectList = null;
		try(var scanner = new Scanner(input)) {
			subjectList = new SubjectList();
			while(scanner.hasNextLine()) {
				var line = scanner.nextLine();
				if(line.isEmpty()) {
					continue;
				}
				else {				
				String[] list = line.replace("{","").replace("}","").split(",");
					for (int i = 0; i < list.length; i++) {
						String element = list[i].strip();
					    String[] list2 = element.split("=");					
						subjectList.addSubject(list2[0], Integer.parseInt(list2[1]));
				}
				}
			}
		}
		return subjectList;		
	}
	
	public SubjectList load(String fileName) throws IOException {
        var subjectListPath = getSubjectListPath(fileName);
        try (var input = new FileReader(subjectListPath.toFile())) {
            return load(input);
        }
    }
			
	public void save(SubjectList subjectList, FileWriter os) throws IOException {
		try (PrintWriter writer = new PrintWriter(os)) {			
			writer.println(subjectList.getSubjectList());
		}
	} 				
	
	
	public void save(SubjectList subjectList) throws IOException {
		var subjectListPath = getSubjectListPath(subjectList.getFileName());
		ensureUserFolder();		
        try (var output = new FileWriter(subjectListPath.toFile())) {
        	save(subjectList, output);
        }        	        
	}	
}
