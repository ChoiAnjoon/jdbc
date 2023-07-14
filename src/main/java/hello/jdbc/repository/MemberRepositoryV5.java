package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;


/**
 * JdbcTemplate 추가
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository{

    private final JdbcTemplate template;

    public MemberRepositoryV5(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member){
        String sql = "insert into member(member_id, money) values (?, ?)";
        template.update(sql, member.getMemberId(), member.getMoney());
        return member;
    }

    @Override
    public Member findById(String memberId){
        String sql = "select * from member where member_id = ?";
        // 한건 조회 하는 것 queryForObject()
//        Member member = template.queryForObject(sql, memberRowMapper(), memberId);
//        return member;
        return template.queryForObject(sql, memberRowMapper(), memberId);

    }

    @Override
    public void update(String memberId, int money) {
        String sql = "update member set money=? where member_id=?";
        template.update(sql, money, memberId);
    }

    @Override
    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";
        template.update(sql, memberId);
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }

    // JDBC Template을 쓰면 트랜잭션을 위한 커넥션 동기화, 예외 발생시 스프링 예외 변환기도 자동으로 실행해 준다.

//    private void close(Connection con, Statement stmt, ResultSet rs) {
//
//        JdbcUtils.closeResultSet(rs);
//        JdbcUtils.closeStatement(stmt);
//        // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
//        DataSourceUtils.releaseConnection(con, dataSource); // 커넥션 닫을때
////        JdbcUtils.closeConnection(con);
//    }
//
//    private Connection getConnection() throws SQLException {
//        // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
//        Connection con = DataSourceUtils.getConnection(dataSource); // 커넥션 얻을때
//        log.info("get connection={}, class={}", con, con.getClass());
//        return con;
//    }

}
