package portit.model.dto;

import java.util.Date;
import java.lang.Math;
/**
 * 
 * @author hyang
 *
 */
public class Bookmark {
	  
	private int bm_id = (int) (Math.random() * 10000000);
	private int mem_id;
	private int pf_id;
	private Date bm_date;

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
	
}
