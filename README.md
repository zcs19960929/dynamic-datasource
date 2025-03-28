# 动态数据源示例项目

## 简介

这是一个基于Spring Boot实现的动态多数据源切换示例项目。通过集成Baomidou的dynamic-datasource组件，实现了在应用运行时灵活切换不同数据源的功能，解决了多数据源管理的复杂问题。本项目同时展示了从基础多数据源配置到分库分表、SQL监控以及自定义分布式ID生成的多种高级应用场景。

## 主要特性

- ✅ 多数据源配置与动态切换
- ✅ 注解驱动的简便数据源切换方式
- ✅ 支持分布式事务处理（跨库事务）
- ✅ 多种连接池支持（Hikari/Druid）
- ✅ SQL监控与分析
- ✅ 分库分表策略支持
- ✅ 分布式ID生成
- ✅ 完整的示例代码与使用场景

## 技术框架

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 8 | 开发语言 |
| Spring Boot | 2.2.6.RELEASE | 应用框架 |
| MyBatis-Plus | 3.4.0 | ORM框架 |
| Dynamic Datasource | 4.3.1 | 动态数据源组件 |
| ShardingSphere | 5.2.0 | 分库分表组件 |
| P6spy | 1.10.0 | SQL分析打印工具 |
| MySQL | 8.0.20 | 数据库 |

## 快速上手

### 前置条件

- JDK 8或更高版本
- Maven 3.5+
- MySQL数据库服务
- 创建以下数据库：`test1`、`test2`、`test3`

### 运行项目

1. 克隆项目到本地
2. 修改对应配置文件中的数据库连接信息
3. 执行以下命令启动项目（根据需要选择配置文件）：

```bash
# 基础多数据源配置
mvn spring-boot:run -Dspring.profiles.active=local

# SQL监控与分析
mvn spring-boot:run -Dspring.profiles.active=p6spy

# 分库分表配置
mvn spring-boot:run -Dspring.profiles.active=shardingsphere

# 自定义分布式ID生成
mvn spring-boot:run -Dspring.profiles.active=generate
```

## 配置模式说明

本项目提供了四种不同的配置模式，从简单到复杂逐步展示了动态数据源的多种应用场景：

### 1. 基础多数据源配置（local）

最简单的多数据源配置，演示了如何配置和使用多个数据源。使用原生的MySQL驱动，适合初步了解多数据源架构的场景。

```properties
# 主数据源（默认）
spring.datasource.dynamic.datasource.master.url=jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.dynamic.datasource.master.driver-class-name=com.mysql.cj.jdbc.Driver

# 从数据源
spring.datasource.dynamic.datasource.slave_1.url=jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.dynamic.datasource.slave_1.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 2. SQL监控与分析（p6spy）

在基础配置之上，增加了P6Spy组件用于SQL性能监控和分析。可以记录SQL执行时间、参数等信息，方便开发和调试。

```properties
# 使用P6Spy代理驱动
spring.datasource.dynamic.datasource.master.url=jdbc:p6spy:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf-8&useSSL=false
spring.datasource.dynamic.datasource.master.driver-class-name=com.p6spy.engine.spy.P6SpyDriver

# 配置P6Spy日志格式
decorator.datasource.p6spy.enable-logging=true
decorator.datasource.p6spy.log-format=\ntime:%(executionTime) || sql:%(sql)\n
```

同时此配置还引入了基础的ShardingSphere分库分表配置，展示了分片规则与表映射关系。

### 3. 分库分表配置（shardingsphere）

在SQL监控的基础上，添加了完整的ShardingSphere分库分表配置，包括：分片规则、分片算法和内置的分布式ID生成（雪花算法）。

```properties
# 配置分布式ID生成策略（雪花算法）
spring.shardingsphere.rules.sharding.default-key-generate-strategy.column=id
spring.shardingsphere.rules.sharding.default-key-generate-strategy.key-generator-name=snowflake
spring.shardingsphere.rules.sharding.key-generators.snowflake.type=SNOWFLAKE
spring.shardingsphere.rules.sharding.key-generators.snowflake.props.worker-id=1

# 分片规则配置
spring.shardingsphere.rules.sharding.tables.t_order.actual-data-nodes=db$->{0..2}.t_order_$->{0..1}
spring.shardingsphere.rules.sharding.tables.t_order.database-strategy.standard.sharding-column=tenant_id
```

### 4. 自定义分布式ID生成（generate）

在分库分表配置的基础上，实现和集成了自定义的分布式ID生成器（取代雪花算法），展示了如何扩展ShardingSphere的SPI机制。

```properties
# 使用自定义ID生成器
spring.shardingsphere.rules.sharding.key-generators.snowflake.type=MY_GENERATE_ID
```

自定义分布式ID生成器实现：

```java
public class MyKeyGenerateAlgorithm implements KeyGenerateAlgorithm {
    private static AtomicLong counter = new AtomicLong(0);
    
    @Override
    public Comparable<?> generateKey() {
        return counter.incrementAndGet();
    }
    
