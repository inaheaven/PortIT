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
		} else if (cmd.equals("ProfileAdd")) {
			return new ProfileAddController();
		} else if (cmd.equals("ProfileView")) {
			return new ProfileViewController();
		} else if (cmd.equals("ProfileEdit")) {
			return new ProfileEditController();
		} else if (cmd.equals("ProfileDelete")) {
			return new ProfileDeleteController();
		} else if (cmd.equals("PortfolioAdd")) {
			return new PortfolioAddController();
		} else if (cmd.equals("PortfolioView")) {
			return new PortfolioViewController();
		} else if (cmd.equals("PortfolioEdit")) {
			return new PortfolioEditController();
		} else if (cmd.equals("PortfolioDelete")) {
			return new PortfolioDeleteController();
		} else if (cmd.equals("ProjectAdd")) {
			return new ProjectAddController();
		} else if (cmd.equals("ProjectView")) {
			return new ProjectViewController();
		} else if (cmd.equals("ProjectEdit")) {
			return new ProjectEditController();
		} /*else if (cmd.equals("ProjectDelete")) {
			return new ProjectDeleteController();
		}*/ else {
			return null;
		}
	}
	
}
