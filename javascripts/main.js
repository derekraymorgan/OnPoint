$(document).ready(function(){
	$('.homeSlider').slick({
		dots: false,
		slidesToShow: 3,
		slidesToScroll: 1,
		variableWidth: true,
		centerMode: true,
		autoplay: true,
		autoplaySpeed: 2000,
		pauseOnHover: false,
		centerPadding: '100px'
	});
});
