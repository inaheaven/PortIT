/**
 * 클릭시 페이지 이동을 위한 스크립트
 */
	
	function pf(pf_id){
		document.pf_info.pf_id.value = pf_id;
		document.pf_info.submit();
	}
	function tag(tag_name){
		document.tag_info.tag_name.value = tag_name;		
		document.tag_info.submit();	
	}
	function prof(prof_id){
		document.prof_info.prof_id.value = prof_id;
		document.prof_info.submit();		
	}
	function proj(proj_id){
		document.proj_info.proj_id.value = proj_id;
		document.proj_info.submit();
	}