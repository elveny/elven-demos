package com.gen.jpa.utils;

/**
 * 数据库常量
 * @author	zhanglikun
 * @mail	likun_zhang@yeah.net
 * @date 	2013-6-6
 */
public interface Consts {

    String DB_NAME = "paycore" ;		// 数据库名称
    String DB_HOST = "192.168.2.60" ;	// 数据库HOST
    int DB_PORT = 3306 ;					// 数据库端口
    String DB_USER="paycore" ;					// 用户名
    String DB_PASS="paycore111" ;	// 密码

    String DB_TABLE_PREFIX = "" ;		// 表前缀

    String TARGET_DIR = "E:\\temp\\genEntity\\" +DB_NAME+"\\";	// 生成代码存放目录

}
