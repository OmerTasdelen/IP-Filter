package spring.service;

import spring.model.IpFilterRuleModel;
import org.springframework.stereotype.Service;
import spring.utils.IpUtils;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FilterService {

    //Performans açısından bu yöntemle IP servislerini yazmak daha avantajlı

    private static final ConcurrentHashMap<String, IpFilterRuleModel> rules = new ConcurrentHashMap<>();

    public String addRule(String id, IpFilterRuleModel model) {
        try{
            rules.put(id, model);
        }catch (Exception e){
            return  "Save process is Failed"+ e.getMessage();
        }
        return "Successfully Created";
    }

    public String removeRule(String id) {
        try{
            rules.remove(id);
        }catch (Exception e){
            return  "Save process is Failed"+ e.getMessage();
        }
        return "Successfully Created";
    }

    public boolean checkIp(String sourceIp, String destinationIp) {
        IpFilterRuleModel model = new IpFilterRuleModel();
            if (IpUtils.isIpInRange(sourceIp, model.getSourceIpRange()) && IpUtils.isIpInRange(destinationIp, model.getDestinationIpRange())) {
                return model.isAllow();
        }
        return false;
    }

}