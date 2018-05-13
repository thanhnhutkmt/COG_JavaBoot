/**
 * 
 */
package controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author java_dev
 *
 */
public class UserAction extends ActionSupport implements SessionAware, ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> session;
	private HttpServletRequest request;
	
	private String username;
	private String password;		

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String processLogin() {
		if (this.username == null || this.password == null) return ERROR;
		if (this.username.trim().equals("admin")
				&& this.password.trim().equals("admin")) {
			this.session.put("username", this.username);
			return SUCCESS;
		}
		request.setAttribute("message", "Username or password is invalid!");
		return ERROR;
	}
	
	public String logout() throws Exception {
		if (this.session.containsKey("username")) {
			this.session.remove("username");
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}	
}
