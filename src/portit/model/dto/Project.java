package portit.model.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * 프로젝트 DTO
 *
 */
public class Project {

	
	private int mem_id;
	private int pf_id;
	private String proj_app_confirm;
	
	
	public String getProj_app_confirm() {
		return proj_app_confirm;
	}
	public void setProj_app_confirm(String proj_app_confirm) {
		this.proj_app_confirm = proj_app_confirm;
	}
	//해당 프로젝트의 지원자 리시트가 담긴다.
	//dao에 PRO_ID를 입력하면  LIST를 반환하는 메서드를 만들자.
	private ArrayList apply_mem;	
	
	private int d_day;
	
	
	public int getD_day() {
		return d_day;
	}
	
	public void setD_day(int d_day) {
		this.d_day = d_day;
	}
	
	
	
	
	
	
	
	
	
	private int proj_id;
	private String proj_title;
	private String proj_intro;
	private String proj_regdate;
	private String proj_startdate;
	private int proj_period;
	private String proj_regenddate;
	private ArrayList<String> proj_env;
	private ArrayList<String> proj_language;
	private ArrayList<String> proj_tool;
	private ArrayList<String> proj_field;
	private ArrayList<Integer> proj_numofperson;


	public ArrayList<String> getProj_env() {
		return proj_env;
	}
	public void setProj_env(ArrayList<String> proj_env2) {
		this.proj_env = proj_env2;
	}
	public ArrayList<String> getProj_language() {
		return proj_language;
	}
	public void setProj_language(ArrayList<String> proj_language2) {
		this.proj_language = proj_language2;
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
	
	
	public int getProj_id() {
		return proj_id;
	}
	public String getProj_title() {
		return proj_title;
	}
	public String getProj_intro() {
		return proj_intro;
	}

	public String getProj_regdate() {
		return proj_regdate;
	}
	public String getProj_startdate() {
		return proj_startdate;
	}
	public int getProj_period() {
		return proj_period;
	}
	public String getProj_regenddate() {
		return proj_regenddate;
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

	public Project setProj_regdate(String proj_regdate2) {
		this.proj_regdate = proj_regdate2;
		return this;
	}
	public Project setProj_startdate(String proj_startdate2) {
		this.proj_startdate = proj_startdate2;
		return this;
	}
	public Project setProj_period(int proj_period) {
		this.proj_period = proj_period;
		return this;
	}
	public Project setProj_regenddate(String proj_regenddate2) {
		this.proj_regenddate = proj_regenddate2;
		return this;
	}

	
}