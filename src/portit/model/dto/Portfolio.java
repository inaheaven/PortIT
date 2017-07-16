package portit.model.dto;

import java.util.Date;
import java.util.List;

/**
 * 포트폴리오 DTO
 *
 */
public class Portfolio {
	/**작성자 멤버아이디**/
	private int mem_id;
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
	private String pf_url;
	/** 포트폴리오 작성자명 */
	private String pf_authorName;
	/** 포트폴리오 작성자 사진 */
	private String pf_authorIcon;
	/** 포트폴리오 태그 목록 - 환경 */
	private List<Tag> pf_tags_env;
	/** 포트폴리오 태그 목록 - 언어 */
	private List<Tag> pf_tags_language;
	/** 포트폴리오 태그 목록 - 툴 */
	private List<Tag> pf_tags_tool;
	/** 포트폴리오 태그 목록 - 분야 */
	private List<Tag> pf_tags_field;
	/** 미디어 파일 목록 */
	private List<Media> pf_mediaList;
	/** 공동 작업자 목록 */
	private List<Profile> pf_coworkers;
	/** 추천 포트폴리오 목록 */
	private List<Portfolio> pf_recommends;
	
	//추가
	private String prof_nick;
	
	
    private String prof_name;
    
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public String getProf_name() {
		return prof_name;
	}
	public void setProf_name(String prof_name) {
		this.prof_name = prof_name;
	}
	private int bm_id;
	
	public int getBm_id() {
		return bm_id;
	}
	public void setBm_id(int bm_id) {
		this.bm_id = bm_id;
	}
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
	public String getPf_url() {
		return pf_url;
	}
	public String getPf_authorName() {
		return pf_authorName;
	}
	public String getPf_authorIcon() {
		return pf_authorIcon;
	}
	public List<Tag> getPf_tags_env() {
		return pf_tags_env;
	}
	public List<Tag> getPf_tags_language() {
		return pf_tags_language;
	}
	public List<Tag> getPf_tags_tool() {
		return pf_tags_tool;
	}
	public List<Tag> getPf_tags_field() {
		return pf_tags_field;
	}
	public List<Media> getPf_mediaList() {
		return pf_mediaList;
	}
	public List<Profile> getPf_coworkers() {
		return pf_coworkers;
	}
	public List<Portfolio> getPf_recommends() {
		return pf_recommends;
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
	public Portfolio setPf_url(String pf_url) {
		this.pf_url = pf_url;
		return this;
	}
	public Portfolio setPf_authorName(String pf_authorName) {
		this.pf_authorName = pf_authorName;
		return this;
	}
	public Portfolio setPf_authorIcon(String pf_authorIcon) {
		this.pf_authorIcon = pf_authorIcon;
		return this;
	}
	public Portfolio setPf_tags_env(List<Tag> pf_tags_env) {
		this.pf_tags_env = pf_tags_env;
		return this;
	}
	public Portfolio setPf_tags_language(List<Tag> pf_tags_language) {
		this.pf_tags_language = pf_tags_language;
		return this;
	}
	public Portfolio setPf_tags_tool(List<Tag> pf_tags_tool) {
		this.pf_tags_tool = pf_tags_tool;
		return this;
	}
	public Portfolio setPf_tags_field(List<Tag> pf_tags_field) {
		this.pf_tags_field = pf_tags_field;
		return this;
	}
	public Portfolio setPf_mediaList(List<Media> pf_mediaList) {
		this.pf_mediaList = pf_mediaList;
		return this;
	}
	public Portfolio setPf_coworkers(List<Profile> pf_coworkers) {
		this.pf_coworkers = pf_coworkers;
		return this;
	}
	public Portfolio setPf_recommends(List<Portfolio> pf_recommends) {
		this.pf_recommends = pf_recommends;
		return this;
	}
	public void setMl_path(String string) {
		// TODO Auto-generated method stub
		
	}
	public String getProf_nick() {
		return prof_nick;
	}
	public void setProf_nick(String prof_nick) {
		this.prof_nick = prof_nick;
	}
	
}