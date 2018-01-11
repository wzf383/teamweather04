<?php
	header("Content-type:text/html;charset=utf-8");
	mysql_connect("localhost","root","")or die("服务器不存在");
	mysql_select_db("db_androidgrade")or("数据库不存在");
	mysql_query("set names utf8");
	
		$sql = "select id,term_year, term_No from tb_term ";
			
	  
		$r = mysql_query($sql);
		$grade = array();
		$per = array();
		while($row = mysql_fetch_array($r)){
			$per['id'] = $row['id'];
			$per['term'] = $row['term_year'];			
			$per['No'] = $row['term_No'];
			$grade[] = $per;
		}
		echo json_encode($grade);
	
	