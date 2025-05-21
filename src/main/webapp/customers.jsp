<!doctype html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

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
                    <h1 class="mt-2">SweetDelight Bakery | View Customers</h1>
                </div>
            </div>
        </div>

        <div class="col-12">
            <div class="row">
                <div class="col-sm-12 offset-lg-1 col-lg-10">
                    <div class="row">
                        <div class="col-12 mt-2">
                            <table class="table table-bordered table-striped text-center">
                                <thead class="table-dark">
                                <tr>
                                    <th>Name</th>
                                    <th>Mobile</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Ava Smith</td>
                                    <td>077123425</td>
                                    <td><span><a href="edit-customer.jsp" class="btn text-dark"><i class="bi bi-pencil-square"></i></a></span> &nbsp; <span><i class="bi bi-trash3"></i></span></td>
                                </tr>
                                <tr>
                                    <td>Grace Taylor</td>
                                    <td>0778682458</td>
                                    <td><span><a href="edit-customer.jsp" class="btn text-dark"><i class="bi bi-pencil-square"></i></a></span> &nbsp; <span><i class="bi bi-trash3"></i></span></td>
                                </tr>
                                <tr>
                                    <td>Daniel Brown</td>
                                    <td>074945608</td>
                                    <td><span><a href="edit-customer.jsp" class="btn text-dark"><i class="bi bi-pencil-square"></i></a></span> &nbsp; <span><i class="bi bi-trash3"></i></span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-12 text-center mt-2">
                            <a href="add-customer.jsp" class="btn btn-outline-info fw-bold">Add More Customers</a>
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
