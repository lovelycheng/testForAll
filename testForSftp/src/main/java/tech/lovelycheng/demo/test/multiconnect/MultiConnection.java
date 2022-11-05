package tech.lovelycheng.demo.test.multiconnect;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chengtong
 * @date 2022/7/29 17:35
 */
@Slf4j
public class MultiConnection {
    public static ConcurrentHashMap<SessionConfig, Session> sessionMap = new ConcurrentHashMap();

    public static ConcurrentHashMap<Session, List<Channel>> sessionChannelsMap = new ConcurrentHashMap();

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class SessionConfig {
        private String userName;
        private String host;
        private Integer port;
        private boolean useIdentityFile;
        private String identityFile;
    }

    public static void main(String[] args) {
        createConnection();
        createConnection();
    }

    private static void createConnection() {

        Thread s = new Thread(() -> {
            JSch jSch = new JSch();
            Session sshSession = null;
            SessionConfig sessionConfig = new SessionConfig("root", "47.104.232.140", 22,true,"/Users/chengtong/.ssh/windside214.pem");
            try {
                sshSession = sessionMap.computeIfAbsent(sessionConfig, key -> {
                    Session sshSession1 = null;
                    try {
                        sshSession1 = jSch.getSession(key.getUserName(), key.getHost(),
                            key.getPort());
                        if(key.isUseIdentityFile()){
                            jSch.addIdentity(key.getIdentityFile());
                        }else {
                            sshSession1.setPassword("LOVEYXL123l");
                        }
                        Properties sshConfig = new Properties();
                        sshConfig.put("StrictHostKeyChecking", "no");
                        sshSession1.setConfig(sshConfig);
                        log.debug("Session connected before");
                        if (!sshSession1.isConnected()) {
                            log.error("threadid:{} connected", Thread.currentThread()
                                .getId());
                            sshSession1.connect(5000);
                        }
                    } catch (JSchException e) {
                        e.printStackTrace();
                    }
                    return sshSession1;
                });

                log.debug("Session connected.");
                log.debug("Opening Channel.");

                ChannelSftp channel = null;
                try {
                    channel = (ChannelSftp) sshSession.openChannel("sftp");
                    if (channel != null) {
                        channel.connect();
                        channel.cd("/");
                        log.debug("channel success");
                    }
                } catch (Exception e) {
                    log.error("channel open fail");

                } finally {
                    if (channel != null) {
                        log.info("channel disconnect");
                        channel.disconnect();
                    }
                }
            } catch (Exception e) {
                log.error("threadid:{},error:{}", Thread.currentThread()
                    .getId(), e.getMessage());
            } finally {
                if (sshSession != null) {
                    sshSession.disconnect();
                }
            }
        });
        s.start();
    }
}
