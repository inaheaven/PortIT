package portit.model.dto;

import java.util.ArrayList;
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
	private String prof_img = "/assets/img/user-basic.png";
	private String prof_background;
	private String prof_email;
	private String prof_website;
	private String prof_github;
	private String prof_facebook;
	private Date prof_regdate;
	private int prof_follower;

	private String prof_language;
	private String prof_tool;
	private String prof_field;
	private String tag_name;

	private String tag_name2;
	private String tag_name3;
	private String tag_name4;
	private String tag_name5;
	private String tag_name6;
	private int prof_skill_level;
	private int prof_skill_level2;
	private int prof_skill_level3;
	private List tags;

	private List<Tag> prof_tags_language;
	private List<Tag> prof_tags_tool;
	private List<Tag> prof_tags_field;
	private List<TagUse> prof_skillset;
	private List<Portfolio> prof_myPf;
	private List<Project> prof_myProj;
	
	
	private ArrayList<Integer> prof_id_list = new ArrayList<>();
	private ArrayList<String> prof_name_list = new ArrayList<>();
	private ArrayList<String> prof_nick_list = new ArrayList<>();
	private List tag_lang;
	private List tag_tool;
	private List tag_field;
	
	
	
	
	public ArrayList<Integer> getProf_id_list() {
		return prof_id_list;
	}
	public ArrayList<String> getProf_name_list() {
		return prof_name_list;
	}
	public ArrayList<String> getProf_nick_list() {
		return prof_nick_list;
	}
	public Profile setProf_id_list(ArrayList<Integer> prof_id_list) {
		this.prof_id_list = prof_id_list;
		return this;
	}
	public Profile setProf_name_list(ArrayList<String> prof_name_list) {
		this.prof_name_list = prof_name_list;
		return this;
	}
	public Profile setProf_nick_list(ArrayList<String> prof_nick_list) {
		this.prof_nick_list = prof_nick_list;
		return this;
	}
	public List getTags() {
		return tags;
	}
	public Profile setTags(List tags) {
		this.tags = tags;
		return this;
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
	public String getProf_language() {
		return prof_language;
	}
	public String getProf_tool() {
		return prof_tool;
	}
	public String getProf_field() {
		return prof_field;
	}
	public String getTag_name() {
		return tag_name;
	}
	public String getTag_name2() {
		return tag_name2;
	}
	public String getTag_name3() {
		return tag_name3;
	}
	public String getTag_name4() {
		return tag_name4;
	}
	public String getTag_name5() {
		return tag_name5;
	}
	public String getTag_name6() {
		return tag_name6;
	}
	public int getProf_skill_level() {
		return prof_skill_level;
	}
	public int getProf_skill_level2() {
		return prof_skill_level2;
	}
	public int getProf_skill_level3() {
		return prof_skill_level3;
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
	public Profile setProf_language(String prof_language) {
		this.prof_language = prof_language;
		return this;
	}
	public Profile setProf_tool(String prof_tool) {
		this.prof_tool = prof_tool;
		return this;
	}
	public Profile setProf_field(String prof_field) {
		this.prof_field = prof_field;
		return this;
	}
	public Profile setTag_name(String tag_name) {
		this.tag_name = tag_name;
		return this;
	}
	public Profile setTag_name2(String tag_name2) {
		this.tag_name2 = tag_name2;
		return this;
	}
	public Profile setTag_name3(String tag_name3) {
		this.tag_name3 = tag_name3;
		return this;
	}
	public Profile setTag_name4(String tag_name4) {
		this.tag_name4 = tag_name4;
		return this;
	}
	public Profile setTag_name5(String tag_name5) {
		this.tag_name5 = tag_name5;
		return this;
	}
	public Profile setTag_name6(String tag_name6) {
		this.tag_name6 = tag_name6;
		return this;
	}
	public Profile setProf_skill_level(int prof_skill_level) {
		this.prof_skill_level = prof_skill_level;
		return this;
	}
	public Profile setProf_skill_level2(int prof_skill_level2) {
		this.prof_skill_level2 = prof_skill_level2;
		return this;
	}
	public Profile setProf_skill_level3(int prof_skill_level3) {
		this.prof_skill_level3 = prof_skill_level3;
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
	public List getTag_lang() {
		return tag_lang;
	}
	public void setTag_lang(List tag_lang) {
		this.tag_lang = tag_lang;
	}
	public List getTag_tool() {
		return tag_tool;
	}
	public void setTag_tool(List tag_tool) {
		this.tag_tool = tag_tool;
	}
	public List getTag_field() {
		return tag_field;
	}
	public void setTag_field(List tag_field) {
		this.tag_field = tag_field;
	}
	

	
	
	
	
	

}