package portit.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import portit.model.dto.Media;
import portit.model.dto.Profile;
import portit.model.dto.Tag;

public class FileUpload {
	
	/**
	 * 업로드할 파일이 저장될 디렉토리 이름(/{UPLOAD_DIR})
	 */
	private static final String UPLOAD_DIR = "upload";
	
	/**
	 * 파일 업로드
	 * @param req
	 * @param resp
	 * @return 파일명(경로 포함) 목록
	 * @throws ServletException
	 * @throws IOException
	 */
	public Map<String, Object> fileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("\n==== "+this.getClass().getSimpleName()+" ====\n");
		
		// 파일 저장 디렉토리 설정
		String saveDir = req.getServletContext().getRealPath("") + UPLOAD_DIR;
		System.out.println("saveDir : "+saveDir);
		
		// 저장할 디렉토리가 없으면 생성
		File fileSaveDir = new File(saveDir);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		
		// Commons FileUpload 설정
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024*3); // 3MB까지 메모리에서 처리 
		factory.setRepository(new File(saveDir));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024*1024*20); // 최대 20MB
		upload.setHeaderEncoding("UTF-8");

		if (!ServletFileUpload.isMultipartContent(req)) {
			try {
				throw new Exception("요청이 multipart/form-data로 인코딩되지 않았습니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		List<Media> fileList = new ArrayList<Media>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Tag> tagLang = new ArrayList<Tag>();
		List<Tag> tagTool = new ArrayList<Tag>();
		List<Tag> tagField = new ArrayList<Tag>();
		List<Profile> coworker = new ArrayList<Profile>();
		try {
			List<FileItem> items = upload.parseRequest(req);
			String key = null;
			String value = null;
			for(FileItem item : items) {
				if (item.isFormField()) {
					// isFormField()의 반환값이 true이면 일반 파라미터로 처리
					key = item.getFieldName();
					value = item.getString("UTF-8");
					if (value != null || !"".equals(value)) {
						if (key.matches(".*[Ll]ang.*")) {
							tagLang.add(new Tag().setTag_type("language").setTag_name(value));
						} else if (key.matches(".*[Tt]ool.*")) {
							tagTool.add(new Tag().setTag_type("tool").setTag_name(value));
						} else if (key.matches(".*[Ff]ield.*")) {
							tagField.add(new Tag().setTag_type("field").setTag_name(value));
						} else if (key.matches(".*[Cc]oworker.*")) {
							coworker.add(new Profile().setProf_name(value));
						}
					}
				} else {
					// isFormField()의 반환값이 false이면 파일로 처리
					if (item != null && item.getSize() > 0) {
						if (item.getContentType().startsWith("image/")) {
							System.out.println(saveDir + getFileName(item));
							item.write(new File(saveDir + getFileName(item)));
							fileList.add(new Media().setMl_path(UPLOAD_DIR + "/" + getFileName(item)));
						} else {
							throw new Exception("이미지파일만 업로드할 수 있습니다.");
						}
					}
				}
				System.out.println(key + " : " + value);
				map.put(key, value);
			}
			map.put("tagLang", tagLang);
			map.put("tagTool", tagTool);
			map.put("tagField", tagField);
			map.put("fileList",  fileList);
			return map;
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
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