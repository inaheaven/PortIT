package portit.model.dto;

import java.util.Date;

public class Timeline {
	private int pf_id;
	private String pf_title;
	private String pf_intro;
	private Date pf_regdate;
	private int pf_like;
	private Date pf_startdate;
	private Date pf_enddate;
	private int pf_numofperson;
	private String pf_repository;
	private String prof_name;
	private String tag_name;
	private String ml_path;
	private int mem_id;
	
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
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
	public String getMl_path() {
		return ml_path;
	}
	public void setMl_path(String ml_path) {
		this.ml_path = ml_path;
	}
	public int getPf_id() {
		return pf_id;
	}
	public void setPf_id(int pf_id) {
		this.pf_id = pf_id;
	}
	public String getPf_title() {
		return pf_title;
	}
	public void setPf_title(String pf_title) {
		this.pf_title = pf_title;
	}
	public String getPf_intro() {
		return pf_intro;
	}
	public void setPf_intro(String pf_intro) {
		this.pf_intro = pf_intro;
	}
	public Date getPf_regdate() {
		return pf_regdate;
	}
	public void setPf_regdate(Date pf_regdate) {
		this.pf_regdate = pf_regdate;
	}
	public int getPf_like() {
		return pf_like;
	}
	public void setPf_like(int pf_like) {
		this.pf_like = pf_like;
	}
	public Date getPf_startdate() {
		return pf_startdate;
	}
	public void setPf_startdate(Date pf_startdate) {
		this.pf_startdate = pf_startdate;
	}
	public Date getPf_enddate() {
		return pf_enddate;
	}
	public void setPf_enddate(Date pf_enddate) {
		this.pf_enddate = pf_enddate;
	}
	public int getPf_numofperson() {
		return pf_numofperson;
	}
	public void setPf_numofperson(int pf_numofperson) {
		this.pf_numofperson = pf_numofperson;
	}
	public String getPf_repository() {
		return pf_repository;
	}
	public void setPf_repository(String pf_repository) {
		this.pf_repository = pf_repository;
	}
	
}