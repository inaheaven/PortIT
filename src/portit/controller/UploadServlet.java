package portit.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * 업로드 서블릿
 *
 */
@SuppressWarnings("serial")
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	
	/**
	 * 업로드할 파일이 저장될 디렉토리 이름(/{UPLOAD_DIR})
	 */
	private static final String UPLOAD_DIR = "upload";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET 요청일 경우 등록 페이지로 포워딩
		String articleType = req.getParameter("post");
		String actionType = req.getParameter("type");
		RequestDispatcher rd = null;
		if ("profile".equals(articleType)) {
			rd = req.getRequestDispatcher("");
			rd.forward(req, resp);
		} else if ("portfolio".equals(articleType)) {
			rd = req.getRequestDispatcher("/page?page=myPfRegister");
			rd.forward(req, resp);
		} else if ("project".equals(articleType)) {
			rd = req.getRequestDispatcher("");
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST 요청일 때 등록 처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		if (!ServletFileUpload.isMultipartContent(req)) {
			try {
				throw new Exception("요청이 multipart/form-data로 인코딩되지 않았습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		List<String> fileNames = fileUpload(req, resp);
		req.setAttribute("fileNames", fileNames);
		
		String[] viewUrl = null;
		
		String articleType = req.getParameter("type");
		if ("profile".equals(articleType)) {
			/*Controller profileController = ControllerFactory.getInstance().createController("profile");
			viewUrl = profileController.execute(req, resp);*/
		} else if ("portfolio".equals(articleType)) {
			Controller portfolioAddController = ControllerFactory.getInstance().createController("portfolioAdd");
			viewUrl = portfolioAddController.execute(req, resp);
		} else if ("project".equals(articleType)) {
			/*Controller projectController = ControllerFactory.getInstance().createController("project");
			viewUrl = projectController.execute(req, resp);*/
		}
		
		RequestDispatcher rd = null;
		if (viewUrl[0].startsWith("inc")) {
			rd = req.getRequestDispatcher(viewUrl[1]);
			rd.include(req, resp);
		} else if (viewUrl[0].startsWith("fwd")) {
			rd = req.getRequestDispatcher(viewUrl[1]);
			rd.forward(req, resp);
		} else {
			resp.sendRedirect(viewUrl[1]);
		}
	}
	
	/**
	 * 파일 업로드
	 * @param req
	 * @param resp
	 * @return 파일명(경로 포함) 목록
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<String> fileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<String> fileNames = new ArrayList<String>();
		
		String saveDir = req.getServletContext().getRealPath("") + UPLOAD_DIR;
		
		// 저장할 디렉토리가 없으면 생성
		File fileSaveDir = new File(saveDir);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024);
		factory.setRepository(new File(saveDir));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024*1024*20);
		upload.setHeaderEncoding("UTF-8");

		Map<String, String> map = new HashMap<String, String>();
		try {
			List<FileItem> items = upload.parseRequest(req);			
			for(FileItem item : items) {
				if (item.isFormField()) {
					// isFormField()의 반환값이 true이면 일반 파라미터로 처리
					map.put(
							new String(item.getFieldName().getBytes("ISO-8859-1"), "UTF-8")
							, new String(item.getString().getBytes("ISO-8859-1"), "UTF-8")
							);
					req.setAttribute("formData", map);
				} else {
					// isFormField()의 반환값이 false이면 파일로 처리
					if (item != null && item.getSize() > 0) {
						if (item.getContentType().startsWith("image/")) {
							String path = saveDir + File.separator + getFileName(item);
							item.write(new File(path));
							fileNames.add(path);
						} else {
							throw new Exception("이미지파일만 업로드할 수 있습니다.");
						}
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileNames;
		
	}
	
	/**
	 * 파일 삭제
	 * @param paths 파일 경로 목록
	 * @return 삭제한 파일 갯수
	 */
	public int fileDelete(List<String> paths) {
		int idx;
		for (idx = 0; idx < paths.size(); idx++) {
			File file = new File(paths.get(idx));
			if (file.exists()) {
				file.delete();
			}
		}
		return idx;
	}
	
	/**
	 * 파일명을 인코딩
	 * @param item
	 * @return MD5 인코딩된 파일명
	 */
	private String getFileName(FileItem item) {
		String[] fileName = null;
		// content-disposition 헤더 읽어오기
		String fn = item.getName().trim();
		fileName = new String[2];
		fileName[0] = encodeFileName(fn.substring(0, fn.lastIndexOf(".")));
		fileName[1] = fn.substring(fn.lastIndexOf(".") + 1);
		return fileName[0] + "." + fileName[1];
	}
	
	/**
	 * 문자열을 MD5 인코딩
	 * @param str 문자열
	 * @return 인코딩된 문자열
	 */
	private String encodeFileName(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
			byte[] ba = md.digest();
			for (byte b : ba) {
				sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			sb = null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
