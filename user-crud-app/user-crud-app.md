user-crud-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           ├── action/
│       │           │   ├── UserCreateAction.java
│       │           │   ├── UserDeleteAction.java
│       │           │   ├── UserEditAction.java
│       │           │   ├── UserListAction.java
│       │           │   └── UserSaveAction.java
│       │           ├── dao/
│       │           │   ├── UserDAO.java
│       │           │   └── impl/
│       │           │       └── UserDAOImpl.java
│       │           ├── form/
│       │           │   └── UserForm.java
│       │           ├── model/
│       │           │   └── User.java
│       │           └── service/
│       │               ├── UserService.java
│       │               └── impl/
│       │                   └── UserServiceImpl.java
│       └── resources/
│           ├── User.hbm.xml
│           └── ApplicationResources.properties
└── webapp/
    ├── WEB-INF/
    │   ├── jsp/
    │   │   ├── userForm.jsp
    │   │   └── userList.jsp
    │   ├── applicationContext.xml
    │   ├── struts-config.xml
    │   ├── validation.xml
    │   ├── validator-rules.xml
    │   └── web.xml
    ├── css/
    │   └── style.css
    └── index.jsp
