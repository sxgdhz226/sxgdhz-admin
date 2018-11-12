import requests
import http.cookiejar as cookielib
import configparser
from bs4 import BeautifulSoup
import sys
import redis
import json
import math
import pymysql
import traceback
import threading
import time
import random
import os
import urllib3
from bs4 import BeautifulSoup
import http.client
import pymysql
pymysql.install_as_MySQLdb()
import pymongo

cfg = configparser.ConfigParser()
cfg.read('config.ini')

headers = {"Accept":"image/png, image/svg+xml, image/*;q=0.8, */*;q=0.5",
           "Referer":"http://www.imanhua.com/comic/76/list_59262.html",
           "Accept-Language":"zh-CN",
           "User-Agent":"Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko",
           "Accept-Encoding":"gzip, deflate",
           "Host":"t6.mangafiles.com",
           "Connection":"Keep-Alive"};

class fath:

    def __init__(self):
        print ("python 初始化。。。")

    def test():
        try:
            print ("我的第一个")
            print (int("1111"))

            path = os.getcwd();
            new_path = os.path.join(path,u'暴走漫画')
            print (new_path)
            if not os.path.isdir(new_path):
                os.mkdir(new_path)

            sys.exit("程序即将退出")
        except Exception as err:
            print (err);

    def page_loop(page=1):
        url = 'http://baozoumanhua.com/all/hot/page/%s?sv=1389537379' % page
        http = urllib3.PoolManager()
        r = http.request('GET', url)
        print (url)
        print (r)
        soup = BeautifulSoup(r, "html.parser")

        my_girl = soup.find_all('div',class_='img-wrap')
        for girl in my_girl:
            jokes = girl.find('img')
            link = jokes.get('src')
            print (link)
        #     flink = link
        #     print (flink)
        #     content2 = urllib3.urlopen(flink).read()
        #
        #     #with open(u'暴走漫画'+'/'+time.strftime('%H-%M-%S')+random.choice('qwertyuiopasdfghjklzxcvbnm')+flink[-5:],'wb') as code:          #在OSC上现学的
        #     with open(u'暴走漫画'+'/'+flink[-11:],'wb') as code:
        #         code.write(content2)
        #
        # page = int(page) + 1
        # print (u'开始抓取下一页')
        # print ('the %s page' % page)
        # page_loop(page)
        #
        # page_loop()
        # print ("~~~~~~~~~~~~~~~~~~~~~~~~~~END~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

    def get_user_info(self):
        try:
            config = self.cfg;
            host = config.get("redis", "host")
            print (host)
        except Exception as err:
            print (err);

    def parse_guanwang():
        #与网站构建一个连接
        conn = http.client.HTTPSConnection("http://www.zhmb.gov.cn/jeecms/web/index",80)
        print (conn)
        conn.request("GET", "/")
        r1 = conn.getresponse()
        print(r1.status, r1.reason)

    def testWhile():
        while 1:
            print ("111")

    def testthird():
        cl = """测试
                士大夫但是
                手动阀手动阀"""
        print (cl)

    #测试python类型
    #元祖类型
    def tuple():
        tuple = ('a','b','c')
        tuple = ('a','d','c')
        print (tuple)
        print (tuple[0])
        print (tuple*2)

    #字典数据类型相当于map类型
    def dict():
        dict = {}
        dict[1] = 'abc'
        print (dict[1])

    #列表数据类型
    def list():
        list = ['a','b','c']
        print (list)
        print (list[0:2])

    def input():
        str = input("请输入。。。")
        print (str)

    def file():
        fo = open("init.sql","w")
        fo.write( "www.runoob.com!\nVery good site!\n")
        fo.close()
        print ("文件名："+fo.name)

    #日期和时间
    def time():
        ticks  = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        print (ticks )

    #python连接mysql数据库
    def mysql_create():
        # 打开数据库连接
        db = pymysql.connect("127.0.0.1", "root", "", "ry", charset='utf8' )
        # 使用cursor()方法获取操作游标
        cursor = db.cursor()

        # 如果数据表已经存在使用 execute() 方法删除表。
        cursor.execute("DROP TABLE IF EXISTS EMPLOYEE")

        # 创建数据表SQL语句
        sql = """CREATE TABLE EMPLOYEE (
                 FIRST_NAME  CHAR(20) NOT NULL,
                 LAST_NAME  CHAR(20),
                 AGE INT,  
                 SEX CHAR(1),
                 INCOME FLOAT )"""

        cursor.execute(sql)

        # 关闭数据库连接
        db.close()

    def mysql_insert():
        # 打开数据库连接
        db = pymysql.connect("127.0.0.1", "root", "", "ry", charset='utf8' )
        # 使用cursor()方法获取操作游标
        cursor = db.cursor()
        #SQL 插入语句
        sql = """INSERT INTO EMPLOYEE(FIRST_NAME,
                 LAST_NAME, AGE, SEX, INCOME)
                 VALUES ('Mac', 'Mohan', 20, 'M', 2000)"""
        try:
            # 执行sql语句
            cursor.execute(sql)
            # 提交到数据库执行
            db.commit()
        except:
            # 如果发生错误则回滚
            db.rollback()

        # 关闭数据库连接
        db.close()

    def pymongo():
        myclient = pymongo.MongoClient("mongodb://localhost:27017/")
        mydb = myclient["runoobdb"]

    if __name__ == '__main__':
        # get_user_info();
        #test()
        #page_loop()
        #parse_guanwang()
        #testWhile()
        #testthird()
        #tuple()
        #dict()
        #list()
        #input()
        #file()
        #time()
        #mysql_create()
        #mysql_insert()
        pymongo()