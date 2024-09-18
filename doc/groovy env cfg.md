   <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy-all</artifactId>
            <version>3.0.9</version> <!-- 根据需要选择合适版本 -->
        </dependency>
        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy</artifactId>
            <version>3.0.9</version> <!-- 根据需要选择版本 -->
        </dependency>



if cant find calss maybe need chge maven soure

open setting.xml

add mirror site

  <mirror>
            <id>repo1 maven2</id>
            <name>Aliyun Maven Mirror22</name>
            <url>https://repo1.maven.org/maven2 c</url>
            <mirrorOf>central</mirrorOf>
        </mirror>


      <mirror>
            <id>aliyun</id>
            <name>Aliyun Maven Mirror</name>
            <url>https://maven.aliyun.com/repository/public</url>
            <mirrorOf>central</mirrorOf>
        </mirror>
    <mirror>