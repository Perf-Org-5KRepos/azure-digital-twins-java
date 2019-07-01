/**
 * Copyright (c) Microsoft Corporation. Licensed under the MIT License.
 */
package com.microsoft.twins.reflector.topology;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import com.microsoft.twins.model.DeviceRetrieve;
import com.microsoft.twins.reflector.model.IngressMessage;
import com.microsoft.twins.reflector.model.Relationship;
import com.microsoft.twins.reflector.proxy.CachedDigitalTwinProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@Validated
public class TopologyUpdater {

  private final CachedDigitalTwinProxy cachedDigitalTwinProxy;


  // Note: for the time being we assume the ID is stored in the name fields and we only handle the
  // topology element device for now.
  public void updateTopologyElementPartial(@Valid final IngressMessage update, final UUID correlationId) {
    log.trace("Got partial topology update: [{}] with correlation ID: [{}]", update, correlationId);

    // FIXME gateway, get
    final DeviceRetrieve device = cachedDigitalTwinProxy.getDeviceByName(update.getId())
        .orElseGet(() -> cachedDigitalTwinProxy.getDeviceByDeviceId(cachedDigitalTwinProxy.createDevice(update.getId(),
            getParent(update.getRelationships()).orElseThrow(), null)).get());

    // TODO implement
  }

  private static Optional<UUID> getParent(final List<Relationship> relationShips) {
    return relationShips.stream()
        .filter(relationShip -> "space".equals(relationShip.getEntityType()) && "parent".equals(relationShip.getName()))
        .findAny().map(Relationship::getTargetId).map(UUID::fromString);
  }

  public void updateTopologyElementComplete(@Valid final IngressMessage update, final UUID correlationId) {
    log.trace("Got complete topology update: [{}] with correlation ID: [{}]", update, correlationId);


    // TODO implement
  }

  public void deleteTopologyElement(@NotBlank final String id, final UUID correlationId) {
    log.trace("Got delete topology element with ID: [{}] with correlation ID: [{}]", id, correlationId);

    cachedDigitalTwinProxy.deleteDeviceByName(id);
    // TODO implement
  }

}
