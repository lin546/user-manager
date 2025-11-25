# User Manager (前后端分离)

项目目录:

- `frontend/` - Vue3 + Vite 前端
- `backend/` - Spring Boot 后端（MyBatis + MySQL）

快速开始（macOS, zsh）:

1. 初始化数据库：

```bash
# 登录 MySQL 并运行 schema
mysql -u root -p < backend/sql/schema.sql
```

2. 启动后端：

```bash
cd backend
# 编辑 application.properties，设置正确的数据库用户名/密码
mvn clean package
mvn spring-boot:run
```

后端默认监听 `http://localhost:8080`，API 路径以 `/api/users` 开头。

3. 启动前端：

```bash
cd frontend
npm install
npm run dev
```

前端开发服务器默认 `http://localhost:3000`，前端请求基地址为 `http://localhost:8080/api`。

简单说明：
- 前端页面位于 `frontend/src/views`。
- 后端主要代码在 `backend/src/main/java/com/example/usermanager`。
- MyBatis 映射文件在 `backend/src/main/resources/mapper`。

