package portit.model.dto;

/**
 * 태그 DTO
 * @author gnsngck
 *
 */
public class Tag {

	private int tag_id;
	private String tag_type;
	private String tag_name;
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
