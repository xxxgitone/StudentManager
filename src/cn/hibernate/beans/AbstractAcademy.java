package cn.hibernate.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAcademy entity provides the base persistence definition of the
 * Academy entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAcademy implements java.io.Serializable {

	// Fields

	private String aid;
	private String academy;
	private String ainfo;
	private Set teachers = new HashSet(0);
	private Set majors = new HashSet(0);
	private Set courses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractAcademy() {
	}

	/** minimal constructor */
	public AbstractAcademy(String aid, String academy) {
		this.aid = aid;
		this.academy = academy;
	}

	/** full constructor */
	public AbstractAcademy(String aid, String academy, String ainfo,
			Set teachers, Set majors, Set courses) {
		this.aid = aid;
		this.academy = academy;
		this.ainfo = ainfo;
		this.teachers = teachers;
		this.majors = majors;
		this.courses = courses;
	}

	// Property accessors

	public String getAid() {
		return this.aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAcademy() {
		return this.academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getAinfo() {
		return this.ainfo;
	}

	public void setAinfo(String ainfo) {
		this.ainfo = ainfo;
	}

	public Set getTeachers() {
		return this.teachers;
	}

	public void setTeachers(Set teachers) {
		this.teachers = teachers;
	}

	public Set getMajors() {
		return this.majors;
	}

	public void setMajors(Set majors) {
		this.majors = majors;
	}

	public Set getCourses() {
		return this.courses;
	}

	public void setCourses(Set courses) {
		this.courses = courses;
	}

}