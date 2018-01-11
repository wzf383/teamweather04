<?php
	header("Content-type:text/html;charset=utf-8");
	mysql_connect("localhost","root","")or die("服务器不存在");
	mysql_select_db("db_androidgrade")or("数据库不存在");
	mysql_query("set names utf8");
	if(isset($_GET['tid'])){
		
		$tid= $_GET['tid'];//学期id
		
                
               $xh=$_GET['xh'];
		$sql = "select b.km_name,a.cj from ";
		$sql =$sql."tb_grade a,tb_course b,tb_term c";
		$sql =$sql." where c.id=a.tid and a.kid=b.km_id and ";
		$sql = $sql."c.id=$tid and a.xh=$xh";		
	  
		$r = mysql_query($sql);
		$grade = array();
		$per = array();
		while($row = mysql_fetch_array($r)){
			$per['km_name'] = $row['km_name'];
			$per['cj'] = $row['cj'];			
			
			$grade[] = $per;
		}
		echo json_encode($grade);
	}
	