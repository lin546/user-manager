# User Manager Backend

运行后端（macOS, zsh）:

1. 创建数据库并导入 schema：

```bash
mysql -u root -p < backend/sql/schema.sql
```

2. 在 `backend/src/main/resources/application.properties` 中更新数据库用户名和密码。

3. 构建并运行：

```bash
cd backend
mvn clean package
mvn spring-boot:run
```

后端默认监听 `http://localhost:8080`，API 基础路径为 `/api/users`。
