<?php
$con=mysqli_connect("localhost","compsato_dogs","hashbr0wn","compsato_dogs");
if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$idnum = $_POST['idnum'];
$result = mysqli_query($con,"select concat(first_name," ", last_name) from user where id_number = '$idnum'");
$row = mysqli_fetch_array($result);
$data = $row[0];

if($data){
echo $data;
}
mysqli_close($con);
?>