package kharkov.kp.gic.dossier.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import kharkov.kp.gic.dossier.domain.SimpleEntity;

@Service
@Profile("jdbc")
public class JdbcTemplateSimpleEntityService implements SimpleEntityService{

//	JdbcTemplate methods:
//	execute, update
//	query, queryForList, queryForMap, queryForObject, queryForRowSet
//	batchUpdate, call
//	get/set FetchSize MaxRows QueryTimeout
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<SimpleEntity> mapper = (rs, row) -> new SimpleEntity(rs.getLong("simple_id"), rs.getString("simple_description"));
	
	@Override
	public Long save(SimpleEntity se) {
		Long newId = jdbcTemplate.queryForObject("select DS_GENERATE_ID.nextval from dual", Long.class);
		String sql = String.format("insert into ds_simple_entity(simple_id, simple_description) values (%s, '%s')", newId, se.getDescription());
		jdbcTemplate.update(sql);
		return newId;
	}

	@Override
	public void update(SimpleEntity se) {
		String sql = String.format("update ds_simple_entity set simple_description = '%s' where simple_id = %s", se.getDescription(), se.getId());
		jdbcTemplate.update(sql);
	}

	@Override
	public SimpleEntity getById(Long id) {
		String sql = String.format("select simple_id, simple_description from ds_simple_entity where simple_id = %s", id);
		List<SimpleEntity> list = jdbcTemplate.query(sql, mapper);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<SimpleEntity> getByName(String substr) {
		String sql = "select simple_id, simple_description from ds_simple_entity where lower(simple_description) like '%" + substr.toLowerCase() + "%' order by 2";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public List<SimpleEntity> getAll(int page, int size) {
		String sql = "select simple_id, simple_description from ds_simple_entity order by 2";
		return jdbcTemplate.query(sql, new ResultSetExtractor<List<SimpleEntity>>() {			
			@Override
			public List<SimpleEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<SimpleEntity> result = new ArrayList<>();
				int rowIndex = -1;
				int from = page * size;
				int until = from + size;
				while (rs.next()) {
					if(++rowIndex < from)
						continue;
					if (rowIndex >= until)
						break;
					SimpleEntity se = new SimpleEntity(rs.getLong("simple_id"), rs.getString("simple_description"));
					result.add(se);					
				}				
				return result;
			}
		});
	}

	@Override
	public List<SimpleEntity> getAll() {
		String sql = "select simple_id, simple_description from ds_simple_entity order by 2";
		return jdbcTemplate.query(sql, mapper);
	}

	@Override
	public Long count() {
		return jdbcTemplate.queryForObject("select count(*) from ds_simple_entity", Long.class); 
	}

	@Override
	public boolean isExists(Long id) {
		String sql = String.format("select count(*) from ds_simple_entity where simple_id = %s", id);
		return jdbcTemplate.queryForObject(sql, Long.class) > 0;
	}

	@Override
	public void delete(Long id) {
		String sql = String.format("delete from ds_simple_entity where simple_id = %s", id);
		jdbcTemplate.update(sql);		
	}

	@Override
	public void deleteByName(String substr) {
		String sql = "delete from ds_simple_entity where lower(simple_description) like '%" + substr.toLowerCase() + "%'";
		jdbcTemplate.update(sql);		
	}

	@Override
	public String whoAmI() {
		return "----------------- Hi! I'm JdbcTemplateSimpleEntityService -----------------------------";
	}

}
