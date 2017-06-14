package portit.model.dto;

/**
 * 미디어 라이브러리 DTO
 * @author gnsngck
 *
 */
public class Media {
	
	private int ml_id;
	private String ml_type;
	private int ml_type_id;
	private String ml_path;
	
	public int getMl_id() {
		return ml_id;
	}
	public String getMl_type() {
		return ml_type;
	}
	public int getMl_type_id() {
		return ml_type_id;
	}
	public String getMl_path() {
		return ml_path;
	}
	
	public Media setMl_id(int ml_id) {
		this.ml_id = ml_id;
		return this;
	}
	public Media setMl_type(String ml_type) {
		this.ml_type = ml_type;
		return this;
	}
	public Media setMl_type_id(int ml_type_id) {
		this.ml_type_id = ml_type_id;
		return this;
	}
	public Media setMl_path(String ml_path) {
		this.ml_path = ml_path;
		return this;
	}
	
}
