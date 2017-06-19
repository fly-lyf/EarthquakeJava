# EarthquakeJava

### 系统的部分说明
1.config文件夹放的是spring mvc的各种配置，bootstrap代替web.xml用于服务器部署两个sevlet dispatcher实现的配置里边有注释，个部分用途详情见《Java Web高级编程》
2.site文件放的是业务逻辑，按照mvc分文件夹，一般的请求流程是前端发到controller，controller调用service(有的地方直接调用dao)，service通过dao访问entity。
3.所有业务repository都继承了GenericJpaBaseRepository，这个基dao里边的全部方法都被其他业务dao继承，可以直接调用。
4.数据库查询用的是JPQL和Ctritial Query，前者比较多。
5.数据库的映射实体可以通过idea自动生成，但是自增长id貌似需要手动贴上去。@GeneratedValue(strategy = GenerationType.IDENTITY)
6.截止到写这个readme为止，earthquake_nearcity_info和earthquake_relation还属于弃用状态，earthquake_rule_respond，earthquake_rule，earthquake_respond准备用于第二阶段简报，earthquake_loss用于第三阶段，其他表都已经投入使用。各表格字段还需要根据需求不断调整。
7.地震台网爬到的数据，系统解析不了的，会扔到undealed字段里，要手动解析

### 系统的部分逻辑
1.目前的地震台网地点信息的解析逻辑是：

    (1)遍历省份列表，找出对应省份，从省份位置点切词；
    (2)找到各次级行政区的关键词的位置，从这个位置点切词；
    (3)次级行政区{"州", "地区", "市", "盟", "县", "区", "旗", "城区", "行政委员会"};
    (3)比如xx省xx市xx区。先穷举省份、切词，剩下xx市xx区；再匹配次级行政区关键词"市"、切词，剩下xx区；重复第二步。
    (4)搞这么复杂，原因在于地区、市这种行政区，既可能是第二级的也可能是第三级的，还有xx自治区xx市xx市各种奇葩情况

2.各种getByCondition

    调用getConditionMap对传进来的查询参数生成一个map，过滤掉没有value的字段；
    根据业务进行query子句的构造，形成一个query子句的list；
    把各子句拼接生成一个完整的typedQuery，分页也在这一步完成。


### 留下的坑：

1.地震台网全文爬取，batchInsert里边的flush 方法没效果，数据真正写入数据库的时间点是系统跑起来20分钟所有的数据爬完的时候。

2.各种api获取到的json数据，都是简单解析，几乎没有考虑健壮性的逻辑。

3.log4j还不怎么玩的转，有一些信息被我扔到控制台直接输出了。2里提到的如果有解析失败的，接口会返回parse error，控制台也会出错误栈日志。但是爬地震台网对前端是透明的，所以只有控制台日志。按道理应该有application.log的日志文件，但是我还没弄明白日志管理器的逻辑~可以去看下到底有没有。

4.地震台网全文解析的逻辑按理说应该放在某个listner下启动时执行一次就好了，但是不知道放在哪里。所以最后只能用了个定时任务，执行周期改成了一个星期。那个OnStartupTask是那个监听器，一直报contextLoader的初始化错误，不懂为啥。代码没舍得删就留着了。

5.目前行政区表里的数据还是测试数据，前端拉division信息可能有多条记录。

2.地震信息表里前208条数据（id是到209的），没有市级信息，查询天气会报错。这个需要人工格式化一下。





