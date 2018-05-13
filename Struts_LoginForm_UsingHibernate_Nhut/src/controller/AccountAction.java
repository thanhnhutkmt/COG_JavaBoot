/**
 * 
 */
package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.*;

import model.AccountModel;
import util.EncryptUtil;
import entities.Account;

/**
 * @author java_dev
 *
 */
public class AccountAction extends ActionSupport implements SessionAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private AccountModel accountModel = new AccountModel();
	private List<Account> lstAccounts;
	private String username;
	private String password;
	
	public List<Account> getLstAccounts() {
		return lstAccounts;
	}

	public void setLstAccounts(List<Account> lstAccounts) {
		this.lstAccounts = lstAccounts;
	}

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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}
	
	@Override
	public String execute() {	
		return SUCCESS;
	}
	
	public String processLogin() {				
		this.lstAccounts = accountModel.getAccounts();
		for (Account account : lstAccounts) {			
			if(account.getUsername().trim().equals(this.username)
				&& account.getPassword().trim().equals(EncryptUtil.md5(this.password))) {
				this.session.put("username", account.getUsername());
				return SUCCESS;
			}
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
}
