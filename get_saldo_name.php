<?php

include 'connection.php';

$user_id = $_GET['user_id'];


$query = "select * from user where user_id = $user_id";
$result = mysqli_query($koneksi, $query);

$arraydata = array();

if ($line = mysqli_fetch_assoc($result)) {
    $arraydata[] = $line;

    echo json_encode($arraydata);
}
