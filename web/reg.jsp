<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/main.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <form method="post" action="registration.jsp">
            <div class="container">
                <table border="1" width="30%" cellpadding="5">
                    <thead>
                        <tr>
                            <th colspan="2"><h2>Enter Information Here</h2></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>First Name</td>
                            <td><input type="text" name="fname" value="" size="35"/></td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td><input type="text" name="lname" value="" size="35"/></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" name="email" value="" size="35"/></td>
                        </tr>
                        <tr>
                            <td>User Name</td>
                            <td><input type="text" name="uname" value="" size="35"/></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="pass" value="" size="35"/></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Submit" /></td>
                            <td><input type="reset" value="Reset" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><b>Already registered!!</b> <a href="index.jsp"><b>Login Here</b></a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>