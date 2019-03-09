package org.mengyun.tcctransaction.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "tcc.feign")
public class TccFeignConfigProperties {
    /**
     * 是否开启tcc springcloud feign集成
     */
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}