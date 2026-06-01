let toggleImage = function() {
	let checkbox = document.getElementById('delete_image');
	let imageBlock = document.getElementById('image');
	
	if(checkbox.checked){
		imageBlock.disabled = true;
		imageBlock.value = "";
		imageBlock.style.opacity = '0.4';
		imageBlock.style.cursor = "not-allowed";
	} else {
		imageBlock.disabled = false;
		imageBlock.style.opacity = "1";
		imageBlock.style.cursor = "pointer";
	}
}