<?php

include 'connection.php';

class user
{
}


$password =  $_POST['password'];
$username = $_POST['username'];





$query = "select password from user where username = '$username'";


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
