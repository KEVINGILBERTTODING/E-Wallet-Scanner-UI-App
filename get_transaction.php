<?php
include 'connection.php';

$user_id = $_GET['user_id'];

$query = "SELECT t.transaction_id, t.user_id, t.date, t.total, t.product_id, p.product_id, p.name, p.package_name, p.image,
 u.username, u.photo_profile, u.balance from transaction t, product p, user u where t.product_id = p.product_id and
t.user_id= u.user_id and u.user_id = '$user_id' order by t.transaction_id desc";

$result = mysqli_query($koneksi, $query);
$arraydata = array();

while ($baris = mysqli_fetch_assoc($result)) {
    $arraydata[] = $baris;
}
echo json_encode($arraydata);
