<?php
include 'connection.php';

class usr
{
}

$total = $_POST['total'];
$user_id = $_POST['user_id'];

$query = "SELECT balance from user where balance >= $total and user_id = $user_id";
$result = mysqli_query($koneksi, $query);

if ($line = mysqli_fetch_assoc($result)) {
    $response = new usr();
    $response->status = 1;
    $response->message = "Saldo cukup";
    die(json_encode($response));
} else {
    $response = new usr();
    $response->status = 0;
    $response->message = "Saldo tidak cukup";
    die(json_encode($response));
}
