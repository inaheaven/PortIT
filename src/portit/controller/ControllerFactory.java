package portit.controller;

/**
 * 컨트롤러 인스턴스를 생성하는 팩토리
 * @author gnsngck
 *
 */
public class ControllerFactory {
	
	/**
	 * ControllerFactory의 생성자를 private로 외부 접근 차단
	 */
	private ControllerFactory() {}
	
	/**
	 * private static ControllerFactory 인스턴스 생성
	 */
	private static ControllerFactory instance = new ControllerFactory();
	
	/**
	 * 인스턴스를 가져다 쓸 수 있는 getter 메서드
	 * @return ControllerFactory 인스턴스
	 */
    public static ControllerFactory getInstance() {
    	return instance;
    }

    /**
     * 기능별 컨트롤러 인스턴스 생성
     * @param servletPath 클라이언트가 요청한 서블릿 주소
     * @return 기능별 컨트롤러 인스턴스
     */
	public Controller createController(String param) {
		if (param.equals("portfolio")) {
			return new PortfolioController();
		}
		else {
			return null;
		}
	}

}
