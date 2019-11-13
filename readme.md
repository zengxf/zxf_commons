# commons 工具包

## 发布&引用
发布命令：
```
gradle publishToMavenLocal -Dfile.encoding=UTF-8
gradle publish -Dfile.encoding=UTF-8
```

引用：
```
mavenLocal()
compile group: 'cn.zxf', name: '${name}', version: '${commons-version}'
```

## commons-basic
一些常用的工具类汇总

## commons-generator
代码生成器

## commons-json
json 工具类
10w 次解析耗时 1.2 s
10w 次格式化耗时 0.6 s

## commons-data-mining
数据挖掘小轮子