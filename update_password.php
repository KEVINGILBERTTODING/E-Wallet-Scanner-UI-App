<?php

include 'connection.php';

class user
{
}

$password = password_hash($_POST['password'], PASSWORD_DEFAULT);
$user_id = $_POST['user_id'];


$query = "UPDATE user SET password = '$password' WHERE user_id = '$user_id'";
$result = mysqli_query($koneksi, $query);
if ($result) {
    $response = new user();
    $response->status = 1;
    $response->message = "Password is updated";

    die(json_encode($response));
} else {
    $response = new user();
    $response->status = 0;
    $response->message = "Failed";

    die(json_encode($response));
}
