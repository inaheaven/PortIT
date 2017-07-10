/**
 * 자주 쓰는건 이렇게 모듈화를 시켜서 사용한다.
 * ajax module
 * write by taesoo 
 */

var httpRequest = null

function sendRequest(method, url,param, callback){
	httpRequest = new XMLHttpRequest();
	
	//현재 넘어온 첫번 째 인수가 값이 있으면 GET으로 넣어준다.
	var httpMethod = method ? method : "GET";
	// 들어온 값이 GET도 아니고 POST도 아니면 그냥 GET을 넣어준다(안전처리)
	if(httpMethod != "GET" && httpMethod != "POST"){
		httpMethod = "GET";
	}
	
	// 파라미터가 값이없으면 널처리하고 있으면 그 값을 받아옴
	var httpParam = (param == null) || (param=="") ? null : param
	// GET방식일때 ?를 통해서 보낸다.
	if(httpMethod== "GET" && httpParam != null){
		url = url + "?" + httpParam;
	}
	
	httpRequest.open(httpMethod, url, true);
	httpRequest.onreadystatechange=callback;
	httpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
	httpRequest.send(httpMethod=="POST"?httpParam:null);
}
