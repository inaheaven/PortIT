package portit.model.dto;

import java.lang.Math;
import java.util.Date;

/**
 * 사용자 프로필 DTO
 * @author gnsngck
 *
 */
public class Profile {
	private int prof_id;
	private String prof_nick;
	private String prof_name;
	private String prof_intro;
	private String prof_img = "/assets/img/user-basic.png";
	private String prof_background;
	private String prof_websit;
	private String prof_github;
	private String prof_facebook;
	private Date prof_regdate;
	private int prof_follower;
	
	public int getProf_id() {
		return prof_id;
	}
	public void setProf_id(int prof_id) {
		this.prof_id = prof_id;
	}
	public String getProf_nick() {
		return prof_nick;
	}
	public void setProf_nick(String prof_nick) {
		this.prof_nick = prof_nick;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	public String getProf_intro() {
		return prof_intro;
	}
	public void setProf_intro(String prof_intro) {
		this.prof_intro = prof_intro;
	}
	public String getProf_img() {
		return prof_img;
	}
	public void setProf_img(String prof_img) {
		this.prof_img = prof_img;
	}
	public String getProf_background() {
		return prof_background;
	}
	public void setProf_background(String prof_background) {
		this.prof_background = prof_background;
	}
	public String getProf_websit() {
		return prof_websit;
	}
	public void setProf_websit(String prof_websit) {
		this.prof_websit = prof_websit;
	}
	public String getProf_github() {
		return prof_github;
	}
	public void setProf_github(String prof_github) {
		this.prof_github = prof_github;
	}
	public String getProf_facebook() {
		return prof_facebook;
	}
	public void setProf_facebook(String prof_facebook) {
		this.prof_facebook = prof_facebook;
	}
	public Date getProf_regdate() {
		return prof_regdate;
	}
	public void setProf_regdate(Date prof_regdate) {
		this.prof_regdate = prof_regdate;
	}
	public int getProf_follower() {
		return prof_follower;
	}
	public void setProf_follower(int prof_follower) {
		this.prof_follower = prof_follower;
	}
}