/*
 * Copyright 2011 gitblit.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gitblit.tests;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.gitblit.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;

public class JsonUtilsTest {

	@Test
	public void testSerialization() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "alligator");
		map.put("b", "bear");
		map.put("c", "caterpillar");
		map.put("d", "dingo");
		map.put("e", "eagle");
		String json = JsonUtils.toJsonString(map);
		assertEquals(
				"{\n  \"d\": \"dingo\",\n  \"e\": \"eagle\",\n  \"b\": \"bear\",\n  \"c\": \"caterpillar\",\n  \"a\": \"alligator\"\n}",
				json);
		Map<String, String> map2 = JsonUtils.fromJsonString(json,
				new TypeToken<Map<String, String>>() {
				}.getType());
		assertEquals(map, map2);

		SomeJsonObject someJson = new SomeJsonObject();
		json = JsonUtils.toJsonString(someJson);
		SomeJsonObject someJson2 = JsonUtils.fromJsonString(json, SomeJsonObject.class);
		assertEquals(someJson.name, someJson2.name);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
		assertEquals(df.format(someJson.date), df.format(someJson2.date));
	}

	private class SomeJsonObject {
		Date date = new Date();
		String name = "myJson";
	}
}