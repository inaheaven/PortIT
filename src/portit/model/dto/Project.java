package portit.model.dto;

import java.util.Date;

/**
 * 프로젝트 DTO
 * @author gnsngck
 *
 */
public class Project {

	private int proj_id = (int) (Math.random() * 100000000);
	private String proj_title;
	private String proj_intro;
	private int proj_numofperson;
	private Date proj_regdate;
	private Date proj_startdate;
	private int proj_period;
	private Date proj_regenddate;
	private Tag proj_language;
	private Tag proj_tool;
	private Tag proj_field;
	
	public int getProj_id() {
		return proj_id;
	}
	public String getProj_title() {
		return proj_title;
	}
	public String getProj_intro() {
		return proj_intro;
	}
	public int getProj_numofperson() {
		return proj_numofperson;
	}
	public Date getProj_regdate() {
		return proj_regdate;
	}
	public Date getProj_startdate() {
		return proj_startdate;
	}
	public int getProj_period() {
		return proj_period;
	}
	public Date getProj_regenddate() {
		return proj_regenddate;
	}
	public Tag getProj_language() {
		return proj_language;
	}
	public Tag getProj_tool() {
		return proj_tool;
	}
	public Tag getProj_field() {
		return proj_field;
	}
	
	public Project setProj_id(int proj_id) {
		this.proj_id = proj_id;
		return this;
	}
	public Project setProj_title(String proj_title) {
		this.proj_title = proj_title;
		return this;
	}
	public Project setProj_intro(String proj_intro) {
		this.proj_intro = proj_intro;
		return this;
	}
	public Project setProj_numofperson(int proj_numofperson) {
		this.proj_numofperson = proj_numofperson;
		return this;
	}
	public Project setProj_regdate(Date proj_regdate) {
		this.proj_regdate = proj_regdate;
		return this;
	}
	public Project setProj_startdate(Date proj_startdate) {
		this.proj_startdate = proj_startdate;
		return this;
	}
	public Project setProj_period(int proj_period) {
		this.proj_period = proj_period;
		return this;
	}
	public Project setProj_regenddate(Date proj_regenddate) {
		this.proj_regenddate = proj_regenddate;
		return this;
	}
	public Project setProj_language(Tag proj_language) {
		this.proj_language = proj_language;
		return this;
	}
	public Project setProj_tool(Tag proj_tool) {
		this.proj_tool = proj_tool;
		return this;
	}
	public Project setProj_field(Tag proj_field) {
		this.proj_field = proj_field;
		return this;
	}
	
}
