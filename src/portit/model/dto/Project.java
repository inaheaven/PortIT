package portit.model.dto;

import java.util.Date;
import java.util.List;

/**
 * 프로젝트 DTO
 *
 */
public class Project {
	private int proj_id;
	private String proj_title;
	private String proj_intro;
	private int proj_to;
	private Date proj_regdate;
	private Date proj_startdate;
	private int proj_period;
	private Date proj_regenddate;
	/** 모집인원수 */
	private int proj_numofperson;
	/** 작성자명 */
	private String proj_authorName;
	/** 작성자 사진 */
	private String proj_authorIcon;
	/** 태그 목록 - 개발환경 */
	private List<Tag> proj_tags_env;
	/** 태그 목록 - 언어 */
	private List<Tag> proj_tags_language;
	/** 태그 목록 - 툴 */
	private List<Tag> proj_tags_tool;
	/** 태그 목록 - 분야 */
	private List<Tag> proj_tags_field;
	/** 미디어 파일 목록 */
	private List<Media> proj_mediae;
	/** 공동 작업자 목록 */
	private List<Profile> proj_coworkers;
	
	public int getProj_numofperson() {
		return proj_numofperson;
	}
	public String getProj_authorName() {
		return proj_authorName;
	}
	public String getProj_authorIcon() {
		return proj_authorIcon;
	}
	public List<Tag> getProj_tags_env() {
		return proj_tags_env;
	}
	public List<Tag> getProj_tags_language() {
		return proj_tags_language;
	}
	public List<Tag> getProj_tags_tool() {
		return proj_tags_tool;
	}
	public List<Tag> getProj_tags_field() {
		return proj_tags_field;
	}
	public List<Media> getProj_mediae() {
		return proj_mediae;
	}
	public List<Profile> getProj_coworkers() {
		return proj_coworkers;
	}
	
	public void setProj_numofperson(int proj_numofperson) {
		this.proj_numofperson = proj_numofperson;
	}
	public void setProj_authorName(String proj_authorName) {
		this.proj_authorName = proj_authorName;
	}
	public void setProj_authorIcon(String proj_authorIcon) {
		this.proj_authorIcon = proj_authorIcon;
	}
	public void setProj_tags_env(List<Tag> proj_tags_env) {
		this.proj_tags_env = proj_tags_env;
	}
	public void setProj_tags_language(List<Tag> proj_tags_language) {
		this.proj_tags_language = proj_tags_language;
	}
	public void setProj_tags_tool(List<Tag> proj_tags_tool) {
		this.proj_tags_tool = proj_tags_tool;
	}
	public void setProj_tags_field(List<Tag> proj_tags_field) {
		this.proj_tags_field = proj_tags_field;
	}
	public void setProj_mediae(List<Media> proj_mediae) {
		this.proj_mediae = proj_mediae;
	}
	public void setProj_coworkers(List<Profile> proj_coworkers) {
		this.proj_coworkers = proj_coworkers;
	}
	
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public String getProj_title() {
		return proj_title;
	}
	public void setProj_title(String proj_title) {
		this.proj_title = proj_title;
	}
	public void setProj_title(String proj_title) {
		this.proj_title = proj_title;
	}
	public String getProj_intro() {
		return proj_intro;
	}
	public void setProj_intro(String proj_intro) {
		this.proj_intro = proj_intro;
	}
	public int getProj_to() {
		return proj_to;
	}
	public void setProj_to(int proj_to) {
		this.proj_to = proj_to;
	}
	public Date getProj_regdate() {
		return proj_regdate;
	}
	public void setProj_regdate(Date proj_regdate) {
		this.proj_regdate = proj_regdate;
	}
	public Date getProj_startdate() {
		return proj_startdate;
	}
	public void setProj_startdate(Date proj_startdate) {
		this.proj_startdate = proj_startdate;
	}
	public int getProj_period() {
		return proj_period;
	}
	public void setProj_period(int proj_period) {
		this.proj_period = proj_period;
	}
	public Date getProj_regenddate() {
		return proj_regenddate;
	}
	public void setProj_regenddate(Date proj_regenddate) {
		this.proj_regenddate = proj_regenddate;
	}
}
