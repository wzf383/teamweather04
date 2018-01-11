-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2016 年 12 月 16 日 04:38
-- 服务器版本: 5.6.12-log
-- PHP 版本: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `db_androidgrade`
--
CREATE DATABASE IF NOT EXISTS `db_androidgrade` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `db_androidgrade`;

-- --------------------------------------------------------

--
-- 表的结构 `tb_class`
--

CREATE TABLE IF NOT EXISTS `tb_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(20) NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='班级表' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `tb_class`
--

INSERT INTO `tb_class` (`id`, `class_name`) VALUES
(1, '软件1108'),
(2, '软件1109');

-- --------------------------------------------------------

--
-- 表的结构 `tb_course`
--

CREATE TABLE IF NOT EXISTS `tb_course` (
  `km_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '科目编号',
  `km_name` varchar(50) NOT NULL,
  PRIMARY KEY (`km_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='科目信息表' AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `tb_course`
--

INSERT INTO `tb_course` (`km_id`, `km_name`) VALUES
(1, '语文'),
(2, '数学'),
(3, '生物'),
(4, '化学'),
(6, '地理');

-- --------------------------------------------------------

--
-- 表的结构 `tb_grade`
--

CREATE TABLE IF NOT EXISTS `tb_grade` (
  `xh` varchar(8) NOT NULL COMMENT '学号',
  `kid` int(10) NOT NULL COMMENT '科目',
  `cj` int(11) DEFAULT NULL COMMENT '成绩',
  `tid` int(11) NOT NULL COMMENT '所属学期',
  PRIMARY KEY (`xh`,`kid`),
  KEY `tid` (`tid`),
  KEY `kid` (`kid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成绩表';

--
-- 转存表中的数据 `tb_grade`
--

INSERT INTO `tb_grade` (`xh`, `kid`, `cj`, `tid`) VALUES
('07110801', 1, 90, 1),
('07110801', 2, 80, 1),
('07110802', 1, 67, 1),
('07110802', 2, 77, 1),
('07110901', 1, 85, 1),
('07110901', 2, 66, 1),
('07110902', 1, 92, 1),
('07110902', 2, 88, 1);

-- --------------------------------------------------------

--
-- 表的结构 `tb_stuinf`
--

CREATE TABLE IF NOT EXISTS `tb_stuinf` (
  `xh` varchar(8) NOT NULL COMMENT '学号',
  `xm` varchar(10) NOT NULL COMMENT '姓名',
  `sex` bit(1) NOT NULL DEFAULT b'0' COMMENT '性别,0为男，1为女',
  `pwd` varchar(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `class_id` int(10) NOT NULL COMMENT '班级',
  PRIMARY KEY (`xh`),
  KEY `class_id` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生信息表';

--
-- 转存表中的数据 `tb_stuinf`
--

INSERT INTO `tb_stuinf` (`xh`, `xm`, `sex`, `pwd`, `class_id`) VALUES
('07110801', '陈一', b'0', '123456', 1),
('07110802', '李明', b'0', '123456', 1),
('07110901', '张三', b'0', '123456', 2),
('07110902', '李斯', b'0', '123456', 2);

-- --------------------------------------------------------

--
-- 表的结构 `tb_term`
--

CREATE TABLE IF NOT EXISTS `tb_term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term_year` varchar(15) NOT NULL COMMENT '学年',
  `term_No` varchar(5) NOT NULL COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='学期信息表' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `tb_term`
--

INSERT INTO `tb_term` (`id`, `term_year`, `term_No`) VALUES
(1, '2015-2016学年', '第1学期'),
(2, '2015-2016学年', '第2学期');

-- --------------------------------------------------------

--
-- 表的结构 `tb_user`
--

CREATE TABLE IF NOT EXISTS `tb_user` (
  `user` varchar(10) NOT NULL,
  `pwd` varchar(10) NOT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息表';

--
-- 转存表中的数据 `tb_user`
--

INSERT INTO `tb_user` (`user`, `pwd`) VALUES
('admin', '123');

-- --------------------------------------------------------

--
-- 替换视图以便查看 `vw_showallgradeinf`
--
CREATE TABLE IF NOT EXISTS `vw_showallgradeinf` (
`class_name` varchar(20)
,`xh` varchar(8)
,`xm` varchar(10)
,`km_name` varchar(50)
,`term_year` varchar(15)
,`term_No` varchar(5)
,`cj` int(11)
);
-- --------------------------------------------------------

--
-- 视图结构 `vw_showallgradeinf`
--
DROP TABLE IF EXISTS `vw_showallgradeinf`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_showallgradeinf` AS select `c`.`class_name` AS `class_name`,`a`.`xh` AS `xh`,`a`.`xm` AS `xm`,`e`.`km_name` AS `km_name`,`f`.`term_year` AS `term_year`,`f`.`term_No` AS `term_No`,`b`.`cj` AS `cj` from (((((`tb_stuinf` `a` join `tb_grade` `b`) join `tb_class` `c`) join `tb_term` `d`) join `tb_course` `e`) join `tb_term` `f`) where ((`a`.`xh` = `b`.`xh`) and (`a`.`class_id` = `c`.`id`) and (`b`.`tid` = `d`.`id`) and (`b`.`kid` = `e`.`km_id`) and (`b`.`tid` = `f`.`id`));

--
-- 限制导出的表
--

--
-- 限制表 `tb_grade`
--
ALTER TABLE `tb_grade`
  ADD CONSTRAINT `tb_grade_ibfk_3` FOREIGN KEY (`xh`) REFERENCES `tb_stuinf` (`xh`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_grade_ibfk_1` FOREIGN KEY (`kid`) REFERENCES `tb_course` (`km_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_grade_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `tb_term` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `tb_stuinf`
--
ALTER TABLE `tb_stuinf`
  ADD CONSTRAINT `tb_stuinf_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `tb_class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
