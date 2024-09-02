package spring;

import spring.model.IpFilterRuleModel;
import org.junit.jupiter.api.Test;
import spring.service.FilterService;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterServiceTest {

    @Test
    public void testAddAndCheckRule() {
        FilterService service = new FilterService();
        IpFilterRuleModel rule = new IpFilterRuleModel("192.168.1.0/24", "10.0.0.0/24", true);

        service.addRule("1", rule);
        assertTrue(service.checkIp("192.168.1.1", "10.0.0.1"));
    }

    @Test
    public void testRemoveRule() {
        FilterService service = new FilterService();
        IpFilterRuleModel rule = new IpFilterRuleModel("192.168.1.0/24", "10.0.0.0/24", true);

        service.addRule("1", rule);
        service.removeRule("1");
        assertFalse(service.checkIp("192.168.1.1", "10.0.0.1"));
    }

    @Test
    public void testDenyRule() {
        FilterService service = new FilterService();
        IpFilterRuleModel rule = new IpFilterRuleModel("192.168.1.0/24", "10.0.0.0/24", false);

        service.addRule("1", rule);
        assertFalse(service.checkIp("192.168.1.1", "10.0.0.1"));
    }
}
