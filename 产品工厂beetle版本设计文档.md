## 声明
   - 为了编写文档快捷，使用一些便捷的词汇单词表达意思
     - PBean 指的是参数的实体类
## 1. 编写目的
   - 为了让自己思路在研发新版本的时候思路清晰
   - 为了让自己目标明确
## 2.设计思想
   - 本次在太平洋保险实施项目过程中发现现有产品工厂的不足  
### 2.1 不足点
   - 在实施的时候，如果想要注册产品工厂需要写注册类非常的繁琐麻烦
   - 在实施的过程中，需要开发来开发大量的参数页面，导致想你的开发周期特别的长
   - 参数的存储为xml，查询的时候需要让xml转换为java 对象，然后再转换为json 这样十分耗费性能
   - 表结构十分混乱不清晰
   - 参数需要在修改和删除的时候进行提示
### 2.2 优化思路
   - 去除注册类，编写对应的注解，在容器启动的时候通过注解+包扫描把PBean扫描到集合中进行统一的管理
   - 统一参数页面，所有的参数页面统一管理，通过jsp js html 等来根据PBean的结构来生成对应的参数页面
   - 去除开发，把产品工厂做成一个独立的产品，无需要项目组维护开发，（需要让参数类的编写通过上传，或者是通过编写保存到本地磁盘或者数据库，自己写一个classLoader 来加载参数）
   - 去除xml 结构化，通过json 形式保存
   - 重构表结构
   - 参数在修改删除进行提示判断
## 3.本地版本迭代内容（目标）
   1. 重构表结构
   2. 去xml化
   3. 去注册类
   4. 去开发形成真正的产品
   5. 误删除，修改
    
