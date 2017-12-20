/**
 * 
 */

$(document).ready(function(){
	$('#newMapping').click(function(){
		openWindow(this.id);
	});
	
	$('#test').click(function(){
		addRows();
	});
});

function openWindow(fileName){
	window.open(fileName, 'New Mapping', 'width=400, height=400');
}

function addRow(rowInfo) {
	var rowJSON = $.parseJSON(rowInfo);
	$('#mappings tr:last').after(function(){
		row = "<tr><td><input type='checkbox'></td>";
		row += "<td>" + rowJSON.csv + "</td>";
		row += "<td>" + rowJSON.log + "</td>";
		row += "<td>" + rowJSON.baseline + "</td>";
		row += "<td>" + rowJSON.pubs + "</td></tr>";
		return row;
	})
}