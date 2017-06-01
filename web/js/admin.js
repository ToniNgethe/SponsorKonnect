/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//get all fee stracture
function getFees() {
    $.get('SchoolServlet?action=feeStracture', function (responseJson) {
        if (responseJson != null) {
            $("#recordedFeeStracture").find("tr:gt(0)").remove();

            var skulTable = $("#recordedFeeStracture");
            $.each(responseJson, function (key, value) {

                var rowNew = $('<tr id="row_1" onclick="javascript:getFeesDetails(this);"><td></td><td></td><td><td></td></td><td></td><td></td><td></td><td></td></tr>');
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['name']);
                rowNew.children().eq(2).text(value['first']);
                rowNew.children().eq(3).text(value['second']);
                rowNew.children().eq(4).text(value['third']);
                rowNew.children().eq(5).text(value['total']);
                rowNew.children().eq(6).html('<a href="#editSchoolFees" class="btn-floating btn-large waves-effect waves-light blue"><i class="material-icons">mode_edit</i></a>');
                rowNew.children().eq(7).html('<a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete_forever</i></a>');
                rowNew.appendTo(skulTable);

            });
        }
    });

    return true;
}

function getFeesDetails(row) {
    var x = row.cells;
    document.getElementById('selected_school_id').value = x[0].innerHTML;
    document.getElementById('selected_first_term_fees').value = x[2].innerHTML;
    document.getElementById('selected_second_term_fees').value = x[3].innerHTML;
    document.getElementById('selected_third_term_fees').value = x[4].innerHTML;
}

//get list of all recorded schools
function getAllSkuls() {

    $.get('SchoolServlet?action=all', function (responseJson) {
        if (responseJson != null) {
            $("#recordedSkulsTable").find("tr:gt(0)").remove();

            var skulTable = $("#recordedSkulsTable");
            $.each(responseJson, function (key, value) {

                var rowNew = $('<tr id="row_1" onclick="javascript:getStudentDet(this);"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['name']);
                rowNew.children().eq(2).text(value['mode']);
                rowNew.children().eq(3).text(value['means']);
                rowNew.children().eq(4).text(value['date']);
                rowNew.children().eq(5).html('<a class="btn-floating btn-large waves-effect waves-light blue"><i class="material-icons">mode_edit</i></a>');
                rowNew.children().eq(6).html('<a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete_forever</i></a>');
                rowNew.appendTo(skulTable);

            });
        }
    });

    return true;
}

//get all students

function getAllStudents() {

    $('#student_card').hide();
    $.get('GetStudentsServ?action=all', function (responseJson) {

        if (responseJson != null) {
            $("#studentsTable").find("tr:gt(0)").remove();

            var table1 = $("#studentsTable");

            $.each(responseJson, function (key, value) {

                var rowNew = $('<tr id="row_1" onclick="javascript:getStudentDets(this);"><td></td><td></td><td></td></tr>');
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['email']);
                rowNew.children().eq(2).html('<a class="btn-floating btn-large waves-effect waves-light blue"><i class="material-icons">person_pin</i></a>');
                rowNew.appendTo(table1);
            });
        }
    });

    return true;
}
function getStudentDets(row) {

    var x = row.cells;
    $('#sponsor_to_assign').val("");
    $('#social_to_assign').val("");
    $('#acc_to_assign').val("");

    $('#ass_acc_assigned').val("");
    $('#ass_social_assigned').val("");
    $('#ass_sponsor_assigned').val("");

    $('#sponsor_to_assign_bg').hide();
    $('#social_to_assign_bg').hide();
    $('#acc_to_assign_bg').hide();

//    $('#accountant_to_assign_form').hide();
//    $('#social_to_assign_form').hide
//    $('#sponsor_to_assign_form').hide();


    var id = x[0].innerHTML;

    //copy stud id to hidden field stud id
    document.getElementById('ass_student_id').value = x[0].innerHTML;

    $.get('GetStudentsServ?action=' + id, function (responseJson) {

        if (responseJson !== null) {
            $('#student_status').hide();
            $('#student_card').show();
            $("#studentPersonalTable").find("tr:gt(0)").remove();

            var table1 = $("#studentPersonalTable");

            $.each(responseJson, function (key, value) {

                var rowNew = $('<tr id="row_1"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
                rowNew.children().eq(0).text(value['s_name']);
                rowNew.children().eq(1).text(value['f_name']);
                rowNew.children().eq(2).text(value['l_name']);
                rowNew.children().eq(3).text(value['gender']);
                rowNew.children().eq(4).text(value['number']);
                rowNew.children().eq(5).text(value['location']);
                rowNew.children().eq(6).text(value['status']);
                rowNew.children().eq(7).text(value['age']);

                rowNew.appendTo(table1);
            });
        }
    });

}

var request = new XMLHttpRequest();

//load acc to assign
function searchAccountant() {
    $('#acc_to_assign_bg').show();
    var name = document.accountant_to_assign_form.acc_to_assign.value;
    var url = "getAccountants.jsp?val=" + name;

    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                var val = request.responseText;
                document.getElementById('acc_to_assign_bg').innerHTML = val;

            }
        }//end of function  
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }

    document.getElementById('ass_acc_assigned').value = name;
}

