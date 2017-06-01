/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var request = new XMLHttpRequest();

function theFunction() {

    $.get('PopulateSocialWorker?action=soc', function (responseJson) {
        $("#editSocial").hide();
        if (responseJson != null) {
            $("#socialFromDb").find("tr:gt(0)").remove();

            var table1 = $("#socialFromDb");

            $.each(responseJson, function (key, value) {

                var rowNew = $('<tr id="row_1" onclick="javascript:showSoccEdit(this);"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['name']);
                rowNew.children().eq(2).text(value['number']);
                rowNew.children().eq(3).text(value['email']);
                rowNew.children().eq(4).text(value['location']);
                rowNew.children().eq(5).text(value['password']);
                rowNew.children().eq(6).html('<a class="btn-floating btn-large waves-effect waves-light green" onclick="return enableSocEdit(this);"><i class="material-icons">edit</i></a>');
                rowNew.children().eq(7).html('<a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></a>');
                rowNew.appendTo(table1);
            });
        }
    });

    return true;
}

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
        endingTop: '-1%' // Ending top style attribute
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
        $.ajax({
            type: 'POST',
            data: $("#selcted_student_form").serialize(),
            url: "SocialWorkerServlet?action=new",
            success: function (result) {

                //$('#modal1').modal('close');
                Materialize.toast(result, 4000);

            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });
});

