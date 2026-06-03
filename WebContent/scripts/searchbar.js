let searchBar = function(elem) {
	const query = elem.value.trim();
	
	if(query.length == 0) {
		let searchResultDiv = document.getElementById("search-result");
		if(searchResultDiv) {
	        searchResultDiv.innerHTML = "";
	        searchResultDiv.style.display = "none";
	    }
		return;
	}
	
	let params = "searchQuery=" + encodeURIComponent(query);
	loadAJAXDoc(contextPath + "/SearchBarServlet", "GET", params, handleSearch);
}

function handleSearch(request) {
	let response = JSON.parse(request.responseText);
	let searchResultDiv = document.getElementById("search-result");
	
	searchResultDiv.innerHTML = "";
	
	if (response.length == 0) {
	    searchResultDiv.innerHTML = "<div class='no-results'>Nessun libro trovato</div>";
	    searchResultDiv.style.display = "block";
	    return;
	}
	
	for(let i=0; i<response.length; i++) {
		let book = response[i];
		let a = document.createElement("a");
		a.href = contextPath + "/BookServlet?code=" + book.code;
		a.className = "search-result-item";
		a.innerHTML = "<strong>" + book.name + "</strong><br><small>di " + book.author + "</small>";	
		searchResultDiv.appendChild(a);
	}
	
	searchResultDiv.style.display = "block";
}

function loadAJAXDoc(url, method, params, cFunction) {
	let request = createXMLHttpRequest();
	if(request) {
		request.onreadystatechange = function() {
			if(this.readyState == 4) {
				if(this.status == 200){ 
					cFunction(this);
				} else {
					if(this.status == 0) {
						alert("Richiesta abortita");
					} else {
						alert("Problemi con la richiesta: " + this.statusText);
					}
					return null;
				}
			}
		};
		
		setTimeout(function() {
			if(request.readyState < 4)
				request.abort();
		}, 15000);
		
		if(method.toLowerCase() == 'get'){
			if(params) {
				request.open("GET", url + "?" + params, true);
			} else {
				request.open("GET", url, true);
			}
			request.send(null);
		} else {
			if(params) {
				request.open("POST", url, true);
				request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
				request.send(params);
			}
		}
		
	} 
}

function createXMLHttpRequest() {
	let request;
	try {
		request = new XMLHttpRequest();
	} catch(e) {
		try {
			request = new ActiveXObject("Msxm12.XMLHTTP");
		} catch(e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e) {
				alert("Il browser non supporta AJAX!");
				return null;
			}	
		}	
	}
	
	return request;
}