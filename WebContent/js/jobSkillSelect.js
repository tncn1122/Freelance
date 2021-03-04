$(document).ready(function(){
	var select = new SlimSelect({
		  select: '#slim-select'
		});
		
		
	$("#submitBtn").click(function(){
	var listSelected = select.selected();
	var thisId = $( "#jobID" ).val();

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "ajax/job/" + thisId +".htm",
			data : JSON.stringify(listSelected),
			dataType : 'json',
			accepts: {
                  json: 'application/json',
                  xml: 'application/xml'
            },
			success :function(result) {
		       	console.log(listSelected);
		       },
			error: function(jqXHR, textStatus, errorThrown) { 
				console.log(JSON.stringify(listSelected))
		     	console.log("error:" + errorThrown);
		     	console.log("error:" + textStatus);
			    }
		});
	});
	
}); 

