package com.krixon.javarest.resource;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ResourceMapper<DomainType, ResourceType>
{
    public abstract ResourceType toResource(DomainType domainObject);
    public abstract DomainType fromResource(ResourceType resource);

    public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainObjects)
    {
        return domainObjects.stream().map(this::toResource).collect(Collectors.toList());
    }

    public Collection<DomainType> fromResourceCollection(Collection<ResourceType> resources)
    {
        return resources.stream().map(this::fromResource).collect(Collectors.toList());
    }
}
