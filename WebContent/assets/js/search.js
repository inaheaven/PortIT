var count = 0;
	function fnAppendItem(_param) {

		//전달받은 파라미터를 기존의 input태그에 삽입하거나
		//새롭게 input태그를 생성해야한다.

		//새로운 input태그를 삽입하는 식으로 진행하자.

		count++;
		//1부터 시작한다.
		//alert('function check');
		//alert(_param);

		//span tag를 생성한다.
		var newSpan = document.createElement("span");
		//var newSpan_value = document.createElement("span");
		var newInput = document.createElement("input");

		//span tag에 id를 입력한다.
		newSpan.setAttribute("id", "item_" + count);
		newSpan.setAttribute("class", "btn");
		newSpan.className += " tagcondition";
		//newSpan_value.setAttribute("id","item_value" + count);

		//속성셋팅..
		newInput.setAttribute("id", "param" + count);
		newInput.setAttribute("name", "param" + count);
		newInput.setAttribute("value", _param);
		newInput.setAttribute("type", "hidden");

		//span tag안에 자식태그를 삽입한다. <삭제버튼>
		newSpan.innerHTML = window.event.target.value
				+ "<input type='button' class='btn delete' value='x' onclick='fnDeleteItem("
				+ count + ")'/>"
		//newSpan_value.innerHTML = window.event.target.value +"<input type='hidden' name='param"+count+"' id='param"+count+"' value='"+_param+"' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"

		//itemList의 태그위치 저장
		var itemList = document.getElementById("itemList");

		//itemList에  생성된 span태그를 삽입한다.
		//X버튼
		itemList.appendChild(newSpan);

		//태그 value를 input hidden으로 삽입한다.
		itemList.appendChild(newInput);

		//태그 value
		//itemList.appendChild(newSpan_value);	

		//input에 값이 잘 전달되었는지 확인한다.
		//inputTeg 삽입 확인...
		var test = document.getElementById("param1").value;
		var test2 = document.getElementById("param2").value;
		var test3 = document.getElementById("param3").value;

	}

	function fnDeleteItem(idx) {
		var item = document.getElementById("item_" + idx);

		if (item != null) {
			item.parentNode.removeChild(item);
		}

	}

	function fnAppendItem2(_param) {
		count++;

		var newSpan = document.createElement("span");
		//INPUT생성
		var newInput = document.createElement("input");
		
		newSpan.setAttribute("id", "item_" + count);
		newSpan.setAttribute("class", "btn");
		newSpan.className += " tagcondition";
		
		//속성
		newInput.setAttribute("id", "param" + count);
		newInput.setAttribute("name", "param" + count);
		newInput.setAttribute("value", _param);
		newInput.setAttribute("type", "hidden");
		

		newSpan.innerHTML = window.event.target.value
				+ "<input type='button' class='btn delete' value='x' onclick='fnDeleteItem("
				+ count + ")'/>"

		var itemList = document.getElementById("itemList");
		itemList.appendChild(newSpan);
		itemList.appendChild(newInput);
		
		var test = document.getElementById("param4").value;
	}