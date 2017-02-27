package com.nb.daipengfei.conf;

import com.google.common.base.Optional;
import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import org.apache.commons.lang3.StringUtils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by daipengfei
 * on 2017/2/26.
 */

public class ConsulConfigSource implements PolledConfigurationSource {
	private final String name;

	public ConsulConfigSource(String name) {
		this.name = name;
	}

	@Override
	public PollResult poll(boolean initial, Object checkPoint) throws Exception {
		Consul consul = Consul.builder().build();
		KeyValueClient kvClient = consul.keyValueClient();
		Optional<String> kvOpt = kvClient.getValueAsString(name);
		String kvStr = StringUtils.EMPTY;
		if (kvOpt.isPresent()) {
			kvStr = kvOpt.get();
		}

		Properties props = new Properties();
		props.load(new StringReader(kvStr));

		Map<String, Object> propMap = new HashMap<>();
		for (Object key : props.keySet()) {
			propMap.put((String) key, props.get(key));
		}
		return PollResult.createFull(propMap);
	}

}
