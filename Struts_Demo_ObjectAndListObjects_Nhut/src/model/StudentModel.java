/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

import entities.Student;

/**
 * @author java_dev
 *
 */
public class StudentModel {
	private List<Student> lstStudents = new ArrayList<Student>();

	public List<Student> getLstStudents() {
		return lstStudents;
	}

	public void setLstStudents(List<Student> lstStudents) {
		this.lstStudents = lstStudents;
	}
	
	public List<Student> getStudents() {
		this.lstStudents.add(new Student("ST-001", "Student A", "HCM"));
		this.lstStudents.add(new Student("ST-002", "Student B", "HCM"));
		this.lstStudents.add(new Student("ST-003", "Student C", "HN"));
		this.lstStudents.add(new Student("ST-004", "Student D", "HCM"));
		this.lstStudents.add(new Student("ST-005", "Student E", "HN"));
		
		return lstStudents;
	}
}
