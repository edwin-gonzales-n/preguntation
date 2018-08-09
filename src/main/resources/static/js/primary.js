// var slideIndex = 1;
// showSlides(slideIndex);
//
// // Next/previous controls
// function plusSlides(n) {
//     showSlides(slideIndex += n);
// }
//
// // Thumbnail image controls
// function currentSlide(n) {
//     showSlides(slideIndex = n);
// }
//
// function showSlides(n) {
//     var i;
//     var slides = document.getElementsByClassName("mySlides");
//     var dots = document.getElementsByClassName("dot");
//     if (n > slides.length) {slideIndex = 1}
//     if (n < 1) {slideIndex = slides.length}
//     for (i = 0; i < slides.length; i++) {
//         slides[i].style.display = "none";
//     }
//     for (i = 0; i < dots.length; i++) {
//         dots[i].className = dots[i].className.replace(" active", "");
//     }
//     slides[slideIndex-1].style.display = "block";
//     dots[slideIndex-1].className += " active";
// }
//
// var slideIndex = 0;
// showSlides();
//
// function showSlides() {
//     var i;
//     var slides = document.getElementsByClassName("mySlides");
//     for (i = 0; i < slides.length; i++) {
//         slides[i].style.display = "none";
//     }
//     slideIndex++;
//     if (slideIndex > slides.length) {slideIndex = 1}
//     slides[slideIndex-1].style.display = "block";
//     setTimeout(showSlides, 5000);
// }

$('document').ready(function(){
    $('#searchButton').click(function(){
        var search = $('#search-box-main').val();
        $.post('/posts',{search: search},function(response){
            $('#userSearchResultsTable').html(response);
        });
    })
    $('#search-box-main').keypress(function(e){
        if(e.which == 13){//Enter key pressed
            $('#searchButton').click();//Trigger search button click event
        }
    });
});

function callMe(){
    $.ajax({
        type: "POST",
        url: "/posts",
        data: { methodToInvoke: "sayHello" , data: "Abc" }
    }).done(function( msg ) {
        alert( "Data Saved: " + msg );
    });
}
console.log("here");
let urlParams = new URLSearchParams(window.location.search);
if (urlParams.has("error")) {
    console.log("error");
    document.getElementsByClassName("fade-in").removeProperty("animation");
    // document.getElementsByClassName("dropdown-toggle").enableProperty("");
    document.getElementById("error").innerHTML = "Invalid Login";
}
