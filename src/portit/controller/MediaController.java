package portit.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


/**
 * 미디어 라이브러리 컨트롤러
 *
 */
@MultipartConfig(fileSizeThreshold=1024*1024, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*20)
public class MediaController {

	/**
	 * 업로드할 파일이 저장될 디렉토리 이름(/{UPLOAD_DIR})
	 */
	private static final String UPLOAD_DIR = "uploads";
	
	/**
	 * 파일 업로드
	 * @param req HttpServletRequest
	 * @param resp HttpServletResponse
	 * @return 파일 경로 목록
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<String> fileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> fileNames = new ArrayList<String>();
		// 파일 저장 위치의 경로
		String uploadFilePath = req.getServletContext().getRealPath("") + UPLOAD_DIR;
		
		// 저장할 디렉토리가 없으면 생성
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		Part file = null;
		for (Part part : req.getParts()) {
			String fileName = getFileName(part);
			String location = uploadFilePath + File.separator + fileName;
			part.write(location);
			fileNames.add(location);
			part.delete();
		}

		// 업로드가 성공했을 때의 처리
		return fileNames;
	}
	
	/**
	 * 파일 삭제
	 * @param paths 파일 경로 목록
	 * @return 삭제한 파일 갯수
	 */
	public int fileDelete(List<String> paths) {
		int files = 0;
		for (String path : paths) {
			File file = new File(path);
			file.delete();
		}
		return files;
	}
	
	/**
	 * content-disposition 헤더를 통한 파일이름 추출
	 * @param part
	 * @return MD5 인코딩된 파일명
	 */
	private String getFileName(Part part) {
		String[] fileName = null;
		// content-disposition 헤더 읽어오기
		String[] cd = part.getHeader("content-disposition").split(";");
		// 파일 이름 추출
		for (String s : cd) {
            if (s.trim().startsWith("filename")) {
				String fn = s.substring(s.indexOf("=") + 1, s.length() - 1);
				fileName = new String[2];
				fileName[0] = encodeFileName(fn.substring(0, fn.lastIndexOf(".")));
				fileName[1] = fn.substring(fn.lastIndexOf(".") + 1);
                return fileName[0] + "." + fileName[1];
            }
        }
		return null;
	}
	
	/**
	 * 파일명을 MD5 인코딩
	 * @param fileName 파일명
	 * @return 인코딩된 문자열
	 */
	private String encodeFileName(String fileName) {
		StringBuffer newFileName = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(fileName.getBytes("UTF-8"));
			byte[] ba = md.digest();
			for (byte b : ba) {
				newFileName.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return newFileName.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			newFileName = null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
