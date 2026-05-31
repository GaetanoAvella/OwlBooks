let toggleMenu = function() {
	let dropdown = document.getElementById('dropdown-menu');
	if(dropdown.style.display == 'none' || dropdown.style.display == '')
		dropdown.style.display = 'block';
	else if(dropdown.style.display == 'block')
		dropdown.style.display = 'none';	
}