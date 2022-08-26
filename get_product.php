<?php

include 'connection.php';

$query = "SELECT * from product order by product_id";
$result = mysqli_query($koneksi, $query);
$arrayData = array();
while ($line = mysqli_fetch_assoc($result)) {
    $arrayData[] = $line;
}
echo json_encode($arrayData);
