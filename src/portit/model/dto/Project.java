package portit.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 프로젝트 DTO
 *
 */


public class Project {
	
	private int mem_id;

	private int pf_id;
	private String proj_app_confirm;
	
	private ArrayList apply_mem;
	
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
	
	private String ml_path;
	private int d_day;

	private ArrayList<String> proj_env;
	private ArrayList<String> proj_language;
	private ArrayList<String> proj_tool;
	private ArrayList<String> proj_field;
	private ArrayList<Integer> proj_numofperson;
	
	private List<String> tags;
	private List<String> tags2;
	private List<String> media_path;
	 
	
	
	
	public List<String> getTags() {
		return tags;
	}
	public List<String> getTags2() {
		return tags2;
	}
	public List<String> getMedia_path() {
		return media_path;
	}
	public Project setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}
	public Project setTags2(List<String> tags2) {
		this.tags2 = tags2;
		return this;
	}
	public Project setMedia_path(List<String> media_path) {
		this.media_path = media_path;
		return this;
	}
	public int getMem_id() {
		return mem_id;
	}
	public int getPf_id() {
		return pf_id;
	}
	public String getProj_app_confirm() {
		return proj_app_confirm;
	}
	public ArrayList getApply_mem() {
		return apply_mem;
	}
	public int getProj_id() {
		return proj_id;
	}
	public String getProj_title() {
		return proj_title;
	}
	public String getProj_intro() {
		return proj_intro;
	}
	public int getProj_to() {
		return proj_to;
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
	public String getTag_name() {
		return tag_name;
	}
	public String getProf_name() {
		return prof_name;
	}
	public String getMl_path() {
		return ml_path;
	}
	public int getD_day() {
		return d_day;
	}
	public ArrayList<String> getProj_env() {
		return proj_env;
	}
	public ArrayList<String> getProj_language() {
		return proj_language;
	}
	public ArrayList<String> getProj_tool() {
		return proj_tool;
	}
	public ArrayList<String> getProj_field() {
		return proj_field;
	}
	public ArrayList<Integer> getProj_numofperson() {
		return proj_numofperson;
	}
	public Project setMem_id(int mem_id) {
		this.mem_id = mem_id;
		return this;
	}
	public Project setPf_id(int pf_id) {
		this.pf_id = pf_id;
		return this;
	}
	public Project setProj_app_confirm(String proj_app_confirm) {
		this.proj_app_confirm = proj_app_confirm;
		return this;
	}
	public Project setApply_mem(ArrayList apply_mem) {
		this.apply_mem = apply_mem;
		return this;
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
	public Project setProj_to(int proj_to) {
		this.proj_to = proj_to;
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
	public Project setTag_name(String tag_name) {
		this.tag_name = tag_name;
		return this;
	}
	public Project setProf_name(String prof_name) {
		this.prof_name = prof_name;
		return this;
	}
	public Project setMl_path(String ml_path) {
		this.ml_path = ml_path;
		return this;
	}
	public Project setD_day(int d_day) {
		this.d_day = d_day;
		return this;
	}
	public Project setProj_env(ArrayList<String> proj_env) {
		this.proj_env = proj_env;
		return this;
	}
	public Project setProj_language(ArrayList<String> proj_language) {
		this.proj_language = proj_language;
		return this;
	}
	public Project setProj_tool(ArrayList<String> proj_tool) {
		this.proj_tool = proj_tool;
		return this;
	}
	public Project setProj_field(ArrayList<String> proj_field) {
		this.proj_field = proj_field;
		return this;
	}
	public Project setProj_numofperson(ArrayList<Integer> proj_numofperson) {
		this.proj_numofperson = proj_numofperson;
		return this;
	}
	
	
	
	

	
}
