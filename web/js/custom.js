/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $(".dropdown-button").dropdown();
    $('select').material_select();
    $('.modal').modal();
    $('#schoolDetails').hide();
});
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

function showSoccEdit(row) {

    var x = row.cells;
    document.getElementById('soc_id').value = x[0].innerHTML;
    document.getElementById('soci_name').value = x[1].innerHTML;
    document.getElementById('soc_number').value = x[2].innerHTML;
    document.getElementById('soc_email').value = x[3].innerHTML;
    document.getElementById('soc_locationn').value = x[4].innerHTML;
    document.getElementById('soc_passwordd').value = x[5].innerHTML;
}

function getAccs() {

    $.get('PopulateSocialWorker?action=acc', function (responseJson) {
        $("#editAccount").hide();
        if (responseJson != null) {
            $("#accountantTable").find("tr:gt(0)").remove();

            var table1 = $("#accountantTable");

            $.each(responseJson, function (key, value) {

                var rowNew = $('<tr id="row_1" onclick="javascript:showAccsEdit(this);"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['name']);
                rowNew.children().eq(2).text(value['number']);
                rowNew.children().eq(3).text(value['email']);
                rowNew.children().eq(4).text(value['location']);
                rowNew.children().eq(5).text(value['password']);
                rowNew.children().eq(6).html('<a class="btn-floating btn-large waves-effect waves-light green" onclick="return enableAccEdit(this);"><i class="material-icons">edit</i></a>');
                rowNew.children().eq(7).html('<a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></a>');
                rowNew.appendTo(table1);
            });
        }
    });

    return true;
}
function enableSocEdit() {

    if ($('#editSocial').is(':visible'))
    {
        $("#editSocial").hide();

    } else {

        $("#editSocial").show();
    }

}
function enableAccEdit() {

    if ($('#editAccount').is(':visible'))
    {
        $("#editAccount").hide();

    } else {

        $("#editAccount").show();
    }

}
function enableAdminEdit() {

    if ($('#editAdmin').is(':visible'))
    {
        $("#editAdmin").hide();

    } else {

        $("#editAdmin").show();
    }

}
function showAccsEdit(row) {

    var x = row.cells;
    document.getElementById('acc_id').value = x[0].innerHTML;
    document.getElementById('acc_name').value = x[1].innerHTML;
    document.getElementById('acc_number').value = x[2].innerHTML;
    document.getElementById('acc_email').value = x[3].innerHTML;
    document.getElementById('acc_locationn').value = x[4].innerHTML;
    document.getElementById('acc_passwordd').value = x[5].innerHTML;
}
function showRow(row) {

    var x = row.cells;
    document.getElementById('id').value = x[0].innerHTML;
    document.getElementById('name').value = x[1].innerHTML;
    document.getElementById('number').value = x[2].innerHTML;
    document.getElementById('email').value = x[3].innerHTML;
    document.getElementById('locationn').value = x[4].innerHTML;
    document.getElementById('passwordd').value = x[5].innerHTML;
}

function getAdmin() {

    $.get('PopulateSocialWorker?action=admin', function (responseJson) {

        $("#editAdmin").hide();

        if (responseJson != null) {
            $("#adminTable").find("tr:gt(0)").remove();

            var table1 = $("#adminTable");

            $.each(responseJson, function (key, value) {

                var rowNew = $('<tr id="row_1" onclick="javascript:showRow(this);"><td id="admin_id"></td><td id="admin_name"></td><td id="admin_number"></td><td id="admin_email"></td><td id="admin_location"></td><td id="admin_password"></td><td></td><td></td></tr>');
                rowNew.children().eq(0).text(value['id']);
                rowNew.children().eq(1).text(value['name']);
                rowNew.children().eq(2).text(value['number']);
                rowNew.children().eq(3).text(value['email']);
                rowNew.children().eq(4).text(value['location']);
                rowNew.children().eq(5).text(value['password']);
                rowNew.children().eq(6).html('<a class="btn-floating btn-large waves-effect waves-light green" onclick="return enableAdminEdit(this);"><i class="material-icons">edit</i></a>');
                rowNew.children().eq(7).html('<a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">delete</i></a>');
                rowNew.appendTo(table1);
            });
        }
    });

    return true;
}

//$('.datepicker').pickadate({
//    selectMonths: true, // Creates a dropdown to control month
//    selectYears: 15 // Creates a dropdown of 15 years to control year
//});

//submit personal details
$(document).ready(function () {



    $('#continur_msg').show().delay(6000).fadeOut('slow');
    $('#login_bg').show().delay(2000).fadeOut('slow');

    //save social edits
    $('#soc_edit_btn').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            data: $("#edit_social_table").serialize(),
            url: "AdminServlet?add=saveSoc",
            success: function (result) {
                //$('#edit_admin_bg').html(result);
                $("#editSocial").hide();
                // Materialize.toast(result, 4000);
                swal(
                        'Server Feedback',
                        result,
                        'info'
                        );

            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });


    //selected school
    $('#selectedSchool').change(function () {

        $('#schoolDetails').show();
        var value = $(this).val();

        $.ajax({
            type: 'GET',
            data: {category: value},
            url: "GetSelectedSchool",
            success: function (result) {

                $("#selcetdSchoolInfo").html(result);
            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });


    //save accs edits
    $('#acc_edit_btn').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            data: $("#edit_acc_table").serialize(),
            url: "AdminServlet?add=saveAcc",
            success: function (result) {
                //$('#edit_admin_bg').html(result);
                $("#editAccount").hide();
                //Materialize.toast(result, 4000);
                swal(
                        'Server Feedback',
                        result,
                        'info'
                        );

            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });

    //save admin edits
    $('#admin_edit_btn').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            data: $("#edit_admin_table").serialize(),
            url: "AdminServlet?add=saveAdmin",
            success: function (result) {
                //$('#edit_admin_bg').html(result);
                $("#editAdmin").hide();
                // Materialize.toast(result, 4000);
                swal(
                        'Server Feedback',
                        result,
                        'info'
                        );

            },
            error: function (result) {
                alert("error" + result);
            }

        });
    });

