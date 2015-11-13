/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2015 Red Hat, Inc., and individual contributors
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
package org.wildfly.security.auth.provider.ldap;

import org.wildfly.security.auth.server.RealmUnavailableException;
import org.wildfly.security.auth.server.SecurityRealm;
import org.wildfly.security.auth.server.SupportLevel;
import org.wildfly.security.evidence.Evidence;

/**
 * An individual evidence verifier to associate with an LDAP {@link SecurityRealm}, multiple verifiers
 * can be associated with the realm allowing for different verification strategies to be applied to different named credentials.
 *
 * @author <a href="mailto:darran.lofthouse@jboss.com">Darran Lofthouse</a>
 */
interface EvidenceVerifier {

    /**
     * Get the {@link SupportLevel} for the level of evidence validation support for the named credential.
     *
     * @param contextFactory the {@link DirContextFactory} to access the LDAP server
     * @param evidenceType the evidence type (must not be {@code null})
     * @param algorithmName the evidence algorithm name or {@code null} if none
     * @return the level of support for the named credential
     * @throws RealmUnavailableException if the realm is currently unable to handle requests
     */
    SupportLevel getEvidenceVerifySupport(DirContextFactory contextFactory, Class<? extends Evidence> evidenceType, String algorithmName) throws RealmUnavailableException;

    /**
     * Obtain an {@link IdentityEvidenceVerifier} to verify the evidence for a specific identity.
     *
     * Note: By this point referrals relating to the identity should have been resolved so the {@link DirContextFactory} should
     * be suitable for use with the supplied {@code distinguishedName}
     *
     * @param contextFactory the {@link DirContextFactory} to use to connect to LDAP.
     * @param distinguishedName the distinguished name of the identity.
     * @return An {@link IdentityEvidenceVerifier} for the specified identity identified by their distinguished name.
     */
    IdentityEvidenceVerifier forIdentity(DirContextFactory contextFactory, String distinguishedName) throws RealmUnavailableException;

}
