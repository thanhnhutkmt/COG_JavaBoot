package controller;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import entities.Student;
import model.StudentModel;

/**
 * @author java_dev
 *
 */
public class StudentAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentModel studentModel = new StudentModel();
	private List<Student> lstStudents;
	private Student student = new Student();		
	
	public List<Student> getLstStudents() {
		return lstStudents;
	}

	public void setLstStudents(List<Student> lstStudents) {
		this.lstStudents = lstStudents;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String execute() {	
		this.student = new Student("ST-001", "Student A", "Ho Chi Minh");
		this.lstStudents = studentModel.getStudents();
		return SUCCESS;
	}	
}
