package portit.model.dto;

/**
 * 태그 DTO
 *
 */
public class Tag {

	private int tag_id;
	private String tag_type;
	private String tag_name;
	private int prof_skill_level; // 프로필에 사용되는 기술 숙련도
	private int proj_numofperson; // 프로젝트에 사용되는 모집정원 
	
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

}