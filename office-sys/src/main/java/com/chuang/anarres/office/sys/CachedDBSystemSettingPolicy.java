package com.chuang.anarres.office.sys;

import com.chuang.anarres.office.sys.entity.Config;
import com.chuang.anarres.office.sys.service.IConfigService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CachedDBSystemSettingPolicy implements SystemConfigPolicy {

    private final IConfigService configService;
    private final ConcurrentHashMap<String, String> cached = new ConcurrentHashMap<>();

    public CachedDBSystemSettingPolicy(IConfigService configService) {
        this.configService = configService;
    }

    @Override
    public boolean contains(String key) {
        if(cached.containsKey(key)) {
            return true;
        }
        return get(key).isPresent();
    }

    @Override
    public void clearCached() {
        cached.clear();
    }


    @Override
    public Optional<String> get(String key) {
        if(cached.containsKey(key)) {
            return Optional.of(cached.get(key));
        }

        Optional<String> value = configService.findByCode(key).map(Config::getValue);
        value.ifPresent(v -> cached.put(key, v));
        return value;
    }

    @Override
    public void set(String key, String value, String regex) {
        Config sp = configService.lambdaQuery().eq(Config::getCode, key).one();
        Config entity = new Config();
        if (null == sp) {
            entity.setValue(value);
            configService.updateById(entity);
        } else {
            entity.setCode(key);
            entity.setValue(value);
            entity.setValueRegex(regex);
            configService.save(entity);
        }
        cached.put(key, value);
    }
}
