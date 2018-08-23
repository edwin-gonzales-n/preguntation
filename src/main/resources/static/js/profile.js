let numberOfQuestions = [];

$('#edit-profile').click( function() {
    $("#edit-form").toggleClass("hidden");
} );

$.get("/triviaAllQuestions",function(questions) {
    console.log('Beginning console log - success', questions);
    console.log("Length of questions is: "+questions.length);
    $.each(questions, function(i, question){
        numberOfQuestions.push(question.id);
    });
}).done(function(questions){
    console.log(" This is the done message -- success", questions);
    console.log(numberOfQuestions);
});

function getQuestion() {
    for(var i = 1; i < numberOfQuestions.length+1; i++){
        $.get('/triviaByQuestion/'+i+'',function (question) {
            console.log(question);
        }).done(function (question) {
            $('#questions').append('<div class="container">');
            $('#questions').append('<h3>'+question.question+'</h3>');
            $('#questions').append('<a onclick="getQuestion()" class="btn btn-outline btn-outline-primary d-inline-block">Next Question</a>');
            $('#questions').append('</div>');
        });
    }
}