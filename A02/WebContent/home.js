var xmlHttpRequest;

function sendPostRequest() {
	var postElement = document.getElementById("text");
	var url = "upload";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkPostRequest;
	xmlHttpRequest.open("POST", url, true);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("text=" + encodeURIComponent(postElement.value));
}

function receivePostResponse() {
	//var response = eval("(" + xmlHttpRequest.responseText + ")");
}

function checkPostRequest() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receivePostResponse();
	}
}

function sendUpdateRequest() {
	var url = "update";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkUpdateRequest;
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send(null);
}

function receiveUpdateResponse() {
	var response = JSON.parse(xmlHttpRequest.responseText);
	var nameElement = document.getElementById("studentId");
	nameElement.innerHTML = response.user.studentId;
	var statementListElement = document.getElementById("statement_list");
	statementListElement.innerHTML = "";
	for (var i = 0; i < response.workList.length; i++) {
		var work = response.workList[i];
		var statementElement = document.createElement("tr");
		statementListElement.appendChild(statementElement);
		var userElement = document.createElement("td");
		statementElement.appendChild(userElement);
		var urlElement = document.createElement("a");
    userElement.appendChild(urlElement);
    urlElement.href = "http://localhost:8080/isp2/work?studentId=" + encodeURIComponent(work.user.studentId) + "&workName=" + encodeURIComponent(work.workName) + "&pass=" + encodeURIComponent(work.pass);
    urlElement.innerHTML = work.workName;
    console.log(xmlHttpRequest.responseText);
	}
}

function checkUpdateRequest() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receiveUpdateResponse();
	}
}

window.addEventListener("load", function() {
  //var postButtonElement = document.getElementById("post_button");
  //postButtonElement.addEventListener("click", sendPostRequest, false);
	sendUpdateRequest();
	setInterval(sendUpdateRequest, 1000);
}, false);