    @Override
    public String getType() {
        return "MY_GENERATE_ID";
    }
}
```

## 使用方式

### 1. 在Mapper层切换数据源

使用`@DS`注解在Mapper接口上指定数据源：

```java
@DS("master")
public interface StudentMapper extends BaseMapper<StudentPO> {
}

@DS("slave_1")
public interface TeacherMapper extends BaseMapper<TeacherPO> {
}
```

### 2. 在Service层方法上切换数据源

```java
@Service
public class StudentServiceImpl implements StudentService {
    
    @DS("slave_1") // 指定使用slave_1数据源
    @Override
    public StudentPO getByCustomMethod(Long id) {
        return studentMapper.selectById(id);
    }
}
```

### 3. 跨数据源事务处理

使用`@DSTransactional`实现跨库事务：

```java
@Service
public class TransactionalServiceImpl implements TransactionalService {

    @Resource
    private StudentService studentService; // 使用master数据源
    
    @Resource
    private TeacherService teacherService; // 使用slave_1数据源
    
    @Override
    @DSTransactional // 确保跨库事务的一致性
    public void saveTeacherAndStudent(TeacherPO teacher, StudentPO student) {
        teacherService.save(teacher);
        studentService.save(student);
    }
}
```

## 示例接口

| 接口 | 请求方式 | 功能描述 |
|------|--------|----------|
| `/test/hello` | GET | 简单测试接口 |
| `/test/student?id=1` | GET | 从master数据源查询学生信息 |
| `/test/teacher?id=1` | GET | 从slave_1数据源查询教师信息 |
| `/test/saveTeacherAndStudent` | POST | 跨数据源事务测试 |

## 项目结构

```
dynamic-datasource
├── src/main/java/com/example/dynamicdatasource
│   ├── aspect          # 切面定义
│   ├── base            # 基础组件
│   ├── config          # 配置类和自定义分布式ID生成器
│   ├── constant        # 常量定义
│   ├── controller      # 控制器
│   ├── entity          # 实体类
│   ├── filter          # 过滤器
│   ├── mapper          # MyBatis Mapper
│   ├── service         # 服务接口及实现
│   └── util            # 工具类
└── src/main/resources
    ├── application.properties           # 主配置文件
    ├── application-local.properties     # 基础多数据源配置
    ├── application-p6spy.properties     # SQL监控与分析配置
    ├── application-shardingsphere.properties # 分库分表配置
    ├── application-generate.properties  # 自定义分布式ID配置
    ├── application-daily.properties     # 日常环境配置
    ├── application-pre.properties       # 预发环境配置
    └── application-prod.properties      # 生产环境配置
```

## 配置详解

### 连接池设置

```properties
# 全局连接池配置(Hikari)
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.dynamic.hikari.min-idle=10
spring.datasource.dynamic.hikari.max-pool-size=100
spring.datasource.dynamic.hikari.connection-timeout=60

# 单独为数据源配置连接池参数
spring.datasource.dynamic.datasource.slave_1.hikari.min-idle=5
```

### 分片规则配置

```properties
# 分片算法配置
spring.shardingsphere.rules.sharding.sharding-algorithms.database-inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.database-inline.props.algorithm-expression=db${tenant_id % 3}

spring.shardingsphere.rules.sharding.sharding-algorithms.table-inline.type=INLINE
spring.shardingsphere.rules.sharding.sharding-algorithms.table-inline.props.algorithm-expression=t_order_${tenant_id % 2}

# 表配置
spring.shardingsphere.rules.sharding.tables.t_order.actual-data-nodes=db$->{0..2}.t_order_$->{0..1}
spring.shardingsphere.rules.sharding.tables.t_order.database-strategy.standard.sharding-column=tenant_id
spring.shardingsphere.rules.sharding.tables.t_order.database-strategy.standard.sharding-algorithm-name=database-inline
```

## 常见问题

1. **数据源切换失效？**
   - 检查是否存在事务嵌套，内部方法的@DS注解在事务中会失效
   - 确认切换的数据源名称是否正确配置

2. **跨库事务不生效？**
   - 确认使用的是`@DSTransactional`而非`@Transactional`
   - 检查事务传播行为是否正确

3. **SQL监控日志不显示？**
   - 确认已启用P6Spy配置(`-Dspring.profiles.active=p6spy`)
   - 检查日志级别设置

4. **分布式ID生成问题？**
   - ShardingSphere模式下检查worker-id配置
   - 自定义生成器模式下确认SPI加载正确

## 参考资料

- [动态数据源文档](https://dynamic-datasource.com/)
- [MyBatis-Plus文档](https://baomidou.com/)
- [ShardingSphere文档](https://shardingsphere.apache.org/document/current/cn/overview/)
- [P6Spy GitHub](https://github.com/p6spy/p6spy)
- [Spring Boot文档](https://spring.io/projects/spring-boot)

## 许可证

本项目采用[Apache License 2.0](LICENSE)开源许可
