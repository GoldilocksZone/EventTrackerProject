window.addEventListener("load", function(event) {
	console.log("Window Event Listener");
	init();
});

function init() {
	document.addPlant.submit.addEventListener("click", addPlant);
	document.addMoistureReading.submit.addEventListener("click", addMoistureReading);
	document.updatePlant.submit.addEventListener("click", updatePlant);
	document.updateMoistureReading.submit.addEventListener("click", updateMoistureReading);
	document.deletePlant.submit.addEventListener("click", deletePlant);
	document.deleteMoistureReading.submit.addEventListener("click", deleteMoistureReading);
	getPlantStats();
}

function addPlant(event) {
	console.log("IN ADD PLANT");
	event.preventDefault();
	console.log(event);
	let obj = {};
	let formChildren = event.target.parentElement.children;
	for (let child of formChildren) {
		if (child.type === "text") {
			obj[child.name] = child.value;
		}
	}
	console.log(obj);
	callAPI("POST", "plants", document.getElementById("addPlantResults"), obj);
}

function updatePlant(event) {
	console.log("IN UPDATE PLANT");
	event.preventDefault();
	console.log(event);
	let obj = {};
	let formChildren = event.target.parentElement.children;
	for (let child of formChildren) {
		if (child.type === "text" && child.value !== "") {
			obj[child.name] = child.value;
		}
	}
	console.log(obj);
	if (obj.id) {
		callAPI("PUT", "plants/" + obj.id, document.getElementById("updatePlantResults"), obj);
	} else {
		let p = document.createElement("p");
		p.textContent = "ERROR: Must enter plant id";
		clearDiv(document.getElementById("updatePlantResults"));
		document.getElementById("updatePlantResults").appendChild(p);
	}
}

function deletePlant(event) {
	console.log("IN DELETE PLANT");
	event.preventDefault();
	let id = event.target.parentElement.id.value;
	console.log("Plant ID: " + id);
	callAPI("DELETE", "plants/" + id, document.getElementById("deletePlantResults"));
}

function addMoistureReading(event) {
	console.log("IN ADD MOISTURE READING");
	event.preventDefault();
	console.log(event);
	let obj = {};
	let formChildren = event.target.parentElement.children;
	for (let child of formChildren) {
		if (child.type === "text") {
			if (child.name === "plantId") {
				obj.plant = {};
				obj.plant.id = child.value;
			} else {
				obj[child.name] = child.value;
			}
		}
	}
	console.log(obj);
	callAPI("POST", "readings/moisture", document.getElementById("addMoistureReadingResults"), obj);
}

function updateMoistureReading(event) {
	console.log("IN UPDATE MOISTURE READING");
	event.preventDefault();
	console.log(event);
	let obj = {};
	let formChildren = event.target.parentElement.children;
	for (let child of formChildren) {
		if (child.type === "text" && child.value !== "") {
			obj[child.name] = child.value;
		}
	}
	console.log(obj);
	if (obj.id) {
		callAPI("PUT", "readings/moisture/" + obj.id, document.getElementById("updateMoistureReadingResults"), obj);
	} else {
		let p = document.createElement("p");
		p.textContent = "ERROR: Must enter moisture reading id";
		clearDiv(document.getElementById("updateMoistureReadingResults"));
		document.getElementById("updateMoistureReadingResults").appendChild(p);
	}
}

function deleteMoistureReading(event) {
	console.log("IN DELETE SOIL MOISTURE READING");
	event.preventDefault();
	let id = event.target.parentElement.id.value;
	console.log("Moisture Reading ID: " + id);
	callAPI("DELETE", "readings/moisture/" + id, document.getElementById("deleteMoistureReadingResults"));
}

function getPlantStats() {
	console.log("Get Plant Stats");
	getPlantList();
	getMoistureReadings();
//	getLightReadings();
}

function getPlantList() {
	console.log("Get Plant List");
	callAPI("GET", "plants", document.getElementById("plantList"));
}

function getMoistureReadings() {
	console.log("Get Moisture Reading List");
	callAPI("GET", "readings/moisture", document.getElementById("moistureReadingList"));

}

function getLightReadings() {
	console.log("Get Light Reading List");
	callAPI("GET", "readings/light", document.getElementById("lightReadingList"));

}

function callAPI(method, path, target, obj={}) {
	console.log("Get API");
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status >= 200 && xhr.status < 300) {
				let data = xhr.responseText;
				console.log("Raw Data: " + data);
				let dataObj = JSON.parse(data);
				console.log("JSON Parsed Data: " + dataObj[0]);
				console.log("Target: " + target);
				clearDiv(target);
				target.appendChild(objToList(dataObj));
			} else {
				let p = document.createElement("p");
				p.textContent = "Error: HTTP Status " + xhr.status;
				console.log("ERROR");
				target.appendChild(p);
			}
		}
	};
	
	let url = "http://localhost:8083/api/" + path;
	xhr.open(method, url);
	switch(method) {
		case "GET":
		case "DELETE":
			xhr.send();
			break;
		case "POST":
		case "PUT":
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.send(JSON.stringify(obj));
			break;
	}
}

function clearDiv(div) {
	while (div.firstElementChild) {
		div.removeChild(div.firstElementChild);
	}
}

function objToList(obj) {
	let ul = document.createElement("ul");
		Object.keys(obj).forEach(function(property) {
			let li;
			let liChild;
			li = document.createElement("li");
			if (obj[property] != null) {
				if (typeof obj[property] === "object") {
					if (isNaN(property)) {
						let p = document.createElement("p");
						p.textContent = property;
						li.appendChild(p);
					}
					liChild = objToList(obj[property]);
				} else {
					liChild = document.createElement("p");
					liChild.textContent = property + ": " + obj[property];
				}
				console.log(liChild);
				li.appendChild(liChild);
				ul.appendChild(li);
			}
		});
		return ul;
}
