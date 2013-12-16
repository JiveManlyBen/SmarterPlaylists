jQuery(function($){
	$('#playlistUploadFile').on("change", function() {
		$(".playlistUploadButton").prop("disabled", false);
	});
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