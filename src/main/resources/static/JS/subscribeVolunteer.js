$(document).ready(function(){

	$(":checkbox#checkTask").click(function(){
		
		if( $(':checkbox.checkClassTask').prop('checked') ){

			$(':checkbox.checkClassTask').prop('checked', false);
		} else {
			$(':checkbox.checkClassTask').prop('checked', true);
		}

})

$(":checkbox#checkTimeSlot").click(function(){
	
	if( $(':checkbox.checkClassTimeSlot').prop('checked') ){

		$(':checkbox.checkClassTimeSlot').prop('checked', false);
	} else {
		$(':checkbox.checkClassTimeSlot').prop('checked', true);
	}

})});
