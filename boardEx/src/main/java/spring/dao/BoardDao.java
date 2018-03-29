package spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import spring.controller.ListDTO;
import spring.controller.WriteDTO;

public class BoardDao extends SqlSessionDaoSupport {
	public List<ListDTO> list(int pageNum) {
		System.out.println("List");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startPage", (pageNum-1) * 10 + 1);
		map.put("endPage", pageNum * 10);
		return getSqlSession().selectList("board.list", map);
	}
	
	public int insert(WriteDTO write) {
		return getSqlSession().insert("board.insert", write);
	}
	
	public int update(WriteDTO update) {
		System.out.println("Update");
		return getSqlSession().update("board.update", update);
	}
	
	public int delete(int no) {
		System.out.println("Delete");
		return getSqlSession().delete("board.delete", no);
	}
	
	public WriteDTO select(int no) {
		return getSqlSession().selectOne("board.select", no);
	}
	
	public int count() {
		return getSqlSession().selectOne("board.count");
	}
}