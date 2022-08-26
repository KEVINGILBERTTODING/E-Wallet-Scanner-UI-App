<?php
include 'connection.php';
class trc
{
}


$user_id = $_POST['user_id'];

$total = $_POST['total'];
$product_id = $_POST['product_id'];
$date = $_POST['date'];

$query  = "INSERT INTO transaction (user_id, date, total, product_id) 
VALUES ('$user_id', '$date', '$total', '$product_id')";

$result = mysqli_query($koneksi, $query);

if ($result) {
    $response = new trc();
    $response->status = 1;
    $response->message = "success";

    die(json_encode($response));
} else {
    $response = new trc();
    $response->status = 2;
    $response->message = "Failed uploaded";

    die(json_encode($response));
}
