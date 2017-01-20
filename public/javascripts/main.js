jQuery(function($) {
	enableUploadButton();
	$('.file-input').on("change", function() {
		enableUploadButton();
	});
	function enableUploadButton() {
		var hasInvalidElement = false;
		$('input,textarea,select').filter('[required]:visible').each(function () {
			if ($(this).val() === '') {
				hasInvalidElement = true;
			}
		});
		if (hasInvalidElement) {
			$(".playlistUploadButton").prop("disabled", true);
		}
		else {
			$(".playlistUploadButton").prop("disabled", false);
		}
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
    if (navigator.platform.toUpperCase().indexOf('MAC')>=0) {
    	$('.helper-image').attr('href', $('.helper-image').attr('href').replace("\.windows\.", ".mac.", "gi") );
    }
    $('.helper-image').magnificPopup({type:'image'});
});