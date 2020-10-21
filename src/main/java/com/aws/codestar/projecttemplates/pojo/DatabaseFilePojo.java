
package com.aws.codestar.projecttemplates.pojo;

import java.util.ArrayList;

/**
 *
 * @author Anisha Agarwal
 */

public class DatabaseFilePojo {
	private String firstusername;
	private String lastusername;
	private String fileName;
	private String fileDescription;
	private String fileUploadTime;
	private ArrayList<String> userlist = new ArrayList<String>();

	/**
	 * @return firstusername
	 */
	public String getFirstusername() {
		return firstusername;
	}

	/**
	 * @param firstusername
	 *            to set
	 */
	public void setFirstusername(String firstusername) {
		this.firstusername = firstusername;
	}

	/**
	 * @return lastusername
	 */
	public String getLastusername() {
		return lastusername;
	}

	/**
	 * @param lastusername
	 *            to set
	 */
	public void setLastusername(String lastusername) {
		this.lastusername = lastusername;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileDescription
	 */
	public String getFileDescription() {
		return fileDescription;
	}

	/**
	 * @param the
	 *            fileDescription to set
	 */
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	/**
	 * @return fileUploadTime
	 */
	public String getFileUploadTime() {
		return fileUploadTime;
	}

	/**
	 * @param fileUploadTime
	 *            to set
	 */
	public void setFileUploadTime(String fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}

	/**
	 * @return the userlist
	 */
	public ArrayList<String> getUserlist() {
		return userlist;
	}

	/**
	 * @param userlist
	 *            to set
	 */
	public void setUserlist(ArrayList<String> userlist) {
		this.userlist = userlist;
	}

}
