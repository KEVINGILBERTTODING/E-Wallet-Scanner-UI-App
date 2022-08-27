<?php
include 'connection.php';

class usr
{
}

$username = $_POST['username'];
$password = password_hash($_POST['password'], PASSWORD_DEFAULT);
$balance = $_POST['balance'];
$photo_profile = $_POST['photo_profile'];

$query = "insert into user (username, password, balance, photo_profile) 
values ('$username', '$password', '$balance', '$photo_profile')";

$result = mysqli_query($koneksi, $query);
if ($result) {
    $response  = new usr();
    $response->success = 1;
    $response->message = "Register success, please login.";
    die(json_encode($response));
} else {
    $response  = new usr();
    $response->success = 0;
    $response->message = "Register failed.";
    die(json_encode($response));
}
