package spring.service;

import org.springframework.stereotype.Service;
import spring.entity.IpFilterRule;
import spring.repository.IpFilterRuleRepository;
import spring.utils.IpUtils;

import java.util.List;

@Service
public class IpFilterService {

    //IP adreslerini tutmak için DB operasyonlarına gerek yok biliyorum fakat DBye kayıt atma veriyi ordan çekip
    //istenilen şekilde servisileri yazma örneğinide göstermek için bununda kodlarını buraya ekliyorum

    private final IpFilterRuleRepository repository;

    public IpFilterService(IpFilterRuleRepository repository) {
        this.repository = repository;
    }

    public String addRule(IpFilterRule rule) {
        try{
            repository.save(rule);
        }catch (Exception e){
           return  "Save process is Failed"+ e.getMessage();
        }
        return "Successfully Created";
    }

    public String deleteRule(Long id) {
        try{
            repository.deleteById(id);
            return "Successfully Deleted";
        }catch (Exception e){
            return  "Delete process is Failed" + e.getMessage();
        }

    }

    public String checkIp(String sourceIp, String destinationIp) {
        List<IpFilterRule> rules = repository.findAll();
        for (IpFilterRule rule : rules) {
            if (IpUtils.isIpInRange(sourceIp, rule.getSourceIpRange()) &&
                    IpUtils.isIpInRange(destinationIp, rule.getDestinationIpRange())) {
                return rule.isAllow() ? "ALLOW" : "DENY";
            }
        }
        return "NO RULE";
    }
}
