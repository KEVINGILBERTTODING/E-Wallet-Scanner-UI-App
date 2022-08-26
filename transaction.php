<?php
include 'connection.php';
class trc
{
}

$user_id = $_POST['user_id'];
$total = $_POST['total'];

$query = "UPDATE user SET balance = balance - $total where user_id = $user_id";
$result = mysqli_query($koneksi, $query);


if ($result) {
    $response = new trc();
    $response->status = 1;
    $response->message = "Transaksi berhasil";

    die(json_encode($response));
} else {
    $response = new trc();
    $response->status = 2;
    $response->message = "Transaksi Gagal";

    die(json_encode($response));
}
