package portit.model.dto;

import java.util.Date;
import java.util.List;

/**
 * 포트폴리오 DTO
 * @author 
 *
 */
public class Portfolio {
	
	/** 포트폴리오 글번호 */
	private int pf_id;
	/** 포트폴리오 제목 */
	private String pf_title;
	/** 포트폴리오 소개글 */
	private String pf_intro;
	/** 포트폴리오 등록일자 */
	private Date pf_regdate;
	/** 포트폴리오 좋아요 수 */
	private int pf_like;
	/** 포트폴리오 작업시작일자 */
	private Date pf_startdate;
	/** 포트폴리오 작업종료일자 */
	private Date pf_enddate;
	/** 포트폴리오 공동작업인원 */
	private int pf_numofperson;
	/** 포트폴리오 URL */
	private String pf_repository;
	/** 포트폴리오 작성자 */
	private String pf_prof_name;
	/** 포트폴리오 태그 목록 - 언어 */
	private List<Tag> pf_tags_language;
	/** 포트폴리오 태그 목록 - 툴 */
	private List<Tag> pf_tags_tool;
	/** 포트폴리오 태그 목록 - 분야 */
	private List<Tag> pf_tags_field;
	/** 미디어 파일 목록 */
	private List<Media> pf_mediae;
	
	public int getPf_id() {
		return pf_id;
	}
	public String getPf_title() {
		return pf_title;
	}
	public String getPf_intro() {
		return pf_intro;
	}
	public Date getPf_regdate() {
		return pf_regdate;
	}
	public int getPf_like() {
		return pf_like;
	}
	public Date getPf_startdate() {
		return pf_startdate;
	}
	public Date getPf_enddate() {
		return pf_enddate;
	}
	public int getPf_numofperson() {
		return pf_numofperson;
	}
	public String getPf_repository() {
		return pf_repository;
	}
	public String getPf_prof_name() {
		return pf_prof_name;
	}
	public List<Tag> getPf_tags_language() {
		return pf_tags_language;
	}
	public List<Tag> getPf_tag_tool() {
		return pf_tags_tool;
	}
	public List<Tag> getPf_tag_field() {
		return pf_tags_field;
	}
	public List<Media> getPf_mediae() {
		return pf_mediae;
	}
	
	public Portfolio setPf_id(int pf_id) {
		this.pf_id = pf_id;
		return this;
	}
	public Portfolio setPf_title(String pf_title) {
		this.pf_title = pf_title;
		return this;
	}
	public Portfolio setPf_intro(String pf_intro) {
		this.pf_intro = pf_intro;
		return this;
	}
	public Portfolio setPf_regdate(Date pf_regdate) {
		this.pf_regdate = pf_regdate;
		return this;
	}
	public Portfolio setPf_like(int pf_like) {
		this.pf_like = pf_like;
		return this;
	}
	public Portfolio setPf_startdate(Date pf_startdate) {
		this.pf_startdate = pf_startdate;
		return this;
	}
	public Portfolio setPf_enddate(Date pf_enddate) {
		this.pf_enddate = pf_enddate;
		return this;
	}
	public Portfolio setPf_numofperson(int pf_numofperson) {
		this.pf_numofperson = pf_numofperson;
		return this;
	}
	public Portfolio setPf_repository(String pf_repository) {
		this.pf_repository = pf_repository;
		return this;
	}
	public Portfolio setPf_prof_name(String pf_prof_name) {
		this.pf_prof_name = pf_prof_name;
		return this;
	}
	public Portfolio setPf_tags_language(List<Tag> pf_tags_languages) {
		this.pf_tags_language = pf_tags_languages;
		return this;
	}
	public Portfolio setPf_tags_tool(List<Tag> pf_tags_tools) {
		this.pf_tags_tool = pf_tags_tools;
		return this;
	}
	public Portfolio setPf_tags_field(List<Tag> pf_tags_fields) {
		this.pf_tags_field = pf_tags_fields;
		return this;
	}
	public Portfolio setPf_mediae(List<Media> pf_mediae) {
		this.pf_mediae = pf_mediae;
		return this;
	}
	
}