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

function addRows() {
	var json = [
		{
			"csv": "out.csv",
			"folder": "folder",
			"baseline": "baseline"
		},
		{
			"csv": "out1.csv",
			"folder": "folder1",
			"baseline": "baseline1"
		}
	];
	$('#mappings tr:last').after(function(){
		let rows = "";
		for(index in json){
			rows += "<tr><td><input type='checkbox'></td>";
			rows += "<td>" + json[index].csv + "</td>";
			rows += "<td>" + json[index].folder + "</td>";
			rows += "<td>" + json[index].baseline + "</td></tr>";
		}
		return rows;
	})
}