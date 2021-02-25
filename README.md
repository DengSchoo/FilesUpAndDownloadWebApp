# FilesUpAndDownloadWebApp
SpringBoot小项目实战





# 需求

用户登录展示所有的文件，文件如果是图片则在页面中显示图片

完成文件的==下载==和==在线打开==(在线打开不计算下载次数)

在一张页面中完成文件的上传功能，上传的目录要求根据日期每天创建一个文件夹（文件夹统一命名为"yyyy-MM-dd")上传完成后要跳转到查询所有页面。

# 项目规范

1. 项目命名规则： file_xxxx 如 file_input

2. 包名统一

3. 项目中的webroot

   1. 建立files文件夹为上传下载的总文件夹

      更具日期每天创建不同的文件夹 每天上传文件放入不同的文件夹中 `files/2020-2-3`

   2. 两个页面login.html来做用户登录，showAllFIles做文件展示和上传下载文件

4. 项目统一使用UTF-8

# 数据表设计

```sql
create table t_user(
	id int(11) primary key,
    username varchar,
    password varchar
)
-- id oldFileName newFileName ext path size type isImg downCounts uploadTime
create table files(
    
)
```

