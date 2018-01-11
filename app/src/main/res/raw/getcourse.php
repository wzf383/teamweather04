<?php
	header("Content-type:text/html;charset=utf-8");
	mysql_connect("localhost","root","")or die("服务器不存在");
	mysql_select_db("db_androidgrade")or("数据库不存在");
	mysql_query("set names utf8");
	
		$sql = "select km_id,km_name from tb_course ";
			
	  
		$r = mysql_query($sql);
		$grade = array();
		$per = array();
		while($row = mysql_fetch_array($r)){
			$per['id'] = $row['km_id'];
			$per['km_name'] = $row['km_name'];			
			
			$grade[] = $per;
		}
		echo json_encode($grade);
	
	