jQuery(function($){
	$('#playlistUploadFile').on("change", function() {
		$(".playlistUploadButton").prop("disabled", false);
	});
});