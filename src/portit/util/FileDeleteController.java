package portit.util;

import java.io.File;
import java.util.List;

public class FileDeleteController {

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
	
}
