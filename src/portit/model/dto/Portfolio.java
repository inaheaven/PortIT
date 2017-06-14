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
