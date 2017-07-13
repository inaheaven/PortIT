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
    private String ml_path = "assets/img/login-bg3.png"; // 포트폴리오의 이미지 
	private String pf_title;
    
	private int pf_like;
    private List<String> tags;
    private String prof_nick;
      
    
    

	public String getProf_nick() {
		return prof_nick;
	}
	public Bookmark setProf_nick(String prof_nick) {
		this.prof_nick = prof_nick;
		return this;
	}
	public String getMl_path() {
		return ml_path;
	}
	public Bookmark setMl_path(String ml_path) {
		this.ml_path = ml_path;
		return this;
	}
	public String getPf_title() {
		return pf_title;
	}
	public Bookmark setPf_title(String pf_title) {
		this.pf_title = pf_title;
		return this;
	}
	public int getPf_like() {
		return pf_like;
	}
	public Bookmark setPf_like(int pf_like) {
		this.pf_like = pf_like;
		return this;
	}
	public String getProf_name() {
		return prof_name;
	}
	public Bookmark setProf_name(String prof_name) {
		this.prof_name = prof_name;
		return this;
	}
	public int getBm_id() {
		return bm_id;
	}
	public Bookmark setBm_id(int bm_id) {
		this.bm_id = bm_id;
		return this;
	}
	public int getMem_id() {
		return mem_id;
	}
	public Bookmark setMem_id(int mem_id) {
		this.mem_id = mem_id;
		return this;
	}
	public int getPf_id() {
		return pf_id;
	}
	public Bookmark setPf_id(int pf_id) {
		this.pf_id = pf_id;
		return this;
	}
	public Date getBm_date() {
		return bm_date;
	}
	public Bookmark setBm_date(Date bm_date) {
		this.bm_date = bm_date;
		return this;
	}
    public List<String> getTags() {
		return tags;
	}
	public Bookmark setTags(List<String> tags) {
		this.tags = tags;
		return this;
	}
	
}
