$(document).ready(function()
{
	$('.homeSlider').slick(
	{
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



	/*
	var heights = $(".well2").map(function() {
			return $(this).height();
		}).get(),

		maxHeight = Math.max.apply(null, heights);

	$(".well2").height(maxHeight);
	*/

});
/*
$(window).bind("load", function()
{
	var heights = $(".well1").map(function()
		{
			console.log($(this).height())
			return $(this).height();
		}).get(),

		maxHeight = Math.max.apply(null, heights);

	console.log(maxHeight);

	$(".well1").height(maxHeight);
});

$(window).resize(function()
{
	var heights = $(".well1").map(function()
		{
			console.log($(this).height())
			return $(this).height();
		}).get(),

		maxHeight = Math.max.apply(null, heights);

	console.log(maxHeight);

	$(".well1").height(maxHeight);
});
*/