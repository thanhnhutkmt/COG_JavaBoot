/**
 * 
 */
package entities;

/**
 * @author java_dev
 *
 */
public class Student {
	private String studentID;
	private String studentName;
	private String address;
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Student(String studentID, String studentName, String address) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
		this.address = address;
	}
	public Student() {
		super();
	}		
}
