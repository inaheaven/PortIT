package portit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 컨트롤러 인터페이스
 * @author gnsngck
 *
 */
public interface Controller {
	/**
	 * 컨트롤러의 실행 메서드
	 * @param req
	 * @param resp
	 * @return {"inc:|fwd:", "뷰 URL"}이 저장된 배열
	 * @throws ServletException
	 * @throws IOException
	 */
	public String[] execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
