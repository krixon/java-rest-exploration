package com.krixon.javarest.repository;

import com.krixon.javarest.domain.Identifiable;

import java.util.*;

abstract public class InMemoryRepository<T extends Identifiable>
{
    private List<T> elements = Collections.synchronizedList(new ArrayList<>());

    public void add(T element) throws Exception
    {
        if (findById(element.getId()).isPresent()) {
            throw new Exception("Element already exists.");
        }

        elements.add(element);
    }

    public boolean remove(UUID id)
    {
        return elements.removeIf(element -> element.getId().equals(id));
    }

    public List<T> all()
    {
        return elements;
    }

    public Optional<T> findById(UUID id)
    {
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public int count()
    {
        return elements.size();
    }

    public void clear()
    {
        elements.clear();
    }

    public boolean update(T updated)
    {
        if (updated == null) {
            return false;
        } else {
            return findById(updated.getId()).isPresent();
        }
    }

}
