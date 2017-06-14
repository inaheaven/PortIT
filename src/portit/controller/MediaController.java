package portit.controller;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * 미디어 라이브러리 컨트롤러
 * @author gnsngck
 *
 */
@SuppressWarnings("serial")
@WebServlet("/media")
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*20)
public class MediaController extends HttpServlet {
	
	/**
	 * 업로드할 파일이 저장될 디렉토리 이름
	 */
	private static final String UPLOAD_DIR = "uploads";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일 저장 위치의 경로
		String uploadFilePath = req.getServletContext().getRealPath("")
				+ File.separator
				+ UPLOAD_DIR;
		String fileName = null;
		
		// 저장할 디렉토리가 없으면 생성
		File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        
        for (Part part : req.getParts()) {
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        }
        
        // 업로드가 성공했을 때의 처리
        req.setAttribute("message", fileName + " File uploaded successfully!");
	}
	
	/**
	 * content-disposition 헤더를 통한 파일이름 추출
	 * @param part
	 * @return 파일명 문자열
	 */
	private String getFileName(Part part) {
		// content-disposition 헤더 읽어오기
		String[] cd = part.getHeader("content-disposition").split(";");
		// 파일 이름 추출
		for (String s : cd) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
		return "";
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
