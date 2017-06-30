package portit.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private int proj_to;
	private String proj_regdate;
	private String proj_startdate;
	private String proj_regenddate;	
	private int proj_period;
	private String tag_name;
	private String prof_name;

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
	

	

	public ArrayList getApply_mem() {
		return apply_mem;
	}
	public void setApply_mem(ArrayList apply_mem) {
		this.apply_mem = apply_mem;
	}
	
	

}
