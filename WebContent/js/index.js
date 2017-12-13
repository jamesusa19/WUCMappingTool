/**
 * 
 */
$(document).ready(function(){
	$('#newMapping').click(function(){
		openWindow(this.id);
	})
});

function openWindow(fileName){
	window.open(fileName, 'New Mapping', 'width=400, height=400');
}