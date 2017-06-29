package portit.model.dto;

import java.util.Date;

public class Project {
	private int proj_id;
	private String proj_title;
	private String proj_intro;
	private int proj_to;
	private Date proj_regdate;
	private Date proj_startdate;
	private int proj_period;
	private Date proj_regenddate;
	private String tag_name;
	private String prof_name;

	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public String getProj_title() {
		return proj_title;
	}
	public void setProj_title(String proj_title) {
		this.proj_title = proj_title;
	}
	public String getProj_intro() {
		return proj_intro;
	}
	public void setProj_intro(String proj_intro) {
		this.proj_intro = proj_intro;
	}
	public int getProj_to() {
		return proj_to;
	}
	public void setProj_to(int proj_to) {
		this.proj_to = proj_to;
	}
	public Date getProj_regdate() {
		return proj_regdate;
	}
	public void setProj_regdate(Date proj_regdate) {
		this.proj_regdate = proj_regdate;
	}
	public Date getProj_startdate() {
		return proj_startdate;
	}
	public void setProj_startdate(Date proj_startdate) {
		this.proj_startdate = proj_startdate;
	}
	public int getProj_period() {
		return proj_period;
	}
	public void setProj_period(int proj_period) {
		this.proj_period = proj_period;
	}
	public Date getProj_regenddate() {
		return proj_regenddate;
	}
	public void setProj_regenddate(Date proj_regenddate) {
		this.proj_regenddate = proj_regenddate;
	}
	
	
}
