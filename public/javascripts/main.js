jQuery(function($){
	enableUploadButton();
	$('#playlistUploadFile').on("change", function() {
		enableUploadButton();
	});
	function enableUploadButton() {
		var playlistUploadFile = $('#playlistUploadFile').val();
		if (playlistUploadFile != null && playlistUploadFile !== "")
			$(".playlistUploadButton").prop("disabled", false);
	}
    $('#polyglotLanguageSwitcher').polyglotLanguageSwitcher({
		effect: 'fade',
        testMode: true,
        onChange: function(evt){
            $.ajax({
            	url: "/lang",
            	type: "POST",
            	data: {name: evt.selectedItem},
                success: function (data) {
                	location.reload(false);
                }
            });
        }
	});
    $("a.polyglot").click(function() {
    	  return false;
    });
});