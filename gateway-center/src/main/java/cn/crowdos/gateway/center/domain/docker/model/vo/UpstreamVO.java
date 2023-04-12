package cn.crowdos.gateway.center.domain.docker.model.vo;

import java.util.List;

/**
 * @File : UpstreamVO.py
 * @Author : LiXin Huang, NWPU
 * @Desc : 设定负载均衡的服务器列表
 * @Email : iHuanglixin@outlook.com
 */

public class UpstreamVO {

    /** 名称 */
    private String name;
    /** 复杂策略；least_conn;、 ip_hash; */
    private String strategy;
    /** 服务列表；192.168.1.102:9001; */
    private List<String> servers;

    public UpstreamVO(String name, String strategy, List<String> servers) {
        this.name = name;
        this.strategy = strategy;
        this.servers = servers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

}
