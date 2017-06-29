package portit.model.dto;

import java.util.Date;
import java.util.List;
/**
 * 
 * @author hyang
 *
 */
public class Bookmark {
	  
	
	private int bm_id;
	private int mem_id;
	private int pf_id;
	private Date bm_date;
    private String prof_name;
    private String ml_path;
	private String pf_title;
    private int pf_like;
    private List<String> tags;
    private String prof_nick;
    
    
	public String getProf_nick() {
		return prof_nick;
	}
	public String getMl_path() {
		return ml_path;
	}
	public void setMl_path(String ml_path) {
		this.ml_path = ml_path;
	}
	public String getPf_title() {
		return pf_title;
	}
	public void setPf_title(String pf_title) {
		this.pf_title = pf_title;
	}
	public int getPf_like() {
		return pf_like;
	}
	public void setPf_like(int pf_like) {
		this.pf_like = pf_like;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public int getBm_id() {
		return bm_id;
	}
	public void setBm_id(int bm_id) {
		this.bm_id = bm_id;
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
	public Date getBm_date() {
		return bm_date;
	}
	public void setBm_date(Date bm_date) {
		this.bm_date = bm_date;
	}
    public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public void setProf_nick(String string) {
		
	}
}
