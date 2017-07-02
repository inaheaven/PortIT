package portit.controller;

public class ControllerFactory {

	private static ControllerFactory instance = new ControllerFactory();
	
	private ControllerFactory() {}
	
	public static ControllerFactory getInstance() {
		return instance;
	}
	
	public Controller newController(String cmd) {
		if (cmd.equals("MemberAdd")) {
			return new MemberAddController();
		} else if (cmd.equals("MemberLogin")) {
			return new MemberLoginController();
		} else if (cmd.equals("MemberLogout")) {
			return new MemberLogoutController();
		} else {
			return null;
		}
	}
	
}
