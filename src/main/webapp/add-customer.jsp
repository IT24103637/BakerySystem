<!doctype html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>

    <title>SweetDelight Bakery</title>

    <link rel="icon" href="images/logo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="css/style.css" />

</head>
<body>

<div class="container-fluid vh-100 d-flex justify-content-center">

    <div class="row  align-content-center">

        <div class="col-12">
            <div class="row">
                <div class="col-12 text-center">
                    <h1 class="mt-2">SweetDelight Bakery | Add Customer </h1>
                </div>
            </div>
        </div>

        <div class="col-12 text-center">
            <img src="images/add_customer.png" alt="Customer Logo" class="mt-3" style="width:25%"/>
        </div>

        <div class="col-12">
            <div class="row">
                <div class="col-sm-12 offset-lg-1 col-lg-10">
                    <div class="row">
                        <div class="col-12 mt-2">
                            <label class="form-label">Full Name:</label>
                            <input type="text" id="name" class="form-control" placeholder="John Deo" />
                        </div>
                        <div class="col-12 mt-2">
                            <label class="form-label">Mobile No:</label>
                            <input type="text" class="form-control" id="mobile" placeholder="07********" />
                        </div>
                        <div class="col-6 mt-3">
                            <button class="form-control btn btn-success" onclick="register();">Register</button>
                        </div>
                        <div class="col-6 mt-3">
                            <button class="form-control btn btn-danger">Reset</button>
                        </div>
                        <div class="col-12 text-center mt-2">
                            <a href="customers.jsp">View Customers</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>

</div>

<script src="script/script.js"></script>
</body>
</html>
