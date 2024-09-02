package spring.utils;


import org.apache.commons.net.util.SubnetUtils;

public class IpUtils {

    public static boolean isIpInRange(String ip, String cidr) {
        SubnetUtils utils = new SubnetUtils(cidr);
        return utils.getInfo().isInRange(ip);
    }
}
