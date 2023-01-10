var xmlHttpRequest;

function sendSendRequest() {
	var commentElement = document.getElementById("comment");
	var url = "send";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkSendRequest;
	xmlHttpRequest.open("POST", url, true);
	xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xmlHttpRequest.send("comment=" + encodeURIComponent(commentElement.value));
}

function receiveSendResponse() {
	//var response = eval("(" + xmlHttpRequest.responseText + ")");
}

function checkSendRequest() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		receiveSendResponse();
	}
}

function sendUpdateRequest() {
	var url = "commentUpdate";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkUpdateRequest;
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send(null);
}

function receiveUpdateResponse() {
	var response = JSON.parse(xmlHttpRequest.responseText);
	var workElement = document.getElementById("work");
	workElement.innerHTML = response.pass;
	var nameElement = document.getElementById("studentId");
	nameElement.innerHTML = response.user.studentId;
	var statementListElement = document.getElementById("statement_list");
	statementListElement.innerHTML = "";
	console.log(xmlHttpRequest.responseText);
	for (var i = 0; i < response.comments.length; i++) {
		var comment = response.comments[i];
		var statementElement = document.createElement("tr");
		statementListElement.appendChild(statementElement);
		var userElement = document.createElement("td");
		statementElement.appendChild(userElement);
		userElement.innerHTML = comment.user.studentId + ":";
		var commentElement = document.createElement("td");
		statementElement.appendChild(commentElement);
		commentElement.innerHTML = comment.comment;
	}
}

function checkUpdateRequest() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		console.log(xmlHttpRequest.responseText);
		receiveUpdateResponse();
	}
}

function sendWorkRequest() {
	var url = "workUpdate";
	xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = checkWorkRequest;
	xmlHttpRequest.open("GET", url, true);
	xmlHttpRequest.send(null);
}

function checkWorkRequest() {
	if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
		workIn();
	}
}

function workIn() {
	var response = JSON.parse(xmlHttpRequest.responseText);
	var nameElement = document.getElementById("work");
	nameElement.innerHTML = response.pass;
}

window.addEventListener("load", function() {
	//sendWorkRequest();
	//setInterval(sendWorkRequest, 1000);
	var sendButtonElement = document.getElementById("send_button");
	sendButtonElement.addEventListener("click", sendSendRequest, false);
	sendUpdateRequest();
	setInterval(sendUpdateRequest, 1000);
}, false);
