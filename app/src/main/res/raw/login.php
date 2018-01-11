<?php
	header("Content-type:text/html;charset=utf-8");
	mysql_connect("localhost","root","")or die("服务器不存在");
	mysql_select_db("db_androidgrade")or("数据库不存在");
	mysql_query("set names utf8");
	if(isset($_GET["user"])){
		$user = $_GET['user'];
		$pwd = $_GET['pwd'];
		$login = false;
		$sql="";
		$json=array();
		if($user == 'admin'){
			$sql = "select * from tb_user where pwd='$pwd'";
					
		}else{
			$sql = "select * from tb_stuinf where xh='$user' and pwd='$pwd'";
		}
		$r = mysql_query($sql);
		$rows=mysql_num_rows($r);
		if ($rows>0) $login=true;
		if($login){
			$json["login"]="true";
		}else{
			$json["login"]="false";
		}
		echo json_encode($json);
	}