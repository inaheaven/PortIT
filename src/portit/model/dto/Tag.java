package portit.model.dto;

import java.util.ArrayList;

public class Tag {
	private int tag_id;
	private String tag_type;
	private String tag_name;
	
	/** 프로필 스킬레벨 */
	private int prof_skill_level;
	/** 프로젝트 모집인원수 */
	private int proj_numofperson;
	
	private ArrayList<String> proj_tool_list  = new ArrayList<>();
	private ArrayList<String> proj_field_list = new ArrayList<>();
	private ArrayList<String> proj_lang_list  = new ArrayList<>();
	public int getTag_id() {
		return tag_id;
	}
	public String getTag_type() {
		return tag_type;
	}
	public String getTag_name() {
		return tag_name;
	}
	public int getProf_skill_level() {
		return prof_skill_level;
	}
	public int getProj_numofperson() {
		return proj_numofperson;
	}
	public ArrayList<String> getProj_tool_list() {
		return proj_tool_list;
	}
	public ArrayList<String> getProj_field_list() {
		return proj_field_list;
	}
	public ArrayList<String> getProj_lang_list() {
		return proj_lang_list;
	}
	
	public Tag setTag_id(int tag_id) {
		this.tag_id = tag_id;
		return this;
	}
	public Tag setTag_type(String tag_type) {
		this.tag_type = tag_type;
		return this;
	}
	public Tag setTag_name(String tag_name) {
		this.tag_name = tag_name;
		return this;
	}
	public Tag setProf_skill_level(int prof_skill_level) {
		this.prof_skill_level = prof_skill_level;
		return this;
	}
	public Tag setProj_numofperson(int proj_numofperson) {
		this.proj_numofperson = proj_numofperson;
		return this;
	}
	public Tag setProj_tool_list(ArrayList<String> proj_tool_list) {
		this.proj_tool_list = proj_tool_list;
		return this;
	}
	public Tag setProj_field_list(ArrayList<String> proj_field_list) {
		this.proj_field_list = proj_field_list;
		return this;
	}
	public Tag setProj_lang_list(ArrayList<String> proj_lang_list) {
		this.proj_lang_list = proj_lang_list;
		return this;
	}
	
	
	
	

}