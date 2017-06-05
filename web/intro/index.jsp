<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Welcome || Sponsor Konnect</title>

        <!-- Bootstrap Core CSS -->
        <link href="intro/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="intro/css/landing-page.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="intro/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <%
            String pageName = "StudentRegister.jsp";
        %>


        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
            <div class="container topnav">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand topnav" href="#"> <img src="././img/sponsor_logo.png" alt="" style="margin-top: -30%;width: 73%;"/></a>

                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li>

                            <a href="././login.jsp" >Sponsor Login</a>
                        </li>
                        <li>

                            <a href="././login.jsp" >Student Login</a>
                        </li>
                        <li>
                            <a href="#about">About</a>
                        </li>
                        <li>
                            <a href="#services">Apply</a>
                        </li>
                        <li>
                            <a href="#contact">Contact</a>
                        </li>

                    </ul>
                </div>


                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>



        <!-- Header -->
        <a name="about"></a>
        <div class="intro-header">
            <div class="container">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="intro-message">
                            <h1>SponsorKonnect</h1>
                            <h3>Continuing children education</h3>
                            <hr class="intro-divider">

                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>
        <!-- /.intro-header -->

        <!-- Page Content -->

        <a  name="services"></a>
        <div class="content-section-a">

            <div class="container">
                <div class="row">
                    <div class="col-lg-5 col-sm-6">
                        <hr class="section-heading-spacer">
                        <div class="clearfix"></div>
                        <h2 class="section-heading">Are you a bright student looking for sponsorship:<br>SponsorKonnect got your back</h2>
                        <p class="lead">SponsorKonnect main goal is to provide education to needy children. Ounce applied, it will help you connect to sponsor to take you through your education phase</p>
                        <a id="toRegister" style="margin-bottom: 10px" type="button" class="btn btn-success" href="././Register.jsp"> Apply now</a>
                    </div>
                    <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                        <img class="img-responsive" src="intro/img/ipad.jpeg" alt="">
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>
        <!-- /.content-section-a -->

        <div class="content-section-b">

            <div class="container">

                <div class="row">
                    <div class="col-lg-5 col-lg-offset-1 col-sm-push-6  col-sm-6">
                        <hr class="section-heading-spacer">
                        <div class="clearfix"></div>
                        <h2 class="section-heading">Looking to <br>Sponsor a child Education?</h2>
                        <p class="lead">Apply with Sponsor Konnect and will get back to you promptly</p>
                        <a  href="././SponsorApply.jsp" style="margin-bottom: 10px" type="button" class="btn btn-success"> Sponsor a child </a>
                    </div>
                    <div class="col-lg-5 col-sm-pull-6  col-sm-6">
                        <img class="img-responsive" src="intro/img/continuing.jpg" alt="">
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>
        <!-- /.content-section-b -->

        <div class="content-section-a">

            <div class="container">

                <div class="row">
                    <div class="col-lg-5 col-sm-6">
                        <hr class="section-heading-spacer">
                        <div class="clearfix"></div>
                        <h2 class="section-heading">Help us<br> To help others</h2>
                        <p class="lead">Feel generous by supporting our efforts by donating the small amount you have</p>
                        <button style="margin-bottom: 10px" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal"> Donate </button>
                    </div>
                    <div class="col-lg-5 col-lg-offset-2 col-sm-6">
                        <img class="img-responsive" src="intro/img/kids.jpg" alt="">
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>
        <!-- /.content-section-a -->

        <a  name="contact"></a>
        <div class="banner">

            <div class="container">

                <div class="row">
                    <div class="col-lg-6">
                        <h2>Connect to SponsorKonnect</h2>
                    </div>
                    <div class="col-lg-6">
                        <ul class="list-inline banner-social-buttons">
                            <li>
                                <a href="#" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                            </li>
                            <li>
                                <a href="#" class="btn btn-default btn-lg"><i class="fa fa-facebook fa-fw"></i> <span class="network-name">Facebook</span></a>
                            </li>
                            <li>
                                <a href="#" class="btn btn-default btn-lg"><i class="fa fa-google fa-fw"></i> <span class="network-name">Gmail</span></a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
            <!-- /.container -->

        </div>
        <!-- /.banner -->
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header alert-success">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title " id="myModalLabel">Follow the following steps to make a donation.Thank you in Advance</h4>
                    </div>
                    <div class="modal-body">
                        <h3>To pay for goods and services via Lipa na M-pesa,</h3>
                        <br>
                        <h4>Step 1: Go to M-PESA on your phone</h4>
                        <h4>Step 2: Select Lipa na M-pesa option in the drop-down</h4>
                        <h4>Step 3: Select the Pay Bill option</h4>
                        <h4>Step 4:  Enter the business number 206206</h4>
                        <h4>Step 5: asks for account number, which is automatically generated based on the service you used.</h4>
                        <h4>Step 6: Enter the amount, which was generated as an invoice.</h4>
                        <h4>Step 7: Enter M-pesa Pin Number and Press Send</h4>
                    </div>

                </div>
            </div>
        </div>
        <!-- Footer -->
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="list-inline">
                            <li>
                                <a href="#">Home</a>
                            </li>
                            <li class="footer-menu-divider">&sdot;</li>
                            <li>
                                <a href="#about">About</a>
                            </li>
                            <li class="footer-menu-divider">&sdot;</li>
                            <li>
                                <a href="#services">Services</a>
                            </li>
                            <li class="footer-menu-divider">&sdot;</li>
                            <li>
                                <a href="#contact">Contact</a>
                            </li>
                             <li class="footer-menu-divider">&sdot;</li>
                            <li>
                                <a href="././AccountantLogin.jsp">Accountant</a>
                            </li>
                             <li class="footer-menu-divider">&sdot;</li>
                            <li>
                                <a href="././SocialWorkerLogin.jsp">Social Worker</a>
                            </li>
                             <li class="footer-menu-divider">&sdot;</li>
                            <li>
                                <a href="././AdminLogin.jsp">Admin</a>
                            </li>
                        </ul>
                        <p class="copyright text-muted small">Copyright &copy; SponsorKonnect 2017. All Rights Reserved</p>
                    </div>
                </div>
            </div>


        </footer>
        <script>

            $("#toRegister").click(function () {
                window.location.href = "neuesSpiel.jsp";
            });

        </script>
        <!-- jQuery -->
        <script src="intro/js/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="intro/js/bootstrap.min.js"></script>

    </body>

</html>
