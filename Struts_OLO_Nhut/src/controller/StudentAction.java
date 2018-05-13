package controller;

import com.opensymphony.xwork2.ActionSupport;

import entities.Student;

/**
 * @author java_dev
 *
 */
public class StudentAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Student student = new Student();	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String execute() {	
		this.student = new Student("ST-001", "Student A", "Ho Chi Minh");		
		return SUCCESS;
	}	
}