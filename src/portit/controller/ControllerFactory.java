package portit.controller;

/**
 * 컨트롤러 팩토리
 *
 */
public class ControllerFactory {

	private static ControllerFactory instance = new ControllerFactory();
	
	private ControllerFactory() {}
	
	/**
	 * ControllerFactory의 인스턴스 획득
	 * @return ControllerFactory의 인스턴스
	 */
	public static ControllerFactory getInstance() {
		return instance;
	}
	
	/**
	 * Controller 인스턴스 생성
	 * @param servlet 생성할 컨트롤러의 서블릿 주소
	 * @return 기능별 컨트롤러 인스턴스
	 */
	public Controller newController(String servlet) {
		if (servlet.matches("/join")) {
			return new MemberAddController();
		} else if (servlet.equals("/login")) {
			return new MemberLoginController();
		} else if (servlet.equals("/logout")) {
			return new MemberLogoutController();
		} else {
			return null;
		}
	}
	
}