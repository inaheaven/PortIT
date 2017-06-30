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

	

	public ArrayList getApply_mem() {
		return apply_mem;
	}
	public void setApply_mem(ArrayList apply_mem) {
		this.apply_mem = apply_mem;
	}
	
	
	
	
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public int getPf_id() {
		return pf_id;
	}
	public void setPf_id(int pf_id) {
		this.pf_id = pf_id;
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
	public String getProj_regdate() {
		return proj_regdate;
	}
	public void setProj_regdate(String proj_regdate) {
		this.proj_regdate = proj_regdate;
	}
	public String getProj_startdate() {
		return proj_startdate;
	}
	public void setProj_startdate(String proj_startdate) {
		this.proj_startdate = proj_startdate;
	}
	public int getProj_period() {
		return proj_period;
	}
	public void setProj_period(int proj_period) {
		this.proj_period = proj_period;
	}
	public String getProj_regenddate() {
		return proj_regenddate;
	}
	public void setProj_regenddate(String proj_regenddate) {
		this.proj_regenddate = proj_regenddate;
	}
	
	
}
