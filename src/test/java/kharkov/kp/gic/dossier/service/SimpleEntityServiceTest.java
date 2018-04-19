package kharkov.kp.gic.dossier.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import kharkov.kp.gic.dossier.domain.SimpleEntity;

public abstract class SimpleEntityServiceTest {

	@Autowired
	protected SimpleEntityService service;
	
	@Test
	public void test() {
		System.out.println(service.whoAmI());
		List<SimpleEntity> list = service.getAll();
		assertTrue(list.size() > 0);
		for(SimpleEntity se : list) {
			System.out.println("I'm " + se);
		}
		assertTrue(list.size() == service.count());
	}
	
	@Test
	public void test2() {
		List<SimpleEntity> list = service.getByName("ро");
		assertTrue(list.size() == 3);
		assertEquals(list.get(0).getDescription(), "крокодил");
		assertEquals(list.get(1).getDescription(), "носорог");
		assertEquals(list.get(2).getDescription(), "юрок");
	}
	
	@Test
	public void test3() {
		List<SimpleEntity> list = service.getAll(0, 5);
		assertTrue(list.size() == 5);		
		assertEquals(list.get(0).getDescription(), "аист");
		assertEquals(list.get(1).getDescription(), "белка");
		assertEquals(list.get(2).getDescription(), "верблюд");
		assertEquals(list.get(3).getDescription(), "гиппопотам");
		assertEquals(list.get(4).getDescription(), "дельфин");
		
		//List<SimpleEntity> a = service.getAll(0, 3);
		//List<SimpleEntity> aa = service.getAll(1, 3);
		//List<SimpleEntity> aaa = service.getAll(2, 3);
		
		list = service.getAll(4, 3);
		assertTrue(list.size() == 3);	
		assertEquals(list.get(0).getDescription(), "носорог");
		assertEquals(list.get(1).getDescription(), "олень");
		assertEquals(list.get(2).getDescription(), "панда");
		
		list = service.getAll(4, 10);
		assertTrue(list.size() == 0);
	}
	
	@Test
	public void test4() {
		List<SimpleEntity> list = service.getAll();
		for(SimpleEntity se : list) {
			SimpleEntity same = service.getById(se.getId());
			assertNotNull(same);
			assertTrue(se.getDescription().equals(same.getDescription()));
			assertTrue(se.getId().equals(same.getId()));
		}		
	}
	
	@Test
	public void test5() {
		List<SimpleEntity> list = service.getAll();
		Long all = service.count();
		assertTrue(all == list.size());
		for(SimpleEntity se : list) {
			assertTrue(service.isExists(se.getId()));
		}
		assertFalse(service.isExists(0L));
		assertFalse(service.isExists(1000L));
		assertFalse(service.isExists(-1L));
	}
	
	@Test
	public void test6() {
		Long before = service.count();
		assertTrue(service.getByName("fucked").size() == 0);
		// add three animals
		Long racoonId = service.save(new SimpleEntity("fucked racoon"));
		Long babooId = service.save(new SimpleEntity("fucked baboo"));
		Long snakeId = service.save(new SimpleEntity("fucked snake"));
		assertTrue(service.count() - before == 3);
		assertTrue(service.getByName("fucked").size() == 3);
		
		SimpleEntity snake = service.getById(snakeId);
		assertNotNull(snake);
		assertTrue(snake.getId().equals(snakeId));
		assertTrue(snake.getDescription().equals("fucked snake"));
		// update racoon
		SimpleEntity racoon = service.getById(racoonId);
		racoon.setDescription("very fucked racoon");
		service.update(racoon);
		SimpleEntity racoonalso = service.getById(racoonId);
		assertTrue(racoon != racoonalso);
		assertTrue(racoonalso.getDescription().equals("very fucked racoon"));
		// delete snake
		assertTrue(service.getById(snakeId) != null);
		service.delete(snakeId);
		assertTrue(service.getById(snakeId) == null);
		assertTrue(service.count() - before == 2);
		assertTrue(service.getByName("fucked").size() == 2);
		// delete all fucked animals
		service.deleteByName("fucked");
		assertTrue(service.getById(racoonId) == null);
		assertTrue(service.getById(babooId) == null);
		assertTrue(service.getById(snakeId) == null);
		assertTrue(service.getByName("fucked").size() == 0);
		assertTrue(service.count() == before);
	}
	

}
