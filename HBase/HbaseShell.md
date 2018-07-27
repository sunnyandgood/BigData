# HbaseShell

### 一、进入hbase命令行

      ./hbase shell

### 二、显示hbase中的表

       list

### 三、创建user表，包含info、data两个列族

        create 'user', 'info1', 'data1'
        create 'user', {NAME => 'info', VERSIONS => '3'}

### 四、向表中插入信息

* 向user表中插入信息，row key为rk0001，列族info中添加name列标示符，值为zhangsan

        put 'user', 'rk0001', 'info:name', 'zhangsan'

* 向user表中插入信息，row key为rk0001，列族info中添加gender列标示符，值为female

        put 'user', 'rk0001', 'info:gender', 'female'

* 向user表中插入信息，row key为rk0001，列族info中添加age列标示符，值为20

        put 'user', 'rk0001', 'info:age', 20

* 向user表中插入信息，row key为rk0001，列族data中添加pic列标示符，值为picture

        put 'user', 'rk0001', 'data:pic', 'picture'

### 五、获取表中的信息

* 获取user表中row key为rk0001的所有信息

        get 'user', 'rk0001'

* 获取user表中row key为rk0001，info列族的所有信息

        get 'user', 'rk0001', 'info'

* 获取user表中row key为rk0001，info列族的name、age列标示符的信息

        get 'user', 'rk0001', 'info:name', 'info:age'

* 获取user表中row key为rk0001，info、data列族的信息

        get 'user', 'rk0001', 'info', 'data'
        get 'user', 'rk0001', {COLUMN => ['info', 'data']}

        get 'user', 'rk0001', {COLUMN => ['info:name', 'data:pic']}

* 获取user表中row key为rk0001，列族为info，版本号最新5个的信息

        get 'people', 'rk0002', {COLUMN => 'info', VERSIONS => 2}
        get 'user', 'rk0001', {COLUMN => 'info:name', VERSIONS => 5}
        get 'user', 'rk0001', {COLUMN => 'info:name', VERSIONS => 5, TIMERANGE => [1392368783980, 1392380169184]}

* 获取user表中row key为rk0001，cell的值为zhangsan的信息

        get 'people', 'rk0001', {FILTER => "ValueFilter(=, 'binary:图片')"}

* 获取user表中row key为rk0001，列标示符中含有a的信息

        get 'people', 'rk0001', {FILTER => "(QualifierFilter(=,'substring:a'))"}

        put 'user', 'rk0002', 'info:name', 'fanbingbing'
        put 'user', 'rk0002', 'info:gender', 'female'
        put 'user', 'rk0002', 'info:nationality', '中国'
        get 'user', 'rk0002', {FILTER => "ValueFilter(=, 'binary:中国')"}

### 六、查询表中的数据

* 查询user表中的所有信息

        scan 'user'

* 查询user表中列族为info的信息

        scan 'people', {COLUMNS => 'info'}
        scan 'user', {COLUMNS => 'info', RAW => true, VERSIONS => 5}
        scan 'persion', {COLUMNS => 'info', RAW => true, VERSIONS => 3}
        
* 查询user表中列族为info和data的信息

        scan 'user', {COLUMNS => ['info', 'data']}
        scan 'user', {COLUMNS => ['info:name', 'data:pic']}


* 查询user表中列族为info、列标示符为name的信息

        scan 'user', {COLUMNS => 'info:name'}

* 查询user表中列族为info、列标示符为name的信息,并且版本最新的5个

        scan 'user', {COLUMNS => 'info:name', VERSIONS => 5}

* 查询user表中列族为info和data且列标示符中含有a字符的信息

        scan 'people', {COLUMNS => ['info', 'data'], FILTER => "(QualifierFilter(=,'substring:a'))"}

* 查询user表中列族为info，rk范围是[rk0001, rk0003)的数据

        scan 'people', {COLUMNS => 'info', STARTROW => 'rk0001', ENDROW => 'rk0003'}

* 查询user表中row key以rk字符开头的

        scan 'user',{FILTER=>"PrefixFilter('rk')"}

* 查询user表中指定范围的数据

        scan 'user', {TIMERANGE => [1392368783980, 1392380169184]}


### 七、删除数据

* 删除user表row key为rk0001，列标示符为info:name的数据

        delete 'people', 'rk0001', 'info:name'

* 删除user表row key为rk0001，列标示符为info:name，timestamp为1392383705316的数据

        delete 'user', 'rk0001', 'info:name', 1392383705316


### 八、清空user表中的数据

        truncate 'people'


### 九、修改表结构

* 首先停用user表（新版本不用）

        disable 'user'

* 添加两个列族f1和f2

        alter 'people', NAME => 'f1'
        alter 'user', NAME => 'f2'

* 启用表

        enable 'user'

        ###disable 'user'(新版本不用)

### 十、删除列族：

* 删除一个列族

        alter 'user', NAME => 'f1', METHOD => 'delete' 或 alter 'user', 'delete' => 'f1'

* 添加列族f1同时删除列族f2

        alter 'user', {NAME => 'f1'}, {NAME => 'f2', METHOD => 'delete'}

* 将user表的f1列族版本号改为5

        alter 'people', NAME => 'info', VERSIONS => 5

* 启用表

        enable 'user'


### 十一、删除表

        disable 'user'
        drop 'user'


        get 'person', 'rk0001', {FILTER => "ValueFilter(=, 'binary:中国')"}
        get 'person', 'rk0001', {FILTER => "(QualifierFilter(=,'substring:a'))"}
        scan 'person', {COLUMNS => 'info:name'}
        scan 'person', {COLUMNS => ['info', 'data'], FILTER => "(QualifierFilter(=,'substring:a'))"}
        scan 'person', {COLUMNS => 'info', STARTROW => 'rk0001', ENDROW => 'rk0003'}

        scan 'person', {COLUMNS => 'info', STARTROW => '20140201', ENDROW => '20140301'}
        scan 'person', {COLUMNS => 'info:name', TIMERANGE => [1395978233636, 1395987769587]}
        delete 'person', 'rk0001', 'info:name'

        alter 'person', NAME => 'ffff'
        alter 'person', NAME => 'info', VERSIONS => 10


        get 'user', 'rk0002', {COLUMN => ['info:name', 'data:pic']}


        scan 'people', {COLUMNS => 'info',RAW => true, VERSIONS => 3}
