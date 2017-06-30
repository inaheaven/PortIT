package portit.model.dto;

import java.util.ArrayList;

/**
 * 태그 DTO
 * @author gnsngck
 *
 */
public class Tag {

	private int tag_id;
	private String tag_type;
	private String tag_name;
	
	private ArrayList<String> proj_env_list = new ArrayList<>();
	private ArrayList<String> proj_tool_list  = new ArrayList<>();
	private ArrayList<String> proj_field_list = new ArrayList<>();
	private ArrayList<String> proj_lang_list  = new ArrayList<>();
	
	public ArrayList<String> getProj_env_list() {
		return proj_env_list;
	}
	public void setProj_env_list(ArrayList<String> proj_env_list) {
		this.proj_env_list = proj_env_list;
	}
	public ArrayList<String> getProj_tool_list() {
		return proj_tool_list;
	}
	public void setProj_tool_list(ArrayList<String> proj_tool_list) {
		this.proj_tool_list = proj_tool_list;
	}
	public ArrayList<String> getProj_field_list() {
		return proj_field_list;
	}
	public void setProj_field_list(ArrayList<String> proj_field_list) {
		this.proj_field_list = proj_field_list;
	}
	public ArrayList<String> getProj_lang_list() {
		return proj_lang_list;
	}
	public void setProj_lang_list(ArrayList<String> proj_lang_list) {
		this.proj_lang_list = proj_lang_list;
	}
	

	
	
	
	public int getTag_id() {
		return tag_id;
	}
	public String getTag_type() {
		return tag_type;
	}
	public String getTag_name() {
		return tag_name;
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
	
}