//admin
    $('#btn_admin').click(function (event) {
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
                data: $("#adminData").serialize(),
                url: "AdminServlet?add=admin",
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

    });
    //social worker



    $('#btn_social_worker_1').click(function (event) {
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
                data: $("#socialWorkerData").serialize(),
                url: "AdminServlet?add=social",
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
    });

    //accountant
    $('#btn_accountant').click(function (event) {
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
                data: $("#accountatData").serialize(),
                url: "AdminServlet?add=acct",
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

    });



    $('#save_personal').click(function (event) {

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
                data: $("#personalDetailsTable").serialize(),
                url: "StudentPersonalServlet",
                success: function (result) {
                    // $('#err_p').html(result).show().delay(3000).fadeOut('slow');
                    swal(
                            'Server Feedback',
                            result,
                            'info'
                            );
                },
                error: function (result) {
                    swal(
                            'Oops...',
                            result,
                            'error'
                            );
                }

            });
        });

    });

//submit parent details
    $('#btn_saveparents').click(function (event) {
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
                data: $("#parentalDetails").serialize(),
                url: "StudentParentalServlets",
                success: function (result) {
                    swal(
                            'Server Feedback',
                            result,
                            'info'
                            );
                },
                error: function (result) {
                    swal(
                            'Oops...',
                            result,
                            'error'
                            );
                }

            });
        });

    });

    //submit gurdian details
    $('#btn_gurdian').click(function (event) {
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
                data: $("#studentGurdianDetails").serialize(),
                url: "StudentGurdianServlet",
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

    });

//submit sib details
    $('#btn_sibling').click(function (event) {
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
                data: $("#siblingTable").serialize(),
                url: "StudentSiblingServlet",
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
    });

    //submit skul details
    $('#btn_school').click(function (event) {
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
                data: $("#schoolInfo").serialize(),
                url: "StudentSchoolServlet?action=new",
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

    });


    //submit selected school info
    $('#btnSelectedSchool').click(function (event) {
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
                data: $("#selectedSchoolForm").serialize(),
                url: "StudentSchoolServlet?action=old",
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


    });

}
);
