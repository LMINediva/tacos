package com.lc.tacos.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 * @date 2023/1/18 21:21
 */
@Data
@ConfigurationProperties(prefix = "tacocloud.email")
@Component
public class EmailProperties {
    private String username;
    private String password;
    private String host;
    private String mailbox;
    private long pollRate = 30000;

    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s%s",
                this.username, this.password, this.host, this.mailbox);
    }
}
