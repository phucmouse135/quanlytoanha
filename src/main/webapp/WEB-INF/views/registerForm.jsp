<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
<div class="container">
    <div class="register-form">
        <div class="main-div">
            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    ${param.error}
                </div>
            </c:if>
            <div class="container-fluid">
                <section class="gradient-custom">
                    <div class="page-wrapper">
                        <div class="row d-flex justify-content-center align-items-center">
                            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                                <div class="card text-white" style="border-radius: 1rem; background-color: #35bf76;">
                                    <div class="card-body p-5">
                                        <div class="mb-md-5 mt-md-4 pb-5 text-center">
                                            <h2 class="fw-bold mb-2 text-uppercase">Register</h2>
                                            <p class="text-white-50 mb-5">Please enter your details to create an account!</p>
                                            <form action="j_spring_security_check" id="formRegister" method="post">
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="fullname">Fullname</label>
                                                    <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Fullname">
                                                </div>
                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="username">Username</label>
                                                    <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="email">Email</label>
                                                    <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="password">Password</label>
                                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                                </div>

                                                <div class="form-outline form-white mb-4">
                                                    <label class="form-label" for="retypePassword">Retype Password</label>
                                                    <input type="password" class="form-control" id="retypePassword" name="retypePassword" placeholder="Retype Password">
                                                </div>

                                                <button type="button" class="btn btn-primary" id="sign_up">Register</button>
                                            </form>
                                            <div class="d-flex justify-content-center text-center mt-2 pt-1">
                                                <a href="#!" class="login-extension text-white"><i class="fab fa-facebook-f fa-lg"></i></a>
                                                <a href="#!" class="login-extension text-white"><i class="fab fa-twitter fa-lg mx-4 px-2"></i></a>
                                                <a href="#!" class="login-extension text-white"><i class="fab fa-google fa-lg"></i></a>
                                            </div>
                                        </div>
                                        <div class="text-center">
                                            <p class="mb-0 tex-center account">Already have an account? <a href="/login" class="text-white-50 fw-bold">Login</a></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {

    });
    document.getElementById("sign_up").addEventListener("click", function() {
        var data = {}
        data['full_name'] = document.getElementById("fullname").value;
        data['username'] = document.getElementById("username").value;
        data['email'] = document.getElementById("email").value;
        data['password'] = document.getElementById("password").value;
        data['retype_password'] = document.getElementById("retypePassword").value;
        data['role_code'] = 'USER';
        console.log(data);
        signUp(data);
    });
    function signUp(data) {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/users/register",
            data: JSON.stringify(data),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                console.log("SUCCESS : ", JSON.stringify(data));
                alert("Register successfully!");
                window.location.href = "/login";
            },
            error: function (xhr, status, error) {
                console.log("ERROR : ", xhr.responseText);
                console.log("Status : ", status);
                console.log("Error : ", error);
                alert("Register failed! Error: " + error);
            },
            complete: function (xhr, status) {
                console.log("Response Text: ", xhr.responseText);
            }
        });
    }
</script>
</body>
</html>