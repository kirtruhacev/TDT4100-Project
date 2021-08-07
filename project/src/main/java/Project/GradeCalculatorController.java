package Project;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GradeCalculatorController {
	private SubjectList subjectList; 	
	@FXML private Text SubjectListFileNameView;
	@FXML private Label  averageGradeOutput, medianGradeOutput, highestGradeOutput, lowestSubjectOutput, lowestGradeOutput, highestSubjectOutput, ExceptionOutput, stackTrace;
	@FXML private TextField SubjectEntry, GradeEntry, fileNameEntry;
	@FXML private ListView<String> subjectListEntriesView; 
	@FXML private ListView<Integer> gradeListEntriesView;
	@FXML private Button addButton;
	
	public void setSubjectList(final SubjectList subjectList) {
		this.subjectList = subjectList;	
		updateSubjectListView();
	}
	
	@FXML
	void initialize() {
		final var subjectList = new SubjectList();
		setSubjectList(subjectList);
		subjectList.setFileName("Grade Calculator");
	}
	
	@FXML
	private void updateSubjectListView() {
		gradeListEntriesView.getItems().setAll(getGradeListItems());
		subjectListEntriesView.getItems().setAll(getSubjectListItems());			
	}
	
	protected List <Integer> getGradeListItems() {
		final List<Integer> GradeListItems = new ArrayList<>();
		HashMap<Subject, Grade> List = subjectList.getSubjectList();
		List.forEach((Subject k,Grade v) ->	GradeListItems.add(v.getGrade()));
		
		return GradeListItems;
	}
	
	protected List <String> getSubjectListItems() {
		final List<String> SubjectListItems = new ArrayList<>();
		HashMap<Subject, Grade> List = subjectList.getSubjectList();
		List.forEach((Subject k,Grade v) ->	SubjectListItems.add(k.getSubject()));
		
		return SubjectListItems;
	}
	
	@FXML
	private void addButtonAction() {	
		final var subject = SubjectEntry.getText();
		final var grade = GradeEntry.getText();
		
		try {		
			int myGrade = Integer.parseInt(grade);
			subjectList.addSubject(subject,myGrade);
				
			averageGradeOutput.setText(String.format(String.format("%.02f",subjectList.getAverage())));
			medianGradeOutput.setText(String.format(String.format("%.002f",subjectList.getMedian())));
			highestGradeOutput.setText(String.valueOf("Grade: " + subjectList.getHighestGrade()));
			highestSubjectOutput.setText(String.valueOf("Subjects: " + subjectList.getHighestSubject()));
			lowestGradeOutput.setText(String.valueOf("Grade: " + subjectList.getLowestGrade()));
			lowestSubjectOutput.setText(String.valueOf("Subjects: " + subjectList.getLowestSubject()));
				
			SubjectEntry.clear();
			GradeEntry.clear();
			stackTrace.setText("");
			updateSubjectListView();			
		
			
		} catch (NumberFormatException ex) {
			stackTrace.setText("Your grade can not be empty or be a letter/s");
		
		} catch(IllegalArgumentException  e) {
			String[] result = e.toString().split("\n", 2);
			stackTrace.setText(result[0].substring(36));
			e.printStackTrace();
		}
	}
	
	private SaveHandler saveHandler = new SaveHandler();

	private String ensureFileLocation(final boolean isSave) {
		String fileName = fileNameEntry.getText();
		if (!fileName.isBlank() && fileName != null) {
			
			fileNameEntry.setText(fileName);
		}
		
		return fileName;
	}
	
	@FXML
	private void handleLoadButtonAction() throws IOException {
		final String fileName = ensureFileLocation(false);
		if(!fileName.isBlank()) {
			try {
				setSubjectList(saveHandler.load(fileName));		
				averageGradeOutput.setText(String.format(String.format("%.02f",subjectList.getAverage())));
				medianGradeOutput.setText(String.format(String.format("%.002f",subjectList.getMedian())));
				highestGradeOutput.setText(String.valueOf(subjectList.getHighestGrade()));
				highestSubjectOutput.setText(String.valueOf(subjectList.getHighestSubject()));
				lowestGradeOutput.setText(String.valueOf(subjectList.getLowestGrade()));
				lowestSubjectOutput.setText(String.valueOf(subjectList.getLowestSubject()));
				SubjectListFileNameView.setText(fileName);
				SubjectEntry.clear();
				GradeEntry.clear();
				stackTrace.setText("");
				updateSubjectListView();	
				stackTrace.setText("");
			} catch (final IOException e) {	
				stackTrace.setText("This filename does not exist!");
			}			
		}	
		else {
			stackTrace.setText("You have to type in filename to open it!");
		}
	}
			
	@FXML
	private void handleSaveButtonAction() throws FileNotFoundException{	
		final String fileName = ensureFileLocation(true);
 
		if(!fileName.isBlank() && !subjectList.getSubjectList().isEmpty()) {
			try {
				subjectList.setFileName(fileName);		
				saveHandler.save(subjectList);		
				SubjectListFileNameView.setText(subjectList.getFileName());	
				stackTrace.setText("");
				}catch (Exception e) {
					stackTrace.setText(e.getMessage());
				}
		}
		else {
			stackTrace.setText("Your file or filename is empty!");			
		}		
	}					
}
