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
var correct_answer;
function getQuestion() {
    i+=1;
        $.get('/triviaByQuestion/'+i+'',function (question) {
            correct_answer=question.correct_answer;
            console.log(question);
            // $('#exampleModal').modal('show');
            $('#questions').empty();
            $('#questions').append('<h3>'+question.question+'</h3>');

            if(i === numberOfQuestions.length){
                $('#questions').append('<div class="container">');
                $('#questions').append('<h3>FINISH</h3>');
                $('#questions').append('<a onclick="window.location.reload()" class="btn btn-outline btn-outline-primary d-inline-block">PLAY AGAIN</a>');
                $('#questions').append('</div>');
            }

            $.get('/answersByQuestionID/'+i+'', function(answers){
                console.log(answers);
                $('#answers').empty();
                $.each(answers,function (i,answer) {
                    $('#answers').append('<label onclick="checkAnswer(correct_answer,'+answer.id+')" class="btn btn-outline-primary d-inline-block container">\n' +
                        '<input type="radio" name="id" value="'+answer.id+'" autocomplete="off"/>' +answer.answer+'\n' +
                        '</label>')
                })
            })
        });
}

function checkAnswer(correct_answer,user_answer){
    if(correct_answer === user_answer){
        console.log("That was the correct answer!");
        getQuestion();
    } else{
        console.log("That was the wrong answer!");
        getQuestion();
    }
}

