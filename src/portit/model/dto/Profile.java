package portit.model.dto;

import java.util.Date;
import java.util.List;

/**
 * 사용자 프로필 DTO
 *
 */
public class Profile {
	private int prof_id;
	private int mem_id;
	private String prof_nick;
	private String prof_name;
	private String prof_intro;
	private String prof_img;
	private String prof_background;
	private String prof_email;
	private String prof_website;
	private String prof_github;
	private String prof_facebook;
	private Date prof_regdate;
	private int prof_follower;
	private int prof_skill_level;

	private List<Tag> prof_tags_language;
	private List<Tag> prof_tags_tool;
	private List<Tag> prof_tags_field;
	private List<TagUse> prof_skillset;
	private List<Portfolio> prof_myPf;
	private List<Project> prof_myProj;
	
	public Profile() {
		this.prof_img = "/assets/img/user-basic.png";
	}

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

	public String getProf_email() {
		return prof_email;
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

	public int getProf_skill_level() {
		return prof_skill_level;
	}

	public List<Tag> getProf_tags_language() {
		return prof_tags_language;
	}

	public List<Tag> getProf_tags_tool() {
		return prof_tags_tool;
	}

	public List<Tag> getProf_tags_field() {
		return prof_tags_field;
	}

	public List<TagUse> getProf_skillset() {
		return prof_skillset;
	}

	public List<Portfolio> getProf_myPf() {
		return prof_myPf;
	}

	public List<Project> getProf_myProj() {
		return prof_myProj;
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

	public Profile setProf_email(String prof_email) {
		this.prof_email = prof_email;
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

	public Profile setProf_skill_level(int prof_skill_level) {
		this.prof_skill_level = prof_skill_level;
		return this;
	}

	public Profile setProf_tags_language(List<Tag> prof_tags_language) {
		this.prof_tags_language = prof_tags_language;
		return this;
	}

	public Profile setProf_tags_tool(List<Tag> prof_tags_tool) {
		this.prof_tags_tool = prof_tags_tool;
		return this;
	}

	public Profile setProf_tags_field(List<Tag> prof_tags_field) {
		this.prof_tags_field = prof_tags_field;
		return this;
	}

	public Profile setProf_skillset(List<TagUse> prof_skillset) {
		this.prof_skillset = prof_skillset;
		return this;
	}

	public Profile setProf_myPf(List<Portfolio> prof_myPf) {
		this.prof_myPf = prof_myPf;
		return this;
	}

	public Profile setProf_myProj(List<Project> prof_myProj) {
		this.prof_myProj = prof_myProj;
		return this;
	}
	
	
}