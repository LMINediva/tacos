package com.lc.tacos.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * SpringSecurity反序列化类
 *
 * @author DELL
 * @date 2023/1/20 17:08
 */
public class CustomAuthorityDeserializer
        extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        JsonNode jsonNode = mapper.readTree(parser);
        LinkedList<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
            JsonNode next = elements.next();
            JsonNode authority = next.get("authority");
            //将得到的值放入链表，最终返回该链表
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return grantedAuthorities;
    }
}
