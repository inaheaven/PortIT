package portit.model.dto;

import java.lang.Math;
import java.util.Date;

/**
 * 사용자 프로필 DTO
 * @author gnsngck
 *
 */
public class Profile {

	private int prof_id = (int) (Math.random() * 100000000);
	private int mem_id;
	private String prof_nick;
	private String prof_name;
	private String prof_intro;
	private String prof_img;
	private String prof_background;
	private String prof_website;
	private String prof_github;
	private String prof_facebook;
	private Date prof_regdate;
	private int prof_follower;
	private Tag prof_language;
	private Tag prof_tool;
	private Tag prof_field;
	
	private int tag_use_id;
	private String tag_use_type;
	private int tag_use_type_id;
	private int prof_skill_level;
	private int tag_id;
	
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
	public int getProf_skill_level() {
		return prof_skill_level;
	}
	public void setProf_skill_level(int prof_skill_level) {
		this.prof_skill_level = prof_skill_level;
	}
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_type() {
		return tag_type;
	}
	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	private String tag_type;
	private String tag_name;
	
	public int getProf_id() {
		return prof_id;
	}
	public int getMem_id() {
		return mem_id;
	}
	public String getProf_nick() {
		return prof_nick;
	}
	public String getProf_name() {
		return prof_name;
	}
	public String getProf_intro() {
		return prof_intro;
	}
	public String getProf_img() {
		return prof_img;
	}
	public String getProf_background() {
		return prof_background;
	}
	public String getProf_website() {
		return prof_website;
	}
	public String getProf_github() {
		return prof_github;
	}
	public String getProf_facebook() {
		return prof_facebook;
	}
	public Date getProf_regdate() {
		return prof_regdate;
	}
	public int getProf_follower() {
		return prof_follower;
	}
	public Tag getProf_language() {
		return prof_language;
	}
	public Tag getProf_tool() {
		return prof_tool;
	}
	public Tag getProf_field() {
		return prof_field;
	}
	
	public Profile setProf_id(int prof_id) {
		this.prof_id = prof_id;
		return this;
	}
	public Profile setMem_id(int mem_id) {
		this.mem_id = mem_id;
		return this;
	}
	public Profile setProf_nick(String prof_nick) {
		this.prof_nick = prof_nick;
		return this;
	}
	public Profile setProf_name(String prof_name) {
		this.prof_name = prof_name;
		return this;
	}
	public Profile setProf_intro(String prof_intro) {
		this.prof_intro = prof_intro;
		return this;
	}
	public Profile setProf_img(String prof_img) {
		this.prof_img = prof_img;
		return this;
	}
	public Profile setProf_background(String prof_background) {
		this.prof_background = prof_background;
		return this;
	}
	public Profile setProf_website(String prof_website) {
		this.prof_website = prof_website;
		return this;
	}
	public Profile setProf_github(String prof_github) {
		this.prof_github = prof_github;
		return this;
	}
	public Profile setProf_facebook(String prof_facebook) {
		this.prof_facebook = prof_facebook;
		return this;
	}
	public Profile setProf_regdate(Date prof_regdate) {
		this.prof_regdate = prof_regdate;
		return this;
	}
	public Profile setProf_follower(int prof_follower) {
		this.prof_follower = prof_follower;
		return this;
	}
	public Profile setProf_language(Tag prof_language) {
		this.prof_language = prof_language;
		return this;
	}
	public Profile setProf_tool(Tag prof_tool) {
		this.prof_tool = prof_tool;
		return this;
	}
	public Profile setProf_field(Tag prof_field) {
		this.prof_field = prof_field;
		return this;
	}
	
	/**
	 * 같은 프로필 번호의 프로필이 있을 경우 프로필 번호를 재설정
	 * @param prof_id
	 * @return
	 */
	public Profile resetProf_id(int prof_id) {
		this.prof_id = (int) (Math.random() * 100000000);
		return this;
	}
}
