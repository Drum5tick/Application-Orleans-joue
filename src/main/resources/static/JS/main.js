jQuery(document).ready(function ($) {

    /*******************
    	main  navigation
    ********************/
    var contentSections = $('main section');

    //smooth scroll to the selected section
    $('.cd-main-nav a[href^="#"]').on('click', function (event) {
        event.preventDefault();
        var target = $(this.hash),
            topMargin = target.css('marginTop').replace('px', ''),
            headerHeight = $('header').height();
        $('body,html').animate({
            'scrollTop': parseInt(target.offset().top - headerHeight - topMargin)
        }, 200);
    });
    //update selected navigation element
    $(window).on('scroll', function () {
        updateNavigation();
    });

    function updateNavigation() {
        contentSections.each(function () {
            var actual = $(this),
                actualHeight = actual.height(),
                topMargin = actual.css('marginTop').replace('px', ''),
                actualAnchor = $('.cd-main-nav').find('a[href="#' + actual.attr('id') + '"]');

            if ((parseInt(actual.offset().top - $('.cd-main-nav').height() - topMargin) <= $(window).scrollTop()) && (parseInt(actual.offset().top + actualHeight - topMargin) > $(window).scrollTop() + 1)) {
                actualAnchor.addClass('selected');
            } else {
                actualAnchor.removeClass('selected');
            }
        });
    }

    $(document).click(function (event) {
        var clickover = $(event.target);
        var _opened = $(".navbar-collapse").hasClass("show");
        if (_opened === true && !clickover.hasClass("navbar-toggle")) {
            $("button.navbar-toggler").click();
        }
    });
    

/*    $(".toastCustom-trigger").click(function(e){ */

    $(".close-toastCustom").click(function(e){
      e.preventDefault();
      datatoastCustom = $(this).parent().attr("id");
      $("#" + datatoastCustom).fadeOut(400);
    });
});

window.onload = function(e) {
	e.preventDefault();
	
	switch (toastMsg) {
		
	case 'ok':
		if (!$("#toastAlertOk").is(":visible")) {
			$("#toastAlertOk").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;		
	case 'ko':
		if (!$(".toastKo").is(":visible")) {
			$(".toastKo").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'canceled':
		if (!$(".toastKo").is(":visible")) {
			$(".toastKo p").text('Votre demande d\'annulation a bien été prise en compte.');
			$(".toastKo").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'update':
		if (!$(".toastOk").is(":visible")) {
			$(".toastOk p").text('Votre mise à jour a bien été prise en compte.');
			$(".toastOk").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'alreadyReg':
		if (!$(".toastKo").is(":visible")) {
			$(".toastKo p").text('Vous êtes déjà inscrit.');
			$(".toastKo").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'loginError':
		if (!$(".toastKo").is(":visible")) {
			$(".toastKo p").text('Mauvais nom d\'utilisateur ou mot de passe.');
			$(".toastKo").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'emailError':
		if (!$(".toastKo").is(":visible")) {
			$(".toastKo p").text('Cette adresse email éxiste déjà.');
			$(".toastKo").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'userRegSuccessful':
		if (!$(".toastOk").is(":visible")) {
			$(".toastOk p").text('Nouvel utilisateur enregistré.');
			$(".toastOk").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'userInfosNotCompleted':
		if (!$(".toastKo").is(":visible")) {
			$(".toastKo p").text('Renseignez vos informations de compte pour accéder aux évènements.');
			$(".toastKo").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'accountModifSaved':
		if (!$(".toastOk").is(":visible")) {
			$(".toastOk p").text('Modifications enregistrées.');
			$(".toastOk").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	case 'delete':
		if (!$(".toastOk").is(":visible")) {
			$(".toastOk p").text('Votre inscription à bien étée annulée');
			$(".toastOk").fadeIn(400).delay(3000).fadeOut(400);
		}
		break;
	}
};

