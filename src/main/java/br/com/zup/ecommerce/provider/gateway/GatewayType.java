package br.com.zup.ecommerce.provider.gateway;

import java.util.Optional;

public enum GatewayType {

    PAYPAL,
    PAGSEGURO;

    public static Optional<GatewayType> findByName(String name) {
        GatewayType gatewayType = null;

        for (GatewayType types : values()) {
            if (types.name().equalsIgnoreCase(name)) {
                gatewayType = types;
                break;
            }
        }

        return Optional.ofNullable(gatewayType);
    }

}
