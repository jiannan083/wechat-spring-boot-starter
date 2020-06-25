# wechart-spring-boot-starter
## 使用方法：  
Step 1. Add the JitPack repository to your build file  
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories> 
```
Step 2. Add the dependency  
```
	<dependency>
	    <groupId>com.github.jiannan083</groupId>
	    <artifactId>wechat-spring-boot-starter</artifactId>
	    <version>Tag</version>
	</dependency>
```
Step 3. "application.properties" use  
```
bfay.wechat.appid=
bfay.wechat.secret=
bfay.wechat.token=
bfay.wechat.template.模板名称1=模板值
bfay.wechat.template.模板名称2=模板值（多个）
```
## 2.0.2
基于springboot 2.1.6.RELEASE  