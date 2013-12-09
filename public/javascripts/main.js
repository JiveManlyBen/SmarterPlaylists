jQuery(function($){
	$(".playlistUploadButton").click(function() {
		if ($("#playlistUploadFile").val().length > 1) {
			$("#playlistUploadFile").removeClass("error");
			$(this).prop("disabled", true);
		}
		else {
			$("#playlistUploadFile").addClass("error");
		}
	});
});