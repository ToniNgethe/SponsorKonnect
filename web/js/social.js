/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var request = new XMLHttpRequest();

function studentReport() {

    var name = document.searchstudent_report.serch_student_field.value;
    var url = "GetStudentReports.jsp?val=" + name;
    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                var val = request.responseText;
                document.getElementById('feedback_area').innerHTML = val;
            }
        }//end of function  
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }

}

$(document).ready(function () {

    $('.modal').modal({
        dismissible: false,
        startingTop: '1%', // Starting top style attribute
        endingTop: '1%' // Ending top style attribute
    });

//    $('select').material_select();
    window.picker = $('.datepicker').pickadate({

        selectYears: 16, // Creates a dropdown of 15 years to control year
        format: 'yyyy-mm-dd'
    });

//submit report
//save social edits
    $('#btn_submit_report').click(function (event) {
        event.preventDefault();
        swal({

            text: "Confirm before submitting",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Confirm'
        }).then(function () {
            //all is good perfom ajax
            $.ajax({
                type: 'POST',
                data: $("#selcted_student_form").serialize(),
                url: "SocialWorkerServlet?action=new",
                success: function (result) {
                    swal(
                            'Server Feedback',
                            result,
                            'info'
                            );
                },
                error: function (result) {
                    swal(
                            'Server Feedback',
                            result,
                            'info'
                            );
                }

            });
        });
    }
    );
});

