/*
 * Ch4oS Numismatics rename regression tests
 * Copyright (c) 2026 Cha0sCollective
 * SPDX-License-Identifier: LGPL-3.0-or-later
 */
package dev.ithundxr.createnumismatics.content.backend;

import dev.ithundxr.createnumismatics.content.backend.sub_authorization.SubAccount;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SubAccountRenameTest {
    @BeforeAll
    static void bootstrapMinecraft() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();
    }

    @ParameterizedTest
    @EnumSource(BankAccount.Type.class)
    void clientCanRenameWithoutAPlatformNetworkOrLocalServer(BankAccount.Type type) {
        BankAccount parent = new ClientAccount(type);
        SubAccount account = new SubAccount(parent, "Sub Account", UUID.randomUUID());

        assertDoesNotThrow(() -> account.setLabel("Factory"));
        assertEquals("Factory", account.getLabel());
        assertDoesNotThrow(() -> account.setLabel("Warehouse"));
        assertEquals("Warehouse", account.getLabel());
    }

    @Test
    void changingLabelPreservesAccountIdentityAndFinancialSettings() {
        BankAccount parent = new ClientAccount(BankAccount.Type.PLAYER);
        UUID authorizationId = UUID.randomUUID();
        UUID trustedPlayer = UUID.randomUUID();
        SubAccount account = new SubAccount(parent, "Sub Account", authorizationId);
        account.getTotalLimit().setLimit(100, true);
        account.getTotalLimit().spend(25);
        account.getTrustList().add(trustedPlayer);
        var authorizationType = account.getAuthorizationType();

        account.setLabel("Factory");
        account.setLabel("Factory");

        assertEquals("Factory", account.getLabel());
        assertEquals(authorizationId, account.getAuthorizationID());
        assertEquals(12345, parent.getBalance());
        assertEquals(100, account.getTotalLimit().getLimit());
        assertEquals(25, account.getTotalLimit().getSpent());
        assertEquals(java.util.List.of(trustedPlayer), account.getTrustList());
        assertEquals(authorizationType, account.getAuthorizationType());
    }

    private static final class ClientAccount extends BankAccount {
        private ClientAccount(Type type) {
            super(UUID.randomUUID(), type, 12345, true);
        }
    }
}
