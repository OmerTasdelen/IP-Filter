package spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import spring.entity.IpFilterRule;
import spring.repository.IpFilterRuleRepository;
import spring.service.IpFilterService;
import spring.utils.IpUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IpFilterServiceTest {

	@Mock
	private IpFilterRuleRepository repository;

	@InjectMocks
	private IpFilterService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddRule_Success() {
		IpFilterRule rule = new IpFilterRule();
		when(repository.save(any(IpFilterRule.class))).thenReturn(rule);

		String result = service.addRule(rule);

		assertEquals("Successfully Created", result);
	}

	@Test
	public void testAddRule_Failure() {
		IpFilterRule rule = new IpFilterRule();
		when(repository.save(any(IpFilterRule.class))).thenThrow(new RuntimeException("Database error"));

		String result = service.addRule(rule);

		assertTrue(result.contains("Save process is Failed"));
	}

	@Test
	public void testDeleteRule_Success() {
		doNothing().when(repository).deleteById(anyLong());

		String result = service.deleteRule(1L);

		assertEquals("Successfully Deleted", result);

	}

	@Test
	public void testDeleteRule_Failure() {
		doThrow(new RuntimeException("Database error")).when(repository).deleteById(anyLong());

		String result = service.deleteRule(1L);

		assertTrue(result.contains("Delete process is Failed"));
	}

	@Test
	public void testCheckIp_Allow() {
		IpFilterRule rule = new IpFilterRule();
		rule.setAllow(true);
		rule.setSourceIpRange("192.168.1.0/24");
		rule.setDestinationIpRange("10.0.0.0/24");

		when(repository.findAll()).thenReturn(Arrays.asList(rule));
		when(IpUtils.isIpInRange("192.168.1.1", "192.168.1.0/24")).thenReturn(true);
		when(IpUtils.isIpInRange("10.0.0.1", "10.0.0.0/24")).thenReturn(true);

		String result = service.checkIp("192.168.1.1", "10.0.0.1");

		assertEquals("ALLOW", result);
	}

	@Test
	public void testCheckIp_Deny() {
		IpFilterRule rule = new IpFilterRule();
		rule.setAllow(true);
		rule.setSourceIpRange("192.168.1.0/24");
		rule.setDestinationIpRange("10.0.0.0/24");

		when(repository.findAll()).thenReturn(Arrays.asList(rule));
		when(IpUtils.isIpInRange("192.168.1.1", "192.168.1.0/24")).thenReturn(true);
		when(IpUtils.isIpInRange("10.0.0.1", "10.0.0.0/24")).thenReturn(true);

		String result = service.checkIp("192.168.1.1", "10.0.0.1");

		assertEquals("DENY", result);
	}

	@Test
	public void testCheckIp_NoRule() {
		when(repository.findAll()).thenReturn(Arrays.asList());

		String result = service.checkIp("192.168.1.1", "10.0.0.1");

		assertEquals("NO RULE", result);
	}
}
