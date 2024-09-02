package spring.model;

import lombok.Data;

@Data
public class IpFilterRuleModel {

    public IpFilterRuleModel(String sourceIpRange, String destinationIpRange, boolean allow) {
        this.sourceIpRange = sourceIpRange;
        this.destinationIpRange = destinationIpRange;
        this.allow = allow;
    }

    public IpFilterRuleModel() {
        this.sourceIpRange = sourceIpRange;
        this.destinationIpRange = destinationIpRange;
        this.allow = allow;
    }

    private String sourceIpRange;

    private String destinationIpRange;

    private boolean allow;
}
