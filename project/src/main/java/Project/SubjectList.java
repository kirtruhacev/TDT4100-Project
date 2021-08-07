package Project;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubjectList {
	private String filename;
    private HashMap<Subject, Grade> SubjectList = new HashMap<Subject, Grade>();
    
    public void addSubject (String subject,int grade) {
		Subject sub = new Subject(subject);
		Grade gra = new Grade(grade);
		List <Object> subList = new ArrayList<Object>();
		    for(int i=0; i<SubjectList.size(); i++){
				subList.add((SubjectList.keySet().toArray()[i]));						        
		    }
		    if(!subList.toString().toLowerCase().contains(subject.toLowerCase())) {
		    	SubjectList.put(sub, gra);
		    }
		    else {
		    	throw new IllegalArgumentException("This subject is already in list! You can not add it twice!");
		    }	
	}
	
	public HashMap<Subject, Grade> getSubjectList() {
		return this.SubjectList;
	}
	
	public double getAverage() {
		double average = 0;	
		for (int i = 0; i < SubjectList.size(); i++) {
			Object subjects = SubjectList.keySet().toArray()[i];
			Grade gradeForSubject = SubjectList.get(subjects);
			average = average + gradeForSubject.getGrade();
		}
		return average / SubjectList.size();		
	}
	
	public double getMedian() {
		double median = 0;
		int gradeArray[];
		gradeArray = new int[SubjectList.size()];
		for(int i=0; i<SubjectList.size(); i++){
			Object subjects = SubjectList.keySet().toArray()[i];
			Grade gradeForSubject = SubjectList.get(subjects);
	    	gradeArray[i] = gradeForSubject.getGrade();	   	     
	    }
		Arrays.sort(gradeArray);
		if (gradeArray.length % 2 != 0) {
			int index = gradeArray.length / 2;
			median =  gradeArray[index];
		}
		else {
			int index = gradeArray.length / 2;
			median = (gradeArray[index -1]  + gradeArray[index]) / 2.0f ;
		}
		return median;
	}
	
	public String getHighestSubject() {
		 int max = Integer.MIN_VALUE;	
		    for(int i=0; i<SubjectList.size(); i++){
		    	Object subjects = SubjectList.keySet().toArray()[i];
				Grade gradeForSubject = SubjectList.get(subjects);
		    	if(gradeForSubject.getGrade() > max){
		            max = gradeForSubject.getGrade();		      
		        }
		    }
		    List <Object> subList = new ArrayList<Object>();
		    for(int i=0; i<SubjectList.size(); i++){
		    	Object subjects = SubjectList.keySet().toArray()[i];
				Grade gradeForSubject = SubjectList.get(subjects);
		    	if(gradeForSubject.getGrade() == max){
		    		
		    		subList.add(SubjectList.keySet().toArray()[i]);		      
		        }
		    }
		    
		    return subList.toString().replace("[", "").replace("]", "") ;	
	}
	
	public String getHighestGrade() {
		 int max = Integer.MIN_VALUE;		
		 for(int i=0; i<SubjectList.size(); i++){
		    	Object subjects = SubjectList.keySet().toArray()[i];
				Grade gradeForSubject = SubjectList.get(subjects);
		    	if(gradeForSubject.getGrade() > max){
		            max = gradeForSubject.getGrade();		      
		        }
		    }
		    return String.valueOf(max);
	}
	
	public String getLowestSubject() {
		 int min = Integer.MAX_VALUE;	
		    for(int i=0; i<SubjectList.size(); i++){
		    	Object subjects = SubjectList.keySet().toArray()[i];
				Grade gradeForSubject = SubjectList.get(subjects);
		    	if(gradeForSubject.getGrade() < min){
		    		min = gradeForSubject.getGrade();		      
		        }
		    }
		    List <Object> subList = new ArrayList<Object>();
		    for(int i=0; i<SubjectList.size(); i++){
		    	Object subjects = SubjectList.keySet().toArray()[i];
				Grade gradeForSubject = SubjectList.get(subjects);
		    	if(gradeForSubject.getGrade() == min){
		    		
		    		subList.add(SubjectList.keySet().toArray()[i]);		      
		        }
		    }
		    
		    return subList.toString().replace("[", "").replace("]", "") ;	
	}
	
	public String getLowestGrade() {
		 int min = Integer.MAX_VALUE;		
		   for(int i=0; i<SubjectList.size(); i++){
		    	Object subjects = SubjectList.keySet().toArray()[i];
				Grade gradeForSubject = SubjectList.get(subjects);
		    	if(gradeForSubject.getGrade() < min){
		    		min = gradeForSubject.getGrade();		      
		        }
		    }
		    return String.valueOf(min);
	}
	
	public String toString() {
		return String.valueOf(SubjectList);
	}
	
	public String getFileName() {
        return filename;
    }
	
	public void setFileName(String name) {
        this.filename = name;
    }
}