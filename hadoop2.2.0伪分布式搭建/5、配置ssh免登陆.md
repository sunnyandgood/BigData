## 配置ssh免密登陆

* 生成ssh免登陆密钥


       cd ~，进入到我的home目录
       cd .ssh/

       ssh-keygen -t rsa （四个回车）
    

  * 执行完这个命令后，会生成两个文件id_rsa（私钥）、id_rsa.pub（公钥）
  * 将公钥拷贝到要免登陆的机器上
  
            cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
            或
            ssh-copy-id -i localhost 
## ssh免密登陆原理

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/hadoop2.2.0%E4%BC%AA%E5%88%86%E5%B8%83%E5%BC%8F%E6%90%AD%E5%BB%BA/img/ssh%E5%85%8D%E7%99%BB%E9%99%86%E5%8E%9F%E7%90%86.png"/></div>
