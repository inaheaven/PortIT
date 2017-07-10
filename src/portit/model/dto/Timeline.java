package portit.model.dto;

import java.util.Date;
import java.util.List;

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
	private int mem_id2;
	private List tags;
	
	
	
	
	
	public int getMem_id2() {
		return mem_id2;
	}
	public List getTags() {
		return tags;
	}
	public Timeline setMem_id2(int mem_id2) {
		this.mem_id2 = mem_id2;
		return this;
	}
	public Timeline setTags(List tags) {
		this.tags = tags;
		return this;
	}
	public int getPf_id() {
		return pf_id;
	}
	public String getPf_title() {
		return pf_title;
	}
	public String getPf_intro() {
		return pf_intro;
	}
	public Date getPf_regdate() {
		return pf_regdate;
	}
	public int getPf_like() {
		return pf_like;
	}
	public Date getPf_startdate() {
		return pf_startdate;
	}
	public Date getPf_enddate() {
		return pf_enddate;
	}
	public int getPf_numofperson() {
		return pf_numofperson;
	}
	public String getPf_repository() {
		return pf_repository;
	}
	public String getProf_name() {
		return prof_name;
	}
	public String getTag_name() {
		return tag_name;
	}
	public String getMl_path() {
		return ml_path;
	}
	public int getMem_id() {
		return mem_id;
	}
	public Timeline setPf_id(int pf_id) {
		this.pf_id = pf_id;
		return this;
	}
	public Timeline setPf_title(String pf_title) {
		this.pf_title = pf_title;
		return this;
	}
	public Timeline setPf_intro(String pf_intro) {
		this.pf_intro = pf_intro;
		return this;
	}
	public Timeline setPf_regdate(Date pf_regdate) {
		this.pf_regdate = pf_regdate;
		return this;
	}
	public Timeline setPf_like(int pf_like) {
		this.pf_like = pf_like;
		return this;
	}
	public Timeline setPf_startdate(Date pf_startdate) {
		this.pf_startdate = pf_startdate;
		return this;
	}
	public Timeline setPf_enddate(Date pf_enddate) {
		this.pf_enddate = pf_enddate;
		return this;
	}
	public Timeline setPf_numofperson(int pf_numofperson) {
		this.pf_numofperson = pf_numofperson;
		return this;
	}
	public Timeline setPf_repository(String pf_repository) {
		this.pf_repository = pf_repository;
		return this;
	}
	public Timeline setProf_name(String prof_name) {
		this.prof_name = prof_name;
		return this;
	}
	public Timeline setTag_name(String tag_name) {
		this.tag_name = tag_name;
		return this;
	}
	public Timeline setMl_path(String ml_path) {
		this.ml_path = ml_path;
		return this;
	}
	public Timeline setMem_id(int mem_id) {
		this.mem_id = mem_id;
		return this;
	}
	

}