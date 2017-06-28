package portit.model.dto;

/**
 * 게시물 내에서 태그사용 DTO
 * @author gnsngck
 *
 */
public class TagUse {

	private int tag_use_id;
	private String tag_use_type;
	private int tag_use_type_id;
	private int tag_id;
	private int prof_skill_level; // 프로필에 사용된 skill점수
	
	public int getTag_use_id() {
		return tag_use_id;
	}
	public String getTag_use_type() {
		return tag_use_type;
	}
	public int getTag_use_type_id() {
		return tag_use_type_id;
	}
	public int getTag_id() {
		return tag_id;
	}
	public int getProf_skill_level() {
		return prof_skill_level;
	}
	
	public TagUse setTag_use_id(int tag_use_id) {
		this.tag_use_id = tag_use_id;
		return this;
	}
	public TagUse setTag_use_type(String tag_use_type) {
		this.tag_use_type = tag_use_type;
		return this;
	}
	public TagUse setTag_use_type_id(int tag_use_type_id) {
		this.tag_use_type_id = tag_use_type_id;
		return this;
	}
	public TagUse setTag_id(int tag_id) {
		this.tag_id = tag_id;
		return this;
	}
	public TagUse setProf_skill_level(int prof_skill_level) {
		this.prof_skill_level = prof_skill_level;
		return this;
	}
	
}