//load social worker to assign
function searchSocialWorker() {
    $('#social_to_assign_bg').show();
    var name = document.social_to_assign_form.social_to_assign.value;
    var url = "getSocialDetails.jsp?val=" + name;

    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                var val = request.responseText;
                document.getElementById('social_to_assign_bg').innerHTML = val;
            }
        }//end of function  
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }

    document.getElementById('ass_social_assigned').value = name;

}

function searchInfo() {
    var name = document.vinform.name.value;
    var url = "getData.jsp?val=" + name;

    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                var val = request.responseText;
                document.getElementById('mylocation').innerHTML = val;
            }
        }//end of function  
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }


}

//load spnsor information at student section
function sponsorToAssign() {
    $('#sponsor_to_assign_bg').show();
    var name = document.sponsor_to_assign_form.sponsor_to_assign.value;
    var url = "getSponsorId.jsp?val=" + name;

    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                var val = request.responseText;
                document.getElementById('sponsor_to_assign_bg').innerHTML = val;
            }
        }//end of function  
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }

    document.getElementById('ass_sponsor_assigned').value = name;
}

function searchSponsor() {
    var name = document.sponsorcommit_payments.sponsor_comm_number.value;
    var url = "getSponsorId.jsp?val=" + name;

    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                var val = request.responseText;
                document.getElementById('sponsor_commit_bg').innerHTML = val;
            }
        }//end of function  
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}

function searchSponsorForPay() {
    var name = document.make_sponsor_payments.make_sponsor_payments_id.value;
    var url = "getSponsorDeta.jsp?val=" + name;

    try {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                var val = request.responseText;
                document.getElementById('make_payments_bg').innerHTML = val;
            }
        }//end of function  
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}

$(document).ready(function () {

    var last_id = localStorage.getItem('tab_id');
    if (last_id) {
        $('#nac a').removeClass('active');
        // $('.tab-content').removeClass('active');
        $("#nac a").eq(last_id.match(/\d+/)[0] - 1).addClass('active');
        $("#" + last_id).addClass('active');
    }
    $('#nac a').click(function () {
        var tab_id = $(this).attr('data-tab');
        $('#nac a').removeClass('active');
        // $('.tab-content').removeClass('current');

        $(this).addClass('active');
        $("#" + tab_id).addClass('active');
        localStorage.setItem('tab_id', tab_id);
    });

    $("#school_lists").on('change', function () {



    });

    //add school fees stracture
    $('#btn_add_school_fee').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            data: $("#feeStractureForm").serialize(),
            url: "SchoolServlet?action=fees",
            success: function (result) {
                Materialize.toast(result, 3000, 'round');

            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });

    //register new school
    $('#btn_add_school').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            data: $("#newSchoolData").serialize(),
            url: "SchoolServlet?action=add",
            success: function (result) {
                Materialize.toast(result, 3000, 'round');

            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });

    //assign sponsor
    $('#btn_assign_student').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            data: $("#assign_student").serialize(),
            url: "AssignSponsorServlet",
            success: function (result) {
                Materialize.toast(result, 3000);


            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });

    //sponsor payments...
    $('#btn_make_sponsor_payment').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            data: $("#make_sponsor_payments").serialize(),
            url: "SponsorServlet?action=pay",
            success: function (result) {
                Materialize.toast(result, 3000);
                $('#make_sponsor_payments_id').val("");
            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });
    //pay commitments
    $('#btn_commit_payments').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            data: $("#sponsorcommit_payments").serialize(),
            url: "SponsorServlet?action=payments",
            success: function (result) {
                Materialize.toast(result, 3000);
                $('#sponsor_comm_number').val("");
            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });

    //save sponsor commits
    $('#btn_sponsor_commits').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            data: $("#sponsor_commit_form").serialize(),
            url: "SponsorServlet?action=saveCommits",
            success: function (result) {
                Materialize.toast(result, 3000, 'rounded');
                $('#admin_feedb').html("");
                // $('#sponsor_comm_fb').html(result).show().delay(3000).fadeOut('slow');
            },
            error: function (result) {
                Materialize.toast(result, 4000, 'rounded');
            }

        });
    });
//add new sponsor
    $('#btn_add_newSponsor').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            data: $("#new_sponsor_info").serialize(),
            url: "SponsorServlet?action=commits",
            success: function (result) {

                $('#new_sponsor_fb').html(result).show().delay(3000).fadeOut('slow');
            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });

    $('#btn_save_school_fee').click(function () {
      
        $.ajax({
            type: 'POST',
            data: $("#editFeeStractureForm").serialize(),
            url: "SchoolServlet?action=editFee",
            success: function (data) {
                
                 Materialize.toast(data, 2000);
            }, error: function (data) {
                alert("error:" + data);
            }
        });
    });
    
    
});

