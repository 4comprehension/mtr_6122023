package com.pivovarit.domain.rental;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

record JdbcRentalHistoryRepository(JdbcClient jdbcClient) implements RentalHistoryRepository {

    @Override
    public void save(RentalEvent event) {
        jdbcClient.sql("INSERT INTO rental_history(event_type, account_id, movie_id, account_version) VALUES(?,?,?,?)")
          .param(event.type().toString())
          .param(event.accountId())
          .param(event.movieId().id())
          .param(event.accountVersion())
          .update();
    }

    @Override
    public List<PersistedRentalEvent> findAll() {
        return jdbcClient.sql("SELECT * FROM rental_history ORDER BY id").query(toRentalEvent()).list();
    }

    @Override
    public List<PersistedRentalEvent> findAllBy(long accountId) {
        return jdbcClient.sql("SELECT * FROM rental_history WHERE account_id = ? ORDER BY id")
          .param(accountId)
          .query(toRentalEvent()).list();
    }

    @Override
    public List<PersistedRentalEvent> findUnprocessed() {
        return jdbcClient.sql("SELECT * FROM rental_history WHERE processed = false ORDER BY id").query(toRentalEvent()).list();
    }

    @Override
    public int markProcessed(long eventId) {
        return jdbcClient.sql("UPDATE rental_history SET processed = true WHERE id = ?")
          .param(eventId)
          .update();
    }

    private static RowMapper<PersistedRentalEvent> toRentalEvent() {
        return (rs, rowNum) -> new PersistedRentalEvent(
          rs.getLong("id"),
          EventType.valueOf(rs.getString("event_type")),
          rs.getTimestamp("timestamp").toInstant(),
          rs.getLong("account_id"),
          rs.getLong("movie_id"),
          rs.getLong("account_version"));
    }
}
