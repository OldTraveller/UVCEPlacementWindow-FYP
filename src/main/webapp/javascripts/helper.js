/**
 * THIS FILE IS MEANT FOR THE AJAX REQUEST THAT NEEDS TO BE SENT FROM THE FRONT END 
 * FOR THE DOCTOR DETAILS AS WELL AS THE PATIENT DETAILS FROM THE BACKEND AND GETTING
 * THEM IN SOME PROCESSABLE FORMAT.
 */

const sendRequestToImportantTopics = () => {
	console.log("COMING HERE FOR IMPORTANT TOPICS !!"); 
	const id = document.getElementById('which').value; 
	if (id === "") {
		alert("The Unique Key Field cannot be empty !!"); 
	} else {
		const jsObject = {
			id: id
		};
			$.ajax({
				url: "fetch_subject_related_stuff.jsp",
				type: 'POST',
				data: jsObject,
				success: function(data) {
					const placeToPopulate = document.getElementById('populateHere'); 
					placeToPopulate.innerHTML = data; 
				},
				error: function() {
					return "Soemthing went wrong!!!"; 
				}
			});	
		}
}

const sendRequest = () => {
	console.log("COMING HERE !!"); 
	const id = document.getElementById('id').value; 
	if (id === "") {
		alert("The Unique Key Field cannot be empty !!"); 
	} else {
		const jsObject = {
			id: id
		};
			$.ajax({
				url: "get_sha_hash.jsp",
				type: 'POST',
				data: jsObject,
				success: function(data) {
					const placeToPopulate = document.getElementById('populateHere'); 
					const whatType = document.getElementById('whatType'); 
					whatType.innerHTML = "<hr><h3><strong>Your SHA-1 Hash is</strong></h3>";
					placeToPopulate.innerHTML = data; 
				},
				error: function() {
					return "Something went wrong!!!"; 
				}
			});	
		}
}

const clearStuff = () => {
	document.getElementById('populateHere').innerHTML = "";
}