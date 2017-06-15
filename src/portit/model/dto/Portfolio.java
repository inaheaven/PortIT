package portit.model.dto;

import java.util.Date;

/**
 * 포트폴리오 DTO
 * @author gnsngck
 *
 */
public class Portfolio {
	
	private int pf_id = (int) (Math.random() * 10000000); // 포트폴리오 번호를 8자리 정수로 무작위로 생성
	private String pf_title;
	private String pf_intro;
	private Date pf_regdate;
	private int pf_like;
	private Date pf_startdate;
	private Date pf_enddate;
	private int pf_numofperson;
	private String pf_repository;
	private Tag pf_language;
	private Tag pf_tool;
	private Tag pf_field;
	
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
	public Tag getPf_language() {
		return pf_language;
	}
	public Tag getPf_tool() {
		return pf_tool;
	}
	public Tag getPf_field() {
		return pf_field;
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
	public Portfolio setPf_language(Tag pf_language) {
		this.pf_language = pf_language;
		return this;
	}
	public Portfolio setPf_tool(Tag pf_tool) {
		this.pf_tool = pf_tool;
		return this;
	}
	public Portfolio setPf_field(Tag pf_field) {
		this.pf_field = pf_field;
		return this;
	}
	
	/**
	 * 같은 포트폴리오 번호의 게시물이 있을 경우 포트폴리오 번호를 재설정
	 * @param pf_id
	 * @return
	 */
	public Portfolio resetPf_id(int pf_id) {
		this.pf_id = (int) (Math.random() * 100000000);
		return this;
	}

}
