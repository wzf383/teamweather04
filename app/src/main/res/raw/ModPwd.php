<?php
	header("Content-type:text/html;charset=utf-8");
	mysql_connect("localhost","root","")or die("服务器不存在");
	mysql_select_db("db_androidgrade")or("数据库不存在");
	mysql_query("set names utf8");
	if(isset($_GET["pwd"])){
		$pwd=$_GET["pwd"];
                $user=$_GET["user"];
		$login = false;
		$sql="";
		$json=array();
		
			$sql = "update tb_stuinf set pwd='$pwd' where xh='$user'";
					
		
			
		
		$r = mysql_query($sql);
		
		if($r) $login=true;
		if($login){
			$json["login"]="true";
		}else{
			$json["login"]="false";
		}
		echo json_encode($json);
	}