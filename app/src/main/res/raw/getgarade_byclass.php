<?php
	header("Content-type:text/html;charset=utf-8");
	mysql_connect("localhost","root","")or die("服务器不存在");
	mysql_select_db("db_androidgrade")or("数据库不存在");
	mysql_query("set names utf8");
	if(isset($_GET['tid'])){
		
		$tid= $_GET['tid'];//学期id
		$cid = $_GET['cid'];//课程id
		$bid=$_GET['bid'];//班级编号
		$sql = "select a.xh,d.xm,a.cj from ";
		$sql =$sql."tb_grade a,tb_course b,tb_term c,tb_stuinf d,tb_class e";
		$sql =$sql." where a.xh=d.xh and a.kid=b.km_id and a.tid=c.id and  d.class_id=e.id and ";
		$sql = $sql."c.id=$tid and b.km_id=$cid and e.id=$bid ";		
	  
		$r = mysql_query($sql);
		$grade = array();
		$per = array();
		while($row = mysql_fetch_array($r)){
			$per['xh'] = $row['xh'];
			$per['xm'] = $row['xm'];			
			$per['cj']= $row['cj'];
			$grade[] = $per;
		}
		echo json_encode($grade);
	}
	