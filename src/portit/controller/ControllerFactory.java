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
		
		System.out.println("#2 :"+param);
		
		if (param.equals("profileAdd")) {
			return new ProfileAddController();
		}
		else if (param.equals("portfolioAdd")) {
			return new PortfolioAddController();
		}
		else if (param.equals("projectAdd")) {
			return new ProjectAddController();
		}
		else if (param.equals("profileView")) {
			return new ProfileViewController();
		}
		else if (param.equals("portfolioView")) {
			return new PortfolioViewController();
		}
		else if (param.equals("projectView")) {
			return new ProjectViewController();
		}
		else {
			return null;
		}
	}
	
}