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
	 * @param param 생성할 컨트롤러
	 * @return 기능별 컨트롤러 인스턴스
	 */
	public Controller newController(String param) {
		if ("portfolioAdd".equals(param)) {
			return new PortfolioAddController();
		}
		else if ("portfolioView".equals(param)) {
			return new PortfolioViewController();
		}
		else if ("portfolioEdit".equals(param)) {
			return new PortfolioEditController();
		}
		else if ("portfolioDelete".equals(param)) {
			return new PortfolioDeleteController();
		}
		else if ("profileView".equals(param)) {
			return new ProfileViewController();
		}
		else if ("projectView".equals(param)) {
			return new ProjectViewController();
		}
		else {
			return null;
		}
	}
	
}