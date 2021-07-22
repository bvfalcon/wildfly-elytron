/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2014 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
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
package org.wildfly.security.sasl.test;

import static org.junit.Assert.assertEquals;

import java.security.MessageDigest;

import org.junit.Assert;
import org.junit.Test;
import org.wildfly.security.sasl.util.UsernamePasswordHashUtil;

/**
 * Tests of org.wildfly.security.sasl.util.UsernamePasswordHashUtil
 *
 * @author <a href="mailto:jkalina@redhat.com">Jan Kalina</a>
 */
@Deprecated
public class UsernamePasswordHashUtilTest {

    @Test
    public void testGeneratingHex() throws Exception {
        UsernamePasswordHashUtil util = new UsernamePasswordHashUtil();
        String hash = util.generateHashedHexURP("admin", "test", "secret".toCharArray());
        assertEquals("85249daf67f9e3fc2aa2452709d63ff2e0f2a5828a67f7463ae669db162140b6", hash);
    }

    @Test
    public void testGeneratingBytes() throws Exception {
        UsernamePasswordHashUtil util = new UsernamePasswordHashUtil();
        byte[] hash = util.generateHashedURP("admin", "test", "secret".toCharArray());
        Assert.assertArrayEquals(new byte[] { (byte) 0x85, (byte) 0x24, (byte) 0x9d, (byte) 0xaf, (byte) 0x67, (byte) 0xf9,
                (byte) 0xe3, (byte) 0xfc, (byte) 0x2a, (byte) 0xa2, (byte) 0x45, (byte) 0x27, (byte) 0x09, (byte) 0xd6,
                (byte) 0x3f, (byte) 0xf2, (byte) 0xe0, (byte) 0xf2, (byte) 0xa5, (byte) 0x82, (byte) 0x8a, (byte) 0x67,
                (byte) 0xf7, (byte) 0x46, (byte) 0x3a, (byte) 0xe6, (byte) 0x69, (byte) 0xdb, (byte) 0x16, (byte) 0x21,
                (byte) 0x40, (byte) 0xb6 }, hash);
    }

    @Test
    public void testGeneratingHexWithUtf() throws Exception {
        UsernamePasswordHashUtil util = new UsernamePasswordHashUtil();
        String hash = util.generateHashedHexURP("管理员", "测试", "秘密".toCharArray());
        assertEquals("4d9ab08994e02cdd37b3ee159ba0d6b57afa38b8897dbf5f6e268b112b51f145", hash);
    }

    @Test
    public void testGeneratingHexWithAlternativeDigest() throws Exception {
        UsernamePasswordHashUtil util = new UsernamePasswordHashUtil(MessageDigest.getInstance("SHA1"));
        String hash = util.generateHashedHexURP("admin", "test", "secret".toCharArray());
        assertEquals("88b8bf3682564e447713c1ed8b09810df135195a", hash);
    }

    @Test
    public void testGeneratingBlank() throws Exception {
        UsernamePasswordHashUtil util = new UsernamePasswordHashUtil();
        String hash = util.generateHashedHexURP("", "", new char[] {});
        assertEquals("71546855d6279ef70d20909b292c42c2dcb02cd06bde01485da52d13e304ebf4", hash);
    }

}
