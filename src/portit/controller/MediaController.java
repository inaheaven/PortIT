package portit.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


/**
 * 미디어 라이브러리 컨트롤러
 * @author gnsngck
 *
 */
@SuppressWarnings("serial")
@WebServlet("/media")
public class MediaController extends HttpServlet {
	
	private MultipartRequest multi;
	private String path;
	private int maxSize;
	private String encType;
	
	public void setMulti(HttpServletRequest request) {
		try {
			multi = new MultipartRequest(request, path, maxSize, encType, new DefaultFileRenamePolicy());
		} catch (Exception e) {
			System.out.println("업로드 실패");
			e.printStackTrace();
		}
	}
	public void setPath(String path, HttpServletRequest request) {
		this.path = request.getServletContext().getRealPath(path);
	}
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	public void setEncType(String encType) {
		this.encType = encType;
	}
	
	public String getUser() {
		return multi.getParameter("user");
	}
	public String getTitle() {
		return multi.getParameter("title");
	}
	public String getUpfile() {
		String result = "";
		Enumeration enume = multi.getFileNames();
		while(enume.hasMoreElements()) {
			String name = (String) enume.nextElement();
			File file = multi.getFile(name);
			result += file.getPath()+multi.getFilesystemName(name)+" ("+multi.getContentType(name)+", "+file.length()+"Bytes)";
		}
		return result;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setPath("files", req);
		setMaxSize(20 * 1024 * 1024); // 20MBytes
		setEncType("utf-8");
		setMulti(req);
	}
	
	/**
	 * 무작위 파일명 생성
	 * @param ciphers 파일 이름의 길이
	 * @return 확장자를 제외한 파일명
	 */
	private String generateFileName(int ciphers) {
		Random random = new Random();
		char[] ch = new char[ciphers];
		int cnt = 0;
		while (cnt < ciphers) {
			int n = random.nextInt(128);
			if ((n >= 48 && n <= 57) || (n >= 65 && n <= 90) || (n >= 97 && n <= 122)) {
				ch[cnt] = (char) n;
				cnt++;
			}
		}
		return String.valueOf(ch);
	}

}
