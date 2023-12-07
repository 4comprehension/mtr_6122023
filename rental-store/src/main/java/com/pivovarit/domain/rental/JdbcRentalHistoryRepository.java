package com.pivovarit.domain.rental;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

record JdbcRentalHistoryRepository(JdbcClient jdbcClient) implements RentalHistoryRepository {

    @Override
    public void save(RentalEvent event) {
        jdbcClient.sql("INSERT INTO rental_history(event_type, account_id, movie_id) VALUES(?,?,?)")
          .param(event.type().toString())
          .param(event.accountId())
          .param(event.id().id())
          .update();
    }

    @Override
    public List<PersistedRentalEvent> findAll() {
        return jdbcClient.sql("SELECT * FROM rental_history").query(toRentalEvent()).list();
    }

    @Override
    public List<PersistedRentalEvent> findAllBy(long accountId) {
        return jdbcClient.sql("SELECT * FROM rental_history WHERE account_id = ?")
          .param(accountId)
          .query(toRentalEvent()).list();
    }

    private static RowMapper<PersistedRentalEvent> toRentalEvent() {
        return (rs, rowNum) -> new PersistedRentalEvent(
          rs.getLong("id"),
          EventType.valueOf(rs.getString("event_type")),
          rs.getTimestamp("timestamp").toInstant(),
          rs.getLong("account_id"),
          rs.getLong("movie_id"));
    }
}
