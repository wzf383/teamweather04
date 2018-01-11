<?php
	header("Content-type:text/html;charset=utf-8");
	mysql_connect("localhost","root","")or die("看看的");
	mysql_select_db("db_androidgrade")or("反反复复");
	mysql_query("set names utf8");
	if(isset($_GET['user'])){
		
                  $user=$_GET["user"];
			
	             $sql="";
                   
                    $sql = "select a.xh,a.xm,a.sex,b.class_name from ";
                    $sql =$sql."tb_stuinf a,tb_class b";
                    $sql =$sql." where a.class_id=b.id and xh='$user' ";

		$r = mysql_query($sql);
		$grade = array();
		$per = array();

		while($row = mysql_fetch_array($r)){
			$per['xh'] = $row['xh'];
			$per['xm'] = $row['xm'];			
			$per['sex']= $row['sex'];
                         $per['class_id']= $row['class_name'];
                          
                         if($per['sex']=="0"){
                         
                         	$per['sex']="男";
                         }else{
                         	$per['sex']="女";
                         }
                         $grade[] = $per;
		}
		echo json_encode($grade);
	}
	
	
	
	
	
	