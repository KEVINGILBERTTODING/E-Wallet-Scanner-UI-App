<?php

include 'connection.php';

class usr
{
}

$product_id = $_GET['product_id'];

$query = "select * from product where product_id = '$product_id'";
$result = mysqli_query($koneksi, $query);
if ($result) {
    $arraydata = array();
    while ($baris = mysqli_fetch_assoc($result)) {
        $arraydata[] = $baris;
    }
    echo json_encode($arraydata);
} else {
    $response = new usr();
    $response->success = 0;
    $response->message = "Error while updating";
    die(json_encode($response));
}
