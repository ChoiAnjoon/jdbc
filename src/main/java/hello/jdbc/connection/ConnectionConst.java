package hello.jdbc.connection;

public abstract class ConnectionConst { // 객체 생성 못하게 abstract로 만들기(추상클래스)

    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
