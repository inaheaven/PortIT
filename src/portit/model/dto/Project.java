package portit.model.dto;

<<<<<<< HEAD
import java.util.Date;
import java.util.List;
=======
import java.util.ArrayList;
import java.util.Date;
>>>>>>> refs/remotes/origin/dev-0630myproj

/**
 * 프로젝트 DTO
 *
 */
public class Project {
<<<<<<< HEAD
=======

	
	private int mem_id;
	private int pf_id;
	private String proj_app_confirm;
	
	
	public String getProj_app_confirm() {
		return proj_app_confirm;
	}
	public void setProj_app_confirm(String proj_app_confirm) {
		this.proj_app_confirm = proj_app_confirm;
	}
	//해당 프로젝트의 지원자 리시트가 담긴다.
	//dao에 PRO_ID를 입력하면  LIST를 반환하는 메서드를 만들자.
	private ArrayList apply_mem;	
	
	private int d_day;
	
	
	public int getD_day() {
		return d_day;
	}
	
	public void setD_day(int d_day) {
		this.d_day = d_day;
	}
	
	
	
	
	
	
	
	
	
>>>>>>> refs/remotes/origin/dev-0630myproj
	private int proj_id;
	private String proj_title;
<<<<<<< HEAD
	private String proj_intro;
	private int proj_to;
	private Date proj_regdate;
	private Date proj_startdate;
=======
	private String proj_intro;
	private String proj_regdate;
	private String proj_startdate;
>>>>>>> refs/remotes/origin/dev-0630myproj
	private int proj_period;
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
	private String proj_regenddate;
	private ArrayList<String> proj_env;
	private ArrayList<String> proj_language;
	private ArrayList<String> proj_tool;
	private ArrayList<String> proj_field;
	private ArrayList<Integer> proj_numofperson;
=======
	private Date proj_regenddate;
	private String tag_name;
	private String prof_name;
	private String ml_path;
	private int d_day;
>>>>>>> refs/remotes/origin/dev-Insoo

<<<<<<< HEAD

	public ArrayList<String> getProj_env() {
		return proj_env;
=======
	public String getMl_path() {
		return ml_path;
	}
	public void setMl_path(String ml_path) {
		this.ml_path = ml_path;
	}
	public int getD_day() {
		return d_day;
	}
	public void setD_day(int d_day) {
		this.d_day = d_day;
	}
	public String getProf_name() {
		return prof_name;
>>>>>>> refs/remotes/origin/dev-Insoo
	}
	public void setProj_env(ArrayList<String> proj_env2) {
		this.proj_env = proj_env2;
	}
	public ArrayList<String> getProj_language() {
		return proj_language;
	}
	public void setProj_language(ArrayList<String> proj_language2) {
		this.proj_language = proj_language2;
	}
		
	
	public ArrayList<String> getProj_tool() {
		return proj_tool;
	}
	public void setProj_tool(ArrayList<String> proj_tool) {
		this.proj_tool = proj_tool;
	}
	public ArrayList<String> getProj_field() {
		return proj_field;
	}
	public void setProj_field(ArrayList<String> proj_field) {
		this.proj_field = proj_field;
	}
	public ArrayList<Integer> getProj_numofperson() {
		return proj_numofperson;
	}
	public void setProj_numofperson(ArrayList<Integer> proj_numofperson) {
		this.proj_numofperson = proj_numofperson;
	}
	
>>>>>>> refs/remotes/origin/dev-0630myproj
	
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
<<<<<<< HEAD
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
=======

	public String getProj_regdate() {
>>>>>>> refs/remotes/origin/dev-0630myproj
		return proj_regdate;
	}
<<<<<<< HEAD
	public void setProj_regdate(Date proj_regdate) {
		this.proj_regdate = proj_regdate;
	}
	public Date getProj_startdate() {
=======
	public String getProj_startdate() {
>>>>>>> refs/remotes/origin/dev-0630myproj
		return proj_startdate;
	}
	public void setProj_startdate(Date proj_startdate) {
		this.proj_startdate = proj_startdate;
	}
	public int getProj_period() {
		return proj_period;
	}
<<<<<<< HEAD
	public void setProj_period(int proj_period) {
		this.proj_period = proj_period;
	}
	public Date getProj_regenddate() {
=======
	public String getProj_regenddate() {
>>>>>>> refs/remotes/origin/dev-0630myproj
		return proj_regenddate;
	}
<<<<<<< HEAD
	public void setProj_regenddate(Date proj_regenddate) {
		this.proj_regenddate = proj_regenddate;
	}
=======

	
	public Project setProj_id(int proj_id) {
		this.proj_id = proj_id;
		return this;
	}
	public Project setProj_title(String proj_title) {
		this.proj_title = proj_title;
		return this;
	}
	public Project setProj_intro(String proj_intro) {
		this.proj_intro = proj_intro;
		return this;
	}

	public Project setProj_regdate(String proj_regdate2) {
		this.proj_regdate = proj_regdate2;
		return this;
	}
	public Project setProj_startdate(String proj_startdate2) {
		this.proj_startdate = proj_startdate2;
		return this;
	}
	public Project setProj_period(int proj_period) {
		this.proj_period = proj_period;
		return this;
	}
	public Project setProj_regenddate(String proj_regenddate2) {
		this.proj_regenddate = proj_regenddate2;
		return this;
	}

	
>>>>>>> refs/remotes/origin/dev-0630myproj
}
