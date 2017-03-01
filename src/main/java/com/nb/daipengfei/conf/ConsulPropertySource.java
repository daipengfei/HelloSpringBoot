package com.nb.daipengfei.conf;

import com.netflix.config.ConfigurationManager;
import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import org.springframework.core.env.MapPropertySource;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by daipengfei
 * on 2017/2/26.
 */
public class ConsulPropertySource extends MapPropertySource {
	public ConsulPropertySource(String name, Map<String, Object> source) {
		super(name, source);
		PolledConfigurationSource configurationSource = new ConsulConfigSource(name);
		FixedDelayPollingScheduler scheduler = new FixedDelayPollingScheduler();
		DynamicConfiguration configuration = new DynamicConfiguration(configurationSource,scheduler);
		ConfigurationManager.install(configuration);
		Iterator keys = configuration.getKeys();
		while(keys.hasNext()){
			String key  = (String)keys.next();
			this.source.put(key,configuration.getProperty(key));
		}
	}
}
