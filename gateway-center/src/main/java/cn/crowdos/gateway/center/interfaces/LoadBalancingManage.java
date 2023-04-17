package cn.crowdos.gateway.center.interfaces;

import cn.crowdos.gateway.center.application.IConfigManageService;
import cn.crowdos.gateway.center.application.ILoadBalancingService;
import cn.crowdos.gateway.center.domain.docker.model.aggregates.NginxConfig;
import cn.crowdos.gateway.center.domain.docker.model.vo.LocationVO;
import cn.crowdos.gateway.center.domain.docker.model.vo.UpstreamVO;
import com.alibaba.fastjson.JSON;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @File : LoadBalancingManage.py
 * @Author : LiXin Huang, NWPU
 * @Desc : https://github.com/docker-java/docker-java
 *         https://github.com/docker-java/docker-java/issues/991
 *         https://stackoverflow.com/questions/68634253/how-to-create-mysql-dump-of-docker-image-from-java-level
 * @Email : iHuanglixin@outlook.com
 */

@RestController
@RequestMapping("/wg/admin/load")
public class LoadBalancingManage {



    private Logger logger = LoggerFactory.getLogger(GatewayConfigManage.class);

    @Resource
    private ILoadBalancingService loadBalancingService;

    /**
     * http://localhost:8001/wg/admin/load/copy
     */
    @GetMapping(value = "copy", produces = "application/json;charset=utf-8")
    public void copy() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("docker", "cp", "/nginx.conf", "host:/Users/fuzhengwei/1024/KnowledgePlanet/api-gateway/api-gateway-center/doc/data/nginx/nginx.conf");
        pb.start();
    }

    private static void unTar(TarArchiveInputStream tis,File destFile) throws IOException {
        TarArchiveEntry entry = null;
        while ((entry = tis.getNextTarEntry()) != null) {
            File file = new File(destFile, entry.getName());
            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs();
                IOUtils.copy(tis, new FileOutputStream(file));
            }
        }
    }

    /**
     * http://localhost:8001/wg/admin/load/updateNginxConfig
     */
    @GetMapping(value = "updateNginxConfig", produces = "application/json;charset=utf-8")
    public void updateNginxConfig() {
        List<UpstreamVO> upstreamList = new ArrayList<>();
        upstreamList.add(new UpstreamVO("api01", "least_conn;", Arrays.asList("192.168.1.102:9001;", "192.168.1.102:9002;")));
        upstreamList.add(new UpstreamVO("api02", "least_conn;", Arrays.asList("192.168.1.102:9003;")));

        List<LocationVO> locationList = new ArrayList<>();
        locationList.add(new LocationVO("/api01/", "http://api01;"));
        locationList.add(new LocationVO("/api02/", "http://api02;"));
        NginxConfig nginxConfig = new NginxConfig(upstreamList, locationList);
        try {
            logger.info("刷新Nginx配置文件开始 nginxConfig：{}", JSON.toJSONString(nginxConfig));
            loadBalancingService.updateNginxConfig(nginxConfig);
            logger.info("刷新Nginx配置文件完成");
        } catch (Exception e) {
            logger.error("刷新Nginx配置文件失败", e);
        }
    }

}
