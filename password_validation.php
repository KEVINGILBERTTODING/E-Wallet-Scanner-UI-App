<?php

include 'connection.php';

class user
{
}


$password =  $_POST['password'];
$user_id = $_POST['user_id'];





$query = "select password from user where user_id = '$user_id'";


$result = mysqli_query($koneksi, $query);

$arraydata = array();


if ($line = mysqli_fetch_assoc($result)) {
    $arraydata[] = $line;
    $password_hash = $arraydata[0]['password'];

    if (password_verify($password, $password_hash)) {
        $response = new user();
        $response->success = 1;
        $response->message = "Login success.";
        die(json_encode($response));
    } else {
        $response = new user();
        $response->success = 0;
        $response->message = "Login failed.";
        die(json_encode($response));
    }
}
