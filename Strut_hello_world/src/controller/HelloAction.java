/**
 * 
 */
package controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author java_dev
 *
 */
public class HelloAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() {
		return SUCCESS;
	}
}
