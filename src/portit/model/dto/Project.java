package portit.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 프로젝트 DTO
 *
 */


public class Project {
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
	public int getPf_id() {
		return pf_id;
	}
	public void setPf_id(int pf_id) {
		this.pf_id = pf_id;
	}
	public String getProj_app_confirm() {
		return proj_app_confirm;
	}
	public void setProj_app_confirm(String proj_app_confirm) {
		this.proj_app_confirm = proj_app_confirm;
	}
	public ArrayList getApply_mem() {
		return apply_mem;
	}
	public void setApply_mem(ArrayList apply_mem) {
		this.apply_mem = apply_mem;
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
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public String getMl_path() {
		return ml_path;
	}
	public void setMl_path(String ml_path) {
		this.ml_path = ml_path;
	}
	public int getD_day() {
		return d_day;
	}
	public void setD_day(int d_day) {
		this.d_day = d_day;
	}
	public ArrayList<String> getProj_env() {
		return proj_env;
	}
	public void setProj_env(ArrayList<String> proj_env) {
		this.proj_env = proj_env;
	}
	public ArrayList<String> getProj_language() {
		return proj_language;
	}
	public void setProj_language(ArrayList<String> proj_language) {
		this.proj_language = proj_language;
	}
	public ArrayList<String> getProj_tool() {
		return proj_tool;
	}
	public void setProj_tool(ArrayList<String> proj_tool) {
		this.proj_tool = proj_tool;
	}
	public ArrayList<String> getProj_field() {
		return proj_field;
	}
	public void setProj_field(ArrayList<String> proj_field) {
		this.proj_field = proj_field;
	}
	public ArrayList<Integer> getProj_numofperson() {
		return proj_numofperson;
	}
	public void setProj_numofperson(ArrayList<Integer> proj_numofperson) {
		this.proj_numofperson = proj_numofperson;
	}
	
	
	
	
}
