$( document ).ready(function() {
    var JSON = $('#json').val();
    if(JSON){
    	window.opener.addRow(JSON);
    	window.close();
    }
});