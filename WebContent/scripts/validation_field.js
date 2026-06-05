const nameAndSurnamePattern = /^[A-z]+$/;
const errorNameMsg = "Il nome deve contenere solamente lettere!";
const errorSurnameMsg = "Il cognome deve contenere solamente lettere!";
const addressPattern = /^\w+(\s\w+)+$/;
const errorAddressMsg = "Indirizzo non valido!";

function validateFormElem(formElem, pattern, span, msg) {
	if(pattern.test(formElem.value)) {
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.innerHTML = msg;
	span.style.color = "red";
	return false;
}

function validate() {
	let valid = true;
	let form = document.getElementById("reg-form");
	
	let spanName = document.getElementById("errorName");
	if(!validateFormElem(form.name, nameAndSurnamePattern, spanName, errorNameMsg)) {
		valid = false;
	}
	
	let spanSurname = document.getElementById("errorSurname");
	if(!validateFormElem(form.surname, nameAndSurnamePattern, spanSurname, errorSurnameMsg)) {
		valid = false;
	}
	
	let spanAddress = document.getElementById("errorAddress");
		if(!validateFormElem(form.address, addressPattern, spanAddress, errorAddressMsg)) {
		valid = false;
	}
	
	return valid;
}