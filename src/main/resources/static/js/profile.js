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

let i = 0;
function getQuestion() {
    i+=1;
        $.get('/triviaByQuestion/'+i+'',function (question) {
            console.log(question);
        }).done(function (question) {
            $('#exampleModal').modal('show');
            const questionSection = $('#questions');
            questionsSection.empty()
                .append((''))
            $('#questions').empty();
            $('#questions').append('<div class="container">');
            $('#questions').append('<h3>'+question.question+'</h3>');
            $('#questions').append('<a onclick="getQuestion()" class="btn btn-outline btn-outline-primary d-inline-block">Next Question</a>');
            $('#questions').append('</div>');

            if(i === numberOfQuestions.length){
                $('#questions').append('<div class="container">');
                $('#questions').append('<h3>FINISH</h3>');
                $('#questions').append('<a onclick="window.location.reload()" class="btn btn-outline btn-outline-primary d-inline-block">PLAY AGAIN</a>');
                $('#questions').append('</div>');
            }
        });
}

