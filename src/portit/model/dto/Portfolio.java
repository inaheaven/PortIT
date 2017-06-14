package portit.model.dto;

import java.util.Date;

public class Portfolio {
	private int pf_id;
	private String pf_title;
	private String pf_intro;
	private Date pf_regdate;
	private int pf_like;
	private String prof_name;
	private String tag_name;
	private int tag_use_id;
	private String tag_use_type;
	private int tag_use_type_id;
	private int tag_id;
	private int prof_skill_level;
	
	
	public int getTag_use_id() {
		return tag_use_id;
	}
	public void setTag_use_id(int tag_use_id) {
		this.tag_use_id = tag_use_id;
	}
	public String getTag_use_type() {
		return tag_use_type;
	}
	public void setTag_use_type(String tag_use_type) {
		this.tag_use_type = tag_use_type;
	}
	public int getTag_use_type_id() {
		return tag_use_type_id;
	}
	public void setTag_use_type_id(int tag_use_type_id) {
		this.tag_use_type_id = tag_use_type_id;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public int getProf_skill_level() {
		return prof_skill_level;
	}
	public void setProf_skill_level(int prof_skill_level) {
		this.prof_skill_level = prof_skill_level;
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
}
