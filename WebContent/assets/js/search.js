var count = 0;
function fnAppendItem(){
	count++;
	
	var newSpan = document.createElement("span"); 
	//<span id="item_0" value="">
	newSpan.setAttribute("id","item_" + count);
	newSpan.setAttribute("name","item_" + count);
	newSpan.setAttribute("value","조병규");
	
	newSpan.innerHTML = window.event.target.value +"<input type='button' value='x' id='xbutton' name='xbutton' onclick='fnDeleteItem("+ count +")'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	newSpan.innerHTML = 
window.event.target.value +"<input type='hidden' value='조병규' id='item_"+count+" name=item_"+count+"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	
	var itemList = document.getElementById("itemList");
	itemList.appendChild(newSpan);		
	
	
	
}

function fnDeleteItem(idx){
	var item = document.getElementById("item_" + idx);
	
	if(item != null){
		item.parentNode.removeChild(item);
	}
	
}
function fnAppendItem2(){
	count++;
	
	var newSpan = document.createElement("span"); 
	
	newSpan.setAttribute("id","item_" + count);
	var choice = document.getElementById("language").value;
	
	newSpan.innerHTML = window.event.target.value +"<input type='button' value='x' onclick='fnDeleteItem("+ count +")'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
	
	var itemList = document.getElementById("itemList");
	itemList.appendChild(newSpan);
